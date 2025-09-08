package com.example.camflex.category.menus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.camflex.ProductResultActivity;
import com.example.camflex.R;

public class ProductInputActivity extends AppCompatActivity {

    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_rg); // 네가 올려준 XML 사용

        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 결과 화면으로 이동
                Intent intent = new Intent(ProductInputActivity.this, ProductResultActivity.class);
                startActivity(intent);
            }
        });
    }
}