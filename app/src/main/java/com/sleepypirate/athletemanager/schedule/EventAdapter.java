package com.sleepypirate.athletemanager.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sleepypirate.athletemanager.R;

import java.util.List;

/**
 * Created by Nic on 2/22/2015.
 */
public class EventAdapter extends ArrayAdapter<Event> {
    Context mContext;
    int mLayoutResourceId;
    List<Event> mData =null;

    public EventAdapter(Context context, int resource, List<Event> data) {
        super(context, resource, data);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mData = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        //inflate the layout for a single row
        LayoutInflater inflater = LayoutInflater.from(mContext);
        row = inflater.inflate(mLayoutResourceId,parent,false);

        //get a reference to the different view elements we wish to update
        TextView nameView = (TextView) row.findViewById(R.id.eventTextView);
        TextView tvType = (TextView) row.findViewById(R.id.tvType);

        //Get the data from the data array
        Event event = mData.get(position);

        //Setting the view to reflect the data we need to display
        nameView.setText(event.getName());
        tvType.setText(event.getType());

        //int resId = mContext.getResources().getIdentifier(event.getName(),"drawable", mContext.getPackageName());
        //imageView.setImageResource(resId);

        return row;
    }
}
