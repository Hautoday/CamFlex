package com.example.camflex.createacount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.example.camflex.R;
import com.example.camflex.apiconnect.ApiService;
import com.example.camflex.apiconnect.RetrofitClient;
import com.example.camflex.model.RegisterResponseDto;
import com.example.camflex.model.UserRegisterRequestDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAcountActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPassword, editTextSchool, editTextStudentNo;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acount_view);

        initViews();

        buttonRegister.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String school = editTextSchool.getText().toString().trim();
            String studentNo = editTextStudentNo.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || school.isEmpty() || studentNo.isEmpty()) {
                Toast.makeText(CreateAcountActivity.this, "모든 정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            // 서버에 회원가입 요청
            UserRegisterRequestDto request = new UserRegisterRequestDto(
                    email,
                    name,
                    password,
                    school,
                    studentNo
            );

            ApiService api = RetrofitClient.getApiService();
            api.registerUser(request).enqueue(new Callback<RegisterResponseDto>() {
                @Override
                public void onResponse(Call<RegisterResponseDto> call, Response<RegisterResponseDto> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        RegisterResponseDto res = response.body();
                        if (res.isSuccess()) {
                            // SharedPreferences에 이름 저장
                            SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("username", name);
                            editor.apply();

                            Toast.makeText(CreateAcountActivity.this, res.getMessage(), Toast.LENGTH_SHORT).show();

                            // 다음 화면으로 이동
                            Intent intent = new Intent(CreateAcountActivity.this, Academy_Certifity_Activity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(CreateAcountActivity.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(CreateAcountActivity.this, "응답 실패: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RegisterResponseDto> call, Throwable t) {
                    Toast.makeText(CreateAcountActivity.this, "통신 실패: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void initViews() {
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextSchool = findViewById(R.id.editTextSchool);
        editTextStudentNo = findViewById(R.id.editTextStudentNo);
        buttonRegister = findViewById(R.id.buttonRegister);
    }
}
