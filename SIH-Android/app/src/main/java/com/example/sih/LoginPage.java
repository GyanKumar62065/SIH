package com.example.sih;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sih.Admin.Admin;
import com.example.sih.Affliator.Affiliator;
import com.example.sih.Employee.Employee;
import com.example.sih.Network.Repositry;
import com.example.sih.Students.Student;
import com.example.sih.college.Collage;
import com.example.sih.model.LoginRequest;
import com.example.sih.model.UsersResponse;
import com.example.sih.register.CollageRegister;
import com.example.sih.register.StudentRegister;

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
        setContentView(R.layout.dummy_file);

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
                //**********************
//                emailId = "student@gmail.com";
//                passwordTxt = "123";
//                ************************************
                if(check)
                {
                    login(new LoginRequest(emailId, passwordTxt));
                    editor.putString("EMAIL_ID", emailId);
                    editor.apply();
                }else
                {
                    Toast.makeText(LoginPage.this, "Please click on check box", Toast.LENGTH_SHORT).show();

                }
                
            }
        });
    }


    public void login(LoginRequest loginRequest) {
        Call<LoginRequest> call = Repositry.getInstance().getCommentsService().login(loginRequest);
        call.enqueue(new Callback<LoginRequest>() {
            @Override
            public void onResponse(Call<LoginRequest> call, Response<LoginRequest> response) {
                Log.e("GET_TOKEN", "" + response.body().getToken());


                try {
                    token = response.body().getToken();
                    getUserInfo(emailId, response.body().getToken());
                    editor.putString("TOKEN_KEY", token);
                    editor.apply();
                    Toast.makeText(LoginPage.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Log.e("GETTING TOKEN EXCEPTION" , "" + e.toString());
                }
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
                try {
                    Log.e("GETUSER_DATA_TYPE", "" + response.body().getAuthorities().get(0).getAuthority());
                    Log.e("GETUSER_ID", "" + response.body().getUserId());
                    userId = String.valueOf(response.body().getUserId());
                    editor.putString("USER_ID", userId);
                    editor.apply();
                    userType = response.body().getAuthorities().get(0).getAuthority().toLowerCase();
                    editor.putString("USER_ROLE" , userType.toString());
                    goToActivity(userType);
                }catch (Exception e)
                {
                    Log.e("GETTING USER DATA" , "" + e.toString());
                }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                Toast.makeText(LoginPage.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("EXCEPTION " , "" + t.toString());
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
            startActivity(new Intent(LoginPage.this, Collage.class));
        }
        else if(userType.equals("student")) {
            startActivity(new Intent(LoginPage.this, Student.class));
        }
        else {
            Toast.makeText(LoginPage.this, "Worng User", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    public void studentRegister(View view) {
        Intent intent = new Intent(LoginPage.this , StudentRegister.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void collegeRegister(View view) {
        Intent intent = new Intent(LoginPage.this , CollageRegister.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void forgetPassword(View view) {

    }

    boolean check;
    public void checked(View view) {
        check = ((CheckBox )view).isChecked();
    }
}
