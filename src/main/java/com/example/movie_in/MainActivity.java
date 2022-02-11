package com.example.movie_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    //variables
    EditText edit1, edit2;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This is the text input from the users
        edit1 = (EditText)findViewById(R.id.signUpEmail);
        edit2 = (EditText)findViewById(R.id.signInPassword);

        //this creates a variable to allow interaction with the database
        db = new Database(this);

        //this is creating the variables for the buttons
        Button signIn = findViewById(R.id.button);
        Button signUp = findViewById(R.id.button2);

        //This function is what happens when the users click on the sign-in button
        signIn.setOnClickListener(v -> {
            if(edit1.getText().toString().trim().length() == 0){
                Toast.makeText(MainActivity.this, "Please enter a email!", Toast.LENGTH_SHORT).show();
            }else if(edit2.getText().toString().trim().length() == 0){
                Toast.makeText(MainActivity.this, "Please enter a Password!", Toast.LENGTH_SHORT).show();
            }else{
                Boolean checking = db.CheckLogin(edit1.getText().toString(), edit2.getText().toString());
                if(checking == true) {
                        Intent intent = new Intent(MainActivity.this, MainMenu.class);
                        startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Invalid email or password!", Toast.LENGTH_SHORT).show();
                }
            }

        });

        //This function is what happens when the users click on the sign-up button
        signUp.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignUp.class);
            startActivity(intent);
        });
    }
}