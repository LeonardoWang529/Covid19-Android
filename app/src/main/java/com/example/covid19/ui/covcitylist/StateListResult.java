package com.example.covid19.ui.covcitylist;

import com.example.covid19.models.model.State;

import java.util.List;

import androidx.annotation.Nullable;

public class StateListResult {

    @Nullable
    private List<State> success;

    @Nullable
    private String error;

    StateListResult(String error){this.error = error;}
    StateListResult(List<State> success){this.success = success;}

    List<State> getSuccess(){return success;};
    String getError(){return error;};
}
