package com.app.projectfinal.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.app.projectfinal.Activity.SetTypeActivity;
import com.app.projectfinal.Activity.SettingsActivity;
import com.app.projectfinal.Activity.ShopActivity;
import com.app.projectfinal.DataLocal.DataLocalManager;
import com.app.projectfinal.Model.User;
import com.app.projectfinal.R;

public class UserFragment extends Fragment {

    private ProgressBar progressBar;
    private LinearLayout llSetUser, llRates, llSetType, llPaste, llRewards;
    private TextView name, type, shop;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        initView(view);
        setUser();
        setType();

        User user = DataLocalManager.getUser();
        name.setText(user.getAccount());
        return view;
    }

    private void initView(View view) {
        progressBar = view.findViewById(R.id.progressbar);
        llSetUser = view.findViewById(R.id.llSetUser);
        llSetType = view.findViewById(R.id.llSetType);
        name = view.findViewById(R.id.tvNameUser);
        type = view.findViewById(R.id.tvTypeUser);
        shop = view.findViewById(R.id.tvShop);
    }

    private void setUser() {
        llSetUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SettingsActivity.class));
            }
        });
    }

    private void setType() {
        User user = DataLocalManager.getUser();
        if (user.getId_group() == 1) {
            llSetType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext(), SetTypeActivity.class));
                }
            });
        } else {
            shop.setText("Cửa hàng của tôi");
            llSetType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext(), ShopActivity.class));
                }
            });
        }

    }

    private String getBaseUrl (){
        return "http://"+"/du_an_1/getSanPham.php";
    }
}