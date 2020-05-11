package com.example.covid19.ui.covpigraph;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.covid19.databinding.FragmentPicharBinding;
import com.example.covid19.ui.BaseGraphFragment;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

public class PicharFragment extends Fragment {

    FragmentPicharBinding binding;
    PicharDataViewModel picharDataViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentPicharBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();

        picharDataViewModel = new PicharDataViewModel(getActivity());

        PieChart p = (PieChart) binding.piChart;

        ArrayList<PieEntry> yValue = new ArrayList<>();
        yValue.add(new PieEntry(30,"recover"));
        yValue.add(new PieEntry(30,"Total"));
        yValue.add(new PieEntry(30,"Death"));


        picharDataViewModel.getTotalData().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                yValue.set(1,new PieEntry(integer,"Total"));
                p.notifyDataSetChanged();
                p.invalidate();
            }
        });

        picharDataViewModel.getDeathData().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                yValue.set(2,new PieEntry(integer,"Death"));
                p.notifyDataSetChanged();
                p.invalidate();
            }
        });

        picharDataViewModel.getRecoverData().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                yValue.set(0,new PieEntry(integer,"recover"));
                p.notifyDataSetChanged();
                p.invalidate();
            }
        });


        PieDataSet pieDataSet = new PieDataSet(yValue,"countries");
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setSelectionShift(5f);

        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(20f);
        pieData.setValueTextColor(Color.YELLOW);

        p.setUsePercentValues(true);
        p.getDescription().setEnabled(true);
        p.setDragDecelerationFrictionCoef(0.95f);
        p.setDrawHoleEnabled(true);
        p.setHoleColor(Color.WHITE);
        p.setHoleRadius(40);

        p.setTransparentCircleRadius(61f);

        p.setData(pieData);

        return v;
    }

}
