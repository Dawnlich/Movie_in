package com.example.movie_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class account extends Activity {

    //variables
    EditText edit1, edit2, edit3;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);

        String email = (String) getIntent().getSerializableExtra("email");

        //this takes the input from the user for the sign-up page
        edit1 = (EditText)findViewById(R.id.newEmail);
        edit2 = (EditText)findViewById(R.id.newPassword);
        edit3 = (EditText)findViewById(R.id.confirmPassword);

        edit1.setText(email, TextView.BufferType.EDITABLE);

        //this are the variables for the button
        Button confirm = findViewById(R.id.confirm);
        Button back = findViewById(R.id.back);
        Button history = findViewById(R.id.history);

        confirm.setOnClickListener(v -> {
            Intent intent = new Intent(account.this, MainMenu.class);
            intent.putExtra("email", email);
            startActivity(intent);
        });

        //when the user click on the ticket button
        back.setOnClickListener(v -> {
            Intent intent = new Intent(account.this, MainMenu.class);
            intent.putExtra("email", email);
            startActivity(intent);
        });

        //when the user click on the logout button
        history.setOnClickListener(v -> {
            Intent intent = new Intent(account.this, MainMenu.class);
            intent.putExtra("email", email);
            startActivity(intent);
        });
    }
}
