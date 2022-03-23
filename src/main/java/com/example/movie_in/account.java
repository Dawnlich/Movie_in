package com.example.movie_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class account extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);


        //this are the variables for the button
        Button confirm = findViewById(R.id.confirm);
        Button back = findViewById(R.id.back);
        Button history = findViewById(R.id.history);

        confirm.setOnClickListener(v -> {
            Intent intent = new Intent(account.this, MainMenu.class);
            startActivity(intent);
        });

        //when the user click on the ticket button
        back.setOnClickListener(v -> {
            Intent intent = new Intent(account.this, MainMenu.class);
            startActivity(intent);
        });

        //when the user click on the logout button
        history.setOnClickListener(v -> {
            Intent intent = new Intent(account.this, MainMenu.class);
            startActivity(intent);
        });
    }
}
