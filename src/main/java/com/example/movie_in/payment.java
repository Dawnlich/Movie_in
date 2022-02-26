package com.example.movie_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class payment extends Activity {

    int year, day, month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        year = (int) getIntent().getSerializableExtra("year");
        day = (int) getIntent().getSerializableExtra("day");
        month = (int) getIntent().getSerializableExtra("month");



        Button back = (Button) findViewById(R.id.BackP);
        Button next = (Button) findViewById(R.id.nextP);

        next.setOnClickListener(v -> {
            Intent intent = new Intent(payment.this, MainMenu.class);
            startActivity(intent);
        });

        back.setOnClickListener(v -> {
            Intent intent = new Intent(payment.this, Parking.class);
            intent.putExtra("day", day);
            intent.putExtra("year", year);
            intent.putExtra("month", month);
            startActivity(intent);
        });
    }
}
