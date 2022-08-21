package com.example.sih;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sih.Network.Repositry;
import com.example.sih.model.RegistationRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        initBlock();
    }



    private void initBlock(){
        genrateToken(new RegistationRequest("Ajit","123456789","ajit@gmail.com","USER"));
    }




    public void genrateToken(RegistationRequest registationRequest){
        Call<RegistationRequest> call = Repositry.getInstance().getCommentsService().registation(registationRequest);
        call.enqueue(new Callback<RegistationRequest>() {
            @Override
            public void onResponse(Call<RegistationRequest> call, Response<RegistationRequest> response) {

            }
            @Override
            public void onFailure(Call<RegistationRequest> call, Throwable t) {
            }
        });
    }

}