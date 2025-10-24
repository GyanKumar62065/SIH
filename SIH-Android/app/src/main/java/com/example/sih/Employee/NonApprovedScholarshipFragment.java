package com.example.sih.Employee;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sih.Network.Repositry;
import com.example.sih.R;
import com.example.sih.adapter.NonApprovedScholarshipAdapter;
import com.example.sih.model.ScholarshipStudentFormModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NonApprovedScholarshipFragment extends Fragment implements NonApprovedScholarshipAdapter.ItemClickListener {

    List<ScholarshipStudentFormModel> list = new ArrayList<>();
    NonApprovedScholarshipAdapter nonApprovedScholarshipAdapter;
    RecyclerView recyclerView;

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
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("TOKEN_FILE", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("TOKEN_KEY", "");
        recyclerView = view.findViewById(R.id.recyclerNonApprovedItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        nonApprovedScholarshipAdapter = new NonApprovedScholarshipAdapter(this);
        getAllPendingFromsOfStudent(token);
        Log.e("LIST DATA", "" + list.toString());
        return view;
    }

    public void getAllPendingFromsOfStudent(String token) {
        Call<List<ScholarshipStudentFormModel>> call = Repositry.getInstance().getCommentsService().getAllPendingForms("Bearer " + token);
        call.enqueue(new Callback<List<ScholarshipStudentFormModel>>() {
            @Override
            public void onResponse(Call<List<ScholarshipStudentFormModel>> call, Response<List<ScholarshipStudentFormModel>> response) {
                response.body();
                if (response.isSuccessful()) {
                    Log.e("LIST DATA SUCCESS ", "200");

                    list = response.body();
//                    nonApprovedScholarshipAdapter = new NonApprovedScholarshipAdapter(response.body());
                    nonApprovedScholarshipAdapter.setList(list);
                    recyclerView.setAdapter(nonApprovedScholarshipAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<ScholarshipStudentFormModel>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onItemClick(int Id) {

        Fragment fragment = NonApprovedStudentScholarshipDataFragment.newInstance(String.valueOf(Id));
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.hide(getActivity().getSupportFragmentManager().findFragmentByTag("LETEST APPLICATIONS"));
        ft.add(R.id.container , fragment);
        ft.addToBackStack(null);
        ft.commit();

    }
}