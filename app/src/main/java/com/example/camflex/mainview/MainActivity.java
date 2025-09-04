package com.example.camflex.mainview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.camflex.CategoryFragment;
import com.example.camflex.chattingactivity.ChatFragment;
import com.example.camflex.fragment.HomeFragment;
import com.example.camflex.fragment.ProfileFragment;
import com.example.camflex.R;
import com.example.camflex.additionalfunction.SearchActivity;

public class MainActivity extends AppCompatActivity {

    private ImageButton navHome, navCategory, navChat, navProfile;
    private ImageButton btnSearch, btnNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // 시스템 바 여백 적용
        SystemBarHide();
        // 이벤트 묶음 처리
        events();

        // 초기 화면: 홈 화면(Fragment) 바로 띄우기
        loadFragment(new HomeFragment());


        // 🔍 검색 버튼 클릭 → SearchActivity로 이동
        btnSearch.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        });

        // 🔔 알림 버튼 클릭
        btnNotification.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "알림 버튼 클릭됨", Toast.LENGTH_SHORT).show()
        );

        // 🏠 홈 버튼 눌렀을 때 홈 화면으로 전환
        navHome.setOnClickListener(v -> loadFragment(new HomeFragment()));
        // 📋 게시판 버튼
        navCategory.setOnClickListener(v -> loadFragment(new CategoryFragment()));

        // 💬 채팅 버튼
        navChat.setOnClickListener(v -> loadFragment(new ChatFragment()));

        // 👤 프로필 버튼
        navProfile.setOnClickListener(v -> loadFragment(new ProfileFragment()));
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    // 이벤트 처리를 위한 객체 생성
    // 이렇게 하는 이유는 가독성과 보다 편한 관리를 위해.
    private void events(){
        // 상단 버튼
        btnSearch = findViewById(R.id.btnSearch);
        btnNotification = findViewById(R.id.btnNotification);

        // 하단 네비 버튼
        navHome = findViewById(R.id.nav_home);
        navCategory = findViewById(R.id.nav_board);
        navChat = findViewById(R.id.nav_chat);
        navProfile = findViewById(R.id.nav_profile);
    }

    // 시스템 바 숨김 처리
    private void SystemBarHide(){
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
