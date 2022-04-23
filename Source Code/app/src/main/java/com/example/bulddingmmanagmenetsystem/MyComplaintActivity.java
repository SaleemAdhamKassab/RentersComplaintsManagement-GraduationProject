package com.example.bulddingmmanagmenetsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MyComplaintActivity extends AppCompatActivity {

    ListView lst;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_complaint);

        showData();


    }
    public void  showData(){

        //get renter mobile number from Renter Activity
        Intent renterMobilenumber = getIntent();
        final int    rentermobilenumber = renterMobilenumber.getIntExtra("Mobile_Number",0);

        this.setTitle("My Complaint's Status");

        lst = (ListView) findViewById(R.id.renterComplaintsListView);

        db  = new DatabaseHelper(this);

        ArrayList<String> listData = db.getRenterComplaint(rentermobilenumber);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listData);

        lst.setAdapter(arrayAdapter);
    }
}
