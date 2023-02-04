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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame,new HomeFragment(),"HomeFragment")
                .commit();
        getSupportActionBar().setTitle(null);
        toolbar.setBackgroundColor(Color.parseColor("#5e4386"));

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                MainActivity.this,
                drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
        );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));

        final MenuItem[] prevItemChecked = {null};
        final Fragment[] fragment = {null};
        navigationView.setCheckedItem(R.id.nav_home);

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
                    fragment[0] = new HomeFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame,new HomeFragment(),"HomeFragment")
                            .addToBackStack("HomeFragment")
                            .commit();
                    getSupportActionBar().setTitle(null);
                    toolbar.setBackgroundColor(Color.parseColor("#5e4386"));
                    drawerLayout.closeDrawers();
                }else if(item.getItemId()==R.id.nav_yoga){
                    fragment[0] = new YogaCategoriesFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame,new YogaCategoriesFragment(),"YogaFragment")
                            .addToBackStack("YogaFragment")
                            .commit();
                    getSupportActionBar().setTitle("Yoga Information");
                    toolbar.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                    drawerLayout.closeDrawers();
                }else if(item.getItemId()==R.id.nav_music){
                    fragment[0] = new HealingMusicFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame,new HealingMusicFragment(),"MusicFragment")
                            .addToBackStack("MusicFragment")
                            .commit();
                    getSupportActionBar().setTitle("Healing Music");
                    toolbar.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                    drawerLayout.closeDrawers();
                }else if(item.getItemId()==R.id.nav_about_us){
                    fragment[0] = new AboutUsFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame,new AboutUsFragment(),"AboutUsFragment")
                            .addToBackStack("AboutUsFragment")
                            .commit();
                    getSupportActionBar().setTitle("About Us");
                    toolbar.setBackgroundColor(Color.parseColor("#00FFFFFF"));
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
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame);
                if (fragment.getTag() == "HomeFragment") {
                    toolbar.setTitle(null);
                    toolbar.setBackgroundColor(Color.parseColor("#5e4386"));
                    navigationView.setCheckedItem(R.id.nav_home);
                }else if(fragment.getTag() == "YogaFragment"){
                    getSupportActionBar().setTitle("Yoga Information");
                    toolbar.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                    navigationView.setCheckedItem(R.id.nav_yoga);
                }else if(fragment.getTag()=="MusicFragment"){
                    getSupportActionBar().setTitle("Healing Music");
                    toolbar.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                    navigationView.setCheckedItem(R.id.nav_music);
                }else if(fragment.getTag()=="AboutUsFragment") {
                    getSupportActionBar().setTitle("About Us");
                    toolbar.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                    navigationView.setCheckedItem(R.id.nav_about_us);
                }
            }
        });
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
        getSupportActionBar().setTitle(null);
        if(getSupportActionBar() != null) {
//            getSupportActionBar().setTitle("Title");
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

}