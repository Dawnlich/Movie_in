package com.example.movie_in;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Movie-InDatabase";

    //variables for table one
    public static final String TABLE_NAME = "users_table";
    public static final String ID = "ID";
    public static final String COL_1 = "EMAIL";
    public static final String COL_2 = "PASSWORD";

    //variables for table two
    public static final String TABLE_NAME2 = "movies_table";
    public static final String COL_3 = "MOVIES";
    public static final String COL_4 = "LENGTH";
    public static final String COL_5 = "DAY";
    public static final String COL_6 = "MONTH";
    public static final String COL_7 = "YEAR";
    public static final String COL_8 = "RATING";
    public static final String COL_9 = "Synopsis";
    public static final String COL_10 = "Parking";

    //constructs that database
    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //first table
    public static final String query = "CREATE TABLE " + TABLE_NAME + " ("
            + ID + " integer primary key autoincrement, "
            + COL_1 + " text,"
            + COL_2 + " text)";

    //second table for movies
    public static final String query2 = "CREATE TABLE " + TABLE_NAME2 + " ("
            + ID + " integer primary key autoincrement, "
            + COL_3 + " text,"
            + COL_4 + " double ,"
            + COL_5 + " integer ,"
            + COL_6 + " integer ,"
            + COL_7 + " integer ,"
            + COL_8 + " text, "
            + COL_9 + " text, "
            + COL_10 + " text )";

    //creates the table in the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query);
        db.execSQL(query2);
    }

    //checks to see if there is already a table in the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

    //this function will insert the data into the database
    public boolean insertData(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, email);
        contentValues.put(COL_2, password);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertMovies(String name, double length, int Day, int Month, int Year, String rating, String story, String parking) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_3, name);
        contentValues.put(COL_4, length);
        contentValues.put(COL_5, Day);
        contentValues.put(COL_6, Month);
        contentValues.put(COL_7, Year);
        contentValues.put(COL_8, rating);
        contentValues.put(COL_9, story);
        contentValues.put(COL_10, parking);
        long result = db.insert(TABLE_NAME2, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    //this function checks to see if the emails is already in use
    public Boolean checkEmail(String email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * FROM users_table WHERE EMAIL = ?", new String[]{email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    //this function will check the email and password to make sure it is in the database
    public Boolean CheckLogin(String email, String password){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users_table WHERE EMAIL =? AND PASSWORD=?", new String[]{email, password});
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    //to print all the info about the movies
    public String getMovie(int day, int month, int year) {
        String rv = "No Movie!";
        String queryMV = "SELECT " + COL_3 + " FROM " + TABLE_NAME2 +
                " WHERE " + COL_5 + " = '" + day + "' AND "+ COL_6 + " = '" + month + "' AND "+ COL_7 + " = '" + year + "'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor csr = sqLiteDatabase.rawQuery(queryMV, null);
        if (csr.moveToFirst()) {
            rv = csr.getString(csr.getColumnIndexOrThrow(COL_3));
        }
        return rv;
    }

    //gets the movie rating
    public String getRating(int day, int month, int year) {
        String rv = "No Rating!";
        String queryMV = "SELECT " + COL_8 + " FROM " + TABLE_NAME2 +
                " WHERE " + COL_5 + " = '" + day + "' AND "+ COL_6 + " = '" + month + "' AND "+ COL_7 + " = '" + year + "'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor csr = sqLiteDatabase.rawQuery(queryMV, null);
        if (csr.moveToFirst()) {
            rv = csr.getString(csr.getColumnIndexOrThrow(COL_8));
        }
        return rv;
    }

    //gets the lenght of the moive
    public Double getLength(int day, int month, int year) {
        Double rv = 0.0;
        String queryMV = "SELECT " + COL_4 + " FROM " + TABLE_NAME2 +
                " WHERE " + COL_5 + " = '" + day + "' AND "+ COL_6 + " = '" + month + "' AND "+ COL_7 + " = '" + year + "'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor csr = sqLiteDatabase.rawQuery(queryMV, null);
        if (csr.moveToFirst()) {
            rv = csr.getDouble(csr.getColumnIndexOrThrow(COL_4));
        }
        return rv;
    }

    //gets the story about the moive
    public String getStory(int day, int month, int year) {
        String rv ="Unkown";
        String queryMV = "SELECT " + COL_9 + " FROM " + TABLE_NAME2 +
                " WHERE " + COL_5 + " = '" + day + "' AND "+ COL_6 + " = '" + month + "' AND "+ COL_7 + " = '" + year + "'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor csr = sqLiteDatabase.rawQuery(queryMV, null);
        if (csr.moveToFirst()) {
            rv = csr.getString(csr.getColumnIndexOrThrow(COL_9));
        }
        return rv;
    }

    //get the parking for the movie
    public String getParking(int day, int month, int year) {
        String rv ="Unkown";
        String queryMV = "SELECT " + COL_10 + " FROM " + TABLE_NAME2 +
                " WHERE " + COL_5 + " = '" + day + "' AND "+ COL_6 + " = '" + month + "' AND "+ COL_7 + " = '" + year + "'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor csr = sqLiteDatabase.rawQuery(queryMV, null);
        if (csr.moveToFirst()) {
            rv = csr.getString(csr.getColumnIndexOrThrow(COL_10));
        }
        return rv;
    }

}

