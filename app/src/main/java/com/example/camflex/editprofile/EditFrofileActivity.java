package com.example.camflex.editprofile;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.camflex.R;

public class EditFrofileActivity extends AppCompatActivity {
    ImageView btnClose;
    TextView btnDone;
    EditText editNickname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit2);
        events();

        btnClose.setOnClickListener(v -> finish());
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editNickname.getText().toString().trim();
                if(name.isEmpty()){
                    Toast.makeText(EditFrofileActivity.this, "닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                // 서버에 이름 변경 요청.
                
                finish();
            }
        });
    }
    private void events(){
        btnClose = findViewById(R.id.btnClose);
        btnDone = findViewById(R.id.btnDone);
        editNickname = findViewById(R.id.editNickname);
    }
}
