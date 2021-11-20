package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends Fragment {

    private Button register;
    private RadioButton male, female;
    private EditText address, id,email, secondname, firstname, password;
    private FirebaseAuth firebaseAuth;

    public Register() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        //registerUser();

        register = view.findViewById(R.id.register);
        address = view.findViewById(R.id.address);
        id = view.findViewById(R.id.id);
        email = view.findViewById(R.id.email);
        secondname = view.findViewById(R.id.secondname);
        firstname = view.findViewById(R.id.firstname);
        password = view.findViewById(R.id.password);
        male = view.findViewById(R.id.male);
        female = view.findViewById(R.id.female);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        return view;
    }

    /*method for registration*/
    private void registerUser() {
        String first_name = firstname.getText().toString().trim();
        String second_name = secondname.getText().toString().trim();
        String email_text = email.getText().toString().trim();
        String id_text = id.getText().toString().trim();
        String password_text = password.getText().toString().trim();
        String address_text = address.getText().toString().trim();

        if (TextUtils.isEmpty(first_name) || TextUtils.isEmpty(address_text) || TextUtils.isEmpty(password_text) || TextUtils.isEmpty(second_name) || TextUtils.isEmpty(email_text) || TextUtils.isEmpty(id_text)){
            Toast.makeText(getActivity(), "Required", Toast.LENGTH_SHORT).show();
        }

        else if (!(male.isChecked() || female.isChecked())){
            Toast.makeText(getActivity(), "Select gender", Toast.LENGTH_SHORT).show();
        }

        else if (password.equals(password_text)){
            Toast.makeText(getActivity(), "Not matching", Toast.LENGTH_SHORT).show();
            password.requestFocus();
        }
        else {
            firebaseAuth.createUserWithEmailAndPassword(email_text, password_text).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");
                        String uid = firebaseAuth.getUid();
                        String gender = "";
                        if (male.isChecked()) gender = "Male";
                        else if (female.isChecked()) gender = "Female";

                        HashMap<String, String> hashMap = new HashMap<>();

                        hashMap.put("firstname", first_name);
                        hashMap.put("secondname" , second_name);
                        hashMap.put("email" , email_text);
                        hashMap.put("id" , id_text);
                        hashMap.put("address" , address_text);
                        hashMap.put("gender" , gender);

                        if (uid == null){
                            databaseReference.child(uid).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    startActivity(new Intent(getActivity(), MainActivity.class));
                                }
                            });
                        }
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        }

    }
}