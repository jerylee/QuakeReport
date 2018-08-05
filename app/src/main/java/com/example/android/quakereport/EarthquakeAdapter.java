package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = "of";

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }

        Earthquake currentEarthquakeAdapter = getItem(position);

        //set Text magnitude Value
        TextView tx_Magnitude = listItemView.findViewById(R.id.tx_magnitude);
        String formattedMagnitude = formatMagnitude(currentEarthquakeAdapter.getMagnitude());
        tx_Magnitude.setText(formattedMagnitude);

        //set Text Background Color base the magnitude value
        GradientDrawable magnitudeCircle = (GradientDrawable) tx_Magnitude.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEarthquakeAdapter.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);


        //Split OriginalLocation to become 2 location
        String originalLocation = currentEarthquakeAdapter.getLocation();
        String locationOffset, locationPrimary;
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            locationPrimary = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            locationPrimary = originalLocation;
        }

        TextView tx_locationOffset = listItemView.findViewById(R.id.tx_location_offset);
        tx_locationOffset.setText(locationOffset);

        TextView tx_locationPrimary = listItemView.findViewById(R.id.tx_primary_location);
        tx_locationPrimary.setText(locationPrimary);


        //Modify TimeInMiliseconds to standard time
        Date dateObject = new Date(currentEarthquakeAdapter.getTimeInMiliseconds());

        TextView tx_Date = listItemView.findViewById(R.id.tx_date);
        String formattedDate = formatDate(dateObject);
        tx_Date.setText(formattedDate);


        TextView tx_Time = listItemView.findViewById(R.id.tx_time);
        String formattedTime = formatTime(dateObject);
        tx_Time.setText(formattedTime);


        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }


    // for convert TimeInMillisecond become standard time and years
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        return timeFormat.format(dateObject);
    }

    //format Double to some specific decimal view
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

}


