package com.example.sweetreads;

import android.content.Intent;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sweetreads.inventory.InventoryActivity;
import com.example.sweetreads.shop.PaymentActivity;
import com.example.sweetreads.shop.ItemSelectedActivity;
import com.example.sweetreads.sign_in_sign_up.ClientSearchActivity;
import com.example.sweetreads.sign_in_sign_up.SignInSignUpActivity;

public class ClientCommonActivity extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.client_menu ,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.options_01_c:
                Toast.makeText(this, "Log out successful", Toast.LENGTH_SHORT).show();
                goToSignInSignUp();
                return true;
            case R.id.options_02_c:
                Toast.makeText(this, "Redirecting to client", Toast.LENGTH_SHORT).show();
                goToClientSearch();
                return true;
            case R.id.options_03_c:
                Toast.makeText(this, "Redirecting to user profile", Toast.LENGTH_SHORT).show();
                goToUserProfile();
                return true;
            case R.id.options_contact_us_c:
                Toast.makeText(this, "Redirecting to contact us", Toast.LENGTH_SHORT).show();
                goToContactUS();
                return true;
            case R.id.options_04_c:
                Toast.makeText(this, "Redirecting to settings", Toast.LENGTH_SHORT).show();
                goToApplicationList();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }


    public void goToSignInSignUp() {

        Intent intent = new Intent(getApplicationContext(), SignInSignUpActivity.class);
        startActivity(intent);

    }

    public void goToClientSearch() {

        Intent intent = new Intent(getApplicationContext(), ClientSearchActivity.class);
        startActivity(intent);

    }

    public void goToUserProfile() {

        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intent);

    }

    public void goToContactUS() {

        Intent intent = new Intent(getApplicationContext(), ContactUsActivity.class);
        startActivity(intent);

    }

    public void goToApplicationList() {

        Intent intent = new Intent(Settings.ACTION_APPLICATION_SETTINGS);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /*


    //goes to accessibility settings
    Settings.ACTION_ACCESSIBILITY_SETTINGS

    //goes to the installed app list
    ACTION_APPLICATION_SETTINGS

    //goes to developer options
    ACTION_APPLICATION_DEVELOPMENT_SETTINGS

    //this provide default app settings
    ACTION_HOME_SETTINGS

    //this provides screen saver related settings
    ACTION_DREAM_SETTINGS


    */


}

