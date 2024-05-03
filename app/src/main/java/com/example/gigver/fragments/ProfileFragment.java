package com.example.gigver.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.gigver.R;
import com.example.gigver.adapter.CustomListViewAdapter;

import java.util.ArrayList;
import java.util.List;

import models.Post;
import models.User;
import utils.IServerEvent;
import utils.LoadingDialog;
import utils.RateDialog;
import utils.ServerManager;

public class ProfileFragment extends Fragment {
    public static final String ARG_OBJECT = "object";

    private User user;
    private ArrayList<Post> posts;

    public ProfileFragment() { }

    public ProfileFragment(User user) {
        this.user = user;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        //Intents for selecting Home page and Post page
        TextView profileName = (TextView) rootView.findViewById(R.id.userName);
        TextView address = (TextView) rootView.findViewById(R.id.addressProfile);
        TextView email = (TextView) rootView.findViewById(R.id.emailaddressProfile);
        TextView mobile = (TextView) rootView.findViewById(R.id.mobileNumberProfile);
        TextView telephone = (TextView) rootView.findViewById(R.id.telephoneNumberProfile);

        ListView listView = (ListView) rootView.findViewById(R.id.listView);

        ImageButton rateButton = rootView.findViewById(R.id.rateButton);

        // Disable Rate Initially
        rateButton.setVisibility(View.GONE);

        InitializeRateButton(rateButton);

        if (user != null) {
            // Enable Rating Button for Other users
            if (!user.GetID().equals(User.currentUser.GetID()))
                rateButton.setVisibility(View.VISIBLE);
            else rateButton.setVisibility(View.GONE);

            profileName.setText(user.GetName());
            email.setText(user.GetEmail());
            mobile.setText(user.GetMobileNo());
            address.setText(user.GetAddress());
            telephone.setText(user.GetTelephoneNo());

            ServerManager server = new ServerManager("https://gigver-server.onrender.com");

            LoadingDialog loadingDialog = new LoadingDialog(getActivity());

            loadingDialog.StartLoading();

            server.GetPosts(new IServerEvent<List<Post>>() {
                @Override
                public void OnComplete(List<Post> result) {
                    loadingDialog.DismissDialog();

                    ArrayList<Post> myPosts = new ArrayList<>();

                    for (Post post : result) {
                        if (post.GetPosterID().equals(user.GetID())) {
                            myPosts.add(post);
                        }
                    }

                    requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Intents for selecting Post page and Profile Page
                            CustomListViewAdapter adapter = new CustomListViewAdapter(requireContext().getApplicationContext(), myPosts);
                            listView.setAdapter(adapter);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Post clickedPost = (Post) parent.getItemAtPosition(position);

                                    server.GetUsers(new IServerEvent<List<User>>() {
                                        @Override
                                        public void OnComplete(List<User> result) {
                                            // Get the poster user data
                                            for (User user : result) {
                                                System.out.println(user.GetID() + " == " + clickedPost.GetPosterID());
                                                if (user.GetID().equals(clickedPost.GetPosterID())) {
                                                    // Go to a post view
                                                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                                                    transaction.replace(R.id.flFragment, new PostPreviewFragment(user, clickedPost));
                                                    transaction.commit();
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
                    loadingDialog.DismissDialog();
                }
            });
        } else {
            profileName.setText("Guest");
        }

        ScrollView scrollview = rootView.findViewById(R.id.scrollView);
        // ListView inside scrollview
        listView.setOnTouchListener((v, event) -> {
            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    // Disallow ScrollView to intercept touch events.
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                case MotionEvent.ACTION_UP:
                    // Allow ScrollView to intercept touch events.
                    v.getParent().requestDisallowInterceptTouchEvent(false);
                    break;
            }

            // Handle ListView touch events.
            v.onTouchEvent(event);
            return true;
        });

        return rootView;
    }

    private void InitializeRateButton(ImageButton rateButton) {
        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RateDialog rateDialog = new RateDialog(getActivity());
                rateDialog.ShowDialog(User.currentUser, user);
            }
        });
    }
}