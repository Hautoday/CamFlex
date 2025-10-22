package com.example.camflex.editprofile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.camflex.R;


public class user_infomation_activiry extends AppCompatActivity {
    Button btnEditProfile, notice, timetable, appm;
    Intent intent;
    TextView editUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        events();
        Writeusername();
        btnEditProfile.setOnClickListener(v -> {
            intent = new Intent(user_infomation_activiry.this, EditFrofileActivity.class);
            startActivity(intent);
        });

        timetable.setOnClickListener(view -> {
            intent = new Intent(user_infomation_activiry.this, TimetableActivity.class);
            startActivity(intent);
        });

        notice.setOnClickListener(view -> {
            intent = new Intent(user_infomation_activiry.this, NoticeActivity.class);
            startActivity(intent);
        });

        appm.setOnClickListener(view -> {
            Toast.makeText(this, "미구현입니다 빨리 구현해주세요.", Toast.LENGTH_SHORT).show();
            // 미구현씨 여기서 이러면 안돼
        });
    }
    // 불러오기

    private void events(){
        btnEditProfile = findViewById(R.id.btnEditProfile);
        timetable = findViewById(R.id.Timetable);
        notice = findViewById(R.id.Notice);
        appm = findViewById(R.id.AppM);
        editUserName = findViewById(R.id.editUserName);
    }
    private void Writeusername(){
        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
        String username = prefs.getString("username", "사용자");
        editUserName.setText(username);
    }

}
