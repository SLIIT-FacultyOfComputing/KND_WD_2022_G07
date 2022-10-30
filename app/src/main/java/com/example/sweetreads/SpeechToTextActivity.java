package com.example.sweetreads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.widget.Toast;

import com.example.sweetreads.shop.ItemSelectedActivity;

import java.util.ArrayList;
import java.util.Locale;

public class SpeechToTextActivity extends AppCompatActivity {

    int request_code;
    String result_string;

    Intent intent01 = null;
    Intent intent02 = null;
    Intent intent03 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_to_text);

        intent01 = getIntent();
        request_code = intent01.getIntExtra("MY_USER_REQUEST_CODE",0);

    }


    @Override
    protected void onStart() {
        super.onStart();

        intent02 = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent02.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent02.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent02.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent02, request_code);
        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 100:

                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    result_string = result.get(0);
                    intent03 = new Intent(getApplicationContext(), DisplayResultsActivity.class);
                    intent03.putExtra("RESULT_TEXT", result_string);
                    intent03.putExtra("RESULT_NUMBER",requestCode);
                    Toast.makeText(getApplicationContext(), "Redirecting to search results", Toast.LENGTH_SHORT).show();
                    startActivity(intent03);

                }

            case 200:

                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    result_string = result.get(0);
                    intent03 = new Intent(getApplicationContext(), DisplayResultsActivity.class);
                    intent03.putExtra("RESULT_TEXT", result_string);
                    intent03.putExtra("RESULT_NUMBER",requestCode);
                    Toast.makeText(getApplicationContext(), "Redirecting to search results", Toast.LENGTH_SHORT).show();
                    startActivity(intent03);

                }

            case 300:

                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    result_string = result.get(0);
                    intent03 = new Intent(getApplicationContext(), DisplayResultsActivity.class);
                    intent03.putExtra("RESULT_TEXT", result_string);
                    intent03.putExtra("RESULT_NUMBER",requestCode);
                    Toast.makeText(getApplicationContext(), "Redirecting to search results", Toast.LENGTH_SHORT).show();
                    startActivity(intent03);

                }

        }

    }

    @Override
    public void onBackPressed () {

    }

}