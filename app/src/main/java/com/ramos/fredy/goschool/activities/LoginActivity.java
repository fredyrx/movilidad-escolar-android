package com.ramos.fredy.goschool.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.ramos.fredy.goschool.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    //@BindView(R.id.client_sign_up_button)
    Button clientSignUpButton;

    //@BindView(R.id.driver_sign_up_button)
    Button driverSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //ButterKnife.bind(this);
        clientSignUpButton = (Button) findViewById(R.id.client_sign_up_button);
        driverSignUpButton = (Button) findViewById(R.id.driver_sign_up_button);

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

}
