package com.example.camflex.loginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.camflex.R;
import com.example.camflex.mainview.MainActivity;
import com.example.camflex.startview.StartActivity;

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

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = editTextEmail.getText().toString().trim();
                password = editTextPassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "아이디와 비밀번호를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                // DB에서 계정정보 체크
                if(!AcountCheck()){
                    Toast.makeText(LoginActivity.this, "등록된 계정이 아니거나 비밀번호가 맞지 않습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    // 여기에 실제 서버와 통신하는 코드가 들어가야 하지만, 지금은 성공으로 간주합니다.
                    Toast.makeText(LoginActivity.this, "로그인 성공!", Toast.LENGTH_SHORT).show();

                    // --- 여기부터가 핵심 ---
                    // 1. SharedPreferences 인스턴스를 가져옵니다.
                    android.content.SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
                    // 2. 데이터를 수정하기 위한 Editor를 가져옵니다.
                    android.content.SharedPreferences.Editor editor = prefs.edit();
                    // 3. "isLoggedIn"이라는 키에 true 값을 저장합니다.
                    editor.putBoolean("isLoggedIn", true);
                    // 4. 변경사항을 저장합니다.
                    editor.apply();
                    // --- 여기까지 ---
                    // MainActivity로 이동
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // 로그인 화면 종료
                }
            }
        });
    }

    // 이벤트 처리를 위한 객체 생성
    private void events(){
        editTextEmail = findViewById(R.id.editTextLoginEmail);
        editTextPassword = findViewById(R.id.editTextLoginPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
    }

    // Database와 연동해야 등록된 계정이 맞는지 확인이 가능함.
    private boolean AcountCheck(){
        // 지금은 원활한 테스트를 위해 True로 설정하였음.
        // Database를 연결한 후에는 False로 변경하여야 함.
        // 또한 정보 확인을 통하여 return 값을 True 로 설정 해줘야함.
        return true;
    }
}