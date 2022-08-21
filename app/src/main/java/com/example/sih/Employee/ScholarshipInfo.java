package com.example.sih.Employee;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.sih.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScholarshipInfo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScholarshipInfo extends Fragment {


    public ScholarshipInfo() {
        // Required empty public constructor
    }


    public static ScholarshipInfo newInstance(String Id) {
        ScholarshipInfo fragment = new ScholarshipInfo();
        Bundle args = new Bundle();
        args.putString("ID" , Id);
        fragment.setArguments(args);
        return fragment;
    }

    String ScholarId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           ScholarId = getArguments().getString("ID");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_scholarship_info, container, false);
    }
}