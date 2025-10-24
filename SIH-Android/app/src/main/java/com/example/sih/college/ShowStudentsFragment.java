package com.example.sih.college;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sih.Network.Repositry;
import com.example.sih.R;
import com.example.sih.adapter.CollegeStudentDataViewAdapter;
import com.example.sih.model.ScholarshipStudentFormModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowStudentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowStudentsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    List<ScholarshipStudentFormModel> list = new ArrayList<>();
    RecyclerView recyclerView;
    CollegeStudentDataViewAdapter collegeStudentDataViewAdapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShowStudentsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShowStudentsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowStudentsFragment newInstance(String param1, String param2) {
        ShowStudentsFragment fragment = new ShowStudentsFragment();
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
        View view = inflater.inflate(R.layout.fragment_show_students, container, false);

        recyclerView = view.findViewById(R.id.recyclerStudentsForCollege);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        String token = getActivity().getSharedPreferences("TOKEN_FILE" , Context.MODE_PRIVATE).getString("TOKEN_KEY" , "");

//        gettingCollegeId(token);
//        getStudentScholarshipDataByCollege(id , token);
        return view;
    }

    public void getStudentScholarshipDataByCollege(String id, String token) {
        Call<List<ScholarshipStudentFormModel>> call = Repositry.getInstance().getCommentsService().getAllStudentDataByCollegeId(id, "Bearer " + token);
        call.enqueue(new Callback<List<ScholarshipStudentFormModel>>() {
            @Override
            public void onResponse(Call<List<ScholarshipStudentFormModel>> call, Response<List<ScholarshipStudentFormModel>> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    collegeStudentDataViewAdapter = new CollegeStudentDataViewAdapter(list);
                }
            }

            @Override
            public void onFailure(Call<List<ScholarshipStudentFormModel>> call, Throwable t) {

            }
        });

    }
}