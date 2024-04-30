package com.example.gigver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import models.Post;
import models.User;
import utils.IServerEvent;
import utils.LoadingDialog;
import utils.ServerManager;

public class MainActivity extends AppCompatActivity {

    String eMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ServerManager server = new ServerManager("https://gigver-server.onrender.com");

        //Login Button main function
        Button submitButton =(Button)findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailEditText = findViewById(R.id.editEmail);
                EditText passwordEditText = findViewById(R.id.passwordEditText);
                String emailString = emailEditText.getText().toString();
                String passwordString = passwordEditText.getText().toString();
                eMail = emailEditText.getText().toString();

                LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this);
                loadingDialog.StartLoading();
                server.GetUsers(new IServerEvent<List<User>>() {
                    @Override
                    public void OnComplete(List<User> result) {
                        loadingDialog.DismissDialog();

                        boolean existingUser = false;
                        for(User user : result){
                            // Log the user in when inputted credentials are correct
                            if(user.GetEmail().equals(emailString) && user.GetPassword().equals(passwordString)){
                                Intent intent = new Intent(getApplicationContext(), MainView.class);
                                startActivity(intent);

                                existingUser = true;
                                User.currentUser = user;
                                break;
                            }
                        }

                        // Notify the user if user isn't found
                        if (!existingUser) {
                            // No user found with provided email and password, display error message
                            Snackbar.make(findViewById(android.R.id.content), "Invalid email or password", Snackbar.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void OnFailure(String errorMessage) {
                        loadingDialog.DismissDialog();
                    }
                });
            }
        });

        Animation emailInput = AnimationUtils.loadAnimation(this,R.anim.slide_from_bottom);
        TextInputLayout email = (TextInputLayout) findViewById(R.id.emailTextInputLayout);
        ImageView iconEmail = (ImageView) findViewById(R.id.imageView5);
        email.startAnimation(emailInput);
        iconEmail.startAnimation(emailInput);

        Animation passwordInput = AnimationUtils.loadAnimation(this,R.anim.slide_from_bottom_emaillayout);
        TextInputLayout password = (TextInputLayout) findViewById(R.id.passwordTextInputLayout);
        ImageView passwordIcon = (ImageView) findViewById(R.id.imageView);
        password.startAnimation(passwordInput);
        passwordIcon.startAnimation(passwordInput);
    }
}