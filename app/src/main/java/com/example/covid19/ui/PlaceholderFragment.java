package com.example.covid19.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.covid19.R;
import com.example.covid19.ui.covcitylist.StateListFragment;
import com.example.covid19.ui.covlinegraph.LineCharFragment;
import com.example.covid19.ui.covpigraph.PicharFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        //TextView t = root.findViewById(R.id.t);

        pageViewModel.getText().observe(this.getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                //t.setText(integer+"");
                if(integer == 1){

                    getChildFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, new BaseGraphFragment())
                            .commit();

                }else if(integer == 2){
                    getChildFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, new StateListFragment())
                            .commit();
                }else{

                }
            }
        });
        return root;
    }
}