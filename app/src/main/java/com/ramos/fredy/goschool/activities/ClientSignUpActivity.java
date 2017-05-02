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
import com.ramos.fredy.goschool.models.io.ClientResponse;
import com.ramos.fredy.goschool.models.io.LoginResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientSignUpActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.client_register_button)
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_sign_up);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Client newClient =new Client();
                newClient.setName(mNameTextInput.getText().toString());
                newClient.setLastName(mLastNameTextInput.getText().toString());
                newClient.setEmail(mEmailTextInput.getText().toString());
                newClient.setPassword(mPasswordTextInput.getText().toString());
                newClient.setPhoneNumber(mPhoneNumberTextInput.getText().toString());

                ApiManager.ApiClient client = ApiManager.createService(ApiManager.ApiClient.class);

                Call<ClientResponse> call = client.clientRegister(newClient);

                call.enqueue(new Callback<ClientResponse>() {
                    @Override
                    public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                        ClientResponse res = response.body();

                        if(response.isSuccessful()){
                            if(res.getError().isEmpty()){
                               // Response OK
                                startActivity(new Intent(ClientSignUpActivity.this, LoginActivity.class));
                                Toast.makeText(ClientSignUpActivity.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                // Error controlado
                                Toast.makeText(ClientSignUpActivity.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(ClientSignUpActivity.this, "Algo salió mal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ClientResponse> call, Throwable t) {
                        Toast.makeText(ClientSignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

}
