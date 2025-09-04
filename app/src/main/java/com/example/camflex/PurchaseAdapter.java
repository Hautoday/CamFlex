package com.example.camflex;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.ViewHolder> {

    private final List<PurchaseItem> items;

    public PurchaseAdapter(List<PurchaseItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_purchase_his, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PurchaseItem item = items.get(position);
        holder.imageView.setImageResource(item.imageResId);
        holder.title.setText(item.title);
        holder.locationTime.setText(item.locationTime);
        holder.status.setText(item.status);
        holder.price.setText(item.price);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, locationTime, status, price;
        ImageButton moreBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_product);
            title = itemView.findViewById(R.id.text_product_title);
            locationTime = itemView.findViewById(R.id.text_location_time);
            status = itemView.findViewById(R.id.text_status);
            price = itemView.findViewById(R.id.text_price);
            moreBtn = itemView.findViewById(R.id.button_more_options);
        }
    }
}
