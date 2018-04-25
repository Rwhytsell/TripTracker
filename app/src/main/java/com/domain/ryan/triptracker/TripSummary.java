package com.domain.ryan.triptracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.domain.ryan.triptracker.TripContract.TripDbHelper;

import java.util.ArrayList;
import java.util.List;

public class TripSummary extends AppCompatActivity {

    private TripDbHelper TripDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TripDb = new TripDbHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_summary);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapView = new Intent(view.getContext(), MapActivity.class);
                startActivity(mapView);
            }
        });

        List<TripData> trips = getTrips();

        // Put the trips into the list view
        if(trips.size() == 0){
            TextView warning = findViewById(R.id.NoItems);
            warning.setText(R.string.no_items_warning);
        }else{
            ArrayAdapter<TripData> tripAdapter = new ArrayAdapter<>(this, R.layout.trip_list_item, trips);
            tripAdapter.addAll(trips);
        }
    }

    /**
     * Method to get all trips from the database
     * @return The list of all trips
     */
    public List<TripData> getTrips(){
        SQLiteDatabase tripDB = TripDb.getReadableDatabase();

        String SortOrder = TripContract.Trip._ID + " ASC";

        Cursor cursor = tripDB.query(
                TripContract.Trip.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                SortOrder
        );

        int ID_INDEX = cursor.getColumnIndex(TripContract.Trip._ID);
        int TITLE_INDEX = cursor.getColumnIndex(TripContract.Trip.COLUMN_NAME_TITLE);
        int DESCRIPTION_INDEX = cursor.getColumnIndex(TripContract.Trip.COLUMN_NAME_DESCRIPTION);

        List<TripData> trips = new ArrayList<>();
        while(cursor.moveToNext()) {
            TripData t =
                    new TripData(cursor.getInt(ID_INDEX),
                            cursor.getString(TITLE_INDEX),
                            cursor.getString(DESCRIPTION_INDEX)
                    );
            trips.add(t);
        }
        cursor.close();
        return trips;
    }
}
