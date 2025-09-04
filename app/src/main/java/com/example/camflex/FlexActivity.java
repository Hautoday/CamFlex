package com.example.camflex;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FlexActivity extends AppCompatActivity {

    private Button btnFlex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat2); // flex 레이아웃 파일명

        btnFlex = findViewById(R.id.btn_flex);
        btnFlex.setOnClickListener(v -> {
            Intent intent = new Intent(FlexActivity.this, ScheduleActivity.class);
            startActivity(intent);
        });
    }
}
