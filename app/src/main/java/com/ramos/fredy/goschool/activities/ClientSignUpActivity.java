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

public class ClientSignUpActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.client_register_button)
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_sign_up);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ClientSignUpActivity.this, LoginActivity.class));
                finish();
            }
        });

    }

}
