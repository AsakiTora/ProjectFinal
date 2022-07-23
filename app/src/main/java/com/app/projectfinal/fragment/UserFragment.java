package com.app.projectfinal.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.app.projectfinal.R;
import com.app.projectfinal.activity.myshop;
import com.app.projectfinal.activity.shop_settings;

public class UserFragment extends Fragment {
    private TextView textView ;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        textView = view.findViewById(R.id.linear_user_ban);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(),shop_settings.class));
//            }
//        });
        return view;
    }

}