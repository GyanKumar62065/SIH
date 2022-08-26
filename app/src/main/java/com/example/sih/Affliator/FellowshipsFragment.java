package com.example.sih.Affliator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.sih.R;
import com.example.sih.adapter.FellowshipViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FellowshipsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FellowshipsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FellowshipsFragment() {
    }

    public static FellowshipsFragment newInstance(String param1, String param2) {
        FellowshipsFragment fragment = new FellowshipsFragment();
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

    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fellowships, container, false);
        tabLayout = view.findViewById(R.id.tab);
        viewPager = view.findViewById(R.id.viewPager);
//        ViewPagerScholarshipAdapter viewPagerScholarshipAdapter = new ViewPagerScholarshipAdapter(getActivity().getSupportFragmentManager());
        FellowshipViewPagerAdapter fellowshipViewPagerAdapter = new FellowshipViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(fellowshipViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return  view;
    }
}