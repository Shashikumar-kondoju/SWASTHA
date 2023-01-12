package com.androiddev.projectrelief.Activities;

import static androidx.constraintlayout.widget.StateSet.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.animation.BounceInterpolator;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.androiddev.projectrelief.Fragments.AboutUsFragment;
import com.androiddev.projectrelief.Fragments.HealingMusicFragment;
import com.androiddev.projectrelief.Fragments.HomeFragment;
import com.androiddev.projectrelief.Fragments.YogaCategoriesFragment;
import com.androiddev.projectrelief.Fragments.YogaInfoFragment;
import com.androiddev.projectrelief.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    NavigationView navigationView;
    CoordinatorLayout coordinatorLayout;
    FrameLayout frameLayout;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FirebaseAuth.AuthStateListener mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolBar);
        navigationView = findViewById(R.id.navigationView);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        frameLayout = findViewById(R.id.frame);
        drawerLayout = findViewById(R.id.drawerLayout);

        setUpToolBar();
        openHome();

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                MainActivity.this,
                drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
        );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        final MenuItem[] prevItemChecked = {null};

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(prevItemChecked[0] !=null){
                    prevItemChecked[0].setChecked(false);
                }
                item.setCheckable(true);
                item.setChecked(true);
                prevItemChecked[0] = item;
                if(item.getItemId()==R.id.nav_home){
                    openHome();
                    drawerLayout.closeDrawers();
                }else if(item.getItemId()==R.id.nav_yoga){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame,new YogaCategoriesFragment())
                            .commit();
                    if(getSupportActionBar()!=null){
                        getSupportActionBar().setTitle("Yoga Information");
                    }
                    drawerLayout.closeDrawers();
                }else if(item.getItemId()==R.id.nav_music){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame,new HealingMusicFragment())
                            .commit();
                    if(getSupportActionBar()!=null){
                        getSupportActionBar().setTitle("Healing Music");
                    }
                    drawerLayout.closeDrawers();
                }else if(item.getItemId()==R.id.nav_about_us){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame,new AboutUsFragment())
                            .commit();
                    if(getSupportActionBar()!=null){
                        getSupportActionBar().setTitle("About Us");
                    }
                    drawerLayout.closeDrawers();
                }else if(item.getItemId()==R.id.log_out){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Confirm Exit");
                    builder.setMessage("Are you sure you want to logout?");
                    builder.setPositiveButton("LOGOUT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (FirebaseAuth.getInstance().getCurrentUser() != null)
                                FirebaseAuth.getInstance().signOut();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            SharedPreferences preferences =getSharedPreferences("login", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            startActivity(intent);
                            finishAffinity();
                            dialogInterface.dismiss();
                        }
                    });
                    builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                return true;
            }
        });

    }
    public void openHome(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame,new HomeFragment())
                .commit();
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Home");
        }
        navigationView.setCheckedItem(R.id.nav_home);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    public void setUpToolBar(){
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Title");
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onBackPressed(){
        Fragment frag = getSupportFragmentManager().findFragmentById(R.id.frame);
        if(frag.equals(HomeFragment.class)) {
            super.onBackPressed();
        }else{
            openHome();
        }
    }
}