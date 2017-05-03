package com.ramos.fredy.goschool.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ramos.fredy.goschool.R;
import com.ramos.fredy.goschool.adapter.SchoolAdapter;
import com.ramos.fredy.goschool.api.ApiManager;
import com.ramos.fredy.goschool.base.BaseNavigationDrawerActivity;
import com.ramos.fredy.goschool.models.School;
import com.ramos.fredy.goschool.models.io.SchoolResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestServiceActivity extends BaseNavigationDrawerActivity implements SchoolAdapter.OnSchoolClickListener {

    @BindView(R.id.rcv_school) RecyclerView rcvSchool;

    private SchoolAdapter schoolAdapter;

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_service);

        actionBar = getActionBarToolbar();

        schoolAdapter = new SchoolAdapter(new ArrayList<School>(), this, this);
        rcvSchool.setLayoutManager(new LinearLayoutManager(this));
        rcvSchool.setAdapter(schoolAdapter);

        ApiManager.ApiClient client = ApiManager.createService(ApiManager.ApiClient.class);
        Call<SchoolResponse> call = client.schools();
        call.enqueue(new Callback<SchoolResponse>() {
            @Override
            public void onResponse(Call<SchoolResponse> call, Response<SchoolResponse> response) {

                SchoolResponse schoolResponse = response.body();

                if (response.isSuccessful()) {

                    schoolAdapter.replaceData(schoolResponse.getLstSchool());

                } else {

                }

            }

            @Override
            public void onFailure(Call<SchoolResponse> call, Throwable t) {

            }
        });

    }

    @Override
    protected int getSelfNavDrawerItem() {
        return R.id.nav_req_service;
    }

    @Override
    public void onSelectedSchool(School school) {

    }
}
