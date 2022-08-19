package com.example.sih.Employee;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sih.API.EmployeApi;
import com.example.sih.R;
import com.example.sih.RetrofitService;
import com.example.sih.model.UsersResponse;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Employee extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        drawerLayout = findViewById(R.id.drawerLayoutEmployee);
        navigationView = findViewById(R.id.navigation_view_employee);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(Employee.this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        
        loadFragment(new HomeEmployeeFragment());

        //**********************************************
        //Loading user data
         
        (new RetrofitService()).getRetrofit().create(EmployeApi.class).getUserDeatils().enqueue(new Callback<List<UsersResponse>>() {
            @Override
            public void onResponse(Call<List<UsersResponse>> call, Response<List<UsersResponse>> response) {

                Toast.makeText(Employee.this, "Data Fatched", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<UsersResponse>> call, Throwable t) {

                Toast.makeText(Employee.this, "User Data Loding Failed", Toast.LENGTH_SHORT).show();
            }
        });
         

         //**************************************

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if(id == R.id.home_employee)
                {
                    loadFragment(new HomeEmployeeFragment());
                }
                else if(id == R.id.dashboard)
                {
                    loadFragment(new DashboardEmployeeFragment());
                }
                else if(id == R.id.scholership_form_new)
                {
                    loadFragment(new NewScholershipFragment());
                }
                else if(id == R.id.letest_scholerships)
                {
                    loadFragment(new LetestScholershipsFragment());
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.container , fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
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