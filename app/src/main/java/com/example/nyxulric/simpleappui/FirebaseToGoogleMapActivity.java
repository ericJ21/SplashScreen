package com.example.nyxulric.simpleappui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseToGoogleMapActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;
    private Button btnproceed, btnSave, btnReturn;
    private EditText editTextname,editTextlatitude,editTextlongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_to_google_map);

        btnproceed = findViewById(R.id.btn_proceed);
        btnSave = findViewById(R.id.btn_save);
        btnReturn = findViewById(R.id.btn_return);
        editTextname = findViewById(R.id.edit_text_name);
        editTextlatitude = findViewById(R.id.edit_text_latitude);
        editTextlongitude = findViewById(R.id.edit_text_longitude);

        firebaseAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference(firebaseAuth.getUid()).child("Users");


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserData();
                editTextname.getText().clear();
                editTextlatitude.getText().clear();
                editTextlongitude.getText().clear();
            }
        });

        btnproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent =  new Intent(FirebaseToGoogleMapActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(FirebaseToGoogleMapActivity.this,HomeActivity.class));
            }
        });

    }
    private void saveUserData(){
        String names = editTextname.getText().toString().trim();
        double latitude = Double.parseDouble(editTextlatitude.getText().toString().trim());
        double longitude = Double.parseDouble(editTextlongitude.getText().toString().trim());
        UserInfo userInfo = new UserInfo(names,latitude,longitude);
        mDatabase.child("Users").setValue(userInfo);
        Toast.makeText(this, "Saved",Toast.LENGTH_LONG).show();
    }
}
