package com.example.camflex.additionalfunction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.camflex.mainview.MainActivity;
import com.example.camflex.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class SearchActivity extends AppCompatActivity {

    private ImageView backButton;
    ImageButton serch_image;
    EditText editText;
    ChipGroup chipGroup;
    String inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        events();

        // 검색을 통하여 리스트를 출력하는 이미지 버튼
        serch_image.setOnClickListener(view -> {
            chip();
        });

        // 뒤로가기 버튼 → MainActivity로 이동
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(SearchActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }

    // 이벤트 처리 객체 생성
    private void events(){
        backButton = findViewById(R.id.back_button);
        editText = findViewById(R.id.serch);
        serch_image = findViewById(R.id.serch_image);
        chipGroup = findViewById(R.id.chip_group_recent_searches);
    }

    // chipgroup을 이용한 리스트 생성.
    // 해당부분에 Database와 연동하여 값을 불러와야 함.
    private void chip(){
        inputText = editText.getText().toString();
        chipGroup.removeAllViews();
        for(int i = 1; i<=5; i++) {
            // Chip 인스턴스 생성
            Chip chip = new Chip(SearchActivity.this);
            // Chip 의 텍스트 지정
            chip.setText(i+". "+inputText);
            // 체크 표시 사용 여부
            chip.setCheckable(true);
            // chip close 아이콘 이미지 지정
            //chip.setCloseIcon(getDrawable(R.drawable.ic_close));
            // close icon 표시 여부
            chip.setCloseIconVisible(true);
            // chip group 에 해당 chip 추가
            chipGroup.addView(chip);

            // chip 인스턴스 클릭 리스너
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SearchActivity.this, "Check", Toast.LENGTH_SHORT).show();
                }
            });

            // chip 인스턴스 close 버튼 클릭 리스너
            chip.setOnCloseIconClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chipGroup.removeView(v);
                }
            });
        }
    }
}
