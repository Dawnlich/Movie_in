package com.example.movie_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MoviesTimes extends Activity {


    private Database db;

    int year, day, month;
    TextView date_view, movie;
    String movieName, rating;
    Double runTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_times_layout);

        date_view = (TextView) findViewById(R.id.date_view);
        movie = (TextView) findViewById(R.id.movie);

        year = (int) getIntent().getSerializableExtra("year");
        day = (int) getIntent().getSerializableExtra("day");
        month = (int) getIntent().getSerializableExtra("month");

        String datePick = day + "-" + (month + 1) + "-" + year;
        date_view.setText(datePick);

        db = new Database(this);


        String info = "";
        info += "Name: " + movieName  + " || Length: " + runTime +" hours || Rating: "  + rating;
        info += "\n";
        movie.setText(info);

        Button back = findViewById(R.id.button9);
        Button next = findViewById(R.id.button8);

        next.setOnClickListener(v -> {
            Intent intent = new Intent(MoviesTimes.this, MainActivity.class);
            startActivity(intent);
        });

        back.setOnClickListener(v -> {
            Intent intent = new Intent(MoviesTimes.this, calendar.class);
            startActivity(intent);
        });

    }
}