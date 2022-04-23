package com.example.bulddingmmanagmenetsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RenterActivity extends AppCompatActivity {

    Button registernewcomplaint,mycomplaints,myinformation,homesafety;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renter);



        registernewcomplaint = findViewById(R.id.registernewcomplaint);
        mycomplaints = findViewById(R.id.mycomplaints);
        myinformation = findViewById(R.id.chekmyinformation);
        homesafety = findViewById(R.id.btnPlayActivity);


        //get renter info from login activity (First Name + Mobile Number)
        Intent RenterInfo = getIntent();

        String renterFirstName = RenterInfo.getStringExtra("Renter_Name");
        final int    rentermobilenumber = RenterInfo.getIntExtra("Mobile_Number",0);

        this.setTitle("Welcome Dear: "+renterFirstName);


        registernewcomplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registerNewComplaint = new Intent(RenterActivity.this, RegisterNewComplaint.class);
                registerNewComplaint.putExtra("Mobile_Number",rentermobilenumber);
                startActivity(registerNewComplaint);
            }
        });

        mycomplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent checkYmComplaints = new Intent(RenterActivity.this, MyComplaintActivity.class);
                checkYmComplaints.putExtra("Mobile_Number",rentermobilenumber);
                startActivity(checkYmComplaints);
            }
        });

        myinformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent checkYmComplaints = new Intent(RenterActivity.this, CheckMyInformation.class);
                checkYmComplaints.putExtra("Mobile_Number",rentermobilenumber);
                startActivity(checkYmComplaints);
            }
        });

        homesafety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent checkYmComplaints = new Intent(RenterActivity.this, Home_Safety.class);
                startActivity(checkYmComplaints);
            }
        });



    }
}
