package com.example.sweetreads.shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sweetreads.R;
import com.example.sweetreads.sign_in_sign_up.SignInSignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class PaymentDeleteActivity extends AppCompatActivity {

    EditText payment_delete_slot;
    Button payment_delete_single_button , payment_delete_logout_button;
    Intent logout_intent = null;

    String payment_id;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_delete);

        db = FirebaseFirestore.getInstance();

        logout_intent = new Intent(getApplicationContext() , SignInSignUpActivity.class);
        payment_delete_single_button = findViewById(R.id.payment_delete_single_button);
        payment_delete_logout_button = findViewById(R.id.payment_delete_logout_button);
        payment_delete_slot = findViewById(R.id.payment_delete_slot);

        payment_delete_single_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                payment_id = payment_delete_slot.getText().toString();

                db.collection("PaymentInformation")
                        .document(payment_id)
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful())
                                    Toast.makeText(getApplicationContext(),"Deletion Passed" , Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(getApplicationContext(),"Deletion Failed" , Toast.LENGTH_LONG).show();

                            }
                        });

            }
        });

        payment_delete_logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Log out successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent (getApplicationContext() , SignInSignUpActivity.class));
            }
        });


    }
}