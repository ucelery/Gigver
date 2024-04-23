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

        // Gets Users
        server.GetUsers(new IServerEvent<List<User>>() {
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
        server.GetPosts(new IServerEvent<List<Post>>() {
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

        // Add User
        User newUser = new User("Jhob", "jabalos@mymail.mapua.edu.ph", "Some Random Password", "123 Ordinary St. Madeup City", "09394567777", "02 87000");

        server.AddUser(newUser,
                new IServerEvent<User>() {
            @Override
            public void OnComplete(User result) {
                System.out.println("Newly Added User: " + result.GetID());
            }

            @Override
            public void OnFailure(String errorMessage) {
                System.out.println(errorMessage);
            }
        });

        // Add Post
        Post newPost = new Post("662604696f7ea20e4cd6b23e", "Delivery Nearby", "Some stuff needed to be delivered by foot", "Monetary");

        server.AddPost(newPost,
                new IServerEvent<Post>() {
                    @Override
                    public void OnComplete(Post result) {
                        System.out.println("Newly Added Post: " + result.GetID());
                    }

                    @Override
                    public void OnFailure(String errorMessage) {
                        System.out.println(errorMessage);
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