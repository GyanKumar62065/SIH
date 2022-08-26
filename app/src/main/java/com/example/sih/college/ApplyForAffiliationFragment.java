package com.example.sih.college;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.sih.Network.Repositry;
import com.example.sih.R;
import com.example.sih.model.ApplyAffiliationFormModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ApplyForAffiliationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ApplyForAffiliationFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText clgName, clgEmail, clgContact, clgAddress, clgId, clgDirector, clgCity, clgState;
    Button save;
    String token;
    private String mParam1;
    private String mParam2;

    public ApplyForAffiliationFragment() {
        // Required empty public constructor
    }

    public static ApplyForAffiliationFragment newInstance(String param1, String param2) {
        ApplyForAffiliationFragment fragment = new ApplyForAffiliationFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_apply_for_affiliation, container, false);

        clgName = view.findViewById(R.id.clgName);
        clgEmail = view.findViewById(R.id.clgEmail);
        clgAddress = view.findViewById(R.id.clgAddress);
        clgContact = view.findViewById(R.id.clgContactNumber);
        clgCity = view.findViewById(R.id.city);
        clgState = view.findViewById(R.id.state);
        clgId = view.findViewById(R.id.clgId);
        clgDirector = view.findViewById(R.id.director);
        save = view.findViewById(R.id.clgSave);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("TOKEN_FILE", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("TOKEN_KEY", "");
        Log.e("AUTH KEY" , "" + token);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process();
            }
        });
        return view;
    }

    private void process() {

        String name, email, address, contact, city, state, clgid, director;
        name = clgName.getText().toString();
        address = clgAddress.getText().toString();
        contact = clgContact.getText().toString();
        city = clgCity.getText().toString();
        state = clgState.getText().toString();
        clgid = clgId.getText().toString();
        director = clgDirector.getText().toString();
        email = clgEmail.getText().toString();
        ApplyAffiliationFormModel applyAffiliationFormModel = new ApplyAffiliationFormModel(name, address, director, email, contact, city, state, Integer.parseInt(clgid));
        saveAffiliationFormData(applyAffiliationFormModel, token);
    }

    private void saveAffiliationFormData(ApplyAffiliationFormModel applyAffiliationFormModel, String token) {

        Call<ApplyAffiliationFormModel> call = Repositry.getInstance().getCommentsService().setAffiliationFormData(applyAffiliationFormModel,"Bearer " +  token);
        call.enqueue(new Callback<ApplyAffiliationFormModel>() {
            @Override
            public void onResponse(Call<ApplyAffiliationFormModel> call, Response<ApplyAffiliationFormModel> response) {
                if (response.isSuccessful()) {
                    Log.e("AFILLIATION DATA SAVING", "SUCESS");
                    // GO on uploading data fragment
//                    loadFragment();
                    // GO to Applications section
                    loadFragment();
                }
                else
                {
                    Log.e("UnSucessFul" , "");
                }
            }
            @Override
            public void onFailure(Call<ApplyAffiliationFormModel> call, Throwable t) {
                Log.e("Error is ", "" + t.getMessage());
            }
        });
    }
    private void loadFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new CollegeApplicationsFragment()).commit();
    }
}