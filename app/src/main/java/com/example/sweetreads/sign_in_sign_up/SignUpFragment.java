package com.example.sweetreads.sign_in_sign_up;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.sweetreads.AuthorizationActivity;
import com.example.sweetreads.R;
import com.example.sweetreads.SpeechToTextActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View SignUpFragmentView = inflater.inflate(R.layout.fragment_sign_up, container, false);
        Button sign_up_button = (Button) SignUpFragmentView.findViewById(R.id.sign_up_button);

        EditText sign_up_username_slot = (EditText) SignUpFragmentView.findViewById(R.id.sign_up_username_slot);
        EditText sign_up_email_slot = (EditText) SignUpFragmentView.findViewById(R.id.sign_up_email_slot);
        EditText sign_up_password_slot = (EditText) SignUpFragmentView.findViewById(R.id.sign_up_password_slot);


        sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), AuthorizationActivity.class);
                intent.putExtra("AUTH_CODE", 20);
                intent.putExtra("USER_USERNAME" , sign_up_username_slot.getText().toString());
                intent.putExtra("USER_EMAIl" , sign_up_email_slot.getText().toString());
                intent.putExtra("USER_PASSWORD" , sign_up_password_slot.getText().toString());
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return SignUpFragmentView;
    }
}