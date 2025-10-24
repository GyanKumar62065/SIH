package com.example.sih.Employee;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.sih.Network.Repositry;
import com.example.sih.R;
import com.example.sih.model.UsersResponse;

import retrofit2.Call;
import retrofit2.Callback;

public class DashboardEmployeeFragment extends Fragment {


    public DashboardEmployeeFragment() {
        // Required empty public constructor
    }


    TextView adminName, adminDetails;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_admin, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("TOKEN_FILE", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("TOKEN_KEY", "");
        String emailId = sharedPreferences.getString("EMAIL_ID", "");
        adminName = view.findViewById(R.id.adminName);
        adminDetails = view.findViewById(R.id.adminDetails);
        getUserInfo(emailId, token);
        return view;
    }

    public void getUserInfo(String id, String token) {
        Call<UsersResponse> call = Repositry.getInstance().getCommentsService().getData(id, "Bearer " + token);
        call.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, retrofit2.Response<UsersResponse> response) {
                Log.e("GETUSER_DATA_TYPE___", "" + response.body().getAuthorities().get(0).getAuthority());
//                userType = String.valueOf(response.body().getAuthorities().get(0).getAuthority()).toLowerCase(Locale.ROOT);

                if (!(String.valueOf(response.body().getName())).equals("null"))
                    adminName.setText(String.valueOf(response.body().getName()));
                else
                    adminName.setText("Username");

                adminDetails.setText(
                        "\n\nRole : " + String.valueOf(response.body().getAuthorities().get(0).getAuthority()).toLowerCase() + "\n\n" +
                                "Name : " + response.body().getName() + "\n\n" +
                                "Email : " + response.body().getUsername() + "\n\n" +
                                "Contact No : " + response.body().getContactNumber() + "\n\n" +
                                "Address Line 1 : " + response.body().getAddressLine1() + "\n\n" +
                                "Address Line 2 : " + response.body().getAddressLine2() + "\n\n"
                );
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}