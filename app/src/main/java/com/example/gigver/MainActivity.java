package com.example.gigver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        ServerManager server = new ServerManager("https://gigver-server.onrender.com");

        server.GetUsers(new IServerEvent<List<User>>() {
            @Override
            public void OnComplete(List<User> result) {
                for (User user : result) {
                    if (user.GetEmail().equals("jebautista@mymail.mapua.edu.ph") && user.GetPassword().equals(""))
                    System.out.println("Username: " + user.GetID());
                    System.out.println("Email: " + user.GetEmail());
                    System.out.println("Password: " + user.GetPassword());
                }
            }

            @Override
            public void OnFailure(String errorMessage) {

            }
        });

        //Added a temporary Intent object so that it can run the 2nd page (Profile Page)
        //Subject to change
        Button submitButton =(Button)findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ProfilePage.class);
                startActivity(intent);
            }
        });
    }
}