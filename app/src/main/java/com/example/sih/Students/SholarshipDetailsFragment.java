package com.example.sih.Students;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.sih.Network.Repositry;
import com.example.sih.R;
import com.example.sih.model.ScholarShipFormModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SholarshipDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SholarshipDetailsFragment extends Fragment {

    String scholarId;
    TextView shcolarShipName, scholarShipAmount, scholarShipInfo;
    Button applyScholerShip;
    String token;


    public SholarshipDetailsFragment() {
        // Required empty public constructor
    }


    public static SholarshipDetailsFragment newInstance(String id) {
        SholarshipDetailsFragment fragment = new SholarshipDetailsFragment();
        Bundle args = new Bundle();
        args.putString("SCHOLAR_ID", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            scholarId = getArguments().getString("SCHOLAR_ID");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sholarship_details, container, false);

        shcolarShipName = view.findViewById(R.id.shcolarShipName);
        scholarShipAmount = view.findViewById(R.id.scholarShipAmount);
        scholarShipInfo = view.findViewById(R.id.scholarShipInfo);
        applyScholerShip = view.findViewById(R.id.applyScholerShip);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("TOKEN_FILE", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("TOKEN_KEY", "");
        getScholarshipInfoById(scholarId, token);
        applyScholerShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyScholerShipAmount();
            }
        });

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


            }

            @Override
            public void onFailure(Call<ScholarShipFormModel> call, Throwable t) {

            }
        });
    }

    private void applyScholerShipAmount() {

        Fragment fragment = ScholarShipStudentFormFragment.newInstance(scholarId);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container , fragment);
        ft.addToBackStack(null);
        ft.commit();

    }


}