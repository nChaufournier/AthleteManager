package com.sleepypirate.athletemanager.lifting;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sleepypirate.athletemanager.R;

/**
 * Created by Nic on 1/6/2015.
 */
public class TodayFragment extends Fragment {
    private ImageButton fabExercise;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.today_fragment, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.fragmentName);
        textView.setText("Today Fragment");



        fabExercise = (ImageButton) rootView.findViewById(R.id.fab_exercise);
        fabExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddExercise.class);
                startActivity(i);
            }
        });


        //Needs to be last
        return rootView;
    }
}
