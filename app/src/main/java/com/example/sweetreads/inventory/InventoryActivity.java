package com.example.sweetreads.inventory;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.sweetreads.CommonActivity;
import com.example.sweetreads.InventoryCommonActivity;
import com.example.sweetreads.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class InventoryActivity extends InventoryCommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        TabLayout tabLayout = findViewById(R.id.tabs);
        ViewPager2 viewPager2 = findViewById(R.id.view_pager);

        InventoryViewPageAdapter adapter = new InventoryViewPageAdapter(InventoryActivity.this);
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        if(position == 0)
                        tab.setText("Search");
                        else if(position == 1)
                            tab.setText("Add");
                        else if(position == 2)
                            tab.setText("Remove");
                    }
                }).attach();

    }

    @Override
    public void onBackPressed () {

    }

}