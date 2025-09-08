package com.example.camflex.editprofile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.camflex.R;

import java.util.ArrayList;

public class TimetableActivity extends AppCompatActivity {

    private GridLayout timetableGrid;
    private ArrayList<ClassInfo> classList;  // 수업 목록 저장

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        timetableGrid = findViewById(R.id.grid_timetable);

        // ✅ classList 초기화
        classList = new ArrayList<>();

        // ✅ 더미 수업 데이터 추가
        addClassBlock("자료구조", "목6401", 1, 9, 2);  // 월요일 9시 시작, 2시간
        addClassBlock("운영체제", "공6302", 2, 11, 2); // 화요일 11시 시작, 2시간
        addClassBlock("네트워크", "공6302", 3, 10, 3); // 수요일 10시 시작, 3시간
        addClassBlock("알고리즘", "목6401", 4, 13, 2); // 목요일 13시 시작, 2시간
        addClassBlock("컴퓨터구조", "목6203", 5, 9, 2); // 금요일 9시 시작, 2시간

        drawTimetable();
    }


    private void addClassBlock(String title, String room, int day, int startHour, int duration) {
        String dayOfWeek = "";
        switch (day) {
            case 1: dayOfWeek = "월"; break;
            case 2: dayOfWeek = "화"; break;
            case 3: dayOfWeek = "수"; break;
            case 4: dayOfWeek = "목"; break;
            case 5: dayOfWeek = "금"; break;
        }

        int endHour = startHour + duration;

        // ✅ classList에 추가
        classList.add(new ClassInfo(title, room, dayOfWeek, startHour, endHour));
    }

    /**
     * 시간표 UI를 그리는 메서드
     */
    private void drawTimetable() {
        LayoutInflater inflater = LayoutInflater.from(this);

        for (ClassInfo classInfo : classList) {
            int dayOfWeekIndex = 0;

            // 요일에 따라 열(column) 인덱스 찾기
            switch (classInfo.getDayOfWeek()) {
                case "월": dayOfWeekIndex = 1; break;
                case "화": dayOfWeekIndex = 2; break;
                case "수": dayOfWeekIndex = 3; break;
                case "목": dayOfWeekIndex = 4; break;
                case "금": dayOfWeekIndex = 5; break;
            }

            // 시작/끝 시간을 이용해 행(row) 인덱스 계산 (9시를 0번 행으로 가정)
            int startHourIndex = classInfo.getStartHour() - 9;
            int endHourIndex = classInfo.getEndHour() - 9;

            // 커스텀 수업 블록 레이아웃 inflate
            View classBlock = inflater.inflate(R.layout.item_class_block, timetableGrid, false);

            TextView titleTextView = classBlock.findViewById(R.id.text_class_title);
            TextView roomTextView = classBlock.findViewById(R.id.text_class_room);

            titleTextView.setText(classInfo.getTitle());
            roomTextView.setText(classInfo.getRoom());

            // 수업 블록의 레이아웃 파라미터 설정
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.rowSpec = GridLayout.spec(startHourIndex, (endHourIndex - startHourIndex), GridLayout.FILL);
            params.columnSpec = GridLayout.spec(dayOfWeekIndex, GridLayout.FILL, 1f);
            params.setMargins(4, 4, 4, 4);

            classBlock.setLayoutParams(params);

            // timetableGrid에 추가
            timetableGrid.addView(classBlock);
        }
    }

    /**
     * 수업 정보를 담는 데이터 클래스
     */
    public static class ClassInfo {
        private String title;
        private String room;
        private String dayOfWeek;
        private int startHour;
        private int endHour;

        public ClassInfo(String title, String room, String dayOfWeek, int startHour, int endHour) {
            this.title = title;
            this.room = room;
            this.dayOfWeek = dayOfWeek;
            this.startHour = startHour;
            this.endHour = endHour;
        }

        public String getTitle() {
            return title;
        }

        public String getRoom() {
            return room;
        }

        public String getDayOfWeek() {
            return dayOfWeek;
        }

        public int getStartHour() {
            return startHour;
        }

        public int getEndHour() {
            return endHour;
        }
    }
}
