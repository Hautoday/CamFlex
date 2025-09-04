package com.example.camflex;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.camflex.chattingactivity.Chat2Activity;

public class HeartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart);

        // 🔙 뒤로가기 버튼
        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(HeartActivity.this, Chat2Activity.class);
            startActivity(intent);
            finish(); // 현재 액티비티 종료
        });

        // ✅ 버튼 6개 (A~F)
        int[] buttonIds = {
                R.id.buttonA, R.id.buttonB, R.id.buttonC,
                R.id.buttonD, R.id.buttonE, R.id.buttonF
        };

        for (int id : buttonIds) {
            Button btn = findViewById(id);
            btn.setOnClickListener(v -> {
                Intent intent = new Intent(HeartActivity.this, Chat2Activity.class);
                startActivity(intent);
                finish(); // 현재 액티비티 종료
            });
        }
    }
}
