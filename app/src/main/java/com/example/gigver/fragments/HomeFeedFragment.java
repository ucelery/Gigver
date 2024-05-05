package com.example.gigver.fragments;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.gigver.adapter.CustomListViewAdapter;
import com.example.gigver.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.Post;
import models.User;
import utils.ClickOnSideTouchListener;
import utils.IServerEvent;
import utils.LoadingDialog;
import utils.ServerManager;

public class HomeFeedFragment extends Fragment {
    ViewFlipper viewFlipper;
    private static final int SWIPE_THRESHOLD = 1; // Adjust as needed
    private static final int SWIPE_VELOCITY_THRESHOLD = 1; // Adjust as needed

    public HomeFeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home_feed, container, false);

        viewFlipper = rootView.findViewById(R.id.viewFlipper);
        Button nextBtn = rootView.findViewById(R.id.nextButton);
        Button prevBtn = rootView.findViewById(R.id.prevButton);

        ServerManager server = new ServerManager("https://gigver-server.onrender.com");

        LoadingDialog loadingDialog = new LoadingDialog(getActivity());

        loadingDialog.StartLoading();

        InitializeNavButtons(nextBtn, prevBtn);

        server.GetPosts(new IServerEvent<List<Post>>() {
            @Override
            public void OnComplete(List<Post> result) {
                loadingDialog.DismissDialog();
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        PopulateFlipper(result);
                    }
                });
            }

            @Override
            public void OnFailure(String errorMessage) {
                // Error loading posts
                loadingDialog.DismissDialog();
            }
        });

        return rootView;
    }

    private void InitializeNavButtons(Button nextBtn, Button prevBtn) {
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showNext();
                System.out.println("Next");
            }
        });

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showPrevious();
                System.out.println("Prev");
            }
        });
    }

    private void PopulateFlipper(List<Post> posts) {
        for (Post post : posts) {
            User poster = GetPoster(post);
            View newView = getLayoutInflater().inflate(R.layout.home_post_container, null);
            TextView postSubject = newView.findViewById(R.id.postSubject);
            TextView postDesc = newView.findViewById(R.id.postDesc);

            ImageView bannerImage = newView.findViewById(R.id.bannerImage);
            bannerImage.setImageResource(GetRandomImage());

            postSubject.setText(post.GetSubject());
            postDesc.setText(post.GetDescription());

            newView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Clicked on a view");

                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.flFragment, new PostPreviewFragment(poster, post));
                    transaction.addToBackStack(null);
                    transaction.commit();

                }
            });

            viewFlipper.addView(newView);
        }
    }

    private User GetPoster(Post post) {
        final User[] poster = {null};
        ServerManager server = new ServerManager("https://gigver-server.onrender.com");
        server.GetUsers(new IServerEvent<List<User>>() {
            @Override
            public void OnComplete(List<User> result) {
                // Get the poster user data
                for (User user : result) {
                    System.out.println(user.GetID() + " == " + post.GetPosterID());
                    if (user.GetID().equals(post.GetPosterID())) {
                        // Go to a post view
                        poster[0] = user;
                    }
                }
            }

            @Override
            public void OnFailure(String errorMessage) {}
        });

        return poster[0];
    }

    private int GetRandomImage() {
        int randomImage = 0;

        Random rand = new Random();
        int randomNumber = rand.nextInt(7) + 1;

        switch(randomNumber) {
            case 1:
                randomImage = R.drawable.bg_image1;
                break;
            case 2:
                randomImage = R.drawable.bg_image2;
                break;
            case 3:
                randomImage = R.drawable.bg_image3;
                break;
            case 4:
                randomImage = R.drawable.bg_image4;
                break;
            case 5:
                randomImage = R.drawable.bg_image5;
                break;
            case 6:
                randomImage = R.drawable.bg_image6;
                break;
            case 7:
                randomImage = R.drawable.bg_image7;
                break;
        }

        return randomImage;
    }
}