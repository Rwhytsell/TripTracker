package com.domain.ryan.triptracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TripsAdapter extends ArrayAdapter<TripData> {
    public TripsAdapter(Context context, ArrayList<TripData> trips){
        super(context,0, trips);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TripData trip = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.trip_list_item, parent, false);
        }

        TextView Title = (TextView) convertView.findViewById(R.id.TripTitle);
        TextView Description = (TextView) convertView.findViewById(R.id.TripDescription);

        Title.setText(trip.Title);
        Title.setText(trip.Description);

        return convertView;
    }
}
