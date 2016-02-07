package com.catalyst.travller.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.catalyst.travller.app.data.EventInfoBean;
import com.catalyst.travller.app.fragment.CreateEventFragment;
import com.catalyst.travller.app.fragment.EventDetailFragment;
import com.catalyst.travller.app.fragment.EventListFragment;
import com.catalyst.travller.app.fragment.NewEventFragment;
import com.catalyst.travller.app.fragment.SearchEventFragment;
import com.catalyst.travller.app.listener.RecyclerViewCustomListener;
import com.catalyst.travller.app.utils.AppConstants;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RecyclerViewCustomListener {

    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE);
                String userName = sharedPreferences.getString(AppConstants.USER_NAME, null);
                if (userName == null) {
                    Toast.makeText(HomeActivity.this, "Please login first", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    createEventFragment();
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        showMainFragment();
    }

    public void showMainFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        EventListFragment event = new EventListFragment();
        fragmentTransaction.replace(R.id.fragment_container, event);
        fragmentTransaction.commit();
    }

    private void showEventDetailFragment(EventInfoBean eventInfoBean) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack("Event Detail");
        EventDetailFragment event = new EventDetailFragment();
        event.setEventDetail(eventInfoBean);
        fragmentTransaction.replace(R.id.fragment_container, event);
        fragmentTransaction.commit();
    }

    private CreateEventFragment event;

    private void createEventFragment() {
        mFab.setVisibility(View.GONE);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack("Create Event");
        event = new CreateEventFragment();
        fragmentTransaction.replace(R.id.fragment_container, event);
        fragmentTransaction.commit();
    }

    @Override
    public void onItemClick(View v, int position, int screen, Object object) {
        mFab.setVisibility(View.GONE);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        if (screen == EVENT_LIST_SCREEN) {
            if (position == 0) {
                fragmentTransaction.addToBackStack("NewEvent");
                fragment = new NewEventFragment();
            } else if (position == 1) {
                fragmentTransaction.addToBackStack("Search Event");
                fragment = new SearchEventFragment();
            } else if (position == 2) {
                fragmentTransaction.addToBackStack("All Event");
                fragment = new NewEventFragment();
            }
        } else if (screen == NEW_EVENT_SCREEN) {
            showEventDetailFragment((EventInfoBean) object);
        }
        if (fragment != null) {
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 0 && requestCode == Activity.RESULT_OK && data != null) {
//            Bundle bundle = data.getExtras();
//            event.setList((ArrayList<LatLng>) bundle.getSerializable("Locations"));
//        }
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            FragmentManager fm = getFragmentManager();
            int count = fm.getBackStackEntryCount();
            if (count == 1) {
                mFab.setVisibility(View.VISIBLE);
            }
            if (count > 0) {
                fm.popBackStack();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.create_event) {
            createEventFragment();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.menuLogin) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.menuLogout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
