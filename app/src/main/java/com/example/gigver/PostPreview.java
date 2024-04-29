package com.example.gigver;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PostPreview extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_preview);

        TextView subject = (TextView) findViewById(R.id.gigSubjectLabel);
        TextView details = (TextView) findViewById(R.id.gigDetails);
        TextView rewards = (TextView) findViewById(R.id.gigRewards);

        TextView name = (TextView) findViewById(R.id.nameLabel);
        TextView email = (TextView) findViewById(R.id.emailLabel);
        TextView mobile = (TextView) findViewById(R.id.mobileLabel);
        TextView telephone = (TextView) findViewById(R.id.telephoneLabel);
        TextView rating = (TextView) findViewById(R.id.ratingLabel);

        subject.setText(getIntent().getExtras().getString("gig_subject"));
        details.setText(getIntent().getExtras().getString("gig_desc"));
        rewards.setText(getIntent().getExtras().getString("gig_rewards"));

        name.setText(getIntent().getExtras().getString("name"));
        email.setText(getIntent().getExtras().getString("email"));
        mobile.setText(getIntent().getExtras().getString("mobile"));
        telephone.setText(getIntent().getExtras().getString("telephone"));
        rating.setText(getIntent().getExtras().getString("rating"));
    }
}
