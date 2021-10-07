package com.iqbalahson.whatsappclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.iqbalahson.whatsappclone.databinding.ActivityChatDetailBinding;
import com.squareup.picasso.Picasso;

public class ChatDetailActivity extends AppCompatActivity {


    ActivityChatDetailBinding   binding;
    FirebaseDatabase database;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        String senderId = auth.getUid();
        String recieveId = getIntent().getStringExtra("UserId");
        String userName = getIntent().getStringExtra(":userName");
        String profilePic = getIntent().getStringExtra("profilePic");


        binding.userName.setText(userName);
        Picasso.get().load(profilePic).placeholder(R.drawable.user).into(binding.profileImage);
    }
}