package com.example.gigver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.gigver.fragments.CreatePostFragment;
import com.example.gigver.fragments.HomeFeedFragment;
import com.example.gigver.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainView extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    BottomNavigationView bottomNav;

    CreatePostFragment createPostFrag = new CreatePostFragment();
    ProfileFragment profileFrag = new ProfileFragment();
    HomeFeedFragment homeFeedFrag = new HomeFeedFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        bottomNav
                = findViewById(R.id.bottomNavigationView);

        bottomNav.setOnItemSelectedListener(this);
        bottomNav.setSelectedItemId(R.id.homeButton);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.homeButton) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, homeFeedFrag)
                    .commit();
            return true;
        } else if (id == R.id.postButton) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, createPostFrag)
                    .commit();
            return true;
        } else if (id == R.id.profileButton) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, profileFrag)
                    .commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void ChangeView(int id) {
        if (id == R.id.homeButton) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, homeFeedFrag)
                    .commit();
        } else if (id == R.id.postButton) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, createPostFrag)
                    .commit();
        } else if (id == R.id.profileButton) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, profileFrag)
                    .commit();
        }
    }
}