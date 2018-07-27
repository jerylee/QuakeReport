package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }

        Earthquake currentEarthquakeAdapter = getItem(position);

        TextView tx_Magnitude = listItemView.findViewById(R.id.tx_magnitude);
        tx_Magnitude.setText(currentEarthquakeAdapter.getMagnitude());

        TextView tx_Location = listItemView.findViewById(R.id.tx_location);
        tx_Location.setText(currentEarthquakeAdapter.getLocation());

        TextView tx_Date = listItemView.findViewById(R.id.tx_date);
        tx_Date.setText(currentEarthquakeAdapter.getDate());


        return listItemView;
    }
}
