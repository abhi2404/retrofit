package com.example.drawerlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mdrawrerlayout;
    ActionBarDrawerToggle mtoggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawerlayout);

        Toolbar toolbar=findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        mdrawrerlayout=findViewById(R.id.drawer);
        NavigationView navigationView=(NavigationView)findViewById(R.id.nav);
        mtoggle=new ActionBarDrawerToggle(this,mdrawrerlayout,toolbar,R.string.open,R.string.close);
        mdrawrerlayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed(){
        if(mdrawrerlayout.isDrawerOpen(GravityCompat.START)){
            mdrawrerlayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment=null;
        switch (menuItem.getItemId()){
            case R.id.db:
                fragment=new db();
                loadFragment(fragment);
                Toast.makeText(this,"dashboard clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.activities:
                fragment=new activitiesFragment();
                loadFragment(fragment);
                Toast.makeText(this,"activities clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.events:
                fragment=new eventFragment();
                loadFragment(fragment);
                Toast.makeText(this,"events clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                fragment=new LogoutFragment();
                loadFragment(fragment);
                Toast.makeText(this,"logout clicked",Toast.LENGTH_SHORT).show();
                break;
            default:
                return true;
        }
        return true;
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment).commit();
        mdrawrerlayout.closeDrawer(GravityCompat.START);
        fragmentTransaction.addToBackStack(null);
    }
}