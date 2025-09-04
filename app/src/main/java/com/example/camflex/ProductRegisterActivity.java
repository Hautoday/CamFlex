package com.example.camflex;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProductRegisterActivity extends AppCompatActivity {

    private Spinner spinnerCategory;
    private EditText etTitle, etPrice, etDescription;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_rg); // XML 파일명 맞춰야 함

        // 뷰 연결
        spinnerCategory = findViewById(R.id.spinnerCategory);
        etTitle = findViewById(R.id.etTitle);
        etPrice = findViewById(R.id.etPrice);
        etDescription = findViewById(R.id.etDescription);
        btnRegister = findViewById(R.id.btnRegister);


        // Spinner 초기화
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.categories,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        // 등록 버튼 클릭
        btnRegister.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String price = etPrice.getText().toString().trim();
            String desc = etDescription.getText().toString().trim();
            String category = spinnerCategory.getSelectedItem().toString();

            if(title.isEmpty() || price.isEmpty() || desc.isEmpty()) {
                Toast.makeText(this, "모든 정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            // DB나 서버에 저장할 때 category, title, price, desc 사용 가능
            Toast.makeText(this,
                    "상품 등록 완료!\n카테고리: " + category +
                            "\n상품명: " + title,
                    Toast.LENGTH_LONG).show();

            finish(); // 등록 후 화면 종료
        });
    }
}
