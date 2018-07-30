/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

//        // Create a fake list of earthquake locations.
//        ArrayList<String> earthquakes = new ArrayList<>();
//        earthquakes.add("San Francisco");
//        earthquakes.add("London");
//        earthquakes.add("Tokyo");
//        earthquakes.add("Mexico City");
//        earthquakes.add("Moscow");
//        earthquakes.add("Rio de Janeiro");
//        earthquakes.add("Paris");

        // Create a fake list of earthquake magnitude,locations,date.
        ArrayList<Earthquake> earthquakes = new ArrayList<>();
        earthquakes.add(new Earthquake("7.2", "San Francisco", "Feb 2, 2016"));
        earthquakes.add(new Earthquake("6.1", "London", "July 20, 2015"));
        earthquakes.add(new Earthquake("3.9", "Tokyo", "Nov 10, 2014"));
        earthquakes.add(new Earthquake("1.2", "Mexico City", "Feb 2, 2016"));
        earthquakes.add(new Earthquake("0.2", "Rio de Janeiro", "Feb 2, 2016"));
        earthquakes.add(new Earthquake("3.1", "Paris", "July 2, 2014"));
        earthquakes.add(new Earthquake("7.2", "San Francisco", "Feb 2, 2016"));
        earthquakes.add(new Earthquake("6.1", "London", "July 20, 2015"));
        earthquakes.add(new Earthquake("3.9", "Tokyo", "Nov 10, 2014"));
        earthquakes.add(new Earthquake("1.2", "Mexico City", "Feb 2, 2016"));
        earthquakes.add(new Earthquake("0.2", "Rio de Janeiro", "Feb 2, 2016"));
        earthquakes.add(new Earthquake("3.1", "Paris", "July 2, 2014"));



        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = findViewById(R.id.list);


//        // Create a new {@link ArrayAdapter} of earthquakes
//        ArrayAdapter<Earthquake> adapter = new ArrayAdapter<>(
//                this, android.R.layout.simple_list_item_1, earthquakes);

        // Create a new {@link EarthquakeAdapter} of earthquakes
        EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);

        Log.v(LOG_TAG, "Log Data :" + earthquakes.toString());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
    }
}
