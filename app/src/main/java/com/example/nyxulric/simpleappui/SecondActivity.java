package com.example.nyxulric.simpleappui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    private Button btnAddIteme, btnProfile, btnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnProfile = findViewById(R.id.btnProfileData);
        btnAddIteme = findViewById(R.id.btnAddItem);
        btnMap = findViewById(R.id.btn_map);

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(SecondActivity.this, HomeActivity.class));
            }
        });

        btnAddIteme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(SecondActivity.this, ProductListingActivity.class));
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(SecondActivity.this,FirebaseToGoogleMapActivity.class));
            }
        });
    }
}
