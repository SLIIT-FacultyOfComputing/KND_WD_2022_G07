package com.example.sweetreads.shop;

import static android.icu.lang.UCharacter.toLowerCase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.sweetreads.ClientCommonActivity;
import com.example.sweetreads.R;
import com.example.sweetreads.sign_in_sign_up.ClientSearchActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ItemSelectedActivity extends ClientCommonActivity {

    Intent intent01 = null , intent02 = null;
    String display_string;
    int display_number;
    String book_url , book_price;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public String clientBookSearched = "not defined";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_selected);

        intent02 = new Intent(getApplicationContext() , PaymentActivity.class);


        intent01 = getIntent();
        display_string = toLowerCase(intent01.getStringExtra("RESULT_TEXT"));
        display_number = intent01.getIntExtra("RESULT_NUMBER",0);

        EditText selected_item_name_slot = (EditText) findViewById(R.id.selected_item_name_slot);
        EditText selected_item_price_slot = (EditText) findViewById(R.id.selected_item_price_slot);
        Button selected_item_proceed_button = (Button) findViewById(R.id.selected_item_proceed_button);

        if(display_number == 300) {

            db.collection("BookInformation")
                    .whereEqualTo("Book_name" , display_string)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {

                                for (QueryDocumentSnapshot document : task.getResult())
                                    if(display_string.equals(document.get("Book_name"))){

                                        selected_item_name_slot.setText(document.get("Book_name").toString());
                                        selected_item_price_slot.setText(document.get("Book_price").toString());
                                        intent02.putExtra("SELECTED_BOOK_URL" , document.get("Book_url").toString());
                                        intent02.putExtra("SELECTED_BOOK_PRICE" , document.get("Book_price").toString());

                                        Toast.makeText(getApplicationContext(),"Book Info Obtained" , Toast.LENGTH_SHORT).show();
                                        clientBookSearched = "book is searched";


                                    }
                                    else{

                                        Toast.makeText(getApplicationContext(),"Failed To Access Information" , Toast.LENGTH_SHORT).show();
                                        clientBookSearched = "failed to search book";
                                        startActivity(new Intent(getApplicationContext() , ClientSearchActivity.class));

                                    }
                            }
                            else{

                                Toast.makeText(getApplicationContext(),"Failed To Access Information" , Toast.LENGTH_SHORT).show();
                                clientBookSearched = "failed to search book";
                                startActivity(new Intent(getApplicationContext() , ClientSearchActivity.class));

                            }

                        }
                    });

        }

        selected_item_proceed_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent02);
            }
        });


    }

    @Override
    public void onBackPressed () {

        if(display_number == 300)
            startActivity(new Intent (getApplicationContext() , ClientSearchActivity.class));

    }


}