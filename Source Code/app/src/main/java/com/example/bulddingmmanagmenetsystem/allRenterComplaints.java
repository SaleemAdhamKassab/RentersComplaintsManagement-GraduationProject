package com.example.bulddingmmanagmenetsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class allRenterComplaints extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_renter_complaints);



        showAllrenterComplaints();
    }

    public void  showAllrenterComplaints(){


        ListView lst;
        DatabaseHelper db;

        lst = (ListView) findViewById(R.id.allrenterComplaintsLV);

        db  = new DatabaseHelper(this);

        ArrayList<String> listData = db.getAllrenterComplaints();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listData);

        lst.setAdapter(arrayAdapter);
    }
}
