package com.example.nyxulric.simpleappui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {

    private TextView textViewInfoForm;
    private ImageView imageButtonProfile;
    private TextView textViewUpload;
    private EditText editTextUsername, editTextEmail, editTextPassword, editTextConfirmPassword;
    private Button buttonRegister;
    private View viewTamukuBot;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    private Uri filepath;
    private final int PICK_IMAGE_REQUEST = 71;

    private ProgressDialog progressDialog;

    String username, email, password, confirmpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        progressDialog = new ProgressDialog(this);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        editTextUsername = findViewById(R.id.editUserName);
        editTextEmail = findViewById(R.id.editEmailAddress);
        editTextPassword = findViewById(R.id.editPassword);
        editTextConfirmPassword = findViewById(R.id.editConfirmPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        imageButtonProfile = findViewById(R.id.imageProfileUpdate);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userRegister();
            }
        });
        imageButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture "), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data.getData() != null) {
            filepath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                imageButtonProfile.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void userRegister() {
        username = editTextUsername.getText().toString().trim();
        email = editTextEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();
        confirmpassword = editTextPassword.getText().toString().trim();

        //Registration for email and password
        if (TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(username)) {
            //username is empty
            Toast.makeText(this, "Please Enter Username", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(confirmpassword)) {
            Toast.makeText(this, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (filepath == null) {
            Toast.makeText(this, "Please Enter Profile Image", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registration In Progress...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //userEmail and Password
                            sendUserData();
                            Toast.makeText(RegisterActivity.this, "Register Successfully, Upload Completed", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        } else {
                            Toast.makeText(RegisterActivity.this, "Could not Register. Please Try Again", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    private void sendUserData() {
        username = editTextUsername.getText().toString().trim();
        email = editTextEmail.getText().toString().trim();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());

        UserInformation userInformation = new UserInformation(username, email);
        myRef.setValue(userInformation);

        if (filepath != null) {
            //Upload Image to Firebase Storage
            StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic");
            UploadTask uploadTask = imageReference.putFile(filepath);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegisterActivity.this, "Upload Failed", Toast.LENGTH_SHORT).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(RegisterActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                            .getTotalByteCount());
                    progressDialog.setMessage("Uploaded " + (int) progress + "%");
                }
            });
        }

//
//        if (!password.equals(confirmpassword)) {
//            Toast.makeText(this, "Password not matching", Toast.LENGTH_LONG).show();
//        }else {
//            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//        }
    }
}
