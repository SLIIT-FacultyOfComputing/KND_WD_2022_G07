package com.example.sweetreads;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sweetreads.inventory.InventoryActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UploadBookActivity extends AppCompatActivity {

    private Intent intent01 = null;
    private int edit_code;
    String bookAdded = "not defined";

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_book);

        intent01 = getIntent();
        edit_code = intent01.getIntExtra("EDIT_CODE" , 0);

        if(edit_code == 30) {

            if(intent01.getStringExtra("BOOK_NAME").trim() == null || intent01.getIntExtra("BOOK_PRICE",0) == 0 || intent01.getStringExtra("BOOK_URL").trim() == null || Integer.toString(intent01.getIntExtra("BOOK_PRICE",0)) == null) {

                Toast.makeText(getApplicationContext(), "Invalid entries", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext() , InventoryActivity.class));
                bookAdded = "book adding failed";
            }

            Map<String, Object> book = new HashMap<>();
            book.put("Book_name", intent01.getStringExtra("BOOK_NAME").trim());
            book.put("Book_price", Integer.valueOf(intent01.getIntExtra("BOOK_PRICE",0)).toString());
            book.put("Book_url", intent01.getStringExtra("BOOK_URL").trim());

            db.collection("BookInformation")
                    .document(intent01.getStringExtra("BOOK_NAME").trim())
                    .set(book)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            startActivity(new Intent(getApplicationContext(), InventoryActivity.class));
                            Toast.makeText(getApplicationContext(),"Book Added Successfully",Toast.LENGTH_LONG).show();
                            bookAdded = "book adding passed";
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            startActivity(new Intent(getApplicationContext(), InventoryActivity.class));
                            Toast.makeText(getApplicationContext(),"Failed To Add Book",Toast.LENGTH_LONG).show();
                            bookAdded = "book adding failed";
                        }
                    });

        }

    }

    @Override
    public void onBackPressed () {

    }

}