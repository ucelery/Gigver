package com.example.gigver.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gigver.R;

import models.User;

public class ProfileFragment extends Fragment {
    public ProfileFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        //Intents for selecting Home page and Post page
        TextView profileName = (TextView) rootView.findViewById(R.id.userName);
        TextView address = (TextView) rootView.findViewById(R.id.addressProfile);
        TextView email = (TextView) rootView.findViewById(R.id.emailaddressProfile);
        TextView mobile = (TextView) rootView.findViewById(R.id.mobileNumberProfile);
        TextView telephone = (TextView) rootView.findViewById(R.id.telephoneNumberProfile);

        if (User.currentUser != null) {
            profileName.setText(User.currentUser.GetName());
            email.setText(User.currentUser.GetEmail());
            mobile.setText(User.currentUser.GetMobileNo());
            address.setText(User.currentUser.GetAddress());
            telephone.setText(User.currentUser.GetTelephoneNo());
        } else {
            profileName.setText("Guest");
        }

        return rootView;
    }
}