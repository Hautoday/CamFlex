package com.example.camflex.startview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.camflex.loginActivity.LoginActivity;
import com.example.camflex.createacount.CreateAcountActivity;
import com.example.camflex.R;

public class StartActivity extends AppCompatActivity {
    private Button logButton,memButton;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); // XML 파일명 맞춰 변경
        // 버튼 연결
        ButtonEvent();

        // 로그인 버튼 클릭 시 페이지 전환.
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // 회원가입 버튼 클릭 시 페이지 전환.
        memButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(StartActivity.this, CreateAcountActivity.class);
                startActivity(intent);
            }
        });
    }
    // 버튼 연결 이벤트 처리.
    private void ButtonEvent(){
        logButton = findViewById(R.id.logButton);
        memButton = findViewById(R.id.memButton);
    }
}
