package com.example.gigver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
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

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigatin_view);
        bottomNavigationView.setSelectedItemId(R.id.homeButton);

        if(User.currentUser == null){
            bottomNavigationView.findViewById(R.id.profileButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(findViewById(android.R.id.content), "Please Login", Snackbar.LENGTH_SHORT).show();
                }
            });
            bottomNavigationView.findViewById(R.id.postButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(findViewById(android.R.id.content), "Please Login to Create Post", Snackbar.LENGTH_SHORT).show();
                }
            });
        }else{
            bottomNavigationView.findViewById(R.id.profileButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeFeed.this, ProfilePage.class));
                    overridePendingTransition(R.anim.static_animation,R.anim.static_animation);
                }
            });
            bottomNavigationView.findViewById(R.id.postButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeFeed.this, CreatePost.class));
                    overridePendingTransition(R.anim.static_animation,R.anim.static_animation);
                }
            });
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
                                    Intent postIntent = new Intent(getApplicationContext(), PostPreview.class);

                                    server.GetUsers(new IServerEvent<List<User>>() {
                                        @Override
                                        public void OnComplete(List<User> result) {
                                            // Get the poster user data
                                            for (User user : result) {
                                                System.out.println(user.GetID() + " == " + clickedPost.GetPosterID());
                                                if (user.GetID().equals(clickedPost.GetPosterID())) {
                                                    postIntent.putExtra("name", user.GetName());
                                                    postIntent.putExtra("email", user.GetEmail());
                                                    postIntent.putExtra("mobile", user.GetMobileNo());
                                                    postIntent.putExtra("telephone", user.GetTelephoneNo());
                                                    postIntent.putExtra("rating", "n/a");

                                                    postIntent.putExtra("gig_desc", clickedPost.GetDescription());
                                                    postIntent.putExtra("gig_rewards", clickedPost.GetRewards());
                                                    postIntent.putExtra("gig_subject", clickedPost.GetSubject());

                                                    startActivity(postIntent);
                                                }
                                            }
                                        }

                                        @Override
                                        public void OnFailure(String errorMessage) {

                                        }
                                    });
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
        }
    }
}