package com.example.covid19.ui.covcitylist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.covid19.R;
import com.example.covid19.models.model.State;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StateListAdapter extends RecyclerView.Adapter<StateListAdapter.citylistViewHolder> {

    List<State> stateList = new ArrayList<>();

    public void setStateList(List<State> stateList){
        this.stateList = stateList;
        this.notifyDataSetChanged();
    }

    class citylistViewHolder extends RecyclerView.ViewHolder {
        private TextView state_name;
        private TextView confirm_number;

        public citylistViewHolder(@NonNull View itemView) {
            super(itemView);
            state_name = itemView.findViewById(R.id.state_name);
            confirm_number = itemView.findViewById(R.id.confirm_number);
        }
    }

    @NonNull
    @Override
    public citylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_state, parent,false);
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_state, parent, false);
        return new citylistViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull citylistViewHolder holder, int position) {
        holder.state_name.setText(stateList.get(position).getProvince_State());
        //if(stateList.get(position).getConfirmed()) {
            holder.confirm_number.setText(stateList.get(position).getConfirmed()+"");
        //}
    }

    @Override
    public int getItemCount() {
        return stateList.size();
    }

}
