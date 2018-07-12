package com.example.nyxulric.simpleappui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textViewInfoForm;
    private ImageButton imageButtonProfile;
    private TextView textViewUpload;
    private EditText editTextUsername, editTextEmail, editTextPassword, editTextConfirmPassword;
    private Button buttonSave, buttonLogout;
    private View viewTamukuBot;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();
        editTextUsername = findViewById(R.id.editUserName);
        editTextEmail = findViewById(R.id.editEmailAddress);
        editTextPassword = findViewById(R.id.editPassword);
        editTextConfirmPassword = findViewById(R.id.editConfirmPassword);
        buttonLogout = findViewById(R.id.buttonLogout);
        buttonSave = findViewById(R.id.buttonSave);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        buttonSave.setOnClickListener(this);
        buttonLogout.setOnClickListener(this);
    }

    private void saveUserInformation(){
        String username = editTextUsername.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmpassword = editTextConfirmPassword.getText().toString().trim();

        UserInformation userInformation = new UserInformation(username,email,password, confirmpassword);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (!password.equals(confirmpassword)){
            Toast.makeText(this, "Password not matching",Toast.LENGTH_LONG).show();

        }else {
            databaseReference.child(user.getUid()).setValue(userInformation);
            Toast.makeText(this, "Information Saved...", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onClick(View view) {
        if (view == buttonLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        if (view==buttonSave){
            saveUserInformation();
        }

    }

}
