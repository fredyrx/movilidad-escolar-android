package com.ramos.fredy.goschool.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ramos.fredy.goschool.models.Client;
import com.ramos.fredy.goschool.models.Dependent;
import com.ramos.fredy.goschool.models.Driver;
import com.ramos.fredy.goschool.models.io.ClientResponse;
import com.ramos.fredy.goschool.models.io.DependentResponse;
import com.ramos.fredy.goschool.models.io.DriverLoginResponse;
import com.ramos.fredy.goschool.models.io.DriverResponse;
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
import retrofit2.http.Path;

/**
 * Created by kenok on 30/04/2017.
 */

public class ApiManager {

    private static final String API_URL = "http://192.168.8.231:8080/api/";

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

        @POST("auth/login/driver")
        Call<DriverLoginResponse> driverLogin(@Body LoginBody loginBody);

        @POST("clients")
        Call<ClientResponse> clientRegister(@Body Client clientBody);

        @POST("drivers")
        Call<DriverResponse> driverRegister(@Body Driver driverBody);

        @POST("clients/{client_id}/dependant")
        Call<DependentResponse> addDependantForClient(@Path("client_id") String client_id, @Body Dependent dependentBody);
    }


}
