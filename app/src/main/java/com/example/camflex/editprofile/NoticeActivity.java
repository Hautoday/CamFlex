package com.example.camflex.editprofile;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.camflex.R;

public class NoticeActivity extends AppCompatActivity {
    ImageView btnClose;
    TextView btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        events();
    }

    private void events() {
        btnClose = findViewById(R.id.btnClose);
        btnDone = findViewById(R.id.btnDone);
    }
}
