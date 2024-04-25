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

        //Added a temporary Intent object so that it can run the 2nd page (Profile Page)
        //Subject to change
        Button submitButton =(Button)findViewById(R.id.submitButton);
        TextView create = (TextView) findViewById(R.id.createAccount);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ProfilePage.class);
                startActivity(intent);
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CreateAccount.class);
                startActivity(intent);
            }
        });
    }
}