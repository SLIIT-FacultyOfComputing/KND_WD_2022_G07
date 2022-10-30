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
 * Use the {@link SearchBooksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchBooksFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchBooksFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchBooksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchBooksFragment newInstance(String param1, String param2) {
        SearchBooksFragment fragment = new SearchBooksFragment();
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

        View SearchBooksFragmentView = inflater.inflate(R.layout.fragment_search_books, container, false);
        ImageButton inventory_search_rec_button = (ImageButton) SearchBooksFragmentView.findViewById(R.id.inventory_search_rec_button);

        EditText inventory_search_slot = (EditText) SearchBooksFragmentView.findViewById(R.id.inventory_search_slot);
        Button search_button = (Button) SearchBooksFragmentView.findViewById(R.id.search_button);

        inventory_search_rec_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), SpeechToTextActivity.class);
                intent.putExtra("MY_USER_REQUEST_CODE", 100);
                startActivity(intent);
            }
        });

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), DisplayResultsActivity.class);
                intent.putExtra("RESULT_TEXT", inventory_search_slot.getText().toString());
                intent.putExtra("RESULT_NUMBER",100);
                startActivity(intent);
            }
        });

        return SearchBooksFragmentView;

    }
}