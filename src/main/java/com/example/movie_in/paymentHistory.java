package com.example.movie_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class paymentHistory extends Activity {

    //variables
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymenthistory);

        //this gets the email for the users
        String email = (String) getIntent().getSerializableExtra("email");

        //this are the variables for the button
        Button back = findViewById(R.id.BackHistory);


        //when the user click on the ticket button
        back.setOnClickListener(v -> {
            Intent intent = new Intent(paymentHistory.this, account.class);
            intent.putExtra("email", email);
            startActivity(intent);
        });
    }
}