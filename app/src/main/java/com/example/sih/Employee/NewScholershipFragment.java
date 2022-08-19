package com.example.sih.Employee;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sih.R;
import com.example.sih.RetrofitService;
import com.example.sih.myapi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewScholershipFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewScholershipFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewScholershipFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewScholershipFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewScholershipFragment newInstance(String param1, String param2) {
        NewScholershipFragment fragment = new NewScholershipFragment();
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

    ArrayList<LetestScholershipForm>arrayList = new ArrayList<>();
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_scholership, container, false);

        recyclerView = view.findViewById(R.id.recycler_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        arrayList.add(new LetestScholershipForm("Gyan" , "8000", "Only For Boys"));
        arrayList.add(new LetestScholershipForm("Gyan" , "8000", "Only For Boys"));
        arrayList.add(new LetestScholershipForm("Gyan" , "8000", "Only For Boys"));
        arrayList.add(new LetestScholershipForm("Gyan" , "8000", "Only For Boys"));

        LetestScholershipAdapter letestScholershipAdapter = new LetestScholershipAdapter(arrayList);
        recyclerView.setAdapter(letestScholershipAdapter);

        //***************************************************************

        RetrofitService retrofitService = new RetrofitService();
        Retrofit retrofit = retrofitService.getRetrofit();
        myapi api = retrofit.create(myapi.class);

        Call<List<model>> data = api.getScholarshipData();

        data.enqueue(new Callback<List<model>>() {
            @Override
            public void onResponse(Call<List<model>> call, Response<List<model>> response) {
                populateListView(response.body());
            }

            @Override
            public void onFailure(Call<List<model>> call, Throwable t) {
                Toast.makeText(getContext() , "Scholarship Data Loding Failed", Toast.LENGTH_SHORT).show();
            }
        });








        //***************************************************************




        FloatingActionButton floatingActionButton = view.findViewById(R.id.createNewScholership);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new NewScholershipFormFragment());
            }
        });

        return view;
    }

    private void populateListView(List<model> body) {



    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container , new NewScholershipFormFragment() , "Form");
        ft.addToBackStack(null);
        ft.commit();
    }
}