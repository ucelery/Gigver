package com.example.gigver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeFeed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_feed);
        //Intents for selecting Post page and Profile Page
        ImageView selectPost = (ImageView) findViewById(R.id.unselectedpostButton);
        ImageView selectMe = (ImageView) findViewById(R.id.unselectedmeButton);
        selectPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CreatePost.class);
                startActivity(intent);
                overridePendingTransition(R.anim.static_animation,R.anim.static_animation);
            }
        });
        selectMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfilePage.class);
                startActivity(intent);
                overridePendingTransition(R.anim.static_animation,R.anim.static_animation);
            }
        });
    }
}