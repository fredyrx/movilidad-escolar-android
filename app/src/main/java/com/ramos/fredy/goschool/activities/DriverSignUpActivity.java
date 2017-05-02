package com.ramos.fredy.goschool.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ramos.fredy.goschool.R;
import com.ramos.fredy.goschool.api.ApiManager;
import com.ramos.fredy.goschool.models.Client;
import com.ramos.fredy.goschool.models.Driver;
import com.ramos.fredy.goschool.models.io.ClientResponse;
import com.ramos.fredy.goschool.models.io.DriverResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverSignUpActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.driver_register_button)
    Button registerButton;

    @BindView(R.id.email_text_input)
    TextInputEditText mEmailTextInput;

    @BindView(R.id.password_text_input)
    TextInputEditText mPasswordTextInput;

    @BindView(R.id.name_text_input)
    TextInputEditText mNameTextInput;

    @BindView(R.id.last_name_text_input)
    TextInputEditText mLastNameTextInput;

    @BindView(R.id.phone_text_input)
    TextInputEditText mPhoneNumberTextInput;

    @BindView(R.id.car_info_text_edit)
    TextInputEditText mCarInfoTextInput;

    @BindView(R.id.license_text_edit)
    TextInputEditText mLicenseTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_sign_up);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Driver newDriver =new Driver();
                newDriver.setName(mNameTextInput.getText().toString());
                newDriver.setLastName(mLastNameTextInput.getText().toString());
                newDriver.setEmail(mEmailTextInput.getText().toString());
                newDriver.setPassword(mPasswordTextInput.getText().toString());
                newDriver.setPhoneNumber(mPhoneNumberTextInput.getText().toString());
                newDriver.setLicenseId(mLicenseTextInput.getText().toString());
                newDriver.setCarModel(mCarInfoTextInput.getText().toString());

                ApiManager.ApiClient client = ApiManager.createService(ApiManager.ApiClient.class);

                Call<DriverResponse> call = client.driverRegister(newDriver);

                call.enqueue(new Callback<DriverResponse>() {
                    @Override
                    public void onResponse(Call<DriverResponse> call, Response<DriverResponse> response) {
                        DriverResponse res = response.body();

                        if(response.isSuccessful()){
                            if(res.getError().isEmpty()){
                                // Response OK
                                startActivity(new Intent(DriverSignUpActivity.this, LoginActivity.class));
                                Toast.makeText(DriverSignUpActivity.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                // Error controlado
                                Toast.makeText(DriverSignUpActivity.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(DriverSignUpActivity.this, "Algo salió mal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DriverResponse> call, Throwable t) {
                        Toast.makeText(DriverSignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                //startActivity(new Intent(DriverSignUpActivity.this, LoginActivity.class));

                //finish();
            }
        });

    }

}
