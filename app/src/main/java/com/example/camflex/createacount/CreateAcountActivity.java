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
import com.example.camflex.loginActivity.LoginActivity;


public class CreateAcountActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPassword;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acount_view);
        events();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(CreateAcountActivity.this, "모든 정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 이름 SharedPreferences에 저장
                SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("username", name);
                editor.apply();


                // 회원가입 성공 처리
                Toast.makeText(CreateAcountActivity.this, "회원가입 성공!", Toast.LENGTH_SHORT).show();

                // LoginActivity로 이동
                Intent intent = new Intent(CreateAcountActivity.this, Academy_Certifity_Activity.class);
                startActivity(intent);
                finish(); // 회원가입 화면 종료
            }
        });
    }

    // 이벤트 처리 객체 생성.
    private void events(){
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
    }
}