package com.example.covid19.models.state;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.covid19.models.OKhttpConnect;
import com.example.covid19.models.Result;
import com.example.covid19.models.model.State;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import okhttp3.Request;
import okhttp3.Response;

public class StateDataService extends JobService {
    private static final String TAG = "StateDataService";

    public StateDataService(){

    }

    private void sendMessage(Result result){
        Intent intent = new Intent("state-data");
        intent.putExtra("message",result);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        //new getStateData().execute();
        new getStateData(jobParameters).execute();


        return true;
    }

    class getStateData extends AsyncTask<Void,Void,Void>{
        private static final String TAG = "getAllData";
        JobParameters jobParameters;
        public getStateData(JobParameters jobParameters){
            this.jobParameters = jobParameters;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Request request = new Request.Builder()
                    .url("https://services1.arcgis.com/0MSEUqKaxRlEPj5g/arcgis/rest/services/ncov_cases/FeatureServer/1/query?f=json&where=Country_Region%3D%27US%27&returnGeometry=false&spatialRel=esriSpatialRelIntersects&outFields=*&orderByFields=OBJECTID&resultType=standard&cacheHint=true")
                    .build();

            List<State> stateList = new ArrayList<>();

            try {
                Response response = OKhttpConnect.getInstence().okHttpClient.newCall(request).execute();
                JSONObject myObject = new JSONObject(response.body().string());
                JSONArray jsonArray = (JSONArray) myObject.get("features");


                for(int i = 0; i< jsonArray.length(); i++){
                    myObject = jsonArray.getJSONObject(i);
                    JSONObject result = (JSONObject) myObject.get("attributes");
                    Gson gson=new Gson();
                    State s = gson.fromJson(result.toString(), State.class);
                    stateList.add(s);
                }
            }catch (Exception e){

            }

            Result<List<State>> result = new Result.Success<List<State>>(stateList);

            sendMessage(result);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            jobFinished(jobParameters,false);
        }
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
