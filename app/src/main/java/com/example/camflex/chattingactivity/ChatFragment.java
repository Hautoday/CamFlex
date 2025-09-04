package com.example.camflex.chattingactivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.camflex.R;

public class ChatFragment extends Fragment {

    private String myName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        // 회원가입에서 저장한 이름 불러오기
        SharedPreferences prefs = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        myName = prefs.getString("username", "익명");

        // 채팅 아이템
        LinearLayout chat1 = view.findViewById(R.id.chat_item_1);
        LinearLayout chat2 = view.findViewById(R.id.chat_item_2);
        LinearLayout chat3 = view.findViewById(R.id.chat_item_3);

        // 클릭 → 저장된 이름 기반으로 채팅방 열기
        chat1.setOnClickListener(v -> openChat(myName, "알록이"));
        chat2.setOnClickListener(v -> openChat(myName, "방글이"));
        chat3.setOnClickListener(v -> openChat(myName, "핑클이"));

        return view;
    }

    private void openChat(String myName, String friendName) {
        Intent intent = new Intent(getActivity(), Chat2Activity.class);
        intent.putExtra("my_name", myName);
        intent.putExtra("friend_name", friendName);
        startActivity(intent);
    }
}
