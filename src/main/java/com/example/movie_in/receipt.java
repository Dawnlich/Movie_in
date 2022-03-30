package com.example.movie_in;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Random;

public class receipt extends Activity {

    //variables
    int year, day, month;
    double amount;
    private Database db;
    String email, people, spot, date, movieDate, movie, ticketNum;
    TextView customerInfo, receiptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receipt);

        //database
        db = new Database(this);

        //get the data the is coming from the payment page
        year = (int) getIntent().getSerializableExtra("year");
        day = (int) getIntent().getSerializableExtra("day");
        month = (int) getIntent().getSerializableExtra("month");
        amount = (double) getIntent().getSerializableExtra("amount");
        people = (String) getIntent().getSerializableExtra("people");
        email = (String) getIntent().getSerializableExtra("email");
        spot = (String) getIntent().getSerializableExtra("spot");
        ticketNum = (String) getIntent().getSerializableExtra("ticket");

        //this will show what the data that the user select to the themselves
        customerInfo = (TextView) findViewById(R.id.customerInfo);
        receiptInfo = (TextView) findViewById(R.id.receiptInfo);

        //gets the current date
        int yearCur = Calendar.getInstance().get(Calendar.YEAR);
        int monthCur = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int dayCur = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        date = dayCur + "-" + monthCur + "-" + yearCur;

        //info about the movie
        movieDate = day + "-" + (month + 1) + "-" + year;
        movie = db.getMovie(day, month, year);


        //showing the customer info to them
        String info = "";
        info += "Email: " + email  + " || Date: "  + date;
        info += "\n";
        info += "Ticket number: " + ticketNum;
        customerInfo.setText(info);


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
        receiptInfo.setText(info2);

        //the home button
        Button home = (Button) findViewById(R.id.Home);

        //when the clicks on the home button bring them back to the main menu
        home.setOnClickListener(v -> {
            Intent intent = new Intent(receipt.this, MainMenu.class);
            startActivity(intent);
        });

    }
}