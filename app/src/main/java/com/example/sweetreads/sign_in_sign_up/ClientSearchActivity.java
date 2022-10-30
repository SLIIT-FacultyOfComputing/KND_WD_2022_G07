package com.example.sweetreads.sign_in_sign_up;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.sweetreads.ClientCommonActivity;
import com.example.sweetreads.CommonActivity;
import com.example.sweetreads.DisplayResultsActivity;
import com.example.sweetreads.R;
import com.example.sweetreads.SpeechToTextActivity;

public class ClientSearchActivity extends ClientCommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_search);

        ImageButton client_search_rec_button = (ImageButton) findViewById(R.id.client_search_rec_button);

        EditText client_search_topic_slot = (EditText) findViewById(R.id.client_search_topic_slot);
        Button client_search_button = (Button) findViewById(R.id.client_search_button);

        client_search_rec_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SpeechToTextActivity.class);
                intent.putExtra("MY_USER_REQUEST_CODE", 300);
                startActivity(intent);
            }
        });

        client_search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DisplayResultsActivity.class);
                intent.putExtra("RESULT_TEXT", client_search_topic_slot.getText().toString());
                intent.putExtra("RESULT_NUMBER",300);
                startActivity(intent);
            }
        });

    }
}