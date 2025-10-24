package com.example.sih.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.sih.Affliator.NonApprovedFellowshipFragment;
import com.example.sih.Affliator.ApprovedFellowshipFragment;

public class FellowshipViewPagerAdapter extends FragmentStatePagerAdapter {

    public FellowshipViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NonApprovedFellowshipFragment();
        } else {
            return new ApprovedFellowshipFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Unapproved";
        } else {
            return "Approved";
        }
    }
}
