package com.example.gigver.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gigver.MainView;
import com.example.gigver.R;

import models.Post;
import models.User;
import utils.IServerEvent;
import utils.LoadingDialog;
import utils.ServerManager;

public class CreatePostFragment extends Fragment {
    public CreatePostFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_create_post, container, false);

        EditText titleInput = (EditText) rootView.findViewById(R.id.titlePost);
        EditText rewardInput = (EditText) rootView.findViewById(R.id.rewardPost);
        EditText descriptionInput = (EditText) rootView.findViewById(R.id.descriptionPost);

        rootView.findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Post newPost = new Post(User.currentUser.GetID(), titleInput.getText().toString(), descriptionInput.getText().toString(), rewardInput.getText().toString(), false);

                ServerManager server = new ServerManager("https://gigver-server.onrender.com");
                LoadingDialog loadingDialog = new LoadingDialog(getActivity());

                loadingDialog.StartLoading();
                server.AddPost(newPost, new IServerEvent<Post>() {
                    @Override
                    public void OnComplete(Post result) {
                        System.out.println("Clearing Text");
                        loadingDialog.DismissDialog();

                        titleInput.getText().clear();
                        rewardInput.getText().clear();
                        descriptionInput.getText().clear();

                        System.out.println("Change View");

                        MainView parent = (MainView) getActivity();
                        parent.ChangeView(R.id.homeButton);
                    }

                    @Override
                    public void OnFailure(String errorMessage) {
                        loadingDialog.DismissDialog();

                        System.out.println(errorMessage);
                    }
                });
            }
        });

        return rootView;
    }
}