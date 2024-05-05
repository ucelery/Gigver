package com.example.gigver.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gigver.R;

import models.Post;
import models.User;
import utils.ConfirmDialog;
import utils.IServerEvent;
import utils.RateDialog;
import utils.ServerManager;

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

        ImageButton posterProfile = (ImageButton) rootView.findViewById(R.id.posterProfile);
        ImageButton completeButton = (ImageButton) rootView.findViewById(R.id.completeButton);

        TextView subject = (TextView) rootView.findViewById(R.id.gigSubjectLabel);
        TextView details = (TextView) rootView.findViewById(R.id.gigDetails);
        TextView gigRewards = (TextView) rootView.findViewById(R.id.gigRewards);

        subject.setText(post.GetSubject());
        details.setText(post.GetDescription());
        gigRewards.setText(post.GetRewards());

        if (User.currentUser.GetID().equals(poster.GetID())) {
            completeButton.setVisibility(View.VISIBLE);
            posterProfile.setVisibility(View.GONE);
        }
        else {
            completeButton.setVisibility(View.GONE);
            posterProfile.setVisibility(View.VISIBLE);
        }


        ServerManager server = new ServerManager("https://gigver-server.onrender.com");

        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Server implementation of completing of post
                Runnable completeRunnable = new Runnable() {
                    @Override
                    public void run() {
                        server.CompletePost(post.GetID(), new IServerEvent<String>() {
                            @Override
                            public void OnComplete(String result) {
                                System.out.println("Is this thing working");
                                MakeToast(requireActivity(), "Post Completed!");

                                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                                transaction.replace(R.id.flFragment, new HomeFeedFragment());
                                transaction.commit();
                            }

                            @Override
                            public void OnFailure(String errorMessage) {
                                MakeToast(requireActivity(), "Something unexpected happened.");

                                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                                transaction.replace(R.id.flFragment, new HomeFeedFragment());
                                transaction.commit();
                            }
                        });
                    }
                };

                ConfirmDialog confirmDialog = new ConfirmDialog(requireActivity(), completeRunnable);
                confirmDialog.ShowDialog();
            }
        });

        posterProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.flFragment, new ProfileFragment(poster));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return rootView;
    }

    private void MakeToast(Activity activity, String message) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}