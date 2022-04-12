package com.example.movie_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class account extends Activity {

    //variables
    EditText edit1, edit2, edit3;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);

        //this gets the email for the users
        String email = (String) getIntent().getSerializableExtra("email");

        //this takes the input from the user for the sign-up page
        edit1 = (EditText)findViewById(R.id.newEmail);
        edit2 = (EditText)findViewById(R.id.newPassword);
        edit3 = (EditText)findViewById(R.id.confirmPassword);

        //this creates a variable to allow interaction with the database
        db = new Database(this);

        //this shows the values in the edit text
        edit1.setText(email, TextView.BufferType.EDITABLE);
        edit2.setText(db.getPassword(email), TextView.BufferType.EDITABLE);

        //this are the variables for the button
        Button confirm = findViewById(R.id.confirm);
        Button back = findViewById(R.id.back);
        Button history = findViewById(R.id.history);

        confirm.setOnClickListener(v -> {
            edit1 = (EditText)findViewById(R.id.newEmail);
            edit2 = (EditText)findViewById(R.id.newPassword);
            if(edit3.getText().toString().trim().length() != 0 && edit2.getText().toString().equals(edit3.getText().toString())) {
                boolean checkUpdateEmail = db.updateEmail(email, edit1.getText().toString());
                boolean checkUpdatePassword = db.updatePassword(email, edit2.getText().toString());
                if (checkUpdateEmail != true || checkUpdatePassword != true) {
                    Intent intent = new Intent(account.this, MainMenu.class);
                    intent.putExtra("email", edit1.getText().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(account.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(account.this, "Please re-enter new or old password before committing to the changes!", Toast.LENGTH_SHORT).show();
            }
        });

        //when the user click on the ticket button
        back.setOnClickListener(v -> {
            Intent intent = new Intent(account.this, MainMenu.class);
            intent.putExtra("email", edit1.getText().toString());
            startActivity(intent);
        });

        //when the user click on the logout button
        history.setOnClickListener(v -> {
            Intent intent = new Intent(account.this, paymentHistory.class);
            intent.putExtra("email", edit1.getText().toString());
            startActivity(intent);
        });
    }
}
