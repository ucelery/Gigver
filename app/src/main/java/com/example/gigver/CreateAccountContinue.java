package com.example.gigver;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class CreateAccountContinue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_continue);

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
        String passwordConfirm = getIntent().getExtras().getString("pConf");
    }
}