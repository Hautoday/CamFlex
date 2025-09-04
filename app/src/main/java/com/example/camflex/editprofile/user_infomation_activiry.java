package com.example.camflex.editprofile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.camflex.R;

public class user_infomation_activiry extends AppCompatActivity {
    Button btnEditProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        events();
        btnEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(user_infomation_activiry.this, EditFrofileActivity.class);
            startActivity(intent);
        });
    }

    private void events(){
        btnEditProfile = findViewById(R.id.btnEditProfile);
    }
}
