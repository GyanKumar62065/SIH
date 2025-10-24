package com.example.sih.Employee;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sih.Network.Repositry;
import com.example.sih.R;
import com.example.sih.model.ScholarShipFormModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewScholershipFragment extends Fragment implements LetestScholershipAdapter.ItemClickListener {

    List<ScholarShipFormModel> arrayList = new ArrayList<>();
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    LetestScholershipAdapter letestScholershipAdapter;

    public NewScholershipFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_new_scholership, container, false);


        floatingActionButton = view.findViewById(R.id.createNewScholership);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new NewScholershipFormFragment());
            }
        });

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("TOKEN_FILE", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("TOKEN_KEY", "");
        String userId = sharedPreferences.getString("USER_ID", "");

        getScholarshipInfo(userId, token);
        recyclerView = view.findViewById(R.id.recycler_scholership_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        letestScholershipAdapter = new LetestScholershipAdapter( this);

        return view;
    }

    public void getScholarshipInfo(String userId, String token) {
        Call<List<ScholarShipFormModel>> call = Repositry.getInstance().getCommentsService().getScholarship("Bearer " + token);
        call.enqueue(new Callback<List<ScholarShipFormModel>>() {
            @Override
            public void onResponse(Call<List<ScholarShipFormModel>> call, Response<List<ScholarShipFormModel>> response) {
                arrayList = response.body();

                letestScholershipAdapter.setArrayList(arrayList);
                recyclerView.setAdapter(letestScholershipAdapter);
            }

            @Override
            public void onFailure(Call<List<ScholarShipFormModel>> call, Throwable t) {

            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, new NewScholershipFormFragment(), "Form");
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onItemClick(String Id) {
        Fragment fragment = ScholarshipInfo.newInstance(Id);

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.hide(getActivity().getSupportFragmentManager().findFragmentByTag("Scholarship"));
        ft.add(R.id.container , fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}