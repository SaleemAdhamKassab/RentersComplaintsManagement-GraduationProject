package com.example.bulddingmmanagmenetsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Home_Safety extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {


    YouTubePlayerView mPlayer;
    String Key = "AIzaSyBHhRVNS1dcXvfusrFP2GPRDojMJYv7nkY";
    String id= "xJt5r88FYg4";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home__safety );


        mPlayer = (YouTubePlayerView) findViewById( R.id.videoViewYT );
        mPlayer.initialize( Key,this );

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {

        if (!wasRestored){
            youTubePlayer.cueVideo( id );
            youTubePlayer.play();
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
