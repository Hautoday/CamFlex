package com.example.camflex.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.camflex.R;
import com.example.camflex.category.menus.TextBookActivity;
import com.example.camflex.category.menus.ClothingActivity;
import com.example.camflex.category.menus.BeautyActivity;
import com.example.camflex.category.menus.EtcActivity;
import com.example.camflex.category.menus.GoodsActivity;
import com.example.camflex.category.menus.LostActivity;

public class CategoryFragment extends Fragment {

    private LinearLayout layoutTextBook, layoutClothing, layoutLost,
            layoutBeauty, layoutGoods, layoutEtc;
    private TextView tvTip;

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // fragment_category 레이아웃 인플레이트
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 뷰 연결
        tvTip = view.findViewById(R.id.tv_tip_message);
        layoutTextBook  = view.findViewById(R.id.layout_textbook);
        layoutClothing   = view.findViewById(R.id.layout_clothing);
        layoutLost = view.findViewById(R.id.layout_lost);
        layoutBeauty  = view.findViewById(R.id.layout_beauty);
        layoutGoods = view.findViewById(R.id.layout_goods);
        layoutEtc  = view.findViewById(R.id.layout_etc);

        // 클릭 리스너 설정
        setClick(layoutTextBook, "교재");
        setClick(layoutClothing, "의류");
        setClick(layoutLost, "분실물");
        setClick(layoutBeauty, "뷰티");
        setClick(layoutGoods, "생활용품");
        setClick(layoutEtc, "기타");

        // Tip 클릭 이벤트
        if (tvTip != null) {
            tvTip.setOnClickListener(v ->
                    Toast.makeText(requireContext(), "거래는 밝은 인사부터 😊", Toast.LENGTH_SHORT).show()
            );
        }
    }

//    private void setClick(LinearLayout layout, String categoryName) {
//        if (layout == null) return;
//        layout.setOnClickListener(v -> {
//            Intent intent = new Intent(requireContext(), TextBookActivity.class);
//            intent.putExtra("category", categoryName); // 👈 카테고리명 전달
//            startActivity(intent);
//        });
//    }

    private void setClick(LinearLayout layout, String categoryName) {
        if (layout == null) return;

        layout.setOnClickListener(v -> {
            Intent intent = null;
            switch (categoryName) {
                case "교재":
                    intent = new Intent(requireContext(), TextBookActivity.class);
                    break;
                case "의류":
                    intent = new Intent(requireContext(), ClothingActivity.class);
                    break;
                case "분실물":
                    intent = new Intent(requireContext(), LostActivity.class);
                    break;
                case "뷰티":
                    intent = new Intent(requireContext(), BeautyActivity.class);
                    break;
                case "생활용품":
                    intent = new Intent(requireContext(), GoodsActivity.class);
                    break;
                case "기타":
                    intent = new Intent(requireContext(), EtcActivity.class);
                    break;
            }

            if (intent != null) {
                startActivity(intent);
            }
        });
    }


    private void events(){

    }
}
