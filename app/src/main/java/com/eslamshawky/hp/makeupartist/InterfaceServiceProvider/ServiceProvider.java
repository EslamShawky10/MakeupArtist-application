package com.eslamshawky.hp.makeupartist.InterfaceServiceProvider;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.eslamshawky.hp.makeupartist.R;

public class ServiceProvider extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

       Profile profile = new Profile();
        android.support.v4.app.FragmentTransaction fragmentTransaction
                = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_collection,profile);
        fragmentTransaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.service, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Profile profile = new Profile();
            android.support.v4.app.FragmentTransaction fragmentTransaction
                    = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_collection,profile);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_services) {
            MyService myServices = new MyService();
            android.support.v4.app.FragmentTransaction fragmentTransaction
                    = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_collection, myServices);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_notification) {
            NotificationsByServiceProvider notifications = new NotificationsByServiceProvider();
            android.support.v4.app.FragmentTransaction fragmentTransaction
                    = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_collection,notifications);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_settings) {
            SettingsProviderService settings = new SettingsProviderService();
            android.support.v4.app.FragmentTransaction fragmentTransaction
                    = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_collection,settings);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_order) {
            Orders orders = new Orders();
            android.support.v4.app.FragmentTransaction fragmentTransaction
                    = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_collection,orders);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_singout) {
            LogOutProviderService logOut = new LogOutProviderService();
            android.support.v4.app.FragmentTransaction fragmentTransaction
                    = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_collection,logOut);
            fragmentTransaction.commit();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("إغلاق التطبيق");
            builder.setMessage("هل انت متأكد من الخروج من التطبيق");
            builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finishAffinity();
                }
            });
            builder.setNegativeButton("لا", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
