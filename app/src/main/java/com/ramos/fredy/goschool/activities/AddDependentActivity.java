package com.ramos.fredy.goschool.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.ramos.fredy.goschool.R;
import com.ramos.fredy.goschool.bus.LocationSelectedEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddDependentActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)                     Toolbar toolbar;
    @BindView(R.id.til_dependent_name)          TextInputLayout mTilName;
    @BindView(R.id.tie_dependent_name)          TextInputEditText mTieName;
    @BindView(R.id.til_dependent_last_name)     TextInputLayout mTilLastName;
    @BindView(R.id.tie_dependet_last_name)      TextInputEditText mTieLastName;
    @BindView(R.id.til_dependent_photo)         TextInputLayout mTilPhoto;
    @BindView(R.id.tie_dependent_photo)         TextInputEditText mTiePhoto;
    @BindView(R.id.til_dependent_birthday)      TextInputLayout mTilBirthday;
    @BindView(R.id.tie_dependent_birthday)      TextInputEditText mTieBirthday;
    @BindView(R.id.til_dependent_home_address)  TextInputLayout mTilHomeAddress;
    @BindView(R.id.tie_dependent_home_address)  TextInputEditText mTieHomeAddress;
    @BindView(R.id.til_dependent_location)      TextInputLayout mTilLocation;
    @BindView(R.id.tie_dependent_location)      TextInputEditText mTieLocation;
    @BindView(R.id.btn_dependent_location)      Button mBtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dependent);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.btn_dependent_photo)
    public void takePhoto() {

    }

    @OnClick(R.id.btn_dependent_location)
    public void takeLocation() {
        startActivity(new Intent(this, DependentLocationActivity.class));
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onSelectedLocationEvent(LocationSelectedEvent event) {
        event = EventBus.getDefault().removeStickyEvent(LocationSelectedEvent.class);
        mTieLocation.setText(event.getLocationToString());
    }

    private void saveDependent() {

    }

}
