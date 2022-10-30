package com.example.sweetreads.inventory;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.sweetreads.DisplayResultsActivity;
import com.example.sweetreads.R;
import com.example.sweetreads.SpeechToTextActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RemoveBooksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RemoveBooksFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RemoveBooksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RemoveBooksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RemoveBooksFragment newInstance(String param1, String param2) {
        RemoveBooksFragment fragment = new RemoveBooksFragment();
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

        View RemoveBooksFragmentView = inflater.inflate(R.layout.fragment_remove_books, container, false);
        ImageButton inventory_remove_rec_button = (ImageButton) RemoveBooksFragmentView.findViewById(R.id.inventory_remove_rec_button);

        EditText inventory_remove_book_name_slot = (EditText) RemoveBooksFragmentView.findViewById(R.id.inventory_remove_book_name_slot);
        Button remove_button = (Button) RemoveBooksFragmentView.findViewById(R.id.remove_button);

        inventory_remove_rec_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), SpeechToTextActivity.class);
                intent.putExtra("MY_USER_REQUEST_CODE", 200);
                startActivity(intent);
            }
        });

        remove_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), DisplayResultsActivity.class);
                intent.putExtra("RESULT_TEXT", inventory_remove_book_name_slot.getText().toString());
                intent.putExtra("RESULT_NUMBER",200);
                startActivity(intent);
            }
        });

        return RemoveBooksFragmentView;

    }
}