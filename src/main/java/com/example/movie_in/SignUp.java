package com.example.movie_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends Activity {

    //variables
    EditText edit1, edit2, edit3;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        //this takes the input from the user for the sign-up page
        edit1 = (EditText)findViewById(R.id.editTextTextEmailAddress);
        edit2 = (EditText)findViewById(R.id.editTextTextPassword);
        edit3 = (EditText)findViewById(R.id.editTextTextPassword2);

        //this are the variables for the buttons on the sign-up page
        Button signUP = findViewById(R.id.signUpButton);
        Button back = findViewById(R.id.backButton1);

        //this creates a variable that will help interact with the database
        db = new Database(this);

        //this function is what happens when the users click on the sign-up button
        signUP.setOnClickListener(v -> {
            Boolean checkEmail = db.checkEmail(edit1.getText().toString());
            if(checkEmail != true){
                if(edit2.getText().toString().equals(edit3.getText().toString())){
                    Boolean created  = db.insertData(edit1.getText().toString(),edit2.getText().toString());
                    if (created) {
                        Toast.makeText(SignUp.this, "Got it!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUp.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignUp.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SignUp.this, "Password must match!", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(SignUp.this, "Email already used!", Toast.LENGTH_SHORT).show();
            }
        });

        //this functions  is what happens when the users click on the back button
        back.setOnClickListener(v -> {
            Intent intent = new Intent(SignUp.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
