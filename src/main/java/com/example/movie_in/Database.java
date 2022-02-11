package com.example.movie_in;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Database";

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
            + COL_4 + " text ,"
            + COL_5 + " text ,"
            + COL_6 + " text ,"
            + COL_7 + " text ,"
            + COL_8 + " text )";

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


}

