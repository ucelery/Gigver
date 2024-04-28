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

    String uName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        ServerManager server = new ServerManager("https://gigver-server.onrender.com");

        //Intents for selecting Home page and Post page
        ImageView selectHome = (ImageView) findViewById(R.id.homeButton);
        ImageView selectPost = (ImageView) findViewById(R.id.postButton);
        TextView profileName = (TextView) findViewById(R.id.userName);

        String email = getIntent().getExtras().getString("email");
        if (email == null) {
            profileName.setText("Guest");
        }else {
            server.GetUsers(new IServerEvent<List<User>>() {
                @Override
                public void OnComplete(List<User> result) {
                    for (User user : result) {
                        if (user.GetEmail().equals(email)) {
                            uName = user.GetName();
                            break;
                        }
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            profileName.setText(uName);
                        }
                    });
                }
                @Override
                public void OnFailure(String errorMessage) {
                }
            });
            selectHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), HomeFeed.class);
                    intent.putExtra("email",email);
                    startActivity(intent);
                    overridePendingTransition(R.anim.static_animation,R.anim.static_animation);
                }
            });
            selectPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), CreatePost.class);
                    intent.putExtra("email",email);
                    startActivity(intent);
                    overridePendingTransition(R.anim.static_animation,R.anim.static_animation);
                }
            });
        }
    }
}