package com.example.covid19.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.covid19.R;
import com.example.covid19.databinding.FragmentBasegraphBinding;
import com.example.covid19.ui.covlinegraph.LineCharFragment;
import com.example.covid19.ui.covpigraph.PicharFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class BaseGraphFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_basegraph, container, false);


        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.pgraph, new PicharFragment())
                .commit();
        getChildFragmentManager().beginTransaction().add(R.id.lgraph, new LineCharFragment()).commit();

        return root;
    }
}
