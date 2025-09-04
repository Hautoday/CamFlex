package com.example.camflex;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EtcActivity extends AppCompatActivity {

    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etc); // 레이아웃 연결

        // 버튼 연결
        btnAdd = findViewById(R.id.btn_add);

        // 클릭 이벤트
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(EtcActivity.this, ProductRegisterActivity.class);
            startActivity(intent);
        });
    }
}