package com.ramos.fredy.goschool.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ramos.fredy.goschool.App;
import com.ramos.fredy.goschool.R;
import com.ramos.fredy.goschool.api.ApiManager;
import com.ramos.fredy.goschool.models.io.LoginBody;
import com.ramos.fredy.goschool.models.io.LoginResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.client_sign_up_button)   Button clientSignUpButton;
    @BindView(R.id.driver_sign_up_button)   Button driverSignUpButton;
    @BindView(R.id.btn_login_sign_in)       Button btnLogin;

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
        ApiManager.ApiClient client = ApiManager.createService(ApiManager.ApiClient.class);
        Call<LoginResponse> call = client.login(new LoginBody());

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse loginResponse = response.body();

                if (response.isSuccessful()) {

                    if (!loginResponse.getError().isEmpty()) {

                        //Si devuelve los datos ok

                    } else {
                        Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
