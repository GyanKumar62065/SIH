package com.example.sih.Network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repositry {
    private static Repositry instance;
    private Service commentsService;

    public static Repositry getInstance() {
        if (instance == null) {
            instance = new Repositry();
        }
        return instance;
    }

    public Repositry() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebConstantUrl.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        commentsService = retrofit.create(Service.class);
    }
    public Service getCommentsService() {
        return commentsService;
    }
}
