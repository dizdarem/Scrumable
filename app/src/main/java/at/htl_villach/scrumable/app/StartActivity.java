package at.htl_villach.scrumable.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import at.htl_villach.scrumable.LoginFragment;
import at.htl_villach.scrumable.R;
import at.htl_villach.scrumable.RegisterFragment;

public class StartActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private Button btnScrumActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initControls(savedInstanceState);
    }

    private void initControls(Bundle paramSavedInstanceState) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_start);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_start);
        navigationView.setNavigationItemSelectedListener(StartActivity.this);

        if(paramSavedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_start, new LoginFragment()).commit();
            navigationView.setCheckedItem(R.id.mitemLogin);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.mitemRegister:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_start, new RegisterFragment()).commit();
                setTitle(R.string.mitem_register);
                break;
            case R.id.mitemLogin:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_start, new LoginFragment()).commit();
                setTitle(R.string.mitem_login);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_start);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
