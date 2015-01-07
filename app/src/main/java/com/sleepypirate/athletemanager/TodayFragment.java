package com.sleepypirate.athletemanager;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Nic on 1/6/2015.
 */
public class TodayFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.yesterday_fragment, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.fragmentName);
        textView.setText("Yesterday Fragment");
        return rootView;
    }
}
