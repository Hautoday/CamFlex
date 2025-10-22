package com.example.camflex.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.camflex.CenterActivity;
import com.example.camflex.FavoriteActivity;
import com.example.camflex.PurchaseHistoryActivity;
import com.example.camflex.R;
import com.example.camflex.SellHistoryActivity;
import com.example.camflex.editprofile.user_infomation_activiry;
import com.example.camflex.startview.StartActivity;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 뷰 바인딩
        ImageView profileImage = view.findViewById(R.id.profileImage);
        TextView username = view.findViewById(R.id.username);
        Button btnProfileEdit = view.findViewById(R.id.btnProfileEdit);

        TextView likeList = view.findViewById(R.id.likeList);
        TextView sellHistory = view.findViewById(R.id.saleHistory);
        TextView purchaseHistory = view.findViewById(R.id.purchaseHistory);

        TextView customerCenter = view.findViewById(R.id.customerCenter);
        TextView withdraw = view.findViewById(R.id.withdraw);
        TextView logout = view.findViewById(R.id.logout);

        // ✅ MainActivity에서 전달받은 username 값 표시
        if (getArguments() != null) {
            String name = getArguments().getString("username", "사용자");
            username.setText(name);
        }

        // 클릭 이벤트들
        btnProfileEdit.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), user_infomation_activiry.class);
            startActivity(intent);
        });

        likeList.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), FavoriteActivity.class);
            startActivity(intent);
        });

        sellHistory.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), SellHistoryActivity.class);
            startActivity(intent);
        });

        purchaseHistory.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), PurchaseHistoryActivity.class);
            startActivity(intent);
        });

        customerCenter.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CenterActivity.class);
            startActivity(intent);
        });

        withdraw.setOnClickListener(v ->
                Toast.makeText(requireContext(), "회원탈퇴 클릭됨", Toast.LENGTH_SHORT).show()
        );

        logout.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), StartActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            getActivity().finish();
        });
    }
}
