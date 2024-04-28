package com.example.gigver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import models.User;
import utils.IServerEvent;
import utils.ServerManager;

public class CreateAccountContinue extends AppCompatActivity {

    String addressFinal, mobNum, telNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_continue);

        ServerManager server = new ServerManager("https://gigver-server.onrender.com");

        Animation addressAnim = AnimationUtils.loadAnimation(this,R.anim.slide_from_bottom);
        Animation mobileAnim = AnimationUtils.loadAnimation(this,R.anim.slide_from_bottom_emaillayout);
        Animation telephoneAnim = AnimationUtils.loadAnimation(this,R.anim.slide_from_bottom_passwordadd);

        TextInputLayout address = (TextInputLayout) findViewById(R.id.addressAddTextInputLayout);
        ImageView addressIcon = (ImageView) findViewById(R.id.imageViewAddress);
        address.startAnimation(addressAnim);
        addressIcon.startAnimation(addressAnim);

        TextInputLayout mobile = (TextInputLayout) findViewById(R.id.mobileAddTextInputLayout);
        ImageView mobileIcon = (ImageView) findViewById(R.id.imageViewMobileNum);
        mobile.startAnimation(mobileAnim);
        mobileIcon.startAnimation(mobileAnim);

        TextInputLayout telephone = (TextInputLayout) findViewById(R.id.telephoneAddTextInputLayout);
        ImageView telephoneIcon = (ImageView) findViewById(R.id.imageViewTelephoneNum);
        telephone.startAnimation(telephoneAnim);
        telephoneIcon.startAnimation(telephoneAnim);

        String userName = getIntent().getExtras().getString("uName");
        String emailAddress = getIntent().getExtras().getString("eAdd");
        String password = getIntent().getExtras().getString("pAdd");

        Button create = (Button) findViewById(R.id.submitButton);
        EditText finalAddress = (EditText) findViewById(R.id.addAddress);
        EditText mobileAdd = (EditText) findViewById(R.id.addMobile);
        EditText telAdd = (EditText) findViewById(R.id.addTelephone);
        ImageView back = (ImageView) findViewById(R.id.backArrow);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CreateAccount.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_to_left, R.anim.slide_to_right);
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressFinal = finalAddress.getText().toString();
                mobNum = mobileAdd.getText().toString();
                telNum = telAdd.getText().toString();

                if(addressFinal.isEmpty()){
                    Snackbar.make(findViewById(android.R.id.content), "Please enter an Address", Snackbar.LENGTH_SHORT).show();
                    return;
                }else if(mobNum.isEmpty()){
                    Snackbar.make(findViewById(android.R.id.content), "Please enter a Mobile Number", Snackbar.LENGTH_SHORT).show();
                    return;
                }else if(telNum.isEmpty()){
                    Snackbar.make(findViewById(android.R.id.content), "Please enter a Telephone Number", Snackbar.LENGTH_SHORT).show();
                    return;
                }else{
                    User newUser = new User(userName,emailAddress,password,addressFinal,mobNum,telNum);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.zoom_in,R.anim.static_animation);
                    Snackbar.make(findViewById(android.R.id.content), "Account has been created", Snackbar.LENGTH_SHORT).show();
                    server.AddUser(newUser, new IServerEvent<User>() {
                        @Override
                        public void OnComplete(User result) {
                        }
                        @Override
                        public void OnFailure(String errorMessage) {

                        }
                    });
                }
            }
        });
    }
}