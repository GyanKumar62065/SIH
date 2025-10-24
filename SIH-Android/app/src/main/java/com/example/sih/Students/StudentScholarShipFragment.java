package com.example.sih.Students;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sih.Employee.LetestScholershipAdapter;
import com.example.sih.Network.Repositry;
import com.example.sih.R;
import com.example.sih.model.ScholarShipFormModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentScholarShipFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentScholarShipFragment extends Fragment implements LetestScholershipAdapter.ItemClickListener{

    List<ScholarShipFormModel> arrayList = new ArrayList<>();
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    LetestScholershipAdapter letestScholershipAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudentScholarShipFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentScholarShipFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentScholarShipFragment newInstance(String param1, String param2) {
        StudentScholarShipFragment fragment = new StudentScholarShipFragment();
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
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_student_scholar_ship, container, false);

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


    @Override
    public void onItemClick(String Id) {
        Fragment fragment = SholarshipDetailsFragment.newInstance(Id);

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.hide(getActivity().getSupportFragmentManager().findFragmentByTag("STUDENTSHOLARSHIP_FRAGMENT"));
        ft.add(R.id.container , fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

}