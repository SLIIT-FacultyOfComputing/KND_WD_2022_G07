package com.example.sweetreads.sign_in_sign_up;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.sweetreads.AuthorizationActivity;
import com.example.sweetreads.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignInFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignInFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignInFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignInFragment newInstance(String param1, String param2) {
        SignInFragment fragment = new SignInFragment();
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

        View SignInFragmentView = inflater.inflate(R.layout.fragment_sign_in, container, false);
        Button sign_in_button = (Button) SignInFragmentView.findViewById(R.id.sign_in_button);

        EditText sign_in_email_slot = (EditText) SignInFragmentView.findViewById(R.id.sign_in_email_slot);
        EditText sign_in_password_slot = (EditText) SignInFragmentView.findViewById(R.id.sign_in_password_slot);

        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), AuthorizationActivity.class);
                intent.putExtra("AUTH_CODE", 10);
                intent.putExtra("USER_EMAIL" , sign_in_email_slot.getText().toString());
                intent.putExtra("USER_PASSWORD" , sign_in_password_slot.getText().toString());
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return SignInFragmentView;

    }
}