package com.ramos.fredy.goschool.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ramos.fredy.goschool.models.io.LoginBody;
import com.ramos.fredy.goschool.models.io.LoginResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by kenok on 30/04/2017.
 */

public class ApiManager {

    private static final String API_URL = "http://192.168.1.7:8080/api/";

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_URL);

    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        return  retrofit;
    }

    public static <S> S createService(Class<S> serviceClass) {
        setupConverter();
        setupClient();
        retrofit = builder.build();
        return retrofit.create(serviceClass);
    }

    private static void setupConverter() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        builder.addConverterFactory(GsonConverterFactory.create(gson));
    }
    private static void setupClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(30, TimeUnit.SECONDS);
        httpClient.connectTimeout(30, TimeUnit.SECONDS);
        httpClient.addInterceptor(logging);

    }

    public interface ApiClient{

        @POST("auth/login/client")
        Call<LoginResponse> login(@Body LoginBody loginBody);

    }


}
