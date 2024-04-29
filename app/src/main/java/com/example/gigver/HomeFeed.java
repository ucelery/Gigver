package com.example.gigver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import models.Post;
import models.User;
import utils.IServerEvent;
import utils.ServerManager;

public class HomeFeed extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_feed);

        ServerManager server = new ServerManager("https://gigver-server.onrender.com");

        server.GetPosts(new IServerEvent<List<Post>>() {
            @Override
            public void OnComplete(List<Post> result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Intents for selecting Post page and Profile Page
                        listView = (ListView) findViewById(R.id.listView);
                        CustomListViewAdapter adapter = new CustomListViewAdapter(getApplicationContext(), new ArrayList<Post>(result));
                        listView.setAdapter(adapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Post clickedPost = (Post) parent.getItemAtPosition(position);

                                // Go to a post view
                                Intent postIntent = new Intent(getApplicationContext(), );
                            }
                        });
                    }
                });
            }

            @Override
            public void OnFailure(String errorMessage) {
                // Error loading posts
            }
        });

        InitializeButtons();
    }

    private void InitializeButtons() {
        // Different On Click implementation if the user is logged in or not
        ImageView selectPost = (ImageView) findViewById(R.id.postButton);
        ImageView selectMe = (ImageView) findViewById(R.id.profileButton);

        if (User.currentUser == null){
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
        } else {
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