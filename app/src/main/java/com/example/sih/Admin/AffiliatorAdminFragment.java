package com.example.sih.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.sih.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AffiliatorAdminFragment extends Fragment {



    public AffiliatorAdminFragment() {
        // Required empty public constructor
    }

    FloatingActionButton createAffiliator;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_affiliator_admin, container, false);
        createAffiliator = view.findViewById(R.id.createAffliator);

        createAffiliator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new CreateAffiliatorFormFragment());
            }
        });



        return view;
    }

    private void loadFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
    }
}