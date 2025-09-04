package com.example.camflex.chattingactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.camflex.R;
import com.example.camflex.ScheduleActivity;

public class Chat2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat2);

        // ChatFragment에서 넘겨준 채팅 상대 이름 받기
        String name = getIntent().getStringExtra("name");

        // 상단 타이틀(TextView) 찾아서 이름 표시
        TextView title = findViewById(R.id.tv_title); // xml에 맞춰서 tv_title 사용
        if (title != null && name != null) {
            title.setText(name);
        }

        // 뒤로가기 버튼 처리
        ImageView backBtn = findViewById(R.id.back_btn);
        if (backBtn != null) {
            backBtn.setOnClickListener(v -> finish()); // 액티비티 종료 -> fragment로 돌아감
        }

        // 달력 아이콘 클릭 → ScheduleActivity로 이동
        ImageView dateArrow = findViewById(R.id.calc_btn);
        if (dateArrow != null) {
            dateArrow.setOnClickListener(v -> {
                Intent intent = new Intent(Chat2Activity.this, ScheduleActivity.class);
                startActivity(intent);
            });
        }
    }

    // 기기 자체 뒤로가기 버튼 눌렀을 때도 깔끔하게 종료
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
