package com.example.sih.register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sih.LoginPage;
import com.example.sih.Network.Repositry;
import com.example.sih.R;
import com.example.sih.model.RegistationRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentRegister extends AppCompatActivity {

    EditText name , stuEmail , studentPassword , addressLine1 , addressLine2 , contactNumber;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);

        name = findViewById(R.id.studentname);
        stuEmail = findViewById(R.id.studentEmail);
        studentPassword = findViewById(R.id.studentPassword);
        addressLine1 = findViewById(R.id.studentAddressLine1);
        addressLine2 = findViewById(R.id.studentAddressLine2);
        contactNumber = findViewById(R.id.studentContact);
        register = findViewById(R.id.studentRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initBlock();
            }
        });


    }


    private void initBlock(){
        String studentName = name.getText().toString();
        String role = "student";
        String contact = contactNumber.getText().toString();
        String ad1 = addressLine1.getText().toString();
        String ad2 = addressLine2.getText().toString();
        String email  = stuEmail.getText().toString();
        String password = studentPassword.getText().toString();
        genrateToken(new RegistationRequest(studentName , role , contact , ad1 , ad2 , email , email , password));
    }



    public void genrateToken(RegistationRequest registationRequest){
        Call<RegistationRequest> call = Repositry.getInstance().getCommentsService().registation(registationRequest);
        call.enqueue(new Callback<RegistationRequest>() {
            @Override
            public void onResponse(Call<RegistationRequest> call, Response<RegistationRequest> response) {
                try {
                    if(response.isSuccessful())
                    {
                        Intent intent = new Intent(StudentRegister.this , LoginPage.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(StudentRegister.this, "Something Went Wrong\nPlease Try Aging ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {
                    Log.e("" , "" + e.toString());
                }

            }
            @Override
            public void onFailure(Call<RegistationRequest> call, Throwable t) {
                Toast.makeText(StudentRegister.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}