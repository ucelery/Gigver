package com.example.gigver;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Animation slide = AnimationUtils.loadAnimation(this,R.anim.slide_from_bottom);

        TextView textView = (TextView) findViewById(R.id.textviewCreate);
        textView.startAnimation(slide);

        TextInputLayout userNameInput = (TextInputLayout) findViewById(R.id.usernameAddTextInputLayout);
        ImageView userIcon = (ImageView) findViewById(R.id.userimageicon);
        userNameInput.startAnimation(slide);
        userIcon.startAnimation(slide);

        TextInputLayout emailAddInput = (TextInputLayout) findViewById(R.id.emailAddTextInputLayout);
        ImageView emailIcon = (ImageView) findViewById(R.id.imageView5);
        emailAddInput.startAnimation(slide);
        emailIcon.startAnimation(slide);

        TextInputLayout passwordAddInput = (TextInputLayout) findViewById(R.id.passwordAddTextInputLayout);
        ImageView passwordAddIcon = (ImageView) findViewById(R.id.imageView);
        passwordAddInput.startAnimation(slide);
        passwordAddIcon.startAnimation(slide);

        TextInputLayout confirmPasswordInput = (TextInputLayout) findViewById(R.id.passwordConfirmTextInputLayout);
        ImageView passwordConfirmIcon = (ImageView) findViewById(R.id.confirmimageView);
        confirmPasswordInput.startAnimation(slide);
        passwordConfirmIcon.startAnimation(slide);

        Button createButton = (Button) findViewById(R.id.submitButton);
        createButton.startAnimation(slide);
    }
}