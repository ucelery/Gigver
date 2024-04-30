package com.example.gigver.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gigver.adapter.CustomListViewAdapter;
import com.example.gigver.PostPreview;
import com.example.gigver.R;

import java.util.ArrayList;
import java.util.List;

import models.Post;
import models.User;
import utils.IServerEvent;
import utils.LoadingDialog;
import utils.ServerManager;

public class HomeFeedFragment extends Fragment {
    ListView listView;

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

        ServerManager server = new ServerManager("https://gigver-server.onrender.com");

        LoadingDialog loadingDialog = new LoadingDialog(getActivity());

        loadingDialog.StartLoading();
        server.GetPosts(new IServerEvent<List<Post>>() {
            @Override
            public void OnComplete(List<Post> result) {
                loadingDialog.DismissDialog();
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Intents for selecting Post page and Profile Page
                        listView = (ListView) rootView.findViewById(R.id.listView);
                        CustomListViewAdapter adapter = new CustomListViewAdapter(requireContext().getApplicationContext(), new ArrayList<Post>(result));
                        listView.setAdapter(adapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Post clickedPost = (Post) parent.getItemAtPosition(position);

                                // Go to a post view
                                Intent postIntent = new Intent(requireContext().getApplicationContext(), PostPreview.class);

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
                loadingDialog.DismissDialog();
            }
        });

        return rootView;
    }
}