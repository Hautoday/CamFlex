package com.example.camflex.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.camflex.PostAdapter;
import com.example.camflex.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private PostAdapter adapter;
    private ArrayList<Post> postList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        postlist();
        events();
        return rootView;
    }

    public static class Post {
        public String title;
        public String price;
        public String date;
        public int imageResId;
        public boolean isLiked = false; // 하트 상태
        public Post(String title, String price, String date, int imageResId) {
            this.title = title;
            this.price = price;
            this.date = date;
            this.imageResId = imageResId;
        }
    }
    // 이벤트 처리 객체를 선언함.
    private void events(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PostAdapter(postList);
        recyclerView.setAdapter(adapter);
    }

    // 예시데이터 ( 데이터베이스를 연동하여 값을 넣어야 하는 부분임. )
    private void postlist(){
        postList.add(new Post("후지필름", "278,000원", "07/28", R.drawable.simple_book_image1));
        postList.add(new Post("캐논 카메라", "350,000원", "07/25", R.drawable.simple_book_image2));
        postList.add(new Post("니콘 렌즈", "180,000원", "07/20", R.drawable.simple_book_image1));
    }
}
