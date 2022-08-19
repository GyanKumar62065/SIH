package com.example.sih.Employee;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.sih.R;
import com.example.sih.RetrofitService;
import com.example.sih.myapi;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewScholershipFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewScholershipFormFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewScholershipFormFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewScholershipFormFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewScholershipFormFragment newInstance(String param1, String param2) {
        NewScholershipFormFragment fragment = new NewScholershipFormFragment();
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

    Button create_scholership;
    EditText scholership_name_form , scholership_amount_form,scholership_info_form;

    String url = "http://192.168.1.12:9090/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_scholership_form, container, false);

        create_scholership = view.findViewById(R.id.create_scholership);
        scholership_name_form = view.findViewById(R.id.scholership_name_form);
        scholership_amount_form = view.findViewById(R.id.scholership_amount_form);
        scholership_info_form = view.findViewById(R.id.scholership_info_form);

        create_scholership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process();
                if(getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0)
                {
                    getActivity().getSupportFragmentManager().popBackStackImmediate();
                }
            }
        });
        return view;
    }

    public void process() {

        /*
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String name = String.valueOf(scholership_name_form.getText());
        String amount = String.valueOf(scholership_amount_form.getText());
        String info = String.valueOf(scholership_info_form.getText());

        myapi api = retrofit.create(myapi.class);
        Call<model> call = api.addData(new model(name , amount , info));
        call.enqueue(new Callback<model>() {
            @Override
            public void onResponse(Call<model> call, Response<model> response) {
                Toast.makeText(getContext(), "Form Created", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<model> call, Throwable t) {
                Toast.makeText(getContext(), "Form Failed", Toast.LENGTH_SHORT).show();
                Logger.getLogger(NewScholershipFormFragment.class.getName()).log(Level.SEVERE , "Error occurred" , t);
            }
        });

*/
        String name = String.valueOf(scholership_name_form.getText());
        String amount = String.valueOf(scholership_amount_form.getText());
        String info = String.valueOf(scholership_info_form.getText());

        if(name.endsWith("")  || amount.endsWith("") || info.endsWith(""))
        {
            Toast.makeText(getContext(), "Please Fill Form Properly", Toast.LENGTH_SHORT).show();
            return;
        }

        (new RetrofitService()).getRetrofit().create(myapi.class).addData(new model(name , amount , info)).enqueue(new Callback<model>() {
            @Override
            public void onResponse(Call<model> call, Response<model> response) {
                Toast.makeText(getContext(), "Form Created", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<model> call, Throwable t) {
                Toast.makeText(getContext(), "Form Failed", Toast.LENGTH_SHORT).show();
                Logger.getLogger(NewScholershipFormFragment.class.getName()).log(Level.SEVERE , "Error occurred" , t);
            }
        });

    }
}