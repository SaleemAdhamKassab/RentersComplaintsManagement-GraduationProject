package com.example.bulddingmmanagmenetsystem;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CheckMyInformation extends AppCompatActivity {

    EditText firsiname,lastname,mobilenumber,password;
    ListView lst;
    DatabaseHelper db;

    NotificationManager   nmanager;
    static  int id=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_my_information);

        //**// Set Activity title
        setTitle( "Check OR Update you Information" );

        showMyInformation();

    }

    public void  showMyInformation(){

        //get renter mobile number from Renter Activity
        Intent renterMobilenumber = getIntent();
        final int    rentermobilenumber = renterMobilenumber.getIntExtra("Mobile_Number",0);

        firsiname = (EditText) findViewById(R.id.newfirstname);
        lastname = (EditText) findViewById(R.id.newlastname);
        mobilenumber = (EditText) findViewById(R.id.newmobilenumber);
        password = (EditText) findViewById(R.id.newpassword);
        lst = (ListView) findViewById(R.id.myinformation);

        db  = new DatabaseHelper(this);

        ArrayList<String> listData = db.getRenterInformation(rentermobilenumber);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listData);

        lst.setAdapter(arrayAdapter);
    }



    public void  UpdateRenterInformation(View view){

        String newFirstName = firsiname.getText().toString();
        String newLastName = lastname.getText().toString();
        String newMobileNumber = mobilenumber.getText().toString();
        String newPassword = password.getText().toString();


        Boolean result = db.UpdateRenetrInformation(newFirstName,newLastName,newMobileNumber,newPassword);




        if(TextUtils.isEmpty(newFirstName) ) {
            firsiname.setError("required field");
            return;
        } else if(TextUtils.isEmpty(newLastName)) {
            lastname.setError("required field");
            return;
        }else if(TextUtils.isEmpty(newMobileNumber)) {
            mobilenumber.setError("required field");
            return;
        } else if(newMobileNumber.length()<10){
            Toast.makeText(CheckMyInformation.this, "The Phone Must be equal to 10 Numbers!", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(newPassword)) {
            password.setError( "required field" );
        }
        else if(newPassword.length()<6){
            Toast.makeText(CheckMyInformation.this, "The Password Must be greater than 5 Characters!", Toast.LENGTH_SHORT).show();
        }
        else if (result == true){


            firsiname.setText("");
            lastname.setText("");
            mobilenumber.setText("");
            password.setText("");


            // Information Notification
                NotificationCompat.Builder notificationbuilder = new NotificationCompat.Builder( this )
                        .setSmallIcon( R.drawable.ic_stat_onesignal_default)
                        .setContentTitle( "Complaint Management" )
                        .setContentText( "Your Info Updated Successfully" )
                        .setDefaults( 1 );

                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from( this );
                notificationManagerCompat.notify( 1, notificationbuilder.build());


            // Show renter Information after updated
            showMyInformation();
        }

        else {
            Toast.makeText(CheckMyInformation.this,"Error In Update",Toast.LENGTH_SHORT).show();
        }

    }
}
