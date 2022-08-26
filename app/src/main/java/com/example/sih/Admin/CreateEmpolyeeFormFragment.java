package com.example.sih.Admin;

import android.os.Bundle;
import android.util.Patterns;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateEmpolyeeFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateEmpolyeeFormFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button employeeCreateButton;
    EditText employeeNameField, employeeEmailField, employeeContactNumberFiled, employeeAddressLine1Filed, employeeAddressLine2Filed, employeePasswordFiled;
    String name, email, contact, addressLine1, addressLine2, password , role;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateEmpolyeeFormFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateEmpolyeeFormFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateEmpolyeeFormFragment newInstance(String param1, String param2) {
        CreateEmpolyeeFormFragment fragment = new CreateEmpolyeeFormFragment();
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
        View view = inflater.inflate(R.layout.fragment_create_empolyee_form, container, false);
        employeeNameField = view.findViewById(R.id.employeeNameField);
        employeeEmailField = view.findViewById(R.id.employeeEmailField);
        employeeContactNumberFiled = view.findViewById(R.id.employeeContactNumberFiled);
        employeeAddressLine1Filed = view.findViewById(R.id.employeeAddressLine1Filed);
        employeeAddressLine2Filed = view.findViewById(R.id.employeeAddressLine2Filed);
        employeePasswordFiled = view.findViewById(R.id.employeePasswordFiled);
        employeeCreateButton = view.findViewById(R.id.employeeCreateButton);


        employeeCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = employeeNameField.getText().toString();
                email = employeeEmailField.getText().toString();
                contact = employeeContactNumberFiled.getText().toString();
                addressLine1 = employeeAddressLine1Filed.getText().toString();
                addressLine2 = employeeAddressLine2Filed.getText().toString();
                password = employeePasswordFiled.getText().toString();
                role = "employee";

                if(name.isEmpty()){
                    Toast.makeText(getActivity(), "Enter Name", Toast.LENGTH_SHORT).show();
                }
                else if(email.isEmpty()){
                    Toast.makeText(getActivity(), "Enter Gmail Address", Toast.LENGTH_SHORT).show();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(getActivity(), "Enter Valid Gmail Address", Toast.LENGTH_SHORT).show();
                }else if(contact.isEmpty()){
                    Toast.makeText(getActivity(), "Enter Contact Details", Toast.LENGTH_SHORT).show();
                }
                else if(addressLine1.isEmpty()){
                    Toast.makeText(getActivity(), "Enter Address Line 1", Toast.LENGTH_SHORT).show();
                }else if(addressLine2.isEmpty()){
                    Toast.makeText(getActivity(), "Enter Address Line 2", Toast.LENGTH_SHORT).show();
                }else if(password.isEmpty()){
                    Toast.makeText(getActivity(), "Enter Password", Toast.LENGTH_SHORT).show();
                }else if(password.length() < 8){
                    Toast.makeText(getActivity(), "Password Length Should be greater than 8", Toast.LENGTH_SHORT).show();
                }else{
                    initBlock();

                }

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
                if(response.isSuccessful())
                {
                    Toast.makeText(getContext(), "Employee Created Successfully", Toast.LENGTH_SHORT).show();
                    if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
                        getActivity().getSupportFragmentManager().popBackStackImmediate();
                    }
                }
            }

            @Override
            public void onFailure(Call<EmployeeRegister> call, Throwable t) {
            }
        });
    }
}