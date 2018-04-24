package com.domain.ryan.triptracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class MarkerContract {
    private MarkerContract() {}

    public static class Marker implements BaseColumns {
        // Column names
        public static final String TABLE_NAME = "marker";
        public static final String COLUMN_NAME_TRIP = "trip_id";
        public static final String COLUMN_NAME_LAT = "latitude";
        public static final String COLUMN_NAME_LON = "latitude";

        // Helper SQL queries
        // Create Table
        public static final String SQL_CREATE_MARKERS = "CREATE TABLE " + Marker.TABLE_NAME + "("
                + Marker._ID + " INTEGER PRIMARY KEY," +
                Marker.COLUMN_NAME_TRIP + " INTEGER NOT NULL," +
                Marker.COLUMN_NAME_LAT + " REAL NOT NULL," +
                Marker.COLUMN_NAME_LON + "REAL NOT NULL,"
                +"FOREIGN KEY (" + Marker.COLUMN_NAME_TRIP +") REFERENCES " +
                TripContract.Trip.TABLE_NAME + "(" + TripContract.Trip._ID + "))";
        // Delete Table
        public static final String SQL_DELETE_MARKERS =
                "DROP TABLE IF EXISTS " + Marker.TABLE_NAME;
    }

    public class MarkerDbHelper extends SQLiteOpenHelper {

        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "TripTracker.db";

        public MarkerDbHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(Marker.SQL_CREATE_MARKERS);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(Marker.SQL_DELETE_MARKERS);
            onCreate(sqLiteDatabase);
        }
    }
}

