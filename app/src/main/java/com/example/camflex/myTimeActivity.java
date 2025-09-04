package com.example.camflex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class myTimeActivity extends AppCompatActivity { // or Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit); // "내 시간표" 텍스트뷰가 있는 XML 레이아웃 파일명

        TextView itemTimetable = findViewById(R.id.item_timetable);

        itemTimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TimetableActivity로 이동하는 Intent 생성
                Intent intent = new Intent(myTimeActivity.this, TimetableActivity.class);
                startActivity(intent);
            }
        });
    }
}