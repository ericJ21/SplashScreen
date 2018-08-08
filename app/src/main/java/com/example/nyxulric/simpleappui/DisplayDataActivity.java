package com.example.nyxulric.simpleappui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class DisplayDataActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseStorage firebaseStorage;

    private CircleImageView imageViewProfile;
    private TextView textViewUsername, textViewEmail;
    private Button buttonOut, buttonRemove, buttonEdit, btnChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        imageViewProfile = findViewById(R.id.imageViewProfile);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewUsername = findViewById(R.id.textViewUsername);
        buttonEdit = findViewById(R.id.buttonEdit);
        buttonOut = findViewById(R.id.buttonOut);
        buttonRemove = findViewById(R.id.buttonRemove);
        btnChangePassword = findViewById(R.id.btnChgPassword);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference = firebaseDatabase.getReference(user.getUid()).child("User Information");

        StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child(user.getUid()).child("Images/Profile Pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).fit().centerCrop().into(imageViewProfile);
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserInformation userInformation = dataSnapshot.getValue(UserInformation.class);
                assert userInformation != null;
                textViewUsername.setText(userInformation.getUsername());
                textViewEmail.setText(userInformation.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
//               Toast.makeText(DisplayDataActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        buttonRemove.setOnClickListener(this);
        buttonOut.setOnClickListener(this);
        buttonEdit.setOnClickListener(this);
        btnChangePassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonOut) {
            finish();
            firebaseAuth.signOut();
            startActivity(new Intent(DisplayDataActivity.this, LoginActivity.class));
        }
        if (view == buttonEdit) {
            finish();
            startActivity(new Intent(DisplayDataActivity.this, UpdateProfile.class));
        }
        if (view == btnChangePassword){
            finish();
            startActivity(new Intent(DisplayDataActivity.this,UpdatePassword.class));
        }

    }

}
