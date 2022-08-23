package com.example.sih.Employee;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sih.Network.Repositry;
import com.example.sih.R;
import com.example.sih.adapter.NonApprovedScholarsipAdapter;
import com.example.sih.model.ScholarshipStudentFormModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NonApprovedScholarshipFragment extends Fragment implements NonApprovedScholarsipAdapter.ItemClickListener {


    RecyclerView recyclerView;
    NonApprovedScholarsipAdapter nonApprovedScholarsipAdapter;
    List<ScholarshipStudentFormModel> list = new ArrayList<>();

    public NonApprovedScholarshipFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_non_approved_scholarship, container, false);
//        recyclerView = view.findViewById(R.id.recyclerNonApprovedItems);
//
//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("TOKEN_FILE", Context.MODE_PRIVATE);
//        String token = sharedPreferences.getString("TOKEN_KEY", "");
//
//        getAllPendingScholarshipStudentForm(token);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        nonApprovedScholarsipAdapter = new NonApprovedScholarsipAdapter(this);

        return view;
    }

    @Override
    public void onItemClick(String emailId) {

    }

    public void getAllPendingScholarshipStudentForm(String token) {
        Call<List<ScholarshipStudentFormModel>> call = Repositry.getInstance().getCommentsService().getAllPendingForms("Bearer " + token);
        call.enqueue(new Callback<List<ScholarshipStudentFormModel>>() {
            @Override
            public void onResponse(Call<List<ScholarshipStudentFormModel>> call, Response<List<ScholarshipStudentFormModel>> response) {
                Log.e("NON_APPROVED FORMS" , "SUCSSESS");
                list = response.body();
                nonApprovedScholarsipAdapter.setData(list);
                recyclerView.setAdapter(nonApprovedScholarsipAdapter);
            }
            @Override
            public void onFailure(Call<List<ScholarshipStudentFormModel>> call, Throwable t) {

            }
        });
    }
}