package com.example.movie_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        //this are the variables for the button
        Button account = findViewById(R.id.button5);
        Button ticket = findViewById(R.id.button4);
        Button logout = findViewById(R.id.button6);

        //when the user clicks on the account button
        account.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenu.this, MainActivity.class);
            startActivity(intent);
        });

        ticket.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenu.this, calendar.class);
            startActivity(intent);
        });

        logout.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenu.this, MainActivity.class);
            startActivity(intent);
        });

    }
}
