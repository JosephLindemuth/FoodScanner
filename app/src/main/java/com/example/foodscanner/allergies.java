package com.example.foodscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.foodscanner.ui.main.AllergiesFragment;

public class allergies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergies);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, AllergiesFragment.newInstance())
                    .commitNow();
        }
    }
}