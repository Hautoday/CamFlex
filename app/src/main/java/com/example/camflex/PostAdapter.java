package com.example.camflex;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.camflex.fragment.HomeFragment;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<HomeFragment.Post> postList;

    public PostAdapter(List<HomeFragment.Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        HomeFragment.Post post = postList.get(position);

        holder.textTitle.setText(post.title);
        holder.textPrice.setText(post.price);
        holder.textDate.setText(post.date);
        holder.imageView.setImageResource(post.imageResId);

        // 하트 상태 반영
        holder.heart.setImageResource(post.isLiked ? R.drawable.unlike_image : R.drawable.like_image);

        // 하트 클릭 시 상태 토글
        holder.heart.setOnClickListener(v -> {
            post.isLiked = !post.isLiked; // 반전
            notifyItemChanged(position);  // UI 갱신
        });

        // 아이템 클릭 이벤트
        holder.itemView.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, ProductActivity.class);
            intent.putExtra("title", post.title);
            intent.putExtra("price", post.price);
            intent.putExtra("date", post.date);
            intent.putExtra("imageResId", post.imageResId);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, heart;
        TextView textTitle, textPrice, textDate;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            heart = itemView.findViewById(R.id.heart); // 하트 ImageView 추가
            textTitle = itemView.findViewById(R.id.textTitle);
            textPrice = itemView.findViewById(R.id.textPrice);
            textDate = itemView.findViewById(R.id.textDate);
        }
    }
}
