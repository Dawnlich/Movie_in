package com.example.movie_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
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

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Parking extends Activity {
    RadioButton btn1, btn2, btn3, btn4, btn5, btn6, btn8, btn9, btn10, btn11;
    RadioButton btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19;
    RadioGroup group1, group2, group3;
    RadioGroup lastSelected;
    ArrayList<RadioGroup> rows;

    //for parking
    String parking = "";
    ArrayList<Integer> lot = new ArrayList<Integer>();
    private Database db;

    //for the amount of people in a car
    String[] people = {"1", "2", "3", "4"};
    String amount = "";
    HashMap<String, Integer> hash = new HashMap<String,Integer>();
    List<String> parkingList = new ArrayList<String>();
    List<String> newParkingList = new ArrayList<String>();
    String parkingLetter =  "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R";
    //parkingList.asList()

    @RequiresApi(api = Build.VERSION_CODES.N)
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
        parkingList = new ArrayList<String>();
        parkingList = Arrays.asList(str);
        String letter[] = parkingLetter.split(",");
        newParkingList = new ArrayList<String>();
        newParkingList = Arrays.asList(letter);


        //Test with

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

        //Establish full and available parking lots
        //TODO: send full alphabet string with placeholder values(X) for occupied spaces. Compare received string with parkingLetter string and update radioButtons.
        //todo
        populateHash();
        createIds();
        populateLots();
        //printHash();
        setFull();

        //if the user select yes
        next.setOnClickListener(v -> {
            if(parking != ""){
            Intent intent = new Intent(Parking.this, payment.class);
            amount = spin.getSelectedItem().toString();
            intent.putExtra("day", day);
            intent.putExtra("year", year);
            intent.putExtra("month", month);
            intent.putExtra("amount", amount);
            intent.putExtra("email", email);
            intent.putExtra("spot", parking);
            startActivity(intent);
            }else{
                Toast.makeText(Parking.this,
                        "Please select a spot!",
                        Toast.LENGTH_SHORT).show();
            }
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

    void populateHash(){
        for(String s: newParkingList){
            hash.put(s,-1);
        }
    }

    void createIds(){
        Random rand = new Random();
        RadioGroup[] parkingRows = new RadioGroup[]{group1, group2, group3};
        for(int i = 0; i < parkingRows.length;i++){
        int rowCount = parkingRows[i].getChildCount();
            for(int j = 0; j < rowCount; j++){
                lot.add(parkingRows[i].getChildAt(j).getId());
            }
        }
        Log.e("lot size", String.valueOf(lot.size()));
        Log.e("hash size", String.valueOf(hash.size()));
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    void populateLots(){
        int i = 0;
        for (Map.Entry<String, Integer> entry : hash.entrySet()) {
            String k = entry.getKey();
            Integer v = entry.getValue();
            hash.replace(k, lot.get(i));
            i++;
        }

    }

    //for testing purposes
    void findKey(View v){
        for (Map.Entry<String, Integer> entry : hash.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if(value == v.getId()){
                parking = key;
                Log.e("Found", "Match was found");
            }
        }
    }

    //
    void setFull(){
        int i = 0;
        for (Map.Entry<String, Integer> entry : hash.entrySet()) {
            String k = entry.getKey();
            if(hash.get(k) != -1){
                RadioButton current = findViewById(hash.get(k));
                current.setEnabled(true);
            }
            i++;
        }
    }

//HashMap<Character, Integer> hash => k,v pair
    public void handleCombinedClick(View view) {
        group1.clearCheck();
        group2.clearCheck();
        group3.clearCheck();
        setFull();
        findKey(view);
        if(((RadioButton) view).isEnabled() == true){
            ((RadioButton) view).setChecked(true);
        }
    }
}
