package com.example.sweetreads;

import static android.icu.lang.UCharacter.toLowerCase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.Toast;

import com.example.sweetreads.inventory.InventoryActivity;
import com.example.sweetreads.shop.ItemSelectedActivity;
import com.example.sweetreads.sign_in_sign_up.ClientSearchActivity;
import com.example.sweetreads.sign_in_sign_up.SignInSignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DisplayResultsActivity extends AppCompatActivity {

    TextView display_search_keyword;
    TextView display_search_results_header;

    String display_string;
    int display_number;
    Intent intent04;

    RecyclerView book_rec_view;
    ArrayList<BookModel> book_data_list;
    FirebaseFirestore db;
    BookAdapter book_adapter;

    String inventoryBookDeleted = "not defined";
    String inventoryBookSearched = "not defined";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);

        intent04 = getIntent();
        display_string = toLowerCase(intent04.getStringExtra("RESULT_TEXT"));
        display_number = intent04.getIntExtra("RESULT_NUMBER",0);

        display_search_keyword = (TextView) findViewById(R.id.display_search_keyword);
        display_search_results_header = (TextView) findViewById(R.id.display_search_results_header);

        if(display_number == 100 || display_number == 200 ||  display_number == 300)
            Toast.makeText(getApplicationContext(), "Request Code " + display_number + " returned" ,Toast.LENGTH_SHORT).show();

        if(display_number == 300) {

            intent04 = null;
            intent04 = new Intent(getApplicationContext() , ItemSelectedActivity.class);
            intent04.putExtra("RESULT_TEXT", display_string);
            intent04.putExtra("RESULT_NUMBER",300);
            startActivity(intent04);

        }

        if(display_number == 100) {

            RecyclerView book_rec_view;
            ArrayList<BookModel> book_data_list;
            FirebaseFirestore db;
            BookAdapter book_adapter;

            display_search_results_header.setText("Search results for ...");
            display_search_keyword.setText(display_string);

            book_rec_view=(RecyclerView)findViewById(R.id.book_rec_view);
            book_rec_view.setLayoutManager(new LinearLayoutManager(this));
            book_data_list =new ArrayList<>();
            book_adapter =new BookAdapter(book_data_list);
            book_rec_view.setAdapter(book_adapter);

            db= FirebaseFirestore.getInstance();
            db.collection("BookInformation")
                    .whereEqualTo("Book_name" , display_string)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d:list)
                            {
                                BookModel obj=d.toObject(BookModel.class);
                                book_data_list.add(obj);
                            }
                            book_adapter.notifyDataSetChanged();
                            inventoryBookSearched = "book is searched";
                        }
                    });

        }

        if(display_number == 200 ) {

            display_search_results_header.setText("Deleting......");
            display_search_keyword.setText(display_string);

            db= FirebaseFirestore.getInstance();
            db.collection("BookInformation")
                    .whereEqualTo("Book_name" , display_string)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {

                                for (QueryDocumentSnapshot document : task.getResult())
                                    if( display_string.equals(document.get("Book_name"))){

                                        db.collection("BookInformation")
                                                .document(display_string)
                                                .delete()
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                        if(task.isSuccessful()) {
                                                            Toast.makeText(getApplicationContext(),"Book Successfully Deleted" , Toast.LENGTH_LONG).show();
                                                            startActivity(new Intent (getApplicationContext() , InventoryActivity.class));
                                                            inventoryBookDeleted = "book is deleted";
                                                        }
                                                        else {
                                                            Toast.makeText(getApplicationContext(),"Deletion Failed" , Toast.LENGTH_LONG).show();
                                                            startActivity(new Intent (getApplicationContext() , InventoryActivity.class));
                                                            inventoryBookDeleted = "book is not deleted";
                                                        }

                                                    }
                                                });

                                    }
                                    else{

                                        Toast.makeText(getApplicationContext(),"Deletion Failed" , Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent (getApplicationContext() , InventoryActivity.class));
                                        inventoryBookDeleted = "book is not deleted";

                                    }
                            }
                            else{

                                Toast.makeText(getApplicationContext(),"Deletion Failed" , Toast.LENGTH_SHORT).show();
                                startActivity(new Intent (getApplicationContext() , InventoryActivity.class));
                                inventoryBookDeleted = "book is not deleted";

                            }

                        }
                    });

        }



    }

































    @Override
    public void onBackPressed () {

        if(display_number == 100 || display_number == 200)
            startActivity(new Intent(getApplicationContext(), InventoryActivity.class));
        else if(display_number == 300)
            startActivity(new Intent(getApplicationContext(), ClientSearchActivity.class));
        else
            startActivity(new Intent(getApplicationContext(), SignInSignUpActivity.class));

    }

}