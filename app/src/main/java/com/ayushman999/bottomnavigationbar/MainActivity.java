package com.ayushman999.bottomnavigationbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView bar;
    FragmentManager manager;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar=(BottomNavigationView)findViewById(R.id.bottom_nav);
        bar.setOnNavigationItemSelectedListener(navListener);
        navigationView=(NavigationView) findViewById(R.id.nav_view);
        if(savedInstanceState==null)
        {
            AddMember member=new AddMember();
            FragmentManager manage=getSupportFragmentManager();
            manage.beginTransaction().replace(R.id.fragment_container,member).commit();
        }
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }
    BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                    Fragment fragment=null;
                    switch (item.getItemId())
                    {
                        case R.id.profile:
                        {
                            fragment=new AddMember();
                            break;
                        }
                        case R.id.expiring_package:
                        {
                            fragment=new ExpiringPackages();
                            break;
                        }
                        case R.id.qr_generator:
                        {
                            fragment=new GenerateQR();
                            break;
                        }
                        case R.id.qr_show:
                        {
                            fragment=new ShowQR();
                            break;
                        }
                        case R.id.record_show:
                        {
                            fragment=new CheckRecord();
                            break;
                        }
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
                    return true;
                }
            };
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

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        Fragment fragment=null;
        switch (item.getItemId())
        {
            case R.id.add_member:
            {
                Intent transfer=new Intent(getApplicationContext(),AddMemberA.class);
                startActivity(transfer);
                break;
            }
            case R.id.show_profile:
            {
                Intent transfer=new Intent(getApplicationContext(),ShowProfile.class);
                startActivity(transfer);
                break;
            }
            case R.id.create_qr:
            {
                Intent transfer=new Intent(getApplicationContext(),CreateQR.class);
                startActivity(transfer);
                break;
            }
            case R.id.nav_send:
            {
                Toast.makeText(this, "Send!", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.nav_share:
            {
                Toast.makeText(this, "Share!", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}