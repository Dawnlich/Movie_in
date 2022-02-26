package com.example.movie_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Parking extends Activity {

    RadioButton btn1, btn2, btn3, btn4, btn5, btn6, btn8, btn9, btn10, btn11;
    RadioButton btn12, btn13, btn14, btn15, btn16, btn17, btn18;
    RadioGroup group1, group2, group3;
    RadioGroup[] rows;

    //variables
    String parking;
    int year, day, month;

    //database variables
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_lot);

        //connecting to the database
        db = new Database(this);

        year = (int) getIntent().getSerializableExtra("year");
        day = (int) getIntent().getSerializableExtra("day");
        month = (int) getIntent().getSerializableExtra("month");

        //getting the parking spots for the movie
        String num = db.getParking(day, month, year);
        String str[] = num.split(",");
        List<String> al = new ArrayList<String>();
        al = Arrays.asList(str);

        btn1 = findViewById(R.id.radioButton_01);
        btn2 = findViewById(R.id.radioButton_02);
        btn3 = findViewById(R.id.radioButton_03);
        btn4 = findViewById(R.id.radioButton_04);
        btn5 = findViewById(R.id.radioButton_05);
        btn6 = findViewById(R.id.radioButton_06);
        btn8 = findViewById(R.id.radioButton_08);
        btn9 = findViewById(R.id.radioButton_09);
        btn10 = findViewById(R.id.radioButton_10);
        btn11 = findViewById(R.id.radioButton_11);
        btn12 = findViewById(R.id.radioButton_12);
        btn13 = findViewById(R.id.radioButton_13);
        btn14 = findViewById(R.id.radioButton_14);
        btn15 = findViewById(R.id.radioButton_15);
        btn16 = findViewById(R.id.radioButton_16);
        btn17 = findViewById(R.id.radioButton_17);
        btn18 = findViewById(R.id.radioButton_18);

        group1 = (RadioGroup)findViewById(R.id.top_row);
        group2 = (RadioGroup)findViewById(R.id.middle_row);
        group3 = (RadioGroup)findViewById(R.id.bottom_row);

        rows = new RadioGroup[] {group1, group2, group3};

        group1.clearCheck();
        group2.clearCheck();
        group3.clearCheck();

        group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                printId(group,checkedId);
            }
        });
        group2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                printId(group,checkedId);

            }
        });
        group3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                printId(group,checkedId);
            }
        });

        Button back = (Button) findViewById(R.id.back1);
        Button next = (Button) findViewById(R.id.next1);

        next.setOnClickListener(v -> {
            Intent intent = new Intent(Parking.this, MainMenu.class);
            startActivity(intent);
        });

        back.setOnClickListener(v -> {
            Intent intent = new Intent(Parking.this, MoviesTimes.class);
            intent.putExtra("day", day);
            intent.putExtra("year", year);
            intent.putExtra("month", month);
            startActivity(intent);
        });
    }

    void printId(RadioGroup group, int checkedId){
        //String radioGroups[] = new String[] {"top_row","middle_row","bottom_row"};
        String id = String.valueOf(checkedId);
        String groupName = String.valueOf(group.getId());
        Log.v("group", groupName);
        for(RadioGroup r : rows){
            if(getResources().getResourceEntryName(r.getId()) == getResources().getResourceEntryName(checkedId)) {
                //r.clearCheck();
                Log.v("Success","true");
            } else {
                Log.v("Failure", "false");
            }
        }

    }
}
