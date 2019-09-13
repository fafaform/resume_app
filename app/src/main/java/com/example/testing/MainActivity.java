package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    //////////////////////////////////
    // TODO local parameter cannot be used in different class, so, global (here) is one of the better place for parameter.
    // TODO private or public of parameter can be declare
    //////////////////////////////////

    TabLayout tabLayout;
    ViewPager view_area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout);
        view_area = findViewById(R.id.viewPager);

        //////////////////////////////////
        // TODO: tabview create viewPage and tablayout on xml with code below
        //////////////////////////////////
        view_area.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        //////////////////////////////////
                        // call fragment for the first tab
                        //////////////////////////////////
                        HomeFragment homeFragment = new HomeFragment();
                        return homeFragment;
                    case 1:
                        //////////////////////////////////
                        // cal fragment for the second tab
                        //////////////////////////////////
//                        SportFragment sportFragment = new SportFragment();
//                        return sportFragment;
                        ContentFragment contentFragment = new ContentFragment();
                        return contentFragment;
                    case 2:
//                        MovieFragment movieFragment = new MovieFragment();
//                        return movieFragment;
                        EtcFragment etcFragment = new EtcFragment();
                        return etcFragment;
                    default:
//                        HomeFragment homeFragment_start = new HomeFragment();
//                        return homeFragment_start;
                        return null;
                }
//                return null;
            }

            @Override
            public int getCount() {
                return 3;
            }

            //////////////////////////////////
            // Setting name for each tab
            //////////////////////////////////
            @Override
            public CharSequence getPageTitle(int position) {
                // Generate title based on item position
                switch (position) {
                    case 0:
                        return getBaseContext().getString(R.string.home);
                    case 1:
                        return getBaseContext().getString(R.string.content);
                    case 2:
                        return getBaseContext().getString(R.string.etc);
                    default:
                        return null;
                }
            }
        });
        tabLayout.setupWithViewPager(view_area);
    }
}
