package com.example.bulddingmmanagmenetsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    //*// Variables Declaration
    EditText firstname,mobilenumber,password;
    Button login,about_app;

    //Database Helper Instance
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


                //*//Assign the variables with Activity elements
                firstname = findViewById(R.id.firstname);
                mobilenumber = findViewById(R.id.mob);
                password = findViewById(R.id.password);

                login = findViewById(R.id.login);
                about_app = findViewById(R.id.about_app);
                databaseHelper = new DatabaseHelper(this);



                //*// Login to the App
                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                //**// Get the elements entered vales from the user
                String firstNameValue = firstname.getText().toString();
                String mobilenumberValue = mobilenumber.getText().toString();
                String passwordValue = password.getText().toString();
                final int finalmobilenumberValue = Integer.parseInt(mobilenumberValue);


                //*//Check element values must be entered from the user
                if   (TextUtils.isEmpty(firstNameValue))
                {
                    firstname.setError("required field");
                    return;
                }
               else if( TextUtils.isEmpty(mobilenumberValue) )
               {
                   mobilenumber.setError("required field");
                   return;
               }

                else if (TextUtils.isEmpty(passwordValue))
                {
                    password.setError("required field");
                    return;
                }

                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                ///*/// Now after Checking from user's vales --> we will forward the user into the App Based on his role as the following:
                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                //* 1 *// Login As Admin  --> Forward the the user to the Admin_Activity

                else if (firstNameValue.equals("Admin") & finalmobilenumberValue == 999333221 & passwordValue.equals("P@ssw0rd"))
                {
                    Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                }


                //* 2 * // Login As Normal Renter  ->>  Forward the the user to the Renter_Activity

                else if (databaseHelper.isLoginValid( firstNameValue ,finalmobilenumberValue, passwordValue))

                {
                        Intent RenterInfo = new Intent(LoginActivity.this,RenterActivity.class);
                             RenterInfo.putExtra("Renter_Name",firstNameValue);
                             RenterInfo.putExtra("Mobile_Number",finalmobilenumberValue);
                         startActivity(RenterInfo);

                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                }


                //* 3 * // Invalid Login

                else
                    {
                        Toast.makeText(LoginActivity.this, "Invalid UserName Or Password", Toast.LENGTH_SHORT).show();
                    }
            }
        });


                //*//Go to About_App Activity
                about_app.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent( LoginActivity.this,About_App.class);
                        startActivity(intent);
                    }
                });


    }
}
