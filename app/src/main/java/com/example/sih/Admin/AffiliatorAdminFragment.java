package com.example.sih.Admin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sih.Network.Repositry;
import com.example.sih.R;
import com.example.sih.adapter.EmployeeAdminAdapter;
import com.example.sih.model.UsersResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AffiliatorAdminFragment extends Fragment {



    public AffiliatorAdminFragment() {
        // Required empty public constructor
    }

    FloatingActionButton createAffiliator;
    RecyclerView recyclerView;
    List<UsersResponse> affiliatorAllUserData;
    EmployeeAdminAdapter employeeAdminAdapter = new EmployeeAdminAdapter();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_affiliator_admin, container, false);
        createAffiliator = view.findViewById(R.id.createAffliator);

        lodingEmployeeData(view);

        createAffiliator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new CreateAffiliatorFormFragment());
            }
        });

        return view;
    }

    private void lodingEmployeeData(View view) {

        recyclerView = view.findViewById(R.id.recycler_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("TOKEN_FILE", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("USER_ID", "");
        String token = sharedPreferences.getString("TOKEN_KEY", "");
        Log.e("USER_ID_ADMIN", "" + userId);
        getAffiliatorUserInfo(userId, token);
    }

    public void getAffiliatorUserInfo(String id, String token) {
        Call<List<UsersResponse>> call = Repositry.getInstance().getCommentsService().getAffiliatorUserData(id, "Bearer " + token);
        call.enqueue(new Callback<List<UsersResponse>>() {
            @Override
            public void onResponse(Call<List<UsersResponse>> call, Response<List<UsersResponse>> response) {
                Log.e("GET_EMPLOYEE_LIST", "" + response.body().get(0).getAuthorities().get(0).getAuthority());
                affiliatorAllUserData = response.body();
                employeeAdminAdapter = new EmployeeAdminAdapter(affiliatorAllUserData);
                recyclerView.setAdapter(employeeAdminAdapter);
            }
            @Override
            public void onFailure(Call<List<UsersResponse>> call, Throwable t) {

            }
        });
    }

    private void loadFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
    }
}