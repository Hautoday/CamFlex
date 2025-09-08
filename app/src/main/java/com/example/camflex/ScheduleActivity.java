package com.example.camflex;

import android.widget.ImageView;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import java.util.Calendar;
import android.widget.Switch;
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

    // 날짜, 시간 텍스트뷰
    private TextView dateText, timeText, minuteText;
    private View minuteLayout;
    private Switch reminderSwitch;

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




        // --- 날짜 선택 기능 추가 ---
        dateText = findViewById(R.id.dateText);
        ImageView dateArrow = findViewById(R.id.dateArrow);
        dateArrow.setOnClickListener(v -> showDatePicker());

        // --- 시간 선택 기능 추가 ---
        timeText = findViewById(R.id.timeText);
        ImageView timeArrow = findViewById(R.id.timeArrow);
        timeArrow.setOnClickListener(v -> showTimePicker());

        // 리마인드 스위치 & 분 선택
        minuteText = findViewById(R.id.minuteText);
        minuteLayout = findViewById(R.id.minuteLayout);
        reminderSwitch = findViewById(R.id.reminderSwitch);
        ImageView minuteArrow = findViewById(R.id.minuteArrow);

        // 초기 상태 설정
        setMinuteLayoutEnabled(reminderSwitch.isChecked());

        // 스위치 토글 이벤트
        reminderSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> setMinuteLayoutEnabled(isChecked));

        // 알람 시간 선택
        minuteArrow.setOnClickListener(v -> {
            if (reminderSwitch.isChecked()) {
                showMinuteDialog();
            }
        });
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



    // --- 날짜 선택 다이얼로그 ---
    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // 선택한 날짜 포맷팅
                    String dateStr = String.format("%04d.%02d.%02d", selectedYear, selectedMonth + 1, selectedDay);
                    dateText.setText(dateStr);
                }, year, month, day);
        datePickerDialog.show();
    }


    // --- 시간 선택 다이얼로그 ---
    private void showTimePicker() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, selectedHour, selectedMinute) -> {
                    String ampm = selectedHour >= 12 ? "오후" : "오전";
                    int displayHour = selectedHour % 12;
                    if (displayHour == 0) displayHour = 12;
                    String timeStr = String.format("%s %d:%02d", ampm, displayHour, selectedMinute);
                    timeText.setText(timeStr);
                }, hour, minute, false);
        timePickerDialog.show();
    }

    // 리마인드 알람 설정
    private void showMinuteDialog() {
        String[] options = {"10분 전", "30분 전", "1시간 전", "2시간 전"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("리마인드 알림 시간 선택")
                .setItems(options, (dialog, which) -> minuteText.setText(options[which]))
                .show();
    }

    private void setMinuteLayoutEnabled(boolean enabled) {
        minuteLayout.setAlpha(enabled ? 1f : 0.5f); // 반투명 처리
        minuteLayout.setClickable(enabled);
        minuteText.setTextColor(enabled ? Color.BLACK : Color.GRAY);
    }
}