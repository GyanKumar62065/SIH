package com.example.sih.Affliator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sih.Network.Repositry;
import com.example.sih.R;
import com.example.sih.adapter.FellowshipDataAdapter;
import com.example.sih.model.FellowshipModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApprovedFellowshipFragment extends Fragment implements FellowshipDataAdapter.ItemClickListener {

    List<FellowshipModel> list = new ArrayList<>();
    FellowshipDataAdapter fellowshipDataAdapter;
    RecyclerView recyclerView;

    public ApprovedFellowshipFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_approved_fellowship, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("TOKEN_FILE", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("TOKEN_KEY", "");
        recyclerView = view.findViewById(R.id.recyclerNonApprovedFellowship);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fellowshipDataAdapter = new FellowshipDataAdapter(this);
        getAllPendingFromsOfStudent(token);
        return view;
    }

    private void getAllPendingFromsOfStudent(String token) {
        Call<List<FellowshipModel>> call = Repositry.getInstance().getCommentsService().getAllApprovedFellowshipFroms("Bearer " + token);
        call.enqueue(new Callback<List<FellowshipModel>>() {
            @Override
            public void onResponse(Call<List<FellowshipModel>> call, Response<List<FellowshipModel>> response) {
                list = response.body();
                fellowshipDataAdapter.setList(list);
                recyclerView.setAdapter(fellowshipDataAdapter);
            }

            @Override
            public void onFailure(Call<List<FellowshipModel>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onItemClick(int Id) {
//        Fragment fragment = NonApprovedStudentScholarshipDataFragment.newInstance(String.valueOf(Id));
//        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//        ft.hide(getActivity().getSupportFragmentManager().findFragmentByTag("LETEST APPLICATIONS"));
//        ft.add(R.id.container, fragment);
//        ft.addToBackStack(null);
//        ft.commit();
    }
}