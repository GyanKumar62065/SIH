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

public class CollageRegister extends AppCompatActivity {


    EditText name , stuEmail , studentPassword , addressLine1 , addressLine2 , contactNumber;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collage_register);

        name = findViewById(R.id.collegeName);
        stuEmail = findViewById(R.id.collegeEmail);
        studentPassword = findViewById(R.id.collegePassword);
        addressLine1 = findViewById(R.id.collegeAddressLine1);
        addressLine2 = findViewById(R.id.collegeAddressLine2);
        contactNumber = findViewById(R.id.collegeContact);
        register = findViewById(R.id.collegeRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initBlock();
            }
        });

    }

    private void initBlock(){
        String studentName = name.getText().toString();
        String role = "college";
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
                        Intent intent = new Intent(CollageRegister.this , LoginPage.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(CollageRegister.this, "Something Went Wrong\nPlease Try Aging ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {
                    Log.e("" , "" + e.toString());
                }

            }
            @Override
            public void onFailure(Call<RegistationRequest> call, Throwable t) {
                Toast.makeText(CollageRegister.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}