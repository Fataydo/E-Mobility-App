package com.example.emobility;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.emobility.classes.Verwalter;

public class MainActivity extends AppCompatActivity {
    private SharedViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();

        if (!Verwalter.getAlreadyInit()) {
            Verwalter.setAlreadyInit(true);
        } else {
            Verwalter.getLadesaeule().clear();
            Verwalter.getReportListe().clear();
            Verwalter.getFavouritenListe().clear();
            Verwalter.setAlreadyInit(false);
        }


        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.mapsFragment, R.id.favFragment, R.id.reportListFragment,R.id.reportFragment)
                .build();

        // Here it starting to Initializers
        final NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        final NavController navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(navView, navController);


    }

}