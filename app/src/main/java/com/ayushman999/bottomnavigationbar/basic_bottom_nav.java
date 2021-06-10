package com.ayushman999.bottomnavigationbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class basic_bottom_nav extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_bottom_nav);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame,new ShowProfile()).commit();
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                    Fragment fragment=null;
                    switch (item.getItemId())
                    {
                        case R.id.profile:
                        {
                            fragment=new ShowProfile();
                            break;
                        }
                        case R.id.expiring_package:
                        {
                            fragment=new ExpiringPackages();
                            break;
                        }
                        case R.id.qr_generator: {
                            fragment = new GenerateQR();
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
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment).commit();
                    return true;
                }
            };
}