package com.example.sweetreads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.example.sweetreads.inventory.InventoryActivity;
import com.example.sweetreads.shop.PaymentDeleteActivity;
import com.example.sweetreads.sign_in_sign_up.SignInSignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class AuthorizationActivity extends AppCompatActivity {

    private Intent intent01 = null;
    private Intent intent02 = null;
    int auth_code;
    String signUp , signIn = "not defined" ;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        intent01 = getIntent();
        auth_code = intent01.getIntExtra("AUTH_CODE",0);

        if(auth_code == 10){

            db.collection("UserInformation")
                    .whereEqualTo("Email" , intent01.getStringExtra("USER_EMAIL").trim())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {

                                for (QueryDocumentSnapshot document : task.getResult())
                                    if( intent01.getStringExtra("USER_EMAIL").trim().equals(document.get("Email")) && intent01.getStringExtra("USER_PASSWORD").trim().equals(document.get("Password")) ){

                                        Toast.makeText(getApplicationContext(),"Sign In Successful" , Toast.LENGTH_LONG).show();

                                        if(intent01.getStringExtra("USER_EMAIL").trim().equals("inventory@gmail.com")) {

                                            signIn = "sign In successful";
                                            startActivity(new Intent(getApplicationContext() , InventoryActivity.class));

                                        }
                                        else if(intent01.getStringExtra("USER_EMAIL").trim().equals("payment@gmail.com")) {

                                            signIn = "sign In successful";
                                            startActivity(new Intent(getApplicationContext() , PaymentDeleteActivity.class));

                                        }
                                        else {

                                            intent02 = new Intent(getApplicationContext() , ProfileActivity.class);
                                            intent02.putExtra("USER_NAME" , document.get("Username").toString());
                                            intent02.putExtra("USER_EMAIL" , document.get("Email").toString());
                                            intent02.putExtra("USER_PASSWORD" , document.get("Password").toString());
                                            signIn = "sign In successful";
                                            startActivity(intent02);
                                        }

                                    }
                                    else{

                                        Toast.makeText(getApplicationContext(),"Sign In Failed" , Toast.LENGTH_SHORT).show();
                                        signIn = "sign In failed";
                                        startActivity(new Intent(getApplicationContext() , SignInSignUpActivity.class));

                                    }
                            }
                            else{

                                Toast.makeText(getApplicationContext(),"Sign In Failed" , Toast.LENGTH_SHORT).show();
                                signIn = "sign In failed";
                                startActivity(new Intent(getApplicationContext() , SignInSignUpActivity.class));

                            }

                        }
                    });

        }
        else if(auth_code == 20){

            Map<String, Object> user = new HashMap<>();
            user.put("Username", intent01.getStringExtra("USER_USERNAME").trim());
            user.put("Password", intent01.getStringExtra("USER_PASSWORD").trim());
            user.put("Email", intent01.getStringExtra("USER_EMAIl").trim());

            db.collection("UserInformation")
                    .document(intent01.getStringExtra("USER_EMAIl").trim())
                    .set(user)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            startActivity(new Intent(getApplicationContext(), SignInSignUpActivity.class));
                            Toast.makeText(getApplicationContext(),"Sign Up Successful",Toast.LENGTH_LONG).show();
                            signUp = "sign up successful";

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            startActivity(new Intent(getApplicationContext(), SignInSignUpActivity.class));
                            Toast.makeText(getApplicationContext(),"Sign Up Failed",Toast.LENGTH_LONG).show();
                            signUp = "sign up failed";
                        }
                    });

        }
        else {

            Toast.makeText(getApplication(), "Redirecting to SignIn/SignUp", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext() , SignInSignUpActivity.class));

        }

    }

    @Override
    public void onBackPressed () {

    }


}