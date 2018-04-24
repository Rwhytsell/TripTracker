package com.domain.ryan.triptracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class PointContract {
    private PointContract() {}

    public static class Point implements BaseColumns {
        // Column names
        public static final String TABLE_NAME = "point";
        public static final String COLUMN_NAME_TRIP = "trip_id";
        public static final String COLUMN_NAME_LAT = "latitude";
        public static final String COLUMN_NAME_LON = "latitude";

        // Helper SQL queries
        // Create Table
        public static final String SQL_CREATE_POINTS = "CREATE TABLE " + Point.TABLE_NAME + "("
                + Point._ID + " INTEGER PRIMARY KEY," +
                Point.COLUMN_NAME_TRIP + " INTEGER NOT NULL," +
                Point.COLUMN_NAME_LAT + " REAL NOT NULL," +
                Point.COLUMN_NAME_LON + "REAL NOT NULL,"
                +"FOREIGN KEY (" + Point.COLUMN_NAME_TRIP +") REFERENCES " +
                TripContract.Trip.TABLE_NAME + "(" + TripContract.Trip._ID + "))";

        public static final String SQL_DELETE_POINTS =
                "DROP TABLE IF EXISTS " + Point.TABLE_NAME;
    }

    public class PointDbHelper extends SQLiteOpenHelper {

        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "TripTracker.db";

        public PointDbHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(Point.SQL_CREATE_POINTS);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(Point.SQL_DELETE_POINTS);
            onCreate(sqLiteDatabase);
        }
    }
}

