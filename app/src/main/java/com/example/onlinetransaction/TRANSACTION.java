package com.example.onlinetransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinetransaction.databinding.ActivityTransactionBinding;

public class TRANSACTION extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityTransactionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTransactionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarTransaction.toolbar);
        binding.appBarTransaction.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        navigationView.setNavigationItemSelectedListener(this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_transaction);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.t_r_a_n_s_a_c_t_i_o_n, menu);
        return true;
    }

    //    @Override
    //    public boolean onSupportNavigateUp() {
    //        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_transaction);
    //        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
    //                || super.onSupportNavigateUp();
    //    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.nav_profile)
        {
            startActivity(new Intent(getApplicationContext(),ProfileView.class));
        }
        else if(id==R.id.nav_product)
        {
            startActivity(new Intent(getApplicationContext(),View_category.class));
        }
        else if(id==R.id.nav_cart)
        {
            startActivity(new Intent(getApplicationContext(),ViewCart.class));
        }
        else if(id==R.id.nav_purchase)
        {
            startActivity(new Intent(getApplicationContext(),ViewPurchaseHistory.class));
        }
        else if(id==R.id.nav_logout)
        {
            startActivity(new Intent(getApplicationContext(),loginpage.class));
        }

        return true;
    }
}