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

public class InventoryCommonActivity extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.inventory_menu ,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.options_01_i:
                Toast.makeText(this, "Log out successful", Toast.LENGTH_SHORT).show();
                goToSignInSignUp();
                return true;
            case R.id.options_02_i:
                Toast.makeText(this, "Redirecting to inventory", Toast.LENGTH_SHORT).show();
                goToInventory();
                return true;
            case R.id.options_03_i:
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


    public void goToInventory() {

        Intent intent = new Intent(getApplicationContext(), InventoryActivity.class);
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
