package com.example.bulddingmmanagmenetsystem;

import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RenterComplaints extends AppCompatActivity {

    EditText complaintid,complaintstatus;
    ListView lst;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renter_complaints);




       showAllrenterComplaints();
    }

    public void  showAllrenterComplaints(){

        complaintid = (EditText) findViewById(R.id.complaintid);
        complaintstatus = (EditText) findViewById(R.id.complaintstatus);
        lst = (ListView) findViewById(R.id.renterComplaintsLV);

        db  = new DatabaseHelper(this);

        ArrayList<String> listData = db.getAllrenterComplaints();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listData);

        lst.setAdapter(arrayAdapter);
    }


    public void  updateComplaintStauts(View view){



        String complaintID = complaintid.getText().toString();
        String complaintStatus = complaintstatus.getText().toString();

        Boolean result = db.updateComplaintStatus(complaintID,complaintStatus);

        if(result == true){

            // Complaint Status Notification
            NotificationCompat.Builder notificationbuilder = new NotificationCompat.Builder( this )
                    .setSmallIcon( R.drawable.ic_stat_onesignal_default )
                    .setContentTitle( "Complaint Management" )
                    .setContentText( "The Complaint Updated Successfully" )
                    .setDefaults( 1 );

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from( this );
            notificationManagerCompat.notify( 1,notificationbuilder.build() );


            Toast.makeText(RenterComplaints.this,"Done",Toast.LENGTH_SHORT).show();


         //Reset form Fields
            complaintid.setText("");
            complaintstatus.setText("");

            showAllrenterComplaints();
        }
        else {
            Toast.makeText(RenterComplaints.this,"Error In Update Complaint Status",Toast.LENGTH_SHORT).show();
        }

    }

    public void SendSMS(View view) {

        Intent SMS = new Intent();
        SMS.setAction(Intent.ACTION_VIEW);
        SMS.setData(Uri.parse("sms:09"));
        SMS.putExtra(Intent.EXTRA_TEXT,"Dear Renter, Please Check Admin Comment for your Complaint.");
        startActivity(SMS);
    }

    public void CallRenter(View view) {

        Intent Call = new Intent();
        Call.setAction(Intent.ACTION_DIAL);
        Call.setData(Uri.parse("tel:09"));

        startActivity(Call);
    }


    public void WhatsAppRenter(View view) {

        Intent whatsApp = new Intent();
        whatsApp.setAction(Intent.ACTION_SEND);
        whatsApp.putExtra( Intent.EXTRA_TEXT,"Dear Renter, Please Check Admin Comment for your Complaint.");
        whatsApp.putExtra( Intent.EXTRA_SUBJECT,"New Complaint's Status" );
        whatsApp.putExtra( Intent.EXTRA_EMAIL,"A" );
        whatsApp.setType( "text/html" );
        startActivity( whatsApp );
        startActivity(Intent.createChooser( whatsApp,"s" ));


    }

    public void TelegramRenter(View view) {

        Intent Telegram = new Intent();
        Telegram.setAction(Intent.ACTION_SEND);
        Telegram.putExtra( Intent.EXTRA_TEXT,"Dear Renter, Please Check Admin Comment for your Complaint.");
        Telegram.putExtra( Intent.EXTRA_SUBJECT,"New Complaint's Status" );
        Telegram.putExtra( Intent.EXTRA_EMAIL,"A" );
        Telegram.setType( "text/html" );
        startActivity( Telegram );
        startActivity(Intent.createChooser( Telegram,"s" ));
    }

    public void EmailRenter(View view) {

        Intent Email = new Intent();
        Email.setAction(Intent.ACTION_SEND);
        Email.putExtra( Intent.EXTRA_TEXT,"Dear Renter, Please Check Admin Comment for your Complaint.");
        Email.putExtra( Intent.EXTRA_SUBJECT,"New Complaint's Status" );
        Email.putExtra( Intent.EXTRA_EMAIL,"Admin@gmail.com" );
        Email.setType( "text/html" );
        startActivity( Email );
        startActivity(Intent.createChooser( Email,"select your mail" ));
    }


}
