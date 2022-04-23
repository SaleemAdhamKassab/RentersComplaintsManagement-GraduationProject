package com.example.bulddingmmanagmenetsystem;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterNewComplaint extends AppCompatActivity {

    EditText buildingname,flatnumber,coplaintdescription;
    Button registernewcomplaint;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_complaint);

        //**// Set Activity title
        setTitle( "Add Complaint" );


        //get renter mobile number from Renter Activity
        Intent renterMobilenumber = getIntent();
        final int    rentermobilenumber = renterMobilenumber.getIntExtra("Mobile_Number",0);



        buildingname = findViewById(R.id.buildingname);
        flatnumber = findViewById(R.id.flatnumber);
        coplaintdescription = findViewById(R.id.complaintdescribtion);

        registernewcomplaint = findViewById(R.id.registernewcomplaint);


        databaseHelper  = new DatabaseHelper(this);

        registernewcomplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String buildingNameValue = buildingname.getText().toString();
                String flatNumberValue = flatnumber.getText().toString();
                String complaintDescriptionValue = coplaintdescription.getText().toString();

                if (TextUtils.isEmpty(buildingNameValue)) {
                    buildingname.setError("required field");
                    return;
                } else if (TextUtils.isEmpty(flatNumberValue)) {
                    flatnumber.setError("required field");
                    return;
                } else if (TextUtils.isEmpty(complaintDescriptionValue)) {
                    coplaintdescription.setError("required field");
                    return;
                } else {

                    ContentValues contentValues = new ContentValues();

                    contentValues.put("MOBILE_NUMBER",rentermobilenumber);
                    contentValues.put("BUILDING_NAME", buildingNameValue);
                    contentValues.put("FLAT_NUMBER", flatNumberValue);
                    contentValues.put("STATUS", "Opened");
                    contentValues.put("COMPLAINT_DESCRIPTION", complaintDescriptionValue);


                    databaseHelper.insertComplaint(contentValues);

                    Toast.makeText(RegisterNewComplaint.this, "Complaint Registered Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        }
        );




    }
}