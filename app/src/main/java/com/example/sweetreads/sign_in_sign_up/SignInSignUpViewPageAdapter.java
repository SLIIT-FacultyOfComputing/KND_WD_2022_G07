package com.example.sweetreads.sign_in_sign_up;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class SignInSignUpViewPageAdapter extends FragmentStateAdapter {

    public SignInSignUpViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return  new SignInFragment();
            default:
                return  new SignUpFragment();

        }
    }
    @Override
    public int getItemCount() {return 2; }
}
