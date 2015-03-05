package com.sleepypirate.athletemanager.lifting;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sleepypirate.athletemanager.R;

/**
 * Created by Nic on 1/6/2015.
 */
public class TodayFragment extends Fragment {
    private ImageButton fabExercise;
    private ImageButton fabAdd;
    private ImageButton fabWorkout;

    //Animations
    Animation animSlideDown;
    Animation animSlideUp;
    Animation animMoveUp;
    Animation animTest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.today_fragment, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.fragmentName);
        textView.setText("Today Fragment");

        //Animations
        animSlideUp = AnimationUtils.loadAnimation(getActivity(), R.anim.move);
        animMoveUp = AnimationUtils.loadAnimation(getActivity(), R.anim.abc_slide_out_top);
        animSlideDown = AnimationUtils.loadAnimation(getActivity(), R.anim.abc_slide_in_top);
        animTest = AnimationUtils.loadAnimation(getActivity(), R.anim.movedown);
        //End Animations


        fabAdd = (ImageButton) rootView.findViewById(R.id.fab_exAdd);
        fabWorkout = (ImageButton) rootView.findViewById(R.id.fab_exWorkout);
        fabExercise = (ImageButton) rootView.findViewById(R.id.fab_exExercise);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            boolean fabClicked = false;
            @Override
            public void onClick(View v) {
                if(fabClicked != true){
                    //fabExercise.startAnimation(animTest);
                    fabExercise.setVisibility(View.VISIBLE);
                    fabWorkout.setVisibility(View.VISIBLE);
                    fabClicked = true;
                    //Button to Create an Exercise
                    fabExercise.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(getActivity(), AddExercise.class);
                            startActivity(i);
                        }
                    });

                    fabWorkout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(getActivity(), AddWorkout.class);
                            startActivity(in);
                            Toast.makeText(getActivity(), "Add Workout!", Toast.LENGTH_SHORT).show();
                        }
                    });

                }else{
                    //fabExercise.startAnimation(animSlideDown);
                    fabExercise.setVisibility(View.GONE);
                    fabWorkout.setVisibility(View.GONE);
                    fabClicked = false;
                }

                /**/
            }
        });


        //Needs to be last
        return rootView;
    }
}
