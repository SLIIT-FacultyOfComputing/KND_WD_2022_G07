package com.example.sweetreads;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sweetreads.inventory.InventoryActivity;
import com.example.sweetreads.sign_in_sign_up.SignInSignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ProfileActivity extends ClientCommonActivity {

    private Intent intent01 = null;
    EditText user_profile_email_slot , user_profile_password_slot;
    Button user_profile_delete_button;
    private String username , password , email;
    FirebaseFirestore db;
    String accountDeleted = "not defined";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        user_profile_email_slot = (EditText) findViewById(R.id.user_profile_email_slot);
        user_profile_password_slot = (EditText) findViewById(R.id.user_profile_password_slot);
        user_profile_delete_button = (Button) findViewById(R.id.user_profile_delete_button);

        intent01 = getIntent();

        if(intent01  != null) {

            intent01 = getIntent();
            username = intent01.getStringExtra("USER_NAME");
            password = intent01.getStringExtra("USER_PASSWORD");
            email = intent01.getStringExtra("USER_EMAIL");

            user_profile_email_slot.setText(intent01.getStringExtra("USER_EMAIL"));
            user_profile_password_slot.setText(intent01.getStringExtra("USER_PASSWORD"));

        }


        user_profile_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db= FirebaseFirestore.getInstance();

                email =  user_profile_email_slot.getText().toString();
                password =  user_profile_password_slot.getText().toString();

                db.collection("UserInformation")
                        .whereEqualTo("Email" , email)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {

                                    for (QueryDocumentSnapshot document : task.getResult())
                                        if( email.equals(document.get("Email")) && password.equals(document.get("Password")) ){

                                            db.collection("UserInformation")
                                                    .document(email)
                                                    .delete()
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {

                                                            if(task.isSuccessful()) {
                                                                Toast.makeText(getApplicationContext(),"Account Successfully Deleted" , Toast.LENGTH_LONG).show();
                                                                startActivity(new Intent (getApplicationContext() , SignInSignUpActivity.class));
                                                                accountDeleted = "account deleted";


                                                            }
                                                            else {
                                                                Toast.makeText(getApplicationContext(),"Deletion Failed" , Toast.LENGTH_LONG).show();
                                                                startActivity(new Intent (getApplicationContext() , ProfileActivity.class));
                                                                accountDeleted = "account not deleted";
                                                            }

                                                        }
                                                    });

                                        }
                                        else{

                                            Toast.makeText(getApplicationContext(),"Deletion Failed" , Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent (getApplicationContext() , ProfileActivity.class));
                                            accountDeleted = "account not deleted";

                                        }
                                }
                                else{

                                    Toast.makeText(getApplicationContext(),"Deletion Failed" , Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent (getApplicationContext() , ProfileActivity.class));
                                    accountDeleted = "account not deleted";

                                }

                            }
                        });






            }
        });

    }

    @Override
    public void onBackPressed () {

    }

}