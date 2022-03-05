package com.example.movie_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class payment extends Activity {

    //variables
    int year, day, month;
    TextView customer, cost;
    String email, date, movie, movieDate, spot, people;
    double amount;
    EditText edit1, edit2, edit3;
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
        spot = (String) getIntent().getSerializableExtra("spot");

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

        //credit card info stored
        edit1 = (EditText)findViewById(R.id.editTextTextPersonName3);
        String card =  edit1.getText().toString();
        edit2 = (EditText)findViewById(R.id.editTextTextPersonName5);
        String cvv =  edit2.getText().toString();
        edit3 = (EditText)findViewById(R.id.editTextTextPersonName4);
        String cardDate =  edit3.getText().toString();

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
            if(edit1.getText().toString().trim().length() == 0) {
                Toast.makeText(payment.this,
                        "Please enter a card num!",
                        Toast.LENGTH_SHORT).show();
            }else if(edit2.getText().toString().trim().length() == 0) {
                Toast.makeText(payment.this,
                        "Please enter a card cvv!",
                        Toast.LENGTH_SHORT).show();
            }else if(edit3.getText().toString().trim().length() == 0)  {
                Toast.makeText(payment.this,
                        "Please enter a card date!",
                        Toast.LENGTH_SHORT).show();
            }else {
                startActivity(intent);
            }
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
