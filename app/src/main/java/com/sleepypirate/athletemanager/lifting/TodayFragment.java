package com.sleepypirate.athletemanager.lifting;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sleepypirate.athletemanager.Databases.ExerciseDataSource;
import com.sleepypirate.athletemanager.Databases.WorkoutsDatabase;
import com.sleepypirate.athletemanager.R;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Nic on 1/6/2015.
 */
public class TodayFragment extends Fragment {
    private ImageButton fabExercise;
    private ImageButton fabAdd;
    private ImageButton fabWorkout;
    private ExerciseDataSource db;
    //Animations
    Animation animSlideDown;
    Animation animSlideUp;
    Animation animMoveUp;
    Animation animTest;
    WorkoutsDatabase woDb;
    Date date;
    DateFormat dateFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.today_fragment, container, false);
        db = new ExerciseDataSource(getActivity());
        try{
            db.open();
        }catch (SQLException e){
            e.printStackTrace();
        }
        dateFormat = new SimpleDateFormat("MM/dd/yyy", Locale.US);
        date = new Date();



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
                            showMessage();
                            Toast.makeText(getActivity(), dateFormat.format(date), Toast.LENGTH_SHORT).show();
                            /*Intent i = new Intent(getActivity(), AddExercise.class);
                            startActivity(i);*/
                        }
                    });

                    fabWorkout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            woDb.showAll(date.toString());
                            /*Intent in = new Intent(getActivity(), AddWorkout.class);
                            startActivity(in);*/
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

    public void showMessage()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("Exercises");
        builder.setItems(R.array.exercise_catagory, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int position) {
                //if (position == 1) {
                    showExtendedMessage(position);
                    //Toast.makeText(getActivity(), dialog.toString(), Toast.LENGTH_SHORT).show();

               // }
            }
        });
        builder.show();

    }

    public void showExtendedMessage(int cat)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle("Exercises");
        if (cat == 0) {
            builder.setItems(R.array.upper_body, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int position) {
                    String[] upperBody = getResources().getStringArray(R.array.upper_body);
                    Toast.makeText(getActivity(), upperBody[position], Toast.LENGTH_SHORT).show();
                    woDb.addExercide(db.getExercise(upperBody[position]), date.toString());
                    /*if (position == 1) {
                        Toast.makeText(getActivity(), dialog.toString(), Toast.LENGTH_SHORT).show();

                    }*/
                }
            });
        }else if (cat == 1){
            builder.setItems(R.array.lower_body, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int position) {
                    String[] lowerBody = getResources().getStringArray(R.array.lower_body);
                    Toast.makeText(getActivity(), lowerBody[position], Toast.LENGTH_SHORT).show();
                    Exercise newExercise = new Exercise();

                    if (position == 1) {
                    }
                }
            });

        }
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getActivity(), AddExercise.class);
                startActivity(i);
            }
        });
        builder.show();

    }

}
