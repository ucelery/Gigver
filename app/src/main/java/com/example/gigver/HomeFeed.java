package com.example.gigver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import models.User;

public class HomeFeed extends AppCompatActivity {
    //Sample List views only
    String valoAgents[] = {"Jett","Raze","Reyna"};
    int valoAgentImages [] = {R.drawable.jett,R.drawable.reyna,R.drawable.raze};
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_feed);
        //Intents for selecting Post page and Profile Page
        ImageView selectPost = (ImageView) findViewById(R.id.postButton);
        ImageView selectMe = (ImageView) findViewById(R.id.profileButton);
        listView = (ListView) findViewById(R.id.listView);
        CustomListViewAdapter adapter = new CustomListViewAdapter(getApplicationContext(),valoAgents,valoAgentImages);
        listView.setAdapter(adapter);

        if(User.currentUser == null){
            selectPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(findViewById(android.R.id.content), "Please Login to Create Post", Snackbar.LENGTH_SHORT).show();
                }
            });
            selectMe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(findViewById(android.R.id.content), "Please Login", Snackbar.LENGTH_SHORT).show();
                }
            });
        }else{
            selectPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), CreatePost.class);
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
}