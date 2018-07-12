package com.example.nyxulric.simpleappui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewRegisterHere, textViewLoginHere;
    private EditText editTextEmail, editTextPassword;
    private Button buttonRegister;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        progressDialog = new ProgressDialog(this);

        editTextEmail = findViewById(R.id.editEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        textViewRegisterHere = findViewById(R.id.textViewRegisterHere);
        textViewLoginHere = findViewById(R.id.textViewRegisterAlready);
        buttonRegister = findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(this);
        textViewLoginHere.setOnClickListener(this);
    }

    public void userRegister(){
        String email =editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            //stop function
            return;
        }
        if (TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this,"Please Enter Password", Toast.LENGTH_SHORT).show();
            //stop function
            return;
        }

        //if validation is ok
        //show progress bar

        progressDialog.setMessage("Registration Complete...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            Toast.makeText(RegisterActivity.this, "Registeration Complete", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(RegisterActivity.this, "Could no Register. Please Try Again", Toast.LENGTH_SHORT).show();
                        }
                    progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view == buttonRegister){
            userRegister();
        }
        if (view==textViewLoginHere){
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
