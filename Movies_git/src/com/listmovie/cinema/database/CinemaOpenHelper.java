package com.listmovie.cinema.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CinemaOpenHelper extends SQLiteOpenHelper {

	private static final String TAG = CinemaOpenHelper.class.getName();
	
    private SQLiteDatabase mDatabase;

    private static final String TABLE_CREATE =
    		"CREATE TABLE " + CinemaDatabase.TABLE_NAME + " ("
    				+ Cinema.COLUMN_NAME_ID_S + " TEXT PRIMARY KEY,"
                    + Cinema.COLUMN_NAME_LATITUDE + " TEXT,"
                    + Cinema.COLUMN_NAME_LONGTITUDE + " TEXT,"
                    + Cinema.COLUMN_NAME_NAME + " TEXT,"
                    + Cinema.COLUMN_NAME_VICINITY + " TEXT,"
                    + Cinema.COLUMN_NAME_CREATE_DATE + " DATE,"
                    + Cinema.COLUMN_NAME_OPENING_HOURS + " TEXT,"
                    + Cinema.COLUMN_NAME_WEBSITE + " TEXT,"
                    + Cinema.COLUMN_NAME_ICON + " TEXT,"
                    + Cinema.COLUMN_NAME_SEATS + " TEXT,"
                    + Cinema.COLUMN_NAME_SEATSSIZE + " TEXT,"
                    + Cinema.COLUMN_NAME_FORMATTED_PHONE_NUMBER + " TEXT"
                    + ");";

    public CinemaOpenHelper(Context context) {
        super(context, CinemaDatabase.DATABASE_NAME, null, CinemaDatabase.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        mDatabase = db;
        mDatabase.execSQL("DROP TABLE IF EXISTS " + CinemaDatabase.TABLE_NAME);//TODO: remove it
        mDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + CinemaDatabase.TABLE_NAME);
        onCreate(db);
    }

}
