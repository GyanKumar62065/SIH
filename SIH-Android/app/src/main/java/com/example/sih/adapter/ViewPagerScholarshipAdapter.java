package com.example.sih.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.sih.Employee.ApprovedScholarshipFragment;
import com.example.sih.Employee.NonApprovedScholarshipFragment;

public class ViewPagerScholarshipAdapter extends FragmentStatePagerAdapter {

    public ViewPagerScholarshipAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0)
        {
            return new NonApprovedScholarshipFragment();
        }
        else
        {
            return new ApprovedScholarshipFragment();
        }
    }

    @Override
    public int getCount() {
        return 2; // no of tabs
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0)
        {
            return "Unapproved";
        }
        else
        {
            return "Approved";
        }
    }
}
