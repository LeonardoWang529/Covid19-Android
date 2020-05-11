package com.example.covid19.models;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;

import com.example.covid19.classes.OperationListener;

import java.io.IOException;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import okhttp3.Request;
import okhttp3.Response;

public class GetUSDRSData{

    MutableLiveData<Integer> Death = new MutableLiveData<>();
    MutableLiveData<Integer> Recover = new MutableLiveData<>();
    MutableLiveData<Integer> Total = new MutableLiveData<>();
    Context mContext;

    public GetUSDRSData(Context context){
        mContext = context;
        LocalBroadcastManager.getInstance(context).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-event-name"));

        getAllData();
    }

    public LiveData<Integer> getAllDeath(){
        return Death;
    }

    public LiveData<Integer> getAllRecover(){
        return Recover;
    }

    public LiveData<Integer> getTotal(){
        return Total;
    }

    public void getAllData(){
        ComponentName componentName = new ComponentName(mContext, GetUSDRSDataService.class);

        JobInfo info = new JobInfo.Builder(1000,componentName)
                .setPeriodic(15*60*1000)
                .setPersisted(false)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED).build();
        JobScheduler jobScheduler = (JobScheduler) mContext.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        int resultCode = jobScheduler.schedule(info);

        if(resultCode == JobScheduler.RESULT_SUCCESS){
            Log.d("ServiceActivity", "job start successful");
        }else{
            Log.d("ServiceActivity", "job start fail");
        }
    }


    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            Result message = (Result) intent.getSerializableExtra("message");
            Log.d("receiver", "Got message: " + message);

            Total.setValue(((Result.Success<List<Integer>>) message).getData().get(0));
            Death.setValue(((Result.Success<List<Integer>>) message).getData().get(1));
            Recover.setValue(((Result.Success<List<Integer>>) message).getData().get(2));
        }
    };

}
