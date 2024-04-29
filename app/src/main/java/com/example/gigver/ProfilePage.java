package com.example.gigver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import models.User;
import utils.IServerEvent;
import utils.ServerManager;

public class ProfilePage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        //Intents for selecting Home page and Post page
        ImageView selectHome = (ImageView) findViewById(R.id.homeButton);
        ImageView selectPost = (ImageView) findViewById(R.id.postButton);
        TextView profileName = (TextView) findViewById(R.id.userName);
        TextView address = (TextView) findViewById(R.id.addressProfile);
        TextView email = (TextView) findViewById(R.id.emailaddressProfile);
        TextView mobile = (TextView) findViewById(R.id.mobileNumberProfile);
        TextView telephone = (TextView) findViewById(R.id.telephoneNumberProfile);

        if (User.currentUser != null) {
            profileName.setText(User.currentUser.GetName());
            email.setText(User.currentUser.GetEmail());
            mobile.setText(User.currentUser.GetMobileNo());
            address.setText(User.currentUser.GetAddress());
            telephone.setText(User.currentUser.GetTelephoneNo());
        } else {
            profileName.setText("Guest");
        }

        selectHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeFeed.class);
                startActivity(intent);
                overridePendingTransition(R.anim.static_animation,R.anim.static_animation);
            }
        });

        selectPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreatePost.class);
                startActivity(intent);
                overridePendingTransition(R.anim.static_animation,R.anim.static_animation);
            }
        });
    }
}