package com.example.movie_in;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class paymentHistory extends Activity {

    //variables
    EditText edit1, edit2, edit3;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymenthistory);

    }
}