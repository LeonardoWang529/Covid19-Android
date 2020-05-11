package com.example.covid19.ui.covcitylist;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.covid19.databinding.FragmentCitylistBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StateListFragment extends Fragment {

    FragmentCitylistBinding binding;
    RecyclerView recyclerView;
    StateListViewModel cityListViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentCitylistBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();

        final StateListAdapter adapter = new StateListAdapter();

        recyclerView = binding.citylistRecycleView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(adapter);

        cityListViewModel = new StateListViewModel(getContext());

        cityListViewModel.getlist().observe(getActivity(), new Observer<StateListResult>() {
            @Override
            public void onChanged(StateListResult stateListResult) {
                if(stateListResult.getSuccess() != null){
                    adapter.setStateList(stateListResult.getSuccess());
                }

                getActivity().setResult(Activity.RESULT_OK);
            }
        });


        return v;
    }
}
