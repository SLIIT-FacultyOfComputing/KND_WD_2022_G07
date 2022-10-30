package com.example.sweetreads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.sweetreads.sign_in_sign_up.SignInSignUpActivity;

public class MainActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = new Intent(getApplicationContext(), SignInSignUpActivity.class);
        startActivity(i);

    }

    @Override
    public void onBackPressed () {

    }

}