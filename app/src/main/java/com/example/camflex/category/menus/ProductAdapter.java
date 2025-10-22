package com.example.camflex.category.menus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.camflex.R;
import com.example.camflex.model.Product; // model 패키지명은 실제 프로젝트에 맞게 확인
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    // ✅ 1. 서버의 기본 주소를 상수로 정의합니다.
    private static final String BASE_URL = "http://hautoday.iptime.org:5000";

    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        // 안드로이드에서는 Product 모델을 사용한다고 가정
        Product product = productList.get(position);

        holder.title.setText(product.getTitle());
        holder.date.setText("등록일: " + product.getDate());

        String formattedPrice = NumberFormat.getNumberInstance(Locale.KOREA).format(product.getPrice()) + "원";
        holder.price.setText(formattedPrice);

        // ✅ 2. 서버에서 받은 상대 경로 앞에 기본 주소를 붙여서 전체 URL을 만듭니다.
        // 예: "http://hautoday.iptime.org:5000" + "/uploads/image.jpg"
        String fullImageUrl = BASE_URL + product.getImage();

        // Glide를 사용하여 완성된 전체 URL로 이미지를 로드
        Glide.with(context)
                .load(fullImageUrl) // ⬅️ 완성된 전체 URL 사용
                .placeholder(R.drawable.ic_launcher_background) // 로딩 중에 보여줄 이미지
                .error(R.drawable.ic_launcher_background) // 에러 시 보여줄 이미지
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, date, price;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewProduct);
            title = itemView.findViewById(R.id.textViewTitle);
            date = itemView.findViewById(R.id.textViewDate);
            price = itemView.findViewById(R.id.textViewPrice);
        }
    }
}