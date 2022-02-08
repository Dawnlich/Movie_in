package com.example.movie_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MoviesTimes extends Activity {


    private DatabaseMovies db;

    TextView date_view, movie;
    int year, day, month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_times_layout);

        date_view = (TextView) findViewById(R.id.date_view);
        movie = (TextView) findViewById(R.id.textView6);

        year = (int) getIntent().getSerializableExtra("year");
        day = (int) getIntent().getSerializableExtra("day");
        month = (int) getIntent().getSerializableExtra("month");

        String datePick = day + "-" + (month + 1) + "-" + year;
        date_view.setText(datePick);

        db = new DatabaseMovies(this);

        String info = "";
        info += "Name: "  + " || Length: ";
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