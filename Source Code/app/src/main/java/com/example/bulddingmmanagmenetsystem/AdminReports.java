package com.example.bulddingmmanagmenetsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminReports extends AppCompatActivity {

    Button rentercomplaints,renterlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_reports);

        //**// Set Activity title
        setTitle( "Admin Reports" );



        rentercomplaints = findViewById(R.id.renterComplaints);
        renterlist = findViewById(R.id.renterlist);

        rentercomplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent checkYmComplaints = new Intent(AdminReports.this, allRenterComplaints.class);
                startActivity(checkYmComplaints);
            }
        });

        renterlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent checkYmComplaints = new Intent(AdminReports.this, RentersList.class);
                startActivity(checkYmComplaints);
            }
        });


    }
}
