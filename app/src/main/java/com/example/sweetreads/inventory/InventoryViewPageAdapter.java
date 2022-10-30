package com.example.sweetreads.inventory;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.sweetreads.inventory.AddBooksFragment;
import com.example.sweetreads.inventory.RemoveBooksFragment;
import com.example.sweetreads.inventory.SearchBooksFragment;

public class InventoryViewPageAdapter extends FragmentStateAdapter {

    public InventoryViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return  new SearchBooksFragment();
            case 1:
                return  new AddBooksFragment();
            default:
                return  new RemoveBooksFragment();

        }
    }
    @Override
    public int getItemCount() {return 3; }
}


