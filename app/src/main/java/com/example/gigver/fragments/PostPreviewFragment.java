package com.example.gigver.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gigver.PostPreview;
import com.example.gigver.R;

import models.Post;
import models.User;

public class PostPreviewFragment extends Fragment {
    private User poster;
    private Post post;

    public PostPreviewFragment() {
        // Required empty public constructor
    }

    public PostPreviewFragment(User poster, Post post) {
        this.poster = poster;
        this.post = post;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_post_preview, container, false);

        System.out.println("Post Subject: " + post.GetSubject());
        System.out.println("From User: " + poster.GetName());

        return rootView;
    }
}