package com.example.camflex.loginActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.camflex.R;
import com.example.camflex.mainview.MainActivity;
import com.example.camflex.apiconnect.ApiService;
import com.example.camflex.apiconnect.RetrofitClient;
import com.example.camflex.model.UserLoginRequestDto;
import com.example.camflex.model.UserLoginResponseDto;
import com.example.camflex.permissioncheck.PermissionUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;
    private Intent intent;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);
        events();
        if (!PermissionUtils.checkPermissions(this)) {
            PermissionUtils.requestPermissions(this);
        }

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = editTextEmail.getText().toString().trim();
                password = editTextPassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "아이디와 비밀번호를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                loginUser(email, password);
            }
        });
    }

    // 이벤트 처리를 위한 객체 생성
    private void events() {
        editTextEmail = findViewById(R.id.editTextLoginEmail);
        editTextPassword = findViewById(R.id.editTextLoginPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
    }

    // 실제 서버 로그인 요청
    private void loginUser(String email, String password) {
        ApiService apiService = RetrofitClient.getApiService();
        UserLoginRequestDto request = new UserLoginRequestDto(email, password);

        apiService.loginUser(request).enqueue(new Callback<UserLoginResponseDto>() {
            @Override
            public void onResponse(Call<UserLoginResponseDto> call, Response<UserLoginResponseDto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UserLoginResponseDto res = response.body();
                    if (res.isSuccess()) {
                        // 로그인 성공 → SharedPreferences 저장 + MainActivity로 이동
                        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean("isLoggedIn", true);
                        editor.putString("username", res.getName());
                        editor.apply();

                        Toast.makeText(LoginActivity.this, "로그인 성공!", Toast.LENGTH_SHORT).show();

                        intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("username", res.getName()); // ✅ 이름 추가
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(LoginActivity.this, "로그인 실패: 아이디/비밀번호 확인", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "로그인 실패: 아이디/비밀번호 확인", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserLoginResponseDto> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "서버 연결 실패: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissionUtils.PERMISSION_REQUEST_CODE) {
            if (PermissionUtils.permissionResult(grantResults)) {
                Toast.makeText(this, "모든 권한 허용됨", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "권한이 거부되었습니다. 일부 기능이 제한될 수 있습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
