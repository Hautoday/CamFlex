package com.example.camflex;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.camflex.createacount.Academy_Certifity_Activity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acount_view); // 👉 네가 올려준 xml 파일명으로 바꿔줘

        Button btnNext = findViewById(R.id.buttonRegister);

        btnNext.setOnClickListener(v -> {
            // SignUpActivity 화면으로 이동
            Intent intent = new Intent(RegisterActivity.this, Academy_Certifity_Activity.class);
            startActivity(intent);
        });
    }
}
