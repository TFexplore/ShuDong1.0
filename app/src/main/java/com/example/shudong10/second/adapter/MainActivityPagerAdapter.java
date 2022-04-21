package com.example.shudong10.second.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.shudong10.R;


/*
 * Created by troy379 on 11.04.17.
 */
public class MainActivityPagerAdapter extends FragmentStatePagerAdapter {

    public static final int ID_DEFAULT = 0;
    public static final int ID_STYLED = 1;
    public static final int ID_CUSTOM_LAYOUT = 2;


    private final Context context;

    public MainActivityPagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        String description = null;
        switch (position) {
            case ID_DEFAULT:
                description = context.getString(R.string.first);
                break;
            case ID_STYLED:

                description = context.getString(R.string.second);
                break;
            case ID_CUSTOM_LAYOUT:

                description = context.getString(R.string.third);
                break;

        }
        return DemoCardFragment.newInstance(position, description);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
