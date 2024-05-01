package com.example.gigver.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gigver.R;

import models.Post;

public class PostViewFragment extends Fragment {
    Post post;
    ViewPager viewPager;

    public PostViewFragment() { }

    public PostViewFragment(Post post) {
        this.post = post;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_post_view, container, false);

        TextView subject = (TextView) rootView.findViewById(R.id.gigSubjectLabel);
        TextView details = (TextView) rootView.findViewById(R.id.gigDetails);
        TextView rewards = (TextView) rootView.findViewById(R.id.gigRewards);

        subject.setText(post.GetSubject());
        details.setText(post.GetDescription());
        rewards.setText(post.GetRewards());

        return rootView;
    }
}