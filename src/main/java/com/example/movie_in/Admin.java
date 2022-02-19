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

    //variables for the edit text
    EditText movieName, rating, length, dayEnter, monthEnter, yearEnter, story;

    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);

        //variable for buttons
        Button enter = findViewById(R.id.button10);
        Button exit = findViewById(R.id.button11);

        //stores what the users enters
        story = (EditText)findViewById(R.id.addr_edittext);
        movieName = (EditText)findViewById(R.id.editTextTextPersonName);
        rating = (EditText)findViewById(R.id.editTextTextPersonName2);
        length = (EditText)findViewById(R.id.editTextNumberDecimal);
        dayEnter = (EditText)findViewById(R.id.editTextNumber);
        monthEnter = (EditText)findViewById(R.id.editTextNumber2);
        yearEnter = (EditText)findViewById(R.id.editTextNumber3);

        //grabs the current time
        int yearCur = Calendar.getInstance().get(Calendar.YEAR);
        int monthCur = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int dayCur = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        //this creates a variable that will help interact with the database
        db = new Database(this);

        //when the user click on enter
        enter.setOnClickListener(v -> {

            //changes the edittext to string
            String year = yearEnter.getText().toString();
            String month = monthEnter.getText().toString();
            String day = dayEnter.getText().toString();
            String time = length.getText().toString();
            String rate = rating.getText().toString();
            String movie = movieName.getText().toString();
            String about = story.getText().toString();


            //conditions to meet when enter data
            if(!year.isEmpty() && !month.isEmpty() && !day.isEmpty() &&
                !time.isEmpty() && !rate.isEmpty() && !movie.isEmpty()
                && !about.isEmpty()){

                //changes some of the string to int or doubles
                int yearNum =Integer.parseInt(year);
                int monthNum =Integer.parseInt(month);
                int dayNum =Integer.parseInt(day);
                double runTime = Double.parseDouble(time);

                if(yearNum == yearCur) {
                    if(monthNum >= monthCur){
                        if(dayNum >= dayCur || monthNum > monthCur) {
                            if(rate.equals("G") || rate.equals("PG-13") || rate.equals("PG") || rate.equals("R") || rate.equals("M")){
                                Boolean added = db.insertMovies(movie, runTime, dayNum, monthNum, yearNum, rate, about);
                                if(added){
                                    Toast.makeText(Admin.this, "This enter was added!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Admin.this, MainActivity.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(Admin.this, "Error!", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(Admin.this, "Rating must been either M, R, PG, PG-13, or G ", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Admin.this, "Please select either current day, or a future day!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Admin.this, "Please select either current month, or a future month!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Admin.this, "Please select the current year!", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(Admin.this, "Please fill out all fields!", Toast.LENGTH_SHORT).show();
            }
        });

        //when the user clicks on exit
        exit.setOnClickListener(v -> {
            Intent intent = new Intent(Admin.this, MainActivity.class);
            startActivity(intent);
        });
    }
}

