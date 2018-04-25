package com.domain.ryan.triptracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public final class TripContract {
    private TripContract() {}

    public static class Trip implements BaseColumns {
        // Column names
        public static final String TABLE_NAME = "trip";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";

        // Helper SQL queries
        // Create Table
        public static final String SQL_CREATE_TRIPS = "CREATE TABLE " + Trip.TABLE_NAME +
                "(" + Trip._ID + " INTEGER PRIMARY KEY," +
                Trip.COLUMN_NAME_TITLE + " TEXT," +
                Trip.COLUMN_NAME_DESCRIPTION + " TEXT)";

        public static final String SQL_DELETE_TRIPS =
                "DROP TABLE IF EXISTS " + Trip.TABLE_NAME;
    }

    public static class TripDbHelper extends SQLiteOpenHelper {

        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "TripTracker.db";

        public TripDbHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(Trip.SQL_CREATE_TRIPS);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(Trip.SQL_DELETE_TRIPS);
            onCreate(sqLiteDatabase);
        }
    }
}

