package com.example.sih.Employee;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.sih.Network.Repositry;
import com.example.sih.R;
import com.example.sih.model.ScholarShipFormModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScholarshipInfo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScholarshipInfo extends Fragment {


    String ScholarId;
    TextView shcolarShipName, scholarShipAmount, scholarShipInfo;
    Button deleteScholerShip;
    String token;

    public ScholarshipInfo() {
        // Required empty public constructor
    }

    public static ScholarshipInfo newInstance(String Id) {
        ScholarshipInfo fragment = new ScholarshipInfo();
        Bundle args = new Bundle();
        args.putString("ID", Id);
        fragment.setArguments(args);
        return fragment;
    }

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

        View view = inflater.inflate(R.layout.fragment_scholarship_info, container, false);
        shcolarShipName = view.findViewById(R.id.shcolarShipName);
        scholarShipAmount = view.findViewById(R.id.scholarShipAmount);
        scholarShipInfo = view.findViewById(R.id.scholarShipInfo);
        deleteScholerShip = view.findViewById(R.id.deleteScholerShip);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("TOKEN_FILE", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("TOKEN_KEY", "");
        getScholarshipInfoById(ScholarId, token);
        return view;
    }


    public void getScholarshipInfoById(String scholarShipId, String token) {
        Call<ScholarShipFormModel> call = Repositry.getInstance().getCommentsService().getScholarshipById(scholarShipId, "Bearer " + token);
        call.enqueue(new Callback<ScholarShipFormModel>() {
            @Override
            public void onResponse(Call<ScholarShipFormModel> call, Response<ScholarShipFormModel> response) {
                shcolarShipName.setText(response.body().getScholarShipName());
                scholarShipAmount.setText(response.body().getAmount());
                scholarShipInfo.setText(response.body().getInformation());

                deleteScholerShip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteScholerShipAmount();
                    }
                });
            }

            @Override
            public void onFailure(Call<ScholarShipFormModel> call, Throwable t) {

            }
        });
    }

    private void deleteScholerShipAmount() {
        deleteScholerShipById(ScholarId, token);
//        getScholarshipInfoById(ScholarId , token);

        if(getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0)
        {
            getActivity().getSupportFragmentManager().popBackStackImmediate();
        }
    }


    public void deleteScholerShipById(String id, String token) {
        Call<String> call = Repositry.getInstance().getCommentsService().deleteScholarShipById(id, "Bearer " + token);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(getContext(), "Successfully Deleted Scholarship" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), "Delete Failed" , Toast.LENGTH_SHORT).show();
            }
        });
    }


}