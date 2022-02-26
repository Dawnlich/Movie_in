package com.example.movie_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Parking extends Activity {

    RadioButton btn1, btn2, btn3, btn4, btn5, btn6, btn8, btn9, btn10, btn11;
    RadioButton btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19;
    RadioGroup group1, group2, group3;
    RadioGroup[] rows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_lot);

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
        //getResources().getResourceEntryName(int resid);
    }
}
