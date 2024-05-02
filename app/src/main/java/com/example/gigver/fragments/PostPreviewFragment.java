package com.example.gigver.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.gigver.R;

import models.Post;
import models.User;
import utils.RateDialog;

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

        ImageButton rateButton = (ImageButton) rootView.findViewById(R.id.rateButton);
        ImageButton completeButton = (ImageButton) rootView.findViewById(R.id.completeButton);

        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Server implementation of completing of post
            }
        });

        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RateDialog rate = new RateDialog(requireActivity());
                rate.ShowDialog(User.currentUser, poster);
            }
        });

        return rootView;
    }
}