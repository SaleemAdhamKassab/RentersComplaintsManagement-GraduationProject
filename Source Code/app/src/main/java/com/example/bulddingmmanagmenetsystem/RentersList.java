package com.example.bulddingmmanagmenetsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class RentersList extends AppCompatActivity {

    ListView lst;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renters_list);

        //**// Set Activity title
        setTitle( "Renter's Accounts Log" );

        showAllrenters();
    }

    public void  showAllrenters(){


        lst = (ListView) findViewById(R.id.rentersLV);

        db  = new DatabaseHelper(this);

        ArrayList<String> listData = db.getAllrenters();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listData);

        lst.setAdapter(arrayAdapter);
    }
}
