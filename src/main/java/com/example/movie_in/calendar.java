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

public class calendar extends Activity {

    //variables for the calendar
    CalendarView calendar;
    TextView date_view;
    int pickYear, pickDay, pickMonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);

        //Storing the data into the variables
        calendar = (CalendarView) findViewById(R.id.calendar);
        date_view = (TextView) findViewById(R.id.date_view);

        String email = (String) getIntent().getSerializableExtra("email");

        //variables for the buttons
        Button back = findViewById(R.id.button3);
        Button confirm = findViewById(R.id.button7);

        //gets the current year, month, and date
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);


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
                            Intent intent = new Intent(calendar.this, MoviesTimes.class);
                            intent.putExtra("day", pickDay);
                            intent.putExtra("year", pickYear);
                            intent.putExtra("month", pickMonth);
                            intent.putExtra("email", email);
                            startActivity(intent);
                        }else{
                            Toast.makeText(calendar.this, "Please select either current day, or a future day!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(calendar.this, "Please select either current month, or a future month!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(calendar.this, "Please select the current year!", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(calendar.this, "Please select a date!", Toast.LENGTH_SHORT).show();
            }
        });

        //when users clicks on back button
        back.setOnClickListener(v -> {
            Intent intent = new Intent(calendar.this, MainMenu.class);
            intent.putExtra("email", email);
            startActivity(intent);
        });
    }
}