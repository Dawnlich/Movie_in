package com.example.movie_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Parking extends Activity {
    RadioButton btn1, btn2, btn3, btn4, btn5, btn6, btn8, btn9, btn10, btn11;
    RadioButton btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19;
    RadioGroup group1, group2, group3;
    RadioGroup lastSelected;
    ArrayList<RadioGroup> rows;

    //for parking
    String parking = "No spot was selected";
    ArrayList<Integer> lot = new ArrayList<Integer>();
    private Database db;

    //for the amount of people in a car
    String[] people = {"1", "2", "3", "4"};
    String amount = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_lot);

        //connecting to the database
        db = new Database(this);
        int year = (int) getIntent().getSerializableExtra("year");
        int day = (int) getIntent().getSerializableExtra("day");
        int month = (int) getIntent().getSerializableExtra("month");
        String email = (String) getIntent().getSerializableExtra("email");

        //getting the parking spots for the movie
        String num = db.getParking(day, month, year);
        String str[] = num.split(",");
        List<String> al = new ArrayList<String>();

        //spinner for picking the amount of people in car
        Spinner spin = findViewById(R.id.spinner1);
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, people);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(ad);

        //group for each row
        group1 = (RadioGroup) findViewById(R.id.top_row);
        group2 = (RadioGroup) findViewById(R.id.middle_row);
        group3 = (RadioGroup) findViewById(R.id.bottom_row);
        Log.v("ParkingSpots", num);

        //buttons
        Button back = findViewById(R.id.back1);
        Button next = findViewById(R.id.next1);

        //if the user select yes
        next.setOnClickListener(v -> {
            Intent intent = new Intent(Parking.this, payment.class);
            amount = spin.getSelectedItem().toString();
            intent.putExtra("day", day);
            intent.putExtra("year", year);
            intent.putExtra("month", month);
            intent.putExtra("amount", amount);
            intent.putExtra("email", email);
            intent.putExtra("spot", parking);
            startActivity(intent);
        });

        //if the user selects the back button
        back.setOnClickListener(v -> {
            Intent intent = new Intent(Parking.this, MoviesTimes.class);
            intent.putExtra("day", day);
            intent.putExtra("year", year);
            intent.putExtra("month", month);
            intent.putExtra("email", email);
            startActivity(intent);
        });
    }


    void generateLot(){
        Random rand = new Random();
        for(int i = 0; i < 18; i++){
            lot.add(rand.nextInt());
        }
    }

    void populateLot(){
        int lastNum = 0;
        Random rand = new Random();
        RadioGroup[] parkingRows = new RadioGroup[]{group1, group2, group3};
        for(int i = 0; i < parkingRows.length;i++){
        int rowCount = parkingRows[i].getChildCount();
            for(int j = lastNum; j < lastNum+rowCount; j++){
                View current = parkingRows[i].getChildAt(j-lastNum);
                switch(rand.nextInt()){
                    case 0:
                        ((RadioGroup)current).setEnabled(false);
                        break;
                    case 1:
                        ((RadioGroup)current).setEnabled(true);
                        break;
                }
                lastNum = j;
            }
        }
    }

    public void handleCombinedClick(View view) {
        group1.clearCheck();
        group2.clearCheck();
        group3.clearCheck();
        ((RadioButton) view).setChecked(true);
    }
}
