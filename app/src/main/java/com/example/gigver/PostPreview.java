package com.example.gigver;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.gigver.adapter.PagerAdapter;
import com.example.gigver.fragments.PostViewFragment;
import com.example.gigver.fragments.ProfileFragment;

import java.sql.Array;
import java.util.ArrayList;

import models.Post;
import models.User;

public class PostPreview extends AppCompatActivity {
    private Post post;
    private User poster;

    private PostViewFragment postView = new PostViewFragment();
    private ProfileFragment profileView = new ProfileFragment();

    private PagerAdapter pagerAdapter;
    private ViewPager2 pager;

    public PostPreview() {}

    public PostPreview(User user, Post post) {
        this.poster = user;
        this.post = post;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_preview);

        pagerAdapter = new PagerAdapter(profileView);
        pager = findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);

//        postView = new PostViewFragment(post);
//        profileView = new ProfileFragment(poster);
//
//        ArrayList<Fragment> fragments = new ArrayList<>();
//        fragments.add(profileView);
//        fragments.add(postView);

//        pagerAdapter = new PagerAdapter(fragments);
//        pager = (ViewPager2) findViewById(R.id.pager);
//        pager.setAdapter(pagerAdapter);
    }
}
