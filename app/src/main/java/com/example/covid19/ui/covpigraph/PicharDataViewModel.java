package com.example.covid19.ui.covpigraph;

import android.content.Context;

import com.example.covid19.models.GetUSDRSData;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class PicharDataViewModel {
    LiveData<Integer> deathMutableLiveData;
    LiveData<Integer> recoverMutableLiveData;
    LiveData<Integer> totalMutableLiveData;
    GetUSDRSData getUSDRSData;

    public PicharDataViewModel(Context context){
        getUSDRSData = new GetUSDRSData(context);
        deathMutableLiveData = getUSDRSData.getAllDeath();
        recoverMutableLiveData = getUSDRSData.getAllRecover();
        totalMutableLiveData = getUSDRSData.getTotal();
    }

    public LiveData<Integer> getDeathData(){
        return deathMutableLiveData;
    }

    public LiveData<Integer> getRecoverData(){
        return recoverMutableLiveData;
    }

    public LiveData<Integer> getTotalData(){
        return totalMutableLiveData;
    }
}
