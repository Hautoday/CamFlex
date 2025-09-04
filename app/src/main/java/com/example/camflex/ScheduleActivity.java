package com.example.camflex;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.camflex.chattingactivity.Chat2Activity;

import java.util.Random;

public class ScheduleActivity extends AppCompatActivity {

    // --- TimetableActivity에서 가져온 변수들 ---
    private GridLayout timetableGrid;
    private final String[] colors = {"#E1BEE7", "#D1C4E9", "#C5CAE9", "#BBDEFB", "#B3E5FC", "#B2EBF2", "#B2DFDB", "#C8E6C9"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule); // 기존 레이아웃 파일 그대로 사용

        // --- 기존 ScheduleActivity의 버튼 기능 ---
        Button confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(v -> {
            Intent intent = new Intent(ScheduleActivity.this, Chat2Activity.class);
            startActivity(intent);
            finish();
        });

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(ScheduleActivity.this, Chat2Activity.class);
            startActivity(intent);
            finish();
        });

        // --- TimetableActivity에서 가져온 시간표 생성 기능 ---
        timetableGrid = findViewById(R.id.grid_timetable); // activity_schedule.xml에 이 ID가 있어야 함

        // 샘플 데이터 추가
        addClassBlock("자료구조", "목6401", 1, 0, 2); // 월요일 9시 (2시간)
        addClassBlock("운영체제", "공6302", 2, 2, 2); // 화요일 11시 (2시간)
        addClassBlock("네트워크", "공6302", 3, 1, 3); // 수요일 10시 (3시간)
        addClassBlock("알고리즘", "목6401", 4, 4, 2); // 목요일 1시 (2시간)
        addClassBlock("컴퓨터구조", "목6203", 5, 0, 2); // 금요일 9시 (2시간)
    }

    /**
     * 시간표에 수업 블록을 추가하는 메서드 (TimetableActivity에서 그대로 가져옴)
     * @param title 과목명
     * @param room 강의실
     * @param dayOfWeek 요일 (월:1, 화:2, 수:3, 목:4, 금:5)
     * @param startTime 시작 교시 (9시:0, 10시:1, ...)
     * @param duration 수업 시간 (연강 수)
     */
    private void addClassBlock(String title, String room, int dayOfWeek, int startTime, int duration) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View classBlockView = inflater.inflate(R.layout.item_class_block, timetableGrid, false);

        TextView titleView = classBlockView.findViewById(R.id.text_class_title);
        TextView roomView = classBlockView.findViewById(R.id.text_class_room);
        titleView.setText(title);
        roomView.setText(room);

        Random random = new Random();
        int color = Color.parseColor(colors[random.nextInt(colors.length)]);
        GradientDrawable background = (GradientDrawable) ContextCompat.getDrawable(this, R.drawable.background_class_block);
        if (background != null) {
            background.setColor(color);
            classBlockView.setBackground(background);
        }

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = 0;
        params.rowSpec = GridLayout.spec(startTime, duration, 1f);
        params.columnSpec = GridLayout.spec(dayOfWeek, 1, 1f);
        int margin = (int) (4 * getResources().getDisplayMetrics().density);
        params.setMargins(margin, margin, margin, margin);
        classBlockView.setLayoutParams(params);

        timetableGrid.addView(classBlockView);
    }
}