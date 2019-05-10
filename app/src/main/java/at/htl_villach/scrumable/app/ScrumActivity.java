package at.htl_villach.scrumable.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.EventLogTags;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Date;

import at.htl_villach.scrumable.R;
import at.htl_villach.scrumable.bll.BacklogItem;
import at.htl_villach.scrumable.bll.StatusEnum;
import at.htl_villach.scrumable.bll.User;
import at.htl_villach.scrumable.dal.DatabaseManager;

public class ScrumActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DatabaseManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrum);
        initControls(savedInstanceState);
    }

    private void initControls(Bundle paramSavedInstanceState) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseManager = new DatabaseManager(this);
        databaseManager.open();

        generateTextData();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(paramSavedInstanceState == null) {
            //Successfully logged in --> show on ScrumActivity ScrumboardFragment
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_scrum, new Scrumboard_Fragment()).commit();
            navigationView.setCheckedItem(R.id.mitemScrumboard);
        }
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
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.showProfile:
                //toDo: Open new Intent
                break;
            case R.id.myProjects:
                //toDo: open new Intent
                break;
            case R.id.logout:
                Intent myIntent = new Intent(ScrumActivity.this, StartActivity.class);
                ScrumActivity.this.startActivity(myIntent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.mitemProduct_Backlog:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_scrum, new ProductBacklog_Fragment()).commit();
                setTitle(R.string.mitem_Product_Backlog);
                break;
            case R.id.mitemSprint_Backlog:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_scrum, new SprintBacklog_Fragment()).commit();
                setTitle(R.string.mitem_Sprint_Backlog);
                break;
            case R.id.mitemScrumboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_scrum, new Scrumboard_Fragment()).commit();
                setTitle(R.string.mitem_Scrumboard);
                break;
            case R.id.mitemAddUS:
                Intent myIntent = new Intent(ScrumActivity.this, AddBacklogItemActivity.class);
                ScrumActivity.this.startActivity(myIntent);
                setTitle(R.string.addUS);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private  void generateTextData() {
        User user1 = new User("User_1", "1234", new Date());
        User user2 = new User("User_2", "1234", new Date());
        User user3 = new User("User_3", "1234", new Date());

        databaseManager.insert_User(user1);
        databaseManager.insert_User(user2);
        databaseManager.insert_User(user3);

        for(int i=1; i<=5; i++) {
            databaseManager.insert_BacklogItem(new BacklogItem(i, "ProductBL_"+i, "Description of UserStory_"+i, StatusEnum.PRODUCT_BL, user1));
            databaseManager.insert_BacklogItem(new BacklogItem(i, "SprintBl_"+i, "Description of UserStory_"+i, StatusEnum.SPRINT_BL, user2));
            databaseManager.insert_BacklogItem(new BacklogItem(i, "ToDo_"+i, "Description of UserStory_"+i, StatusEnum.TODO, user3));
            databaseManager.insert_BacklogItem(new BacklogItem(i, "InProcess_"+i, "Description of UserStory_"+i, StatusEnum.IN_PROCESS, user2));
            databaseManager.insert_BacklogItem(new BacklogItem(i, "Testing_"+i, "Description of UserStory_"+i, StatusEnum.TESTING, user3));
            databaseManager.insert_BacklogItem(new BacklogItem(i, "Done_"+i, "Description of UserStory_"+i, StatusEnum.DONE, user1));
        }
    }
}