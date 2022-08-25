package com.example.sih.Students;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.sih.Network.Repositry;
import com.example.sih.R;
import com.example.sih.model.ScholarshipStudentFormModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScholarShipStudentFormFragment extends Fragment {


    String formId;
    EditText studentName, studentEmailId, studentContactNo, studentAddress, studentCity, studentState, studentId, collegeName, collegeId, DOR, studentAadharNo, studentSecondryPercentage, studentSeniorSecPer;
    Button saveStudentData;
    String token;

    public ScholarShipStudentFormFragment() {
        // Required empty public construct
    }

    public static ScholarShipStudentFormFragment newInstance(String param1) {
        ScholarShipStudentFormFragment fragment = new ScholarShipStudentFormFragment();
        Bundle args = new Bundle();
        args.putString("SCHOLAR_FORM_ID", param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            formId = getArguments().getString("SCHOLAR_FORM_ID");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scholar_ship_student_form, container, false);

        studentName = view.findViewById(R.id.student_Name);
        studentEmailId = view.findViewById(R.id.studentEmailId);
        studentContactNo = view.findViewById(R.id.studentContactNo);
        studentAddress = view.findViewById(R.id.studentAddress);
        studentCity = view.findViewById(R.id.studentCity);
        studentState = view.findViewById(R.id.studentState);
        studentId = view.findViewById(R.id.studentId);
        collegeName = view.findViewById(R.id.studentName);
        collegeId = view.findViewById(R.id.collegeId);
        DOR = view.findViewById(R.id.DOR);
        studentAadharNo = view.findViewById(R.id.studentAadharNo);
        studentSecondryPercentage = view.findViewById(R.id.studentSecondryPercentage);
        studentSeniorSecPer = view.findViewById(R.id.studentSeniorSecPer);
        saveStudentData = view.findViewById(R.id.saveStudentData);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("TOKEN_FILE", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("TOKEN_KEY", "");

        saveStudentData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process();
            }
        });
        return view;
    }

    private void process() {
        String name = studentName.getText().toString();
        String emailId = studentEmailId.getText().toString();
        String contactNo = studentContactNo.getText().toString();
        String address = studentAddress.getText().toString();
        String city = studentCity.getText().toString();
        String state = studentState.getText().toString();
        String stuId = studentId.getText().toString();
        String clgName = collegeName.getText().toString();
        String clgId = collegeId.getText().toString();
        String aadhar = studentAadharNo.getText().toString();
        String secondryPercentage = studentSecondryPercentage.getText().toString();
        String seniorSecondryPercentage = studentSeniorSecPer.getText().toString();

        setScholarshipFormDetrails(new ScholarshipStudentFormModel(
                name,
                aadhar,
                Float.parseFloat(seniorSecondryPercentage),
                Float.parseFloat(secondryPercentage),
                Integer.parseInt(clgId),
                Integer.parseInt(formId),
                clgName,
                address,
                emailId,
                contactNo,
                city,
                state,
                Integer.parseInt(stuId)
        ));
    }

    private void initBlock() {

    }

    public void setScholarshipFormDetrails(ScholarshipStudentFormModel st) {
        Call<ScholarshipStudentFormModel> call = Repositry.getInstance().getCommentsService().setScholarshipFrom(st, "Bearer " + token);
        call.enqueue(new Callback<ScholarshipStudentFormModel>() {
            @Override
            public void onResponse(Call<ScholarshipStudentFormModel> call, Response<ScholarshipStudentFormModel> response) {
                Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ScholarshipStudentFormModel> call, Throwable t) {

            }
        });
    }
}