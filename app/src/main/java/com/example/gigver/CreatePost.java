package com.example.gigver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CreatePost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        //Intents for selecting Home page and Profile page
        ImageView selectHome = (ImageView) findViewById(R.id.homeButton);
        ImageView selectMe = (ImageView) findViewById(R.id.profileButton);
        String email = getIntent().getExtras().getString("email");
        if(email != null){
            selectHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), HomeFeed.class);
                    intent.putExtra("email",email);
                    startActivity(intent);
                    overridePendingTransition(R.anim.static_animation,R.anim.static_animation);
                }
            });
            selectMe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), ProfilePage.class);
                    intent.putExtra("email",email);
                    startActivity(intent);
                    overridePendingTransition(R.anim.static_animation,R.anim.static_animation);
                }
            });
        }
    }
}