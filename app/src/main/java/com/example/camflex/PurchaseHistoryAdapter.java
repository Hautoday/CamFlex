package com.example.camflex;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PurchaseHistoryAdapter extends RecyclerView.Adapter<PurchaseHistoryAdapter.ViewHolder> {

    private Context context;
    private int itemCount; // 보여줄 아이템 개수

    public PurchaseHistoryAdapter(Context context, int itemCount) {
        this.context = context;
        this.itemCount = itemCount;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_purchase_his, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // ✅ 버튼 클릭 시 ReviewActivity로 이동
        holder.buttonViewReview.setOnClickListener(v -> {
            Intent intent = new Intent(context, ReviewActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemCount; // 아이템 개수 반환
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Button buttonViewReview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // ✅ item_purchase_his.xml 안에 반드시 button_view_review 버튼 있어야 함
            buttonViewReview = itemView.findViewById(R.id.button_view_review);
        }
    }
}