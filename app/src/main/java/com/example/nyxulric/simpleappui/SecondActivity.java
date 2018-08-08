package com.example.nyxulric.simpleappui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    private Button btnAddIteme, btnProfile, btnMap, btn_map, btn_geo, btn_map_geofire, btn_item_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnProfile = findViewById(R.id.btnProfileData);
        btnAddIteme = findViewById(R.id.btnAddItem);
        btnMap = findViewById(R.id.btn_map);
        btn_map = findViewById(R.id.button_map);
        btn_geo = findViewById(R.id.btn_geofire);
        btn_map_geofire = findViewById(R.id.geofire_map_location);
        btn_item_page = findViewById(R.id.btn_ItemPage);

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

        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(SecondActivity.this, GeoFencingGeofireMapActivity.class));
            }
        });

        btn_geo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(SecondActivity.this, GeofireMapActivity.class));
            }
        });

        btn_map_geofire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(SecondActivity.this, MapTestActivity.class));
            }
        });

        btn_item_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(SecondActivity.this, ItemPageActivity.class));
            }
        });
    }
}
