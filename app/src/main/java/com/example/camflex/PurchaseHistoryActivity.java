package com.example.camflex;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PurchaseHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_his);

        RecyclerView recyclerView = findViewById(R.id.purchase_history_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        List<PurchaseItem> items = new ArrayList<>();
        items.add(new PurchaseItem("MG 이클립스 건담 팝니다.", "경기도 고양시 일산서구 • 1주 전", "거래완료", "50,000원", R.drawable.simple_book_image2));
        items.add(new PurchaseItem("RG 건담 엑시아 판매", "서울 강남구 • 3일 전", "거래중", "45,000원", R.drawable.simple_book_image2));
        items.add(new PurchaseItem("HG 유니콘 건담 팝니다.", "부산 해운대구 • 2주 전", "거래완료", "30,000원", R.drawable.simple_book_image2));

        PurchaseAdapter adapter = new PurchaseAdapter(items);
        recyclerView.setAdapter(adapter);
    }
}
