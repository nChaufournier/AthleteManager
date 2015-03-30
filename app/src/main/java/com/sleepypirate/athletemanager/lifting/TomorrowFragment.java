package com.sleepypirate.athletemanager.lifting;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sleepypirate.athletemanager.R;

/**
 * Created by Nic on 1/6/2015.
 */
public class TomorrowFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tomorrow_fragment, container,false);
        TextView textView = (TextView) rootView.findViewById(R.id.fragmentName);
        textView.setText("Tomorrow Fragment");
        return rootView;
    }
}
