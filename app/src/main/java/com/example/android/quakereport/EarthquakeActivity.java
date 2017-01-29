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

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    /** URL for earthquake data from the USGS dataset */
    private static final String USGS_REQUEST_URL =
            "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    /** Adapter for the list of earthquakes
     *
     * When we get to the onPostExecute() method, we need to update the ListView.
     * The only way to update the contents of the list is to update the data set within the EarthquakeAdapter.
     * To access and modify the instance of the EarthquakeAdapter, we need to make it a global
     * variable in the EarthquakeActivity.
     *
     * */
    private earthquakeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);



        // Create a fake list of earthquakes.
        //ArrayList<earthquake> earthquakes = QueryUtils.extractEarthquakes();


        /**
        // Create a fake list of earthquake locations.
        final ArrayList<earthquake> earthquakes = new ArrayList<earthquake>();
        earthquakes.add(new earthquake("San Francisco", "Sabado 7 de junio 2017", 7.2));
        earthquakes.add(new earthquake("London","Sabado 7 de junio 2017", 7.2));
        earthquakes.add(new earthquake("Tokyo","Sabado 7 de junio 2017", 7.2));
        earthquakes.add(new earthquake("Mexico City","Sabado 7 de junio 2017", 7.2));
        earthquakes.add(new earthquake("Moscow","Sabado 7 de junio 2017", 7.2));
        earthquakes.add(new earthquake("Rio de Janeiro","Sabado 7 de junio 2017", 7.2));


         */
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new earthquakeAdapter(this, new ArrayList<earthquake>(), android.support.v7.appcompat.R.color.abc_background_cache_hint_selector_material_dark);

        // Create a new {@link ArrayAdapter} of earthquakes
        //final earthquakeAdapter adapter = new earthquakeAdapter(
         //       this, earthquakes, android.support.v7.appcompat.R.color.abc_background_cache_hint_selector_material_dark);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //used to declare and get the eathquake selected
                earthquake selected = mAdapter.getItem(position);

                //String earthquakeUrl = selected.getmUrl();
                //Log.v("there is a problem:", selected.getmUrl());

                //String url = earthquakeUrl;

                //Intent i = new Intent(Intent.ACTION_VIEW);
                //i.setData(Uri.parse(url));
                //startActivity(i);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(selected.getmUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        callForEventAsyncTask task = new callForEventAsyncTask();
        task.execute(USGS_REQUEST_URL);
    }

    private class callForEventAsyncTask extends AsyncTask<String, Void, List<earthquake>> {

        @Override
        protected List<earthquake> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            // Create a fake list of earthquakes.
            return QueryUtils.fetchEarthquakeData(urls[0]);
        }

        /**
         * Update the screen with the given earthquake (which was the result of the
         * {@link callForEventAsyncTask}).
         *
         * */
        @Override
        protected void onPostExecute(List<earthquake> data) {
            // Clear the adapter of previous earthquake data
            mAdapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }


    }

}
