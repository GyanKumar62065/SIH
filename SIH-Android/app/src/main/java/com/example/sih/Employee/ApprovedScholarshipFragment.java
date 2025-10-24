package com.example.sih.Employee;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ApprovedScholarshipFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ApprovedScholarshipFragment extends Fragment implements NonApprovedScholarshipAdapter.ItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ApprovedScholarshipFragment.
     */
    // TODO: Rename and change types and number of parameters

    List<ScholarshipStudentFormModel> list = new ArrayList<>();
    NonApprovedScholarshipAdapter nonApprovedScholarshipAdapter;
    RecyclerView recyclerView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public ApprovedScholarshipFragment() {
        // Required empty public constructor
    }

    public static ApprovedScholarshipFragment newInstance(String param1, String param2) {
        ApprovedScholarshipFragment fragment = new ApprovedScholarshipFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_approved_scholarship, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("TOKEN_FILE", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("TOKEN_KEY", "");
        recyclerView = view.findViewById(R.id.recyclerNonApprovedItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        nonApprovedScholarshipAdapter = new NonApprovedScholarshipAdapter(this);
//
        getAllPendingFromsOfStudent(token);
//
//        Log.e("LIST DATA", "" + list.toString());

        return view;
    }


    public void getAllPendingFromsOfStudent(String token) {
        Call<List<ScholarshipStudentFormModel>> call = Repositry.getInstance().getCommentsService().getAllApprovedForms("Bearer " + token);
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
        ft.add(R.id.container, fragment);
        ft.addToBackStack(null);
        ft.commit();

    }
}