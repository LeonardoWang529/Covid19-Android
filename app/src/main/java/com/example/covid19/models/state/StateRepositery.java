package com.example.covid19.models.state;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.example.covid19.classes.Operation;
import com.example.covid19.classes.OperationListener;
import com.example.covid19.models.Result;
import com.example.covid19.models.model.State;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class StateRepositery {


    List<State> stateList = new ArrayList<>();

    Context context;

    public StateRepositery(Context context){


        this.context = context;
        getAllData();
    }

    public List<State> getStateData(){
        return stateList;
    }

    public void getAllData(){
        ComponentName componentName = new ComponentName(context,StateDataService.class);
        JobInfo jobInfo = new JobInfo.Builder(1001,componentName)
                .setPeriodic(15*60*1000)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED).build();

        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        int resultCode = jobScheduler.schedule(jobInfo);

        if(resultCode == JobScheduler.RESULT_SUCCESS){

        }else{

        }
    }


}
