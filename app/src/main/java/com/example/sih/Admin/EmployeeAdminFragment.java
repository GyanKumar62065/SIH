package com.example.sih.Admin;

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
import com.example.sih.adapter.EmployeeAdminAdapter;
import com.example.sih.model.UsersResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EmployeeAdminFragment extends Fragment implements EmployeeAdminAdapter.ItemClickListener {


    FloatingActionButton createEmployee;
    RecyclerView recyclerView;
    List<UsersResponse> employeeAllUserData = new ArrayList<>();
    EmployeeAdminAdapter employeeAdminAdapter ;

    public EmployeeAdminFragment() {
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
        View view = inflater.inflate(R.layout.fragment_employee_admin, container, false);
        createEmployee = view.findViewById(R.id.createEmployee);

        //**************************************
        lodingEmployeeData(view);
        //**************************************
        createEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new CreateEmpolyeeFormFragment());
            }
        });


        return view;
    }

    private void lodingEmployeeData(View view) {

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("TOKEN_FILE", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("USER_ID", "");
        String token = sharedPreferences.getString("TOKEN_KEY", "");
        Log.e("USER_ID_ADMIN", "" + userId);

        getEmployeeUserInfo(userId, token);

        recyclerView = view.findViewById(R.id.recycler_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        employeeAdminAdapter = new EmployeeAdminAdapter(this);
//        employeeAdminAdapter = new EmployeeAdminAdapter(employeeAllUserData , this);
//        recyclerView.setAdapter(employeeAdminAdapter);
    }

    public void getEmployeeUserInfo(String id, String token) {
        Call<List<UsersResponse>> call = Repositry.getInstance().getCommentsService().getEmployeeUserData(id, "Bearer " + token);
        call.enqueue(new Callback<List<UsersResponse>>() {
            @Override
            public void onResponse(Call<List<UsersResponse>> call, Response<List<UsersResponse>> response) {
                Log.e("GET_EMPLOYEE_LIST", "" + response.body().get(0).getAuthorities().get(0).getAuthority());
                employeeAllUserData = response.body();
                employeeAdminAdapter.setData(employeeAllUserData);
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

    @Override
    public void onItemClick(String emailId) {
//        Fragment fragment = new UserDataFragment();
        Fragment fragment = UserDataFragment.newInstance(emailId);

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.container , fragment);
        ft.hide(getActivity().getSupportFragmentManager().findFragmentByTag("EMPLOYEE_FRAGMENT"));
        ft.add(R.id.container , fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}