package com.example.gigver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;

import models.Post;
import models.User;
import utils.ServerEvent;
import utils.ServerManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ServerManager server = new ServerManager("https://gigver-server.onrender.com");

        // Gets Users
        server.GetUsers(new ServerEvent<List<User>>() {
            @Override
            public void OnComplete(List<User> result) {
                for (User user : result) {
                    System.out.println("Hello User, " + user.GetName());
                }
            }

            @Override
            public void OnFailure(String errorMessage) {
                System.out.println(errorMessage);
            }
        });

        // Gets Posts
        server.GetPosts(new ServerEvent<List<Post>>() {
            @Override
            public void OnComplete(List<Post> result) {
                for (Post post : result) {
                    System.out.println("Theres a post about: " + post.GetSubject());
                }
            }

            @Override
            public void OnFailure(String errorMessage) {
                System.out.println(errorMessage);
            }
        });
    }
}