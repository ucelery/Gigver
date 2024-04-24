package com.example.gigver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import models.Post;
import models.User;
import utils.IServerEvent;
import utils.ServerManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Added a temporary Intent object so that it can run the 2nd page (Profile Page)
        //Subject to change
        Button submitButton =(Button)findViewById(R.id.submitButton);
        TextView create = (TextView) findViewById(R.id.createAccount);
        TextView enterEmail = (TextView) findViewById(R.id.editEmail);
        TextView passwordTextView = findViewById(R.id.passwordTextView);
        EditText passwordEditText = findViewById(R.id.passwordEditText); //This is set to invisible, this is where the user types his/her password
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ProfilePage.class);
                startActivity(intent);
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CreateAccount.class);
                startActivity(intent);
            }
        });
        //Implements a listener that if the email TextView is clicked, automatically deletes the word Email
        enterEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && enterEmail.getText().toString().equals("Email")) {
                    enterEmail.setText("");
                }
            }
        });
        //Implements a listener that if the password TextView is clicked, automatically deletes the word password
        passwordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide the TextView
                passwordTextView.setVisibility(View.INVISIBLE);
                // Show the EditText
                passwordEditText.setVisibility(View.VISIBLE);
                // Request focus on the EditText
                passwordEditText.requestFocus();
            }
        });
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                // If the EditText is empty, switch back to TextView
                if (s.length() == 0) {
                    passwordEditText.setVisibility(View.INVISIBLE);
                    passwordTextView.setVisibility(View.VISIBLE);
                    passwordTextView.setText("Password");
                }
            }
        });
    }
}