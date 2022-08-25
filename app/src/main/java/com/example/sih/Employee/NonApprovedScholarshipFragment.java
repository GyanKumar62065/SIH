package com.example.sih.Employee;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sih.Network.Repositry;
import com.example.sih.R;
import com.example.sih.model.ScholarshipStudentFormModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NonApprovedScholarshipFragment extends Fragment {

    List<ScholarshipStudentFormModel>list = new ArrayList<>();

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
        View view = inflater.inflate(R.layout.fragment_non_approved_scholarship, container, false);

//        list.add(new ScholarshipStudentFormModel());
//        list.add(new ScholarshipStudentFormModel());
//        list.add(new ScholarshipStudentFormModel());
//        list.add(new ScholarshipStudentFormModel());
//        list.add(new ScholarshipStudentFormModel());
//
//        RecyclerView recyclerView = view.findViewById(R.id.recyclerNonApprovedItems);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        NonApprovedScholarshipAdapter adapter = new NonApprovedScholarshipAdapter(list);
//        recyclerView.setAdapter(adapter);


        return view;
    }

    public void getAllPendingFromsOfStudent(String token)
    {
        Call<List<ScholarshipStudentFormModel>> call = Repositry.getInstance().getCommentsService().getAllPendingForms("Baerer " + token);
        call.enqueue(new Callback<List<ScholarshipStudentFormModel>>() {
            @Override
            public void onResponse(Call<List<ScholarshipStudentFormModel>> call, Response<List<ScholarshipStudentFormModel>> response) {
                response.body();
                Log.e("" , "");
            }

            @Override
            public void onFailure(Call<List<ScholarshipStudentFormModel>> call, Throwable t) {

            }
        });
    }


}