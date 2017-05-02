package com.ramos.fredy.goschool.activities;

import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.TextInputEditText;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;


import com.ramos.fredy.goschool.App;
import com.ramos.fredy.goschool.R;

import com.ramos.fredy.goschool.api.ApiManager;
import com.ramos.fredy.goschool.models.AddDependent;
import com.ramos.fredy.goschool.models.Client;
import com.ramos.fredy.goschool.models.io.DriverLoginResponse;
import com.ramos.fredy.goschool.models.io.LoginBody;
import com.ramos.fredy.goschool.models.io.LoginResponse;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ApiManager.ApiClient client;

    @BindView(R.id.client_sign_up_button)   Button clientSignUpButton;
    @BindView(R.id.driver_sign_up_button)   Button driverSignUpButton;
    @BindView(R.id.btn_login_sign_in)       Button btnLogin;

    @BindView(R.id.user_login_radio_group) RadioGroup mRadioGoup;
    @BindView(R.id.client_radio_button) RadioButton mClientRadioB;
    @BindView(R.id.driver_radio_button) RadioButton mDriverRadioB;
    @BindView(R.id.username_login_text_input_edit) TextInputEditText mUsernameText;
    @BindView(R.id.password_login_text_input_edit) TextInputEditText mPasswordText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        clientSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ClientSignUpActivity.class));
                finish();
            }
        });

        driverSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, DriverSignUpActivity.class));
                finish();
            }
        });

    }

    @OnClick(R.id.btn_login_sign_in)
    public void sign_in() {


        // Verificar el modo de autenticacion
        int selectedRadioButtonID = mRadioGoup.getCheckedRadioButtonId();

        RadioButton radioButtonSelected = (RadioButton) findViewById(selectedRadioButtonID);


        if (selectedRadioButtonID == -1) {
            Toast.makeText(LoginActivity.this, "Seleccionar tipo de usuario(Client/Driver)", Toast.LENGTH_SHORT).show();
        } else {

            // Cuando algun radioButton esta seteado
            LoginBody loginBody = new LoginBody();
            loginBody.setUsername(mUsernameText.getText().toString());
            loginBody.setPassword(mPasswordText.getText().toString());

            if(selectedRadioButtonID == mClientRadioB.getId()){
                clientAuth(loginBody);
            }
            else if(selectedRadioButtonID == mDriverRadioB.getId()){
                driverAuth(loginBody);
            }
        }

    }

    private void clientAuth(final LoginBody loginBody){
        ApiManager.ApiClient client = ApiManager.createService(ApiManager.ApiClient.class);

        Call<LoginResponse> call = client.login(loginBody);


        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse loginResponse = response.body();

                if (response.isSuccessful()) {

                    if (loginResponse.getError().isEmpty()) {

                        //Si devuelve los datos ok

                        App.getInstance().setClientUser(loginResponse.getClient());
                        startActivity(new Intent(LoginActivity.this, RequestServiceActivity.class));
                        Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(LoginActivity.this, "algo salio mal", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void driverAuth(LoginBody loginBody){
        ApiManager.ApiClient client = ApiManager.createService(ApiManager.ApiClient.class);

        Call<DriverLoginResponse> call = client.driverLogin(loginBody);


        call.enqueue(new Callback<DriverLoginResponse>() {
            @Override
            public void onResponse(Call<DriverLoginResponse> call, Response<DriverLoginResponse> response) {

                DriverLoginResponse loginResponse = response.body();

                if (response.isSuccessful()) {

                    if (loginResponse.getError().isEmpty()) {

                        //Si devuelve los datos ok

                        App.getInstance().setDriverUser(loginResponse.getDriver());
                        startActivity(new Intent(LoginActivity.this, ManageRequestServiceActivity.class));
                        Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(LoginActivity.this, "algo salio mal", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DriverLoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
