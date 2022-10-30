package com.example.sweetreads.sign_in_sign_up;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.sweetreads.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class SignInSignUpActivity extends AppCompatActivity {

    private String[] PERMISSIONS;

    private boolean hasPermissions(Context context, String... PERMISSIONS) {

        if (context != null && PERMISSIONS != null)
            for (String permission: PERMISSIONS)
                if (ActivityCompat.checkSelfPermission(context,permission) != PackageManager.PERMISSION_GRANTED)
                    return false;

        return true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_sign_up);

        TabLayout tabLayout = findViewById(R.id.tabs_);
        ViewPager2 viewPager2 = findViewById(R.id.view_pager_);

        SignInSignUpViewPageAdapter adapter = new SignInSignUpViewPageAdapter(this);
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        if(position == 0)
                            tab.setText("SignIn");
                        else if(position == 1)
                            tab.setText("SignUp");
                    }
                }).attach();

        PERMISSIONS = new String[] {

                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        };

        if (!hasPermissions(SignInSignUpActivity.this,PERMISSIONS))
            ActivityCompat.requestPermissions(SignInSignUpActivity.this,PERMISSIONS,101);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 101) {

            if (!(grantResults[0] == PackageManager.PERMISSION_GRANTED))
                startActivity(new Intent(getApplicationContext(),SignInSignUpActivity.class));

            if (!(grantResults[1] == PackageManager.PERMISSION_GRANTED))
                startActivity(new Intent(getApplicationContext(),SignInSignUpActivity.class));

        }
    }

    @Override
    public void onBackPressed () {

    }

}