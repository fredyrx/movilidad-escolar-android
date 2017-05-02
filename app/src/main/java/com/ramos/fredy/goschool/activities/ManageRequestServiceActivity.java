package com.ramos.fredy.goschool.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ramos.fredy.goschool.R;
import com.ramos.fredy.goschool.base.BaseNavigationDrawerActivity;

public class ManageRequestServiceActivity extends BaseNavigationDrawerActivity {

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_request_service);

        actionBar = getActionBarToolbar();

    }

    @Override
    protected int getSelfNavDrawerItem() {
        return R.id.nav_manage_request_service;
    }

}
