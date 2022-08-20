package com.example.sih.Admin;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileAdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileAdminFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView adminName, adminDetails;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileAdminFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileAdminFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileAdminFragment newInstance(String param1, String param2) {
        ProfileAdminFragment fragment = new ProfileAdminFragment();
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