package com.example.camflex;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.camflex.chattingactivity.Chat2Activity;

public class ProductActivity extends AppCompatActivity {

    // View 변수들을 클래스 멤버로 선언
    private TextView titleView, priceView;
    private ImageView imageView;
    private Button attBtn, chatBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        // 1. 코드를 기능별로 분리하여 가독성을 높임
        initializeViews();
        setupProductDetailsAndListeners();
    }

    /**
     * XML 레이아웃의 View들을 findViewById로 찾아 변수에 할당하는 메소드
     */
    private void initializeViews() {
        titleView = findViewById(R.id.titleTextView);
        priceView = findViewById(R.id.priceTextView);
        imageView = findViewById(R.id.mainImageView);
        attBtn = findViewById(R.id.attBtn);
        chatBtn = findViewById(R.id.chatBtn);
    }

    /**
     * Intent에서 데이터를 꺼내고, View에 세팅하고, 버튼 클릭 이벤트까지 설정하는 메소드
     */
    private void setupProductDetailsAndListeners() {
        // Intent에서 데이터 꺼내기
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String price = intent.getStringExtra("price");
        int imageResId = intent.getIntExtra("imageResId", R.drawable.simple_book_image1);

        // View에 데이터 세팅
        titleView.setText(title);
        priceView.setText(price);
        imageView.setImageResource(imageResId);

        // 찜 버튼 클릭 이벤트 설정
        attBtn.setOnClickListener(v -> {
            // [첫 번째 코드의 핵심 기능]
            // FavoritesActivity로 이동하기 전에, 해당 아이템을 찜 목록에 먼저 추가합니다.
            FavoriteActivity.addFavorite(title, price, imageResId);

            // [두 코드의 공통 기능]
            // FavoritesActivity 화면으로 이동합니다.
            Intent favoriteIntent = new Intent(ProductActivity.this, FavoriteActivity.class);
            startActivity(favoriteIntent);
        });

        // 채팅 버튼 클릭 이벤트 설정
        chatBtn.setOnClickListener(v -> {
            // [두 코드의 공통 기능]
            // Chat2Activity 화면으로 이동합니다.
            Intent chatIntent = new Intent(ProductActivity.this, Chat2Activity.class);
            startActivity(chatIntent);
        });
    }
}