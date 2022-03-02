package com.example.movie_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class payment extends Activity {

    int year, day, month;
    TextView customer, cost;
    String email, date, movie, movieDate, spot;
    int people;
    double amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        year = (int) getIntent().getSerializableExtra("year");
        day = (int) getIntent().getSerializableExtra("day");
        month = (int) getIntent().getSerializableExtra("month");

        customer = (TextView) findViewById(R.id.customer);
        cost = (TextView) findViewById(R.id.cost);

        Button back = (Button) findViewById(R.id.BackP);
        Button next = (Button) findViewById(R.id.nextP);

        String info = "";
        info += "Email: " + email  + " || Date: "  + date;
        customer.setText(info);

        String info2 = "";
        info2 += "Movie Name: " + movie  + " || Date Picked: "  + movieDate;
        info2 += "\n";
        info2 += "Parking spot: " + spot;
        info2 += "\n";
        info2 += "Number of People(" + people +")";
        info2 += "\n";
        info2 += "----------------------------------------------------------------";
        info2 += "\n";
        info2 += "Total Cost: $" + amount;
        info2 += "\n";
        cost.setText(info2);

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
