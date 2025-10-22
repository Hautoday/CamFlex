package com.example.camflex.category.menus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.camflex.R;
import com.example.camflex.category.ProductRegisterActivity;
import com.example.camflex.model.Product;
import com.example.camflex.apiconnect.ApiService;
import com.example.camflex.apiconnect.RetrofitClient;

import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClothingActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        events();
        loadProducts();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 결과 화면으로 이동
                Intent intent = new Intent(ClothingActivity.this, ProductRegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadProducts() {
        ApiService apiService = RetrofitClient.getApiService();
        apiService.getProducts("의류").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                // --- 디버깅용 로그 추가 ---
                Log.d("API_RESPONSE", "isSuccessful: " + response.isSuccessful());
                Log.d("API_RESPONSE", "Response Code: " + response.code());
                Log.d("API_RESPONSE", "Response Body: " + response.body());
                if (response.errorBody() != null) {
                    try {
                        Log.e("API_ERROR_BODY", "Error Body: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                // --- 여기까지 ---

                if (response.isSuccessful() && response.body() != null) {
                    Log.d("API_SUCCESS", "Data size: " + response.body().size());

                    if (response.body().isEmpty()) {
                        Toast.makeText(ClothingActivity.this, "해당 카테고리의 상품이 없습니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    adapter = new ProductAdapter(ClothingActivity.this, response.body());
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(ClothingActivity.this, "데이터 로드 실패 (Code: " + response.code() + ")", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ClothingActivity.this, "서버 연결 실패", Toast.LENGTH_LONG).show();
                Log.e("API_FAILURE", "Error: " + t.getMessage());
            }
        });
    }
    private void events(){
        btnRegister = findViewById(R.id.btn_add);
    }
}

