package com.app.projectfinal.activity;

import static com.app.projectfinal.activity.ProductDetailActivity.carts;
import static com.app.projectfinal.activity.SplashActivity.products;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.app.projectfinal.R;
import com.app.projectfinal.adapter.CartAdapter;
import com.app.projectfinal.adapter.ProductAdapter;
import com.app.projectfinal.model.Cart;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView rcv_cart;
    RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initView();
        manager = new GridLayoutManager(this, 1);
        rcv_cart.setLayoutManager(manager);
        adapter = new CartAdapter(this, carts);
        rcv_cart.setAdapter(adapter);
    }

    private void initView() {
        rcv_cart = (RecyclerView) findViewById(R.id.rcv_cart);

    }


}