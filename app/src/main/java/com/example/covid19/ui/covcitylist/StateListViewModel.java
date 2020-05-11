package com.example.covid19.ui.covcitylist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.example.covid19.models.Result;
import com.example.covid19.models.model.State;
import com.example.covid19.models.state.StateRepositery;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class StateListViewModel extends ViewModel {

    MutableLiveData<StateListResult> citylist = new MutableLiveData<>();
    StateRepositery stateRepositery;

    public StateListViewModel(Context context){
        LocalBroadcastManager.getInstance(context).registerReceiver(mMessageReceivier,
                new IntentFilter("state-data"));

        stateRepositery = new StateRepositery(context);
        stateRepositery.getAllData();
    }

    private BroadcastReceiver mMessageReceivier = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Result message = (Result) intent.getSerializableExtra("message");

            citylist.setValue(new StateListResult(((Result.Success<List<State>>) message).getData()));
        }
    };

    public LiveData<StateListResult> getlist(){
        return citylist;
    }
}
