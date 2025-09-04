package com.example.camflex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {

    // 찜한 아이템을 저장할 static 리스트
    private static final ArrayList<FavoriteItem> favoritesList = new ArrayList<>();

    public static void addFavorite(String title, String price, int imageResId) {
        favoritesList.add(new FavoriteItem(title, price, imageResId));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_att);

        LinearLayout container = findViewById(R.id.favoritesContainer);
        container.removeAllViews();

        // 리스트에 있는 모든 아이템을 화면에 추가
        for (FavoriteItem item : favoritesList) {
            View itemView = LayoutInflater.from(this).inflate(R.layout.favorite_item, container, false);

            ImageView imageView = itemView.findViewById(R.id.itemImageView);
            TextView titleView = itemView.findViewById(R.id.itemTitleTextView);
            TextView priceView = itemView.findViewById(R.id.itemPriceTextView);

            imageView.setImageResource(item.imageResId);
            titleView.setText(item.title);
            priceView.setText(item.price);

            container.addView(itemView);
        }
    }

    // 찜 아이템 객체
    private static class FavoriteItem {
        String title;
        String price;
        int imageResId;

        FavoriteItem(String title, String price, int imageResId) {
            this.title = title;
            this.price = price;
            this.imageResId = imageResId;
        }
    }
}
