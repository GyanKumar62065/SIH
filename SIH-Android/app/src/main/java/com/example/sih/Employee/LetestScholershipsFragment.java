package com.example.sih.Employee;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.sih.R;
import com.example.sih.adapter.ViewPagerScholarshipAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LetestScholershipsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class LetestScholershipsFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;


    public LetestScholershipsFragment() {
        // Required empty public constructor
    }


    public static LetestScholershipsFragment newInstance() {
        LetestScholershipsFragment fragment = new LetestScholershipsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_letest_scholerships, container, false);
        tabLayout = view.findViewById(R.id.tab);
        viewPager = view.findViewById(R.id.viewPager);

        ViewPagerScholarshipAdapter viewPagerScholarshipAdapter = new ViewPagerScholarshipAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(viewPagerScholarshipAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}