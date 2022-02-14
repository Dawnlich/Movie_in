package com.example.movie_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class Admin extends Activity {

    EditText movieName, rating, length, dayEnter, monthEnter, yearEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);

        Button enter = findViewById(R.id.button10);
        Button exit = findViewById(R.id.button11);

        movieName = (EditText)findViewById(R.id.editTextTextPersonName);
        rating = (EditText)findViewById(R.id.editTextTextPersonName2);
        length = (EditText)findViewById(R.id.editTextNumberDecimal);
        dayEnter = (EditText)findViewById(R.id.editTextNumber);
        monthEnter = (EditText)findViewById(R.id.editTextNumber2);
        yearEnter = (EditText)findViewById(R.id.editTextNumber3);




        int yearCur = Calendar.getInstance().get(Calendar.YEAR);

        enter.setOnClickListener(v -> {

        });

        exit.setOnClickListener(v -> {
            Intent intent = new Intent(Admin.this, MainActivity.class);
            startActivity(intent);
        });
    }
}

