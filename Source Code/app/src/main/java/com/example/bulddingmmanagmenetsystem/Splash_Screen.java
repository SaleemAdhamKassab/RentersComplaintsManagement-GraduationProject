package com.example.bulddingmmanagmenetsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash__screen );

        //**// Set Activity title
        setTitle( "Welcome Dear Renter" );


        Thread thread = new Thread(  ){

            @Override
            public void run() {

                try {
                    sleep( 3000 );
                    Intent intent = new Intent( getApplicationContext(),LoginActivity.class );
                    startActivity( intent );
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }
}
