package com.example.sih.Employee;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.sih.model.ScholarShipFormModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewScholershipFormFragment extends Fragment {


    Button create_scholership;
    EditText scholership_name_form, scholership_amount_form, scholership_info_form;
    String name, amount, info;
    String token;
    View view;

    public NewScholershipFormFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_new_scholership_form, container, false);

        create_scholership = view.findViewById(R.id.create_scholership);
        scholership_name_form = view.findViewById(R.id.scholership_name_form);
        scholership_amount_form = view.findViewById(R.id.scholership_amount_form);
        scholership_info_form = view.findViewById(R.id.scholership_info_form);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("TOKEN_FILE", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("TOKEN_KEY", "");

        create_scholership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process();
                if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getActivity().getSupportFragmentManager().popBackStackImmediate();
                }
            }
        });
        return view;
    }

    public void process() {
        name = String.valueOf(scholership_name_form.getText());
        amount = String.valueOf(scholership_amount_form.getText());
        info = String.valueOf(scholership_info_form.getText());
        initBlock();

    }

    private void initBlock() {
        setScholarShipFormData(new ScholarShipFormModel(name, amount, info));
    }

    public void setScholarShipFormData(ScholarShipFormModel scholarShipFormModel) {
        Call<ScholarShipFormModel> call = Repositry.getInstance().getCommentsService().setScholarShip(scholarShipFormModel, "Bearer " + token);
        call.enqueue(new Callback<ScholarShipFormModel>() {
            @Override
            public void onResponse(Call<ScholarShipFormModel> call, Response<ScholarShipFormModel> response) {
                try {
                    if(response.isSuccessful())
                    {
                        Toast.makeText(view.getContext(), "Scholarship Created", Toast.LENGTH_SHORT).show();
//                        if(getActivity().getSupportFragmentManager().getBackStackEntryCount()>0)
//                        {
//                            getActivity().getSupportFragmentManager().popBackStackImmediate();
//                        }
                    }
                }catch (Exception e)
                {
                    Toast.makeText(view.getContext(), "" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ScholarShipFormModel> call, Throwable t) {
            }
        });
    }
}