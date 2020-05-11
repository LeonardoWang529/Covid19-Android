package com.example.covid19.models;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.covid19.classes.Operation;
import com.example.covid19.classes.OperationListener;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetUSDRSDataService extends JobService {

    public GetUSDRSDataService(){

    }

    private void sendMessage(Result result) {
        Log.d("sender", "Broadcasting message");
        Intent intent = new Intent("custom-event-name");
        // You can also include some extra data.
        intent.putExtra("message", result);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        new getAllData(jobParameters).execute();
        return false;
    }

    class getAllData extends AsyncTask<Void,Void,Void>{
        private static final String TAG = "getAllData";
        JobParameters jobParameters;
        public getAllData(JobParameters jobParameters){
            this.jobParameters = jobParameters;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            OkHttpClient okHttpClient = OKhttpConnect.getInstence().okHttpClient;
            Request request = new Request.Builder()
                    .url("https://services1.arcgis.com/0MSEUqKaxRlEPj5g/arcgis/rest/services/ncov_cases/FeatureServer/2/query?f=json&where=Country_Region%3D%27US%27&returnGeometry=false&spatialRel=esriSpatialRelIntersects&outFields=*&orderByFields=OBJECTID&resultType=standard&cacheHint=true")
                    .build();

            int total_confirmed = 0;
            int total_death = 0;
            int total_recover = 0;
            int total_active = 0;


            try {
                Response response = okHttpClient.newCall(request).execute();
                JSONObject myObject = new JSONObject(response.body().string());
                JSONArray jsonArray = (JSONArray) myObject.get("features");
                myObject = jsonArray.getJSONObject(0);
                JSONObject result = (JSONObject) myObject.get("attributes");
                //Log.d(TAG, "doInBackground: " + result.get("value"));

                total_confirmed = (int)result.get("Confirmed");
                total_death = (int)result.get("Deaths");
                total_recover = (int)result.get("Recovered");
                total_active = (int)result.get("Active");


            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            List<Integer> r = new ArrayList<>();
            r.add(total_confirmed);
            r.add(total_death);
            r.add(total_recover);
            r.add(total_active);

            sendMessage(new Result.Success<List<Integer>>(r));

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            jobFinished(jobParameters,false);
        }

        @Override
        protected void onCancelled() {

        }
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }


}
