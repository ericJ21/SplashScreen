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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textViewRegisterHere, textViewLoginHere;
    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){
            //profile activity
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        textViewLoginHere = findViewById(R.id.textViewLoginHere);
        textViewRegisterHere = findViewById(R.id.textViewNotRegistered);
        editTextEmail = findViewById(R.id.editEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        progressDialog = new ProgressDialog(this);

        buttonLogin.setOnClickListener(this);
        textViewRegisterHere.setOnClickListener(this);
    }

    public void userlogin(){
        String email = editTextEmail.getText().toString().trim();
        String paswword = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(paswword)){
            //password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Login User....");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,paswword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if (view == buttonLogin){
            userlogin();
        }
        if (view==textViewRegisterHere){
            finish();
            startActivity(new Intent(this, RegisterActivity.class));
        }

    }
}
