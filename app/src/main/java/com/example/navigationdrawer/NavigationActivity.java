package com.example.navigationdrawer;



import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

public class NavigationActivity extends AppCompatActivity {

    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_layout);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation);
        frameLayout = findViewById(R.id.frame);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        replace(new AboutFragment(),null,false);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.about1:
                        Toast.makeText(NavigationActivity.this, "Inbox", Toast.LENGTH_SHORT).show();
                        replace(new AboutFragment(),"a",true);
                        break;

                    case R.id.about2:
                        Toast.makeText(NavigationActivity.this, "Outbox", Toast.LENGTH_SHORT).show();
                        replace(new BlankFragment(),"b",true);
                        break;

                    case R.id.about3:
                        Toast.makeText(NavigationActivity.this, "Send", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.about4:
                        Toast.makeText(NavigationActivity.this,"Spam",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.about5:
                        Toast.makeText(NavigationActivity.this,"Archive  5",Toast.LENGTH_SHORT).show();
                        break;
                }
                drawerLayout.closeDrawer(Gravity.START, true);
                return true;
            }
        });

    }

    private void replace(Fragment blankFragment,String tag,boolean status) {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(status){
            fragmentTransaction.replace(R.id.frame,blankFragment,tag);
            fragmentTransaction.addToBackStack(tag);
        }
        else
            fragmentTransaction.replace(R.id.frame,blankFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(Gravity.START, true);
        else
            super.onBackPressed();
    }




}
