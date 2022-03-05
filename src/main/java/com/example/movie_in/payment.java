package com.example.movie_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class payment extends Activity {

    int year, day, month;
    TextView customer, cost;
    String email, date, movie, movieDate, spot, people;
    double amount;

    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        //gets data from the parking spot page
        year = (int) getIntent().getSerializableExtra("year");
        day = (int) getIntent().getSerializableExtra("day");
        month = (int) getIntent().getSerializableExtra("month");
        people = (String) getIntent().getSerializableExtra("amount");
        email = (String) getIntent().getSerializableExtra("email");

        //this will show what the data that the user select to the themselves
        customer = (TextView) findViewById(R.id.customer);
        cost = (TextView) findViewById(R.id.cost);

        //the buttons
        Button back = (Button) findViewById(R.id.BackP);
        Button next = (Button) findViewById(R.id.nextP);

        //gets the current date
        int yearCur = Calendar.getInstance().get(Calendar.YEAR);
        int monthCur = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int dayCur = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        date = dayCur + "-" + monthCur + "-" + yearCur;

        //database
        db = new Database(this);

        //info about the movie
        movieDate = day + "-" + (month + 1) + "-" + year;
        movie = db.getMovie(day, month, year);

        //showing the customer info to them
        String info = "";
        info += "Email: " + email  + " || Date: "  + date;
        customer.setText(info);

        int i =Integer.parseInt(people);
        amount = i * 5.50;

        //showing all the info about the item that they wish to purchase
        String info2 = "";
        info2 += "Movie Name: " + movie  + " || Date Picked: "  + movieDate;
        info2 += "\n";
        info2 += "Parking spot: " + spot;
        info2 += "\n";
        info2 += "Number of People(" + people +")" + "x $5.50";
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
            intent.putExtra("email", email);
            startActivity(intent);
        });
    }
}
