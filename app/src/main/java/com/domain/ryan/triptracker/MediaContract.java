package com.domain.ryan.triptracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class MediaContract {
    private MediaContract() {}

    public static class Media implements BaseColumns {
        // Column names
        public static final String TABLE_NAME = "media";
        public static final String COLUMN_NAME_MARKER = "marker_id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_URI = "URI";

        // Helper SQL queries
        // Create Table
        public static final String SQL_CREATE_MEDIA = "CREATE TABLE " + Media.TABLE_NAME + "("
                + Media._ID + " INTEGER PRIMARY KEY," +
                Media.COLUMN_NAME_MARKER + " INTEGER NOT NULL," +
                Media.COLUMN_NAME_TITLE + " TEXT NOT NULL," +
                Media.COLUMN_NAME_URI + "TEXT NOT NULL,"
                +"FOREIGN KEY (" + Media.COLUMN_NAME_MARKER +") REFERENCES " +
                MarkerContract.Marker.TABLE_NAME + "(" + MarkerContract.Marker._ID + "))";

        public static final String SQL_DELETE_MEDIA =
                "DROP TABLE IF EXISTS " + Media.TABLE_NAME;
    }

    public static class MediaDbHelper extends SQLiteOpenHelper {

        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "TripTracker.db";

        public MediaDbHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(Media.SQL_CREATE_MEDIA);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(Media.SQL_DELETE_MEDIA);
            onCreate(sqLiteDatabase);
        }
    }
}

