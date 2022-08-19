package com.example.sih.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.sih.Network.Repositry;
import com.example.sih.R;
import com.example.sih.model.EmployeeRegister;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAffiliatorFormFragment extends Fragment {

    Button affiliatorCreateButton;
    EditText affiliatorNameField, affiliatorEmailField, affiliatorContactNumberFiled, affiliatorAddressLine1Filed, affiliatorAddressLine2Filed, affiliatorPasswordFiled;
    String name, email, contact, addressLine1, addressLine2, password , role;

    public CreateAffiliatorFormFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_create_affiliator_form, container, false);
        affiliatorNameField = view.findViewById(R.id.affiliatorNameField);
        affiliatorEmailField = view.findViewById(R.id.affiliatorEmailField);
        affiliatorContactNumberFiled = view.findViewById(R.id.affiliatorContactNumberFiled);
        affiliatorAddressLine1Filed = view.findViewById(R.id.affiliatorAddressLine1Filed);
        affiliatorAddressLine2Filed = view.findViewById(R.id.affiliatorAddressLine2Filed);
        affiliatorPasswordFiled = view.findViewById(R.id.affiliatorPasswordFiled);
        affiliatorCreateButton = view.findViewById(R.id.affiliatorCreateButton);

        affiliatorCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = affiliatorNameField.getText().toString();
                email = affiliatorEmailField.getText().toString();
                contact = affiliatorContactNumberFiled.getText().toString();
                addressLine1 = affiliatorAddressLine1Filed.getText().toString();
                addressLine2 = affiliatorAddressLine2Filed.getText().toString();
                password = affiliatorPasswordFiled.getText().toString();
                role = "affiliator";

                initBlock();
                if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getActivity().getSupportFragmentManager().popBackStackImmediate();
                }

                Toast.makeText(view.getContext(), "Affliator Created", Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }

    private void initBlock() {
//        genrateToken(new EmployeeRegister("Gyan" , "gk@gmail.com" , "gk@gmail.com" , "employee" , "+91 6206540171" , "Indrapuri Sector C" , "Piplani" , "123"));
        genrateToken(new EmployeeRegister(name , email , email , role , "+91 " + contact , addressLine1 , addressLine2 , password));
    }


    public void genrateToken(EmployeeRegister registationRequest) {
        Call<EmployeeRegister> call = Repositry.getInstance().getCommentsService().registation(registationRequest);
        call.enqueue(new Callback<EmployeeRegister>() {
            @Override
            public void onResponse(Call<EmployeeRegister> call, Response<EmployeeRegister> response) {
                Toast.makeText(getContext(), "Employee Created Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<EmployeeRegister> call, Throwable t) {
            }
        });
    }
}