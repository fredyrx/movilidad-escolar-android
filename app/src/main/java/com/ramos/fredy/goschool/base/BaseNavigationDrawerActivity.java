package com.ramos.fredy.goschool.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ramos.fredy.goschool.R;
import com.ramos.fredy.goschool.activities.AddDependentActivity;
import com.ramos.fredy.goschool.activities.ClientActivity;
import com.ramos.fredy.goschool.activities.ManageRequestServiceActivity;
import com.ramos.fredy.goschool.activities.RequestServiceActivity;

public class BaseNavigationDrawerActivity extends AppCompatActivity {

    protected static final int NAV_DRAWER_ITEM_INVALID = -1;

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setupNavDrawer();
    }

    private void setupNavDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout == null) {
            // current activity does not have a drawer.
            return;
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            toggle.syncState();
            setupDrawerSelectListener(navigationView);
            setSelectedItem(navigationView);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setSelectedItem(NavigationView navigationView) {
        // Which navigation item should be selected?
        int selectedItem = getSelfNavDrawerItem(); // subclass has to override this method
        navigationView.setCheckedItem(selectedItem);
    }

    private void setupDrawerSelectListener(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        drawerLayout.closeDrawer(GravityCompat.START);
                        onNavigationItemClicked(menuItem.getItemId());
                        return true;
                    }
                });
    }

    private void onNavigationItemClicked(final int itemId) {
        if(itemId == getSelfNavDrawerItem()) {
            return;
        }

        goToNavDrawerItem(itemId);
    }

    private void goToNavDrawerItem(int item) {
        // Es para todos navigation
        switch (item) {
            case R.id.nav_req_service:
                startActivity(new Intent(this, RequestServiceActivity.class));
                finish();
                break;
            case R.id.nav_add_dependent:
                startActivity(new Intent(this, AddDependentActivity.class));
                finish();
                break;
            case R.id.nav_manage_request_service:
                startActivity(new Intent(this, ManageRequestServiceActivity.class));
                finish();
                break;
            case R.id.nav_clie_service:
                startActivity(new Intent(this, ClientActivity.class));
                finish();
                break;
        }
    }

    protected ActionBar getActionBarToolbar() {
        if (toolbar == null) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            if (toolbar != null) {
                setSupportActionBar(toolbar);
            }
        }
        return getSupportActionBar();
    }

    protected int getSelfNavDrawerItem() {
        return NAV_DRAWER_ITEM_INVALID;
    }

}
