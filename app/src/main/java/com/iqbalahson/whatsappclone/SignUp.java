package com.iqbalahson.whatsappclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.iqbalahson.whatsappclone.Models.Users;
import com.iqbalahson.whatsappclone.databinding.ActivitySignUpBinding;

public class SignUp extends AppCompatActivity {


    ActivitySignUpBinding binding;

    private FirebaseAuth Auth;
    FirebaseDatabase database;
    ProgressDialog ProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        Auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        // Loading dialog
        ProgressDialog = new ProgressDialog(SignUp.this);
        ProgressDialog.setTitle("creating account");
        ProgressDialog.setMessage("we are creating account");
        
        binding.tbsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog.show();
                Auth.createUserWithEmailAndPassword(binding.textemail.getText().toString(), binding.textpassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        ProgressDialog.dismiss();
                        if(task.isSuccessful()){
                            Users user = new Users(binding.textusername.getText().toString(),binding.textemail.getText().toString(),binding.textpassword.getText().toString());
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Users ").child(id).setValue(user);
                            Toast.makeText(SignUp.this, "User created successfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        binding.textAlreadyhaveaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, SignIn.class);
                startActivity(intent);
            }
        });
    }
}