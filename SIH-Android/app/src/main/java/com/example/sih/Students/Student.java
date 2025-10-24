package com.example.sih.Students;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.sih.Admin.HomeAdminFragment;
import com.example.sih.Admin.ProfileAdminFragment;
import com.example.sih.LoginPage;
import com.example.sih.R;
import com.google.android.material.navigation.NavigationView;

public class Student extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    String token;

    TextView profileName , emailId;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(Student.this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        sharedPreferences = getSharedPreferences("TOKEN_FILE" , MODE_PRIVATE);

        loadFragment(new HomeAdminFragment() , "HOME_FRAGMENT");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                if(itemId == R.id.studentHome)
                {
                    loadFragment(new HomeAdminFragment() , "HOME_FRAGMENT");
                }
                else if(itemId == R.id.studentProfile)
                {
                    loadFragment(new ProfileAdminFragment() , "PROFILE_FRAGMENT");
                }
                else if(itemId == R.id.studentAvlScholarship)
                {
                    loadFragment(new StudentScholarShipFragment() , "STUDENTSHOLARSHIP_FRAGMENT");
                }
                else if(itemId == R.id.studentApplications)
                {
                    loadFragment(new StudentApplicationsFragment() , "STUDENTAPPLICATIONS_FRAGMENT");
                }
                else if(itemId == R.id.studentLogOut)
                {
                    sharedPreferences = getSharedPreferences("TOKEN_FILE" , MODE_PRIVATE);
                    sharedPreferences.edit().clear();
                    sharedPreferences.edit().apply();
                    Intent intent = new Intent(Student.this , LoginPage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }
    private void loadFragment(Fragment fragment , String tag) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container , fragment , tag).addToBackStack(null).commit();
    }


    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }
}