package com.example.movie_in;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseMovies extends SQLiteOpenHelper {


    //variables
    public static final String DATABASE_NAME = "MoviesTimes";
    public static final String TABLE_NAME = "movies";
    public static final String ID = "ID";
    public static final String COL_1 = "MOVIES";
    public static final String COL_2 = "LENGTH";
    public static final String COL_3 = "DAY";
    public static final String COL_4 = "MONTH";
    public static final String COL_5 = "YEAR";
    public static final String COL_6 = "RATING";

    //constructs that database
    public DatabaseMovies(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //creates the table in the datAbase
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " integer primary key autoincrement, "
                + COL_1 + " text,"
                + COL_2 + " float,"
                + COL_3 + " integer,"
                + COL_4 + " integer,"
                + COL_5 + " integer,"
                + COL_6 + " text )";

        db.execSQL(query);
    }

    //checks to see if there is already a table in the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //this function will insert the data into the database
    public boolean insertData(String name, float length, int Day, int Month, int Year, String rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, length);
        contentValues.put(COL_3, Day);
        contentValues.put(COL_4, Month);
        contentValues.put(COL_5, Year);
        contentValues.put(COL_6, rating);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public String getMovieName(int year, int month, int day){
        return "";
    }

}
