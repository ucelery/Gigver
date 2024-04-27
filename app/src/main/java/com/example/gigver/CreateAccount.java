package com.example.gigver;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class CreateAccount extends AppCompatActivity {

    String uName, eAdd, pAdd, pConf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        Animation slide = AnimationUtils.loadAnimation(this,R.anim.slide_from_bottom);
        Animation emailSlide = AnimationUtils.loadAnimation(this, R.anim.slide_from_bottom_emaillayout);
        Animation passwordAddSlide = AnimationUtils.loadAnimation(this,R.anim.slide_from_bottom_passwordadd);
        Animation passwordConfirmSlide = AnimationUtils.loadAnimation(this,R.anim.slide_from_bottom_confirmpass);
        Animation createSlide = AnimationUtils.loadAnimation(this,R.anim.slide_from_bottom_createbutton);

        TextView textView = (TextView) findViewById(R.id.textviewCreate);
        textView.startAnimation(slide);

        TextInputLayout userNameInput = (TextInputLayout) findViewById(R.id.usernameAddTextInputLayout);
        ImageView userIcon = (ImageView) findViewById(R.id.userimageicon);
        userNameInput.startAnimation(slide);
        userIcon.startAnimation(slide);

        TextInputLayout emailAddInput = (TextInputLayout) findViewById(R.id.emailAddTextInputLayout);
        ImageView emailIcon = (ImageView) findViewById(R.id.imageView5);
        emailAddInput.startAnimation(emailSlide);
        emailIcon.startAnimation(emailSlide);

        TextInputLayout passwordAddInput = (TextInputLayout) findViewById(R.id.passwordAddTextInputLayout);
        ImageView passwordAddIcon = (ImageView) findViewById(R.id.imageView);
        passwordAddInput.startAnimation(passwordAddSlide);
        passwordAddIcon.startAnimation(passwordAddSlide);

        TextInputLayout confirmPasswordInput = (TextInputLayout) findViewById(R.id.passwordConfirmTextInputLayout);
        ImageView passwordConfirmIcon = (ImageView) findViewById(R.id.confirmimageView);
        confirmPasswordInput.startAnimation(passwordConfirmSlide);
        passwordConfirmIcon.startAnimation(passwordConfirmSlide);

        Button continueButton = (Button) findViewById(R.id.submitButton);
        continueButton.startAnimation(createSlide);

        EditText userName = (EditText) findViewById(R.id.addUsername);
        EditText emailAdd = (EditText) findViewById(R.id.addEmail);
        EditText passwordAdd = (EditText) findViewById(R.id.addPassword);
        EditText passwordConfirm = (EditText) findViewById(R.id.confirmPassword);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uName = userName.getText().toString();
                eAdd = emailAdd.getText().toString();
                pAdd = passwordAdd.getText().toString();
                pConf = passwordConfirm.getText().toString();
                if(!emailValidator(eAdd)){
                    Snackbar.make(findViewById(android.R.id.content), "Invalid email address", Snackbar.LENGTH_SHORT).show();
                    return; // Stop further execution
                };
                if(uName.isEmpty()){
                    Snackbar.make(findViewById(android.R.id.content), "Please enter a username", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (pAdd.isEmpty() || pConf.isEmpty()) {
                    Snackbar.make(findViewById(android.R.id.content), "Please enter a password", Snackbar.LENGTH_SHORT).show();
                } else if (pAdd.equals(pConf)) {
                    Intent intent = new Intent(getApplicationContext(), CreateAccountContinue.class);
                     intent.putExtra("uName",uName);
                     intent.putExtra("eAdd",eAdd);
                     intent.putExtra("pAdd",pAdd);
                    startActivity(intent);
                    overridePendingTransition(R.anim.static_animation, R.anim.static_animation);
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "Password did not match!", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean emailValidator(String email){
        if(!email.isEmpty()&& Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return true;
        }else{
            return false;
        }
    }
}