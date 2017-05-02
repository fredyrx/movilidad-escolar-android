package com.ramos.fredy.goschool.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.ramos.fredy.goschool.R;
import com.ramos.fredy.goschool.base.BaseNavigationDrawerActivity;

public class RequestServiceActivity extends BaseNavigationDrawerActivity {

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_service);

        actionBar = getActionBarToolbar();

    }

    @Override
    protected int getSelfNavDrawerItem() {
        return R.id.nav_req_service;
    }
}
