package com.example.sih.Employee;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.sih.Network.Repositry;
import com.example.sih.R;
import com.example.sih.model.ScholarshipStudentFormModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NonApprovedStudentScholarshipDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NonApprovedStudentScholarshipDataFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    TextView scholarshipDetais;
    Button downLoadReletedDocument, approveForm, rejectForm;
    ScholarshipStudentFormModel details = new ScholarshipStudentFormModel();
    String userId, userRole, token;
    private String applicationId;

    public NonApprovedStudentScholarshipDataFragment() {
        // Required empty public constructor
    }

    public static NonApprovedStudentScholarshipDataFragment newInstance(String param1) {
        NonApprovedStudentScholarshipDataFragment fragment = new NonApprovedStudentScholarshipDataFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            applicationId = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_non_approved_student_scholarship_data, container, false);

        token = getActivity().getSharedPreferences("TOKEN_FILE", Context.MODE_PRIVATE).getString("TOKEN_KEY", "");
        userId = getActivity().getSharedPreferences("TOKEN_FILE", Context.MODE_PRIVATE).getString("USER_ID", "");
        userRole = getActivity().getSharedPreferences("TOKEN_FILE", Context.MODE_PRIVATE).getString("USER_ROLE", "");
        scholarshipDetais = view.findViewById(R.id.setNonApprovedScholarshipStudentData);
        downLoadReletedDocument = view.findViewById(R.id.setNonApprovedScholarshipStudentReletedDocuments);
        if (userRole.equals("student")) {
            getScholarshipDataByApplicationId(applicationId);
            approveForm.setVisibility(View.GONE);
//            Now we are useing this button as Delete Scholarship Form By Student
            rejectForm.setText("Drop Application");
            rejectForm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dropApplication();
                }
            });

        } else {

            approveForm = view.findViewById(R.id.setNonApprovedScholarshipStudentApprove);
            rejectForm = view.findViewById(R.id.setNonApprovedScholarshipStudentReject);

            downLoadReletedDocument.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    downloadReletedDoucument();
                }
            });

            approveForm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    approveScholarship();
                }
            });

            rejectForm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rejectScholarship();
                }
            });

            getScholarshipDataByApplicationId(applicationId);
        }

        return view;
    }

    private void dropApplication() {
        deleteScholarshipForm();
    }

    private void deleteScholarshipForm() {
        Call<ScholarshipStudentFormModel> call = Repositry.getInstance().getCommentsService().deleteScholarshipForm(applicationId, "Bearer " + token);
        call.enqueue(new Callback<ScholarshipStudentFormModel>() {
            @Override
            public void onResponse(Call<ScholarshipStudentFormModel> call, Response<ScholarshipStudentFormModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Form Dropped", Toast.LENGTH_SHORT).show();
                    if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0)
                        getActivity().getSupportFragmentManager().popBackStackImmediate();
                }
            }

            @Override
            public void onFailure(Call<ScholarshipStudentFormModel> call, Throwable t) {

            }
        });
    }

    private void rejectScholarship() {
        markStudentRejected(userId, applicationId);
    }

    private void approveScholarship() {
        markStudentScholared(userId, applicationId);
    }

    private void downloadReletedDoucument() {
    }

    public void getScholarshipDataByApplicationId(String id) {
        Call<ScholarshipStudentFormModel> call = Repositry.getInstance().getCommentsService().getScholarshipDataByApplicationId(id, "Bearer " + token);

        call.enqueue(new Callback<ScholarshipStudentFormModel>() {
            @Override
            public void onResponse(Call<ScholarshipStudentFormModel> call, Response<ScholarshipStudentFormModel> response) {
                details = response.body();
                String status;
                if (response.body().getScholared())
                    status = "Scholared";
                else if (response.body().getPending())
                    status = "Panding";
                else status = "Rejected";
                scholarshipDetais.setText(
                        "Form Status : " + status + "\n\n\n" +
                                "Student Details\n\n" +
                                "Name : " + details.getStudentName() + "\n\n" +
                                "Email : " + details.getEmailId() + "\n\n" +
                                "Contact Number : " + details.getStudentContactNumber() + "\n\n" +
                                "Address : " + details.getStudentAddress() + "\n\n" +
                                "City : " + details.getStudentCity() + "\n\n" +
                                "State : " + details.getStudentState() + "\n\n" +
                                "Student Id : " + details.getStudentId() + "\n\n" +
                                "Secondry Percentage : " + details.getSecondaryPercentage() + "\n\n" +
                                "Senior Secondry Percentage : " + details.getSeniorSecondaryPercentage() + "\n\n" +
                                "Aadhar Number : " + details.getGovtId() + "\n\n\n" +
                                "College Details\n\n" +
                                "College Name : " + details.getCollegeName() + "\n\n" +
                                "College Id : " + details.getCollegeId() + "\n\n"
                );
                if (response.body().getScholared() || response.body().getScholared()) {
                    approveForm.setVisibility(View.GONE);
                    rejectForm.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<ScholarshipStudentFormModel> call, Throwable t) {

            }
        });
    }

    public void markStudentScholared(String userId, String applicationId) {
        Call<ScholarshipStudentFormModel> call = Repositry.getInstance().getCommentsService().setStudentApproved(userId, applicationId, "Bearer " + token);
        call.enqueue(new Callback<ScholarshipStudentFormModel>() {
            @Override
            public void onResponse(Call<ScholarshipStudentFormModel> call, Response<ScholarshipStudentFormModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Form Approved", Toast.LENGTH_SHORT).show();
                    if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
                        getActivity().getSupportFragmentManager().popBackStackImmediate();
                    }
                    approveForm.setVisibility(View.GONE);
                    rejectForm.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<ScholarshipStudentFormModel> call, Throwable t) {

            }
        });
    }

    public void markStudentRejected(String userId, String applicationId) {
        Call<ScholarshipStudentFormModel> call = Repositry.getInstance().getCommentsService().setStudentRejected(userId, applicationId, "Bearer " + token);
        call.enqueue(new Callback<ScholarshipStudentFormModel>() {
            @Override
            public void onResponse(Call<ScholarshipStudentFormModel> call, Response<ScholarshipStudentFormModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Form Approved", Toast.LENGTH_SHORT).show();
                    if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
                        getActivity().getSupportFragmentManager().popBackStackImmediate();
                    }
                    approveForm.setVisibility(View.GONE);
                    rejectForm.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ScholarshipStudentFormModel> call, Throwable t) {

            }
        });
    }

}