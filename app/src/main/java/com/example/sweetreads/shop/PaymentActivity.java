package com.example.sweetreads.shop;

import com.example.sweetreads.ClientCommonActivity;
import com.example.sweetreads.R;
import com.example.sweetreads.sign_in_sign_up.ClientSearchActivity;
import com.example.sweetreads.sign_in_sign_up.SignInSignUpActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class PaymentActivity extends ClientCommonActivity implements DatePickerDialog.OnDateSetListener {

    Intent intent01 = null , download_intent = null;
    EditText payment_card_number_slot;
    EditText payment_card_expiry_date_slot;
    EditText payment_card_cvv_slot;
    Button payment_card_proceed_to_pay_button;
    RadioGroup payment_option;
    RadioButton selected_payment_option;
    String payment_id , selected_payment_option_text = "Visa" , payment_amount , download_url ;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DateTimeFormatter dtf;
    LocalDateTime now;

    public String paymentAdded = "not defined";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Button payment_card_expiry_date_button = (Button) findViewById(R.id.payment_card_expiry_date_button);
        payment_card_expiry_date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new PaymentDatePickerDialog();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        intent01 = getIntent();
        payment_amount = intent01.getStringExtra("SELECTED_BOOK_PRICE");
        download_url = intent01.getStringExtra("SELECTED_BOOK_URL");

        download_intent = new Intent(Intent.ACTION_VIEW);
        download_intent.setPackage("com.android.chrome");
        download_intent.setData(Uri.parse(download_url));

        payment_card_number_slot = (EditText) findViewById(R.id.payment_card_number_slot);
        payment_card_expiry_date_slot = (EditText) findViewById(R.id.payment_card_expiry_date_slot);
        payment_card_cvv_slot = (EditText) findViewById(R.id.payment_card_cvv_slot);
        payment_card_proceed_to_pay_button = (Button) findViewById(R.id.payment_card_proceed_to_pay_button);
        payment_option = (RadioGroup) findViewById(R.id.payment_option);

        payment_card_proceed_to_pay_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss:.SSS");
                now = LocalDateTime.now();
                payment_id = dtf.format(now);
                payment_id = payment_id.replace("/","-").replace(":","-").replace(" ","_").replace(".","-");

                Map<String, Object> payment = new HashMap<>();
                payment.put("Payment_id", payment_id);
                payment.put("Payment_card", payment_card_number_slot.getText().toString());
                payment.put("Payment_card_cvv", payment_card_cvv_slot.getText().toString());
                payment.put("Payment_card_expiry_date", payment_card_expiry_date_slot.getText().toString());
                payment.put("Payment_option", selected_payment_option_text);
                payment.put("Payment_amount", payment_amount);

                if(payment_card_number_slot.getText().toString().length() != 10)
                    Toast.makeText(getApplicationContext(), "Card number must contain 10 digits", Toast.LENGTH_SHORT).show();
                else if(payment_card_cvv_slot.getText().toString().length() != 3)
                    Toast.makeText(getApplicationContext(), "Card cvv must contain 3 digits", Toast.LENGTH_SHORT).show();
                else if(payment_card_expiry_date_slot.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Card expiry date cannot be empty", Toast.LENGTH_SHORT).show();
                else {

                    db.collection("PaymentInformation")
                            .document(payment_id)
                            .set(payment)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(),"Payment Successful",Toast.LENGTH_LONG).show();
                                    paymentAdded = "payment is passed";
                                    startActivity(download_intent);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(getApplicationContext(),"Payment Failed",Toast.LENGTH_LONG).show();
                                    paymentAdded = "payment is failed";
                                    startActivity(new Intent(getApplicationContext(), ClientSearchActivity.class));
                                }
                            });

                }

            }
        });

    }

    public void checkButton(View view) {

        int radioId = payment_option.getCheckedRadioButtonId();

        selected_payment_option = findViewById(radioId);

        if(selected_payment_option.getText().equals("Visa"))
            selected_payment_option_text = "Visa";
        else if(selected_payment_option.getText().equals("Master"))
            selected_payment_option_text = "Master";
        else
            selected_payment_option_text = "Visa";

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(c.getTime());

        EditText payment_card_expiry_date_slot = (EditText) findViewById(R.id.payment_card_expiry_date_slot);
        payment_card_expiry_date_slot.setText(currentDateString);
    }

    @Override
    public void onBackPressed () {

    }

}