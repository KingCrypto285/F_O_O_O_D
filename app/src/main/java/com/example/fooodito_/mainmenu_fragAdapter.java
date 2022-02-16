package com.example.fooodito_;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class mainmenu_fragAdapter extends FragmentPagerAdapter
{


    public mainmenu_fragAdapter(@NonNull FragmentManager fm)
    {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        return null;
    }

    @Override
    public int getCount()
    {
        return 2;
    }
}
