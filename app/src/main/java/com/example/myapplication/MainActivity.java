package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextInputEditText email;
    TextInputEditText password;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    TextView register_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        button = findViewById(R.id.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser();

            }
        });
    }

    private void signInUser() {
        String email_text = email.getText().toString().trim();
        String password_text = password.getText().toString().trim();

        if (TextUtils.isEmpty(email_text) || (!Patterns.EMAIL_ADDRESS.matcher(email_text).matches())){
            email.setError("Required");
            email.requestFocus();
        }
        if (TextUtils.isEmpty(password_text) || (password_text.length() > 6)){
            email.setError("Required");
            email.requestFocus();
        }
        firebaseAuth.signInWithEmailAndPassword(email_text, password_text).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                firebaseUser.getEmail();
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        register_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Intent intent = new Intent(this, Register.class);
               //startActivity(intent);
            }
        });
    }
}