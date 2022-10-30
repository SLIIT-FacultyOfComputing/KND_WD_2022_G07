package com.example.sweetreads.inventory;

import static android.icu.lang.UCharacter.toLowerCase;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.sweetreads.R;
import com.example.sweetreads.UploadBookActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddBooksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddBooksFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddBooksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddBooksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddBooksFragment newInstance(String param1, String param2) {
        AddBooksFragment fragment = new AddBooksFragment();
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

        View AddBooksFragmentView = inflater.inflate(R.layout.fragment_add_books, container, false);

        EditText inventory_add_book_name_slot = (EditText) AddBooksFragmentView.findViewById(R.id.inventory_add_book_name_slot);
        EditText inventory_add_book_price_slot = (EditText) AddBooksFragmentView.findViewById(R.id.inventory_add_book_price_slot);
        EditText inventory_add_book_url_slot = (EditText) AddBooksFragmentView.findViewById(R.id.inventory_add_book_url_slot);
        Button add_button = (Button) AddBooksFragmentView.findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), UploadBookActivity.class);
                intent.putExtra("EDIT_CODE" , 30);
                intent.putExtra("BOOK_NAME" , toLowerCase(inventory_add_book_name_slot.getText().toString()));
                intent.putExtra("BOOK_PRICE" , Integer.valueOf(inventory_add_book_price_slot.getText().toString().trim()));
                intent.putExtra("BOOK_URL" , inventory_add_book_url_slot.getText().toString());
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return AddBooksFragmentView;
    }


}