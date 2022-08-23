package com.example.sih;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sih.Admin.Admin;
import com.example.sih.Affliator.Affiliator;
import com.example.sih.Employee.Employee;
import com.example.sih.Network.Repositry;
import com.example.sih.Students.Student;
import com.example.sih.model.LoginRequest;
import com.example.sih.model.UsersResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPage extends AppCompatActivity {

    ArrayList<String> rolesArr = new ArrayList<>();

    EditText username, password;
    Button loginBtn;
    String userType, emailId, passwordTxt;
    String userId;
    String token = "";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView profileName, emailIdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        username = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login_btn);

        sharedPreferences = getSharedPreferences("TOKEN_FILE", MODE_PRIVATE);
        editor = sharedPreferences.edit();


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                emailId = username.getText().toString();
                passwordTxt = password.getText().toString();
                login(new LoginRequest(emailId, passwordTxt)); // Pssing username and password
                editor.putString("EMAIL_ID", emailId);
                editor.apply();

//                profileName = findViewById(R.id.profileName);
//                emailIdd = findViewById(R.id.emailId);

//                Log.e("ADMIN>JAVA", "Success");

//                emailIdd.setText(emailId);
//                startActivity(new Intent(LoginPage.this, Admin.class));

//                NavigationView navigationView = findViewById(R.id.navigation_view);
//                View headerView = navigationView.getHeaderView(0);
//                emailIdd = headerView.findViewById(R.id.profileName);
//                profileName = headerView.findViewById(R.id.emailId);
//                emailIdd.setText(emailId);

            }
        });
    }


    public void login(LoginRequest loginRequest) {
        Call<LoginRequest> call = Repositry.getInstance().getCommentsService().login(loginRequest);
        call.enqueue(new Callback<LoginRequest>() {
            @Override
            public void onResponse(Call<LoginRequest> call, Response<LoginRequest> response) {
                Log.e("GET_TOKEN", "" + response.body().getToken());

                token = response.body().getToken();
                getUserInfo(emailId, response.body().getToken());
                //*******************************
                // Passing Token into ProfileAdminFragment

                editor.putString("TOKEN_KEY", token);
                editor.apply();

                //*******************************

            }

            @Override
            public void onFailure(Call<LoginRequest> call, Throwable t) {
            }
        });
    }

    public void getUserInfo(String id, String token) {
        Call<UsersResponse> call = Repositry.getInstance().getCommentsService().getData(id, "Bearer " + token);
        call.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, retrofit2.Response<UsersResponse> response) {
                Log.e("GETUSER_DATA_TYPE", "" + response.body().getAuthorities().get(0).getAuthority());
                Log.e("GETUSER_ID", "" + response.body().getUserId());
                userId = String.valueOf(response.body().getUserId());
                //**************************
                editor.putString("USER_ID", userId);
//                editor.putString("PROFILE_NAME" , response.body().getName().toString());
                editor.apply();
                //************
                userType = response.body().getAuthorities().get(0).getAuthority().toLowerCase();
//                profileName.setText(String.valueOf(response.body().getName()));
                goToActivity(userType);
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                Toast.makeText(LoginPage.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToActivity(String userType) {
        if (userType.equals("admin")) {
            startActivity(new Intent(LoginPage.this, Admin.class));
        } else if (userType.equals("affiliator")) {
            startActivity(new Intent(LoginPage.this, Affiliator.class));
        } else if (userType.equals("employee")) {
            startActivity(new Intent(LoginPage.this, Employee.class));
        } else if (userType.equals("college")) {
            startActivity(new Intent(LoginPage.this, Admin.class));
        }
        else if(userType.equals("student"))
        {
            startActivity(new Intent(LoginPage.this , Student.class));
        }
        else {
            Toast.makeText(LoginPage.this, "Worng User", Toast.LENGTH_SHORT).show();
        }
    }

    private void loginAsEmoloyee() {
        Intent intent = new Intent(LoginPage.this, Employee.class);

        startActivity(intent);
    }

    private void loginAsAdimn() {
        Intent intent = new Intent(LoginPage.this, Admin.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void loginAsAffliator() {
        Intent intent = new Intent(LoginPage.this, Affiliator.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void register(View view) {
        Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
//        super.onBackPressed();
    }
}
