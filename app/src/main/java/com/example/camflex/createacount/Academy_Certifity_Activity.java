package com.example.camflex.createacount;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.camflex.R;
import com.example.camflex.RegisterActivity;
import com.example.camflex.loginActivity.LoginActivity;

public class Academy_Certifity_Activity extends AppCompatActivity {
    Button btnNext;
    ImageButton btnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academy_certified); // 👉 sign_up.xml 파일명 맞게 수정
        events();


        // "완료" 버튼 → 로그인 화면 이동
        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(Academy_Certifity_Activity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        // "닫기" 버튼 → 회원가입(RegisterActivity) 화면으로 이동
        btnClose.setOnClickListener(v -> {
            Intent intent = new Intent(Academy_Certifity_Activity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });
    }

    // 이벤트 처리를 위한 객체 생성
    private void events(){
        btnNext = findViewById(R.id.button_next);
        btnClose = findViewById(R.id.button_close);
    }
}
