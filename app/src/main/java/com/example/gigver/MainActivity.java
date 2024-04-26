package com.example.gigver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import models.Post;
import models.User;
import utils.IServerEvent;
import utils.ServerManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /* Temporary Intent on Submit Button
        Subject to change when backend is connected */
        Button submitButton =(Button)findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() { //Temporary
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ProfilePage.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_in,R.anim.static_animation);
            }
        });

        //Intent for Create Account and Continue as Guest
        TextView create = (TextView) findViewById(R.id.createAccount);
        TextView guest = (TextView) findViewById(R.id.continueGuest);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CreateAccount.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_in,R.anim.static_animation);
            }
        });
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeFeed.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_in,R.anim.static_animation);
            }
        });
    }
}