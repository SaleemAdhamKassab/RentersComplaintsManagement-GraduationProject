package com.example.bulddingmmanagmenetsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {
    Button regnewrenter;
    Button followuprenterscomplaints;
    Button adminreports;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        //**// Set Activity title
        setTitle( "Admin Home Page" );


        regnewrenter = findViewById(R.id.regnewrenter);
        followuprenterscomplaints = findViewById(R.id.followuprenterscomplaints);
        adminreports = findViewById(R.id.adminReports);


        regnewrenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( AdminActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        followuprenterscomplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( AdminActivity.this,RenterComplaints.class);
                startActivity(intent);
            }
        });

        adminreports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( AdminActivity.this,AdminReports.class);
                startActivity(intent);
            }
        });
    }




}
