package com.example.camflex.category;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.camflex.R;
import com.example.camflex.apiconnect.ApiService;
import com.example.camflex.apiconnect.RetrofitClient;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRegisterActivity extends AppCompatActivity {

    private Spinner spinnerCategory;
    private EditText etTitle, etPrice, etDescription;
    private Button btnRegister, btnSelectImage;
    private ImageView productImage;

    private Uri imageUri;
    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_rg);

        spinnerCategory = findViewById(R.id.spinnerCategory);
        etTitle = findViewById(R.id.etTitle);
        etPrice = findViewById(R.id.etPrice);
        etDescription = findViewById(R.id.etDescription);
        btnRegister = findViewById(R.id.btnRegister);
        btnSelectImage = findViewById(R.id.btnSelectImage);
        productImage = findViewById(R.id.productImage);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.categories,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        // 이미지 선택
        btnSelectImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 100);
        });

        // 등록 버튼 클릭
        btnRegister.setOnClickListener(v -> uploadBoard());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imagePath = getRealPathFromURI(imageUri);
            productImage.setImageURI(imageUri);
            Toast.makeText(this, "이미지 선택됨", Toast.LENGTH_SHORT).show();
        }
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor == null) return null;
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);
        cursor.close();
        return path;
    }

    private void uploadBoard() {
        String uid = Writeusername(); // 로그인 유저 ID 가져오기
        String title = etTitle.getText().toString().trim();
        String content = etDescription.getText().toString().trim();
        String category = spinnerCategory.getSelectedItem().toString();
        String priceStr = etPrice.getText().toString().trim();

        if(title.isEmpty() || content.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(this, "모든 정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        int price;
        try {
            price = Integer.parseInt(priceStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "가격은 숫자만 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestBody uidPart = RequestBody.create(MediaType.parse("text/plain"), uid);
        RequestBody titlePart = RequestBody.create(MediaType.parse("text/plain"), title);
        RequestBody categoryPart = RequestBody.create(MediaType.parse("text/plain"), category);
        RequestBody contentPart = RequestBody.create(MediaType.parse("text/plain"), content);
        RequestBody pricePart = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(price));

        MultipartBody.Part imagePart = null;
        if (imagePath != null) {
            File file = new File(imagePath);
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
            imagePart = MultipartBody.Part.createFormData("boardImage", file.getName(), requestFile);
        }

        ApiService apiService = RetrofitClient.getApiService();
        Call<String> call = apiService.writeBoard(uidPart, titlePart, categoryPart, contentPart, pricePart, imagePart);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ProductRegisterActivity.this, "게시글 등록 성공!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ProductRegisterActivity.this, "등록 실패: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(ProductRegisterActivity.this, "서버 연결 실패: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String Writeusername(){
        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
        String username = prefs.getString("username", "사용자");
        return username;
    }
}
