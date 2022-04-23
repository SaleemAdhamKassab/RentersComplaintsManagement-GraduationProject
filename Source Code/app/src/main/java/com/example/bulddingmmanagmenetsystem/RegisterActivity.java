package com.example.bulddingmmanagmenetsystem;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText firstname,lastname,password,mobilenumber,buildingname,flatnumber;
    RadioGroup gender;
    Button register;

    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        password = findViewById(R.id.password);
        mobilenumber = findViewById(R.id.mob);
        buildingname = findViewById(R.id.buildingname);
        flatnumber = findViewById(R.id.flatnumber);
        gender = findViewById(R.id.gender);

        register = findViewById(R.id.register);


        databaseHelper  = new DatabaseHelper(this);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstNameValue = firstname.getText().toString();
                String lastNameValue = lastname.getText().toString();
                String passwordValue = password.getText().toString();
                String mobileNumberValue = mobilenumber.getText().toString();
                String buildingNameValue = buildingname.getText().toString();
                String flatNumberValue = flatnumber.getText().toString();

                RadioButton checkedBtn = findViewById(gender.getCheckedRadioButtonId());
                String genderValue = checkedBtn.getText().toString();


                if(TextUtils.isEmpty(firstNameValue) ) {
                    firstname.setError("required field");
                    return;
                } else if(TextUtils.isEmpty(lastNameValue)) {
                    lastname.setError("required field");
                    return;
                }
                else if(TextUtils.isEmpty(mobileNumberValue)) {
                    mobilenumber.setError("required field");
                    return;
                }
                else if(mobileNumberValue.length()<10){
                    Toast.makeText(RegisterActivity.this, "The Phone Must be equal to 10 Numbers!", Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(passwordValue)) {
                    password.setError("required field");
                    return;
                } else if(passwordValue.length()<6){
                    Toast.makeText(RegisterActivity.this, "The Password Must be greater than 5 Characters!", Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(buildingNameValue)) {
                    buildingname.setError("required field");
                    return;
                }else if(TextUtils.isEmpty(flatNumberValue)) {
                    flatnumber.setError("required field");
                    return;
                }
                else{

                    ContentValues contentValues = new ContentValues();

                    contentValues.put("FIRST_NAME",firstNameValue);
                    contentValues.put("LAST_NAME",lastNameValue);
                    contentValues.put("PASSWORD",passwordValue);
                    contentValues.put("MOBILE_NUMBER",mobileNumberValue);
                    contentValues.put("BUILDING_NAME",buildingNameValue);
                    contentValues.put("FLAT_NUMBER",flatNumberValue);
                    contentValues.put("GENDER",genderValue);


                    databaseHelper.insertRenter(contentValues);
                    Toast.makeText(RegisterActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();



                    TextUtils.isEmpty(firstNameValue);
                    TextUtils.isEmpty(lastNameValue);
                    TextUtils.isEmpty(passwordValue);
                    TextUtils.isEmpty(mobileNumberValue);
                    TextUtils.isEmpty(buildingNameValue);
                    TextUtils.isEmpty(flatNumberValue);

                }
            }
        }
        );
    }
}
