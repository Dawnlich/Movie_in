package com.example.movie_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class paymentHistory extends Activity {

    //variables
    TextView info;
    private Database db;

    //arraylist
    ArrayList<String> ticketList = new ArrayList<String>();
    ArrayList<String> costList = new ArrayList<String>();
    ArrayList<String> dateList = new ArrayList<String>();
    ArrayList<String> spotList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymenthistory);

        //this gets the email for the users
        String email = (String) getIntent().getSerializableExtra("email");

        //this are the variables for the button
        Button back = findViewById(R.id.BackHistory);

        //display all payment information
        info = (TextView) findViewById(R.id.displayHistory);;

        //database
        db = new Database(this);

        //gets the payment history
        ticketList = db.getTicketHistory(email);
        costList = db.getCostHistory(email);
        dateList = db.getDateHistory(email);
        spotList = db.getSpotHistory(email);

        String top = "";
        top += "TICKET ||" + " COST ||" + " DATE           ||" + " SPOT";
        top += "\n";
        info.setText(top);

        info.setTextSize(15);
        for (int i=0; i< ticketList.size();i++) {
            info.append(ticketList.get(i));
            info.append("         $");
            info.append(costList.get(i));
            info.append("       ");
            info.append(dateList.get(i));
            info.append("          ");
            info.append(spotList.get(i));
            info.append("\n");
        }

        //when the user click on the ticket button
        back.setOnClickListener(v -> {
            Intent intent = new Intent(paymentHistory.this, account.class);
            intent.putExtra("email", email);
            startActivity(intent);
        });
    }
}