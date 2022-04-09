package com.example.movie_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.Calendar;

public class deleteMovie extends Activity {

        //variables for the calendar
        CalendarView calendar;
        TextView date_view;
        int pickYear, pickDay, pickMonth;

        //database variable
        private Database db;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.calendar_layout);

            //Storing the data into the variables
            calendar = (CalendarView) findViewById(R.id.calendar);
            date_view = (TextView) findViewById(R.id.date_view);

            //variables for the buttons
            Button back = findViewById(R.id.button3);
            Button confirm = findViewById(R.id.button7);

            //gets the current year, month, and date
            int year = Calendar.getInstance().get(Calendar.YEAR);
            int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
            int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

            //making a variable to call the database functions
            db = new Database(this);


            //when a user selects a date
            calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    String datePick = dayOfMonth + "-" + (month + 1) + "-" + year;
                    pickYear = year;
                    pickDay = dayOfMonth;
                    pickMonth = (month + 1);
                    date_view.setText(datePick);
                }
            });


            //when users click on confirm button
            confirm.setOnClickListener(v -> {
                if(pickYear > 0){
                    if(pickYear == year) {
                        if(pickMonth >= month){
                            if(pickDay >= day || pickMonth > month) {
                                String checkMovie = db.getMovie(pickDay, pickMonth, pickYear);
                                if(!checkMovie.equals("No Movie!")){
                                    Intent intent = new Intent(deleteMovie.this, ShowMovieDelete.class);
                                    intent.putExtra("day", pickDay);
                                    intent.putExtra("year", pickYear);
                                    intent.putExtra("month", pickMonth);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(deleteMovie.this, "There is no movie on this Date! Please select another date.", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(deleteMovie.this, "Please select either current day, or a future day!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(deleteMovie.this, "Please select either current month, or a future month!", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(deleteMovie.this, "Please select the current year!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(deleteMovie.this, "Please select a date!", Toast.LENGTH_SHORT).show();
                }
            });

            //when users clicks on back button
            back.setOnClickListener(v -> {
            Intent intent = new Intent(deleteMovie.this, MainActivity.class);
            startActivity(intent);
            });
        }
}