package com.example.camflex.chattingactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.camflex.mainview.MainActivity;
import com.example.camflex.R;

public class Chat3Activity extends AppCompatActivity {

    private ImageButton backArrow;
    private ImageButton exitIcon;
    private ImageButton blockIcon;
    private TextView userName, notifyLabel, chatPartnerLabel, exitText, blockText;
    private Switch notifySwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat3);

        // 뷰 연결
        backArrow = findViewById(R.id.backArrow);
        exitIcon = findViewById(R.id.exitIcon);
        blockIcon = findViewById(R.id.blockIcon);

        userName = findViewById(R.id.userName);
        notifyLabel = findViewById(R.id.notifyLabel);
        notifySwitch = findViewById(R.id.notifySwitch);
        chatPartnerLabel = findViewById(R.id.chatPartnerLabel);
        exitText = findViewById(R.id.exitText);
        blockText = findViewById(R.id.blockText);

        // backArrow 클릭 시 Chat2Activity로 이동
        backArrow.setOnClickListener(v -> {
            Intent intent = new Intent(Chat3Activity.this, Chat2Activity.class);
            startActivity(intent);
            finish();
        });

        // exitIcon 클릭 시 MainActivity로 이동 후 ChatFragment 띄우기
        exitIcon.setOnClickListener(v -> {
            Intent intent = new Intent(Chat3Activity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        // exitText 클릭 시 exitIcon 클릭 이벤트 실행
        exitText.setOnClickListener(v -> exitIcon.performClick());

        // 알림 스위치 상태 변화 리스너 (필요 시 구현)
        notifySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // TODO: 알림 설정 처리 코드 작성
        });

        // blockIcon 클릭 이벤트 (필요 시 구현)
        blockIcon.setOnClickListener(v -> {
            // TODO: 차단 처리 구현
        });

        // blockText 클릭 시 blockIcon 클릭 이벤트 실행
        blockText.setOnClickListener(v -> blockIcon.performClick());
    }
}
