package com.example.sweetreads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ContactUsActivity extends ClientCommonActivity {

    Intent redirectIntent = null;
    Button contact_us_on_whatsapp_button;
    Button contact_us_on_telegram_button;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);



        contact_us_on_whatsapp_button = (Button) findViewById(R.id.contact_us_on_whatsapp_button);
        contact_us_on_telegram_button = (Button) findViewById(R.id.contact_us_on_telegram_button);

        contact_us_on_whatsapp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                redirectIntent = new Intent(Intent.ACTION_VIEW);
                uri = Uri.parse("https://chat.whatsapp.com/IH1f9OeqWYg13gjKerKdEO");
                redirectIntent.setData(uri);
                redirectIntent.setPackage("com.whatsapp");
                startActivity(redirectIntent);

            }
        });

        contact_us_on_telegram_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                redirectIntent = new Intent(Intent.ACTION_VIEW);
                uri = Uri.parse("https://t.me/SweetReadsProject");
                redirectIntent.setData(uri);
                redirectIntent.setPackage("org.telegram.messenger");
                startActivity(redirectIntent);

            }
        });

    }

    @Override
    public void onBackPressed () {

    }

}