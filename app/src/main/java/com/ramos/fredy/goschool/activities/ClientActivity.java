package com.ramos.fredy.goschool.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ramos.fredy.goschool.App;
import com.ramos.fredy.goschool.R;
import com.ramos.fredy.goschool.adapter.DependentAdapter;
import com.ramos.fredy.goschool.base.BaseNavigationDrawerActivity;
import com.ramos.fredy.goschool.models.Client;
import com.ramos.fredy.goschool.models.Dependent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClientActivity extends BaseNavigationDrawerActivity implements DependentAdapter.onDependentClickListener{

    @BindView(R.id.rcv_dependent)
    RecyclerView rcvDependent;

    @BindView(R.id.client_full_name_text_view)
    TextView txtFullName;

    @BindView(R.id.client_email_text_view)
    TextView txtEmail;

    private DependentAdapter dependentAdapter;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        ButterKnife.bind(this);
        actionBar = getActionBarToolbar();

        Client client = App.getInstance().getClientUser();
        // Traemos lista de cliente
        List<Dependent> dependant = client.getDependant();

        txtFullName.setText(client.getName().toString()+" "+client.getLastName().toString());
        txtEmail.setText(client.getEmail().toString());

        dependentAdapter = new DependentAdapter(dependant, this, this);
        rcvDependent.setLayoutManager(new LinearLayoutManager(this));
        rcvDependent.setAdapter(dependentAdapter);

    }

    @Override
    protected int getSelfNavDrawerItem() {
        return R.id.nav_clie_service;
    }

    @Override
    public void onSelectedDepent(Dependent dependent) {

    }
}
