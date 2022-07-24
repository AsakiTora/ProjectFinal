package com.app.projectfinal.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.projectfinal.R;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductDetailActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    private Toolbar toolbar;
    private ActionBar action_bar;
    private ViewPager2 vp2_img_details;
    private TextView tv_name_product, tv_price, tv_unit, tv_name_shop, tv_is_online, tv_view_shop;
    private CircleImageView img_avt_shop;
    private RecyclerView rcv_new_products;
    private AppCompatImageButton btn_chat, btn_add_cart;
    private AppCompatButton btn_buy_now;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        initView();

        setSupportActionBar(toolbar);

        // Setting up action bar
        action_bar = getSupportActionBar();
        action_bar.setDisplayHomeAsUpEnabled(true);
        action_bar.setTitle(null);
        action_bar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back));
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        vp2_img_details = (ViewPager2) findViewById(R.id.vp2_img_details);
        tv_name_product = (TextView) findViewById(R.id.tv_name_product);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_unit = (TextView) findViewById(R.id.tv_unit);
        tv_name_shop = (TextView) findViewById(R.id.tv_name_shop);
        tv_is_online = (TextView) findViewById(R.id.tv_is_online);
        tv_view_shop = (TextView) findViewById(R.id.tv_view_shop);
        img_avt_shop = (CircleImageView) findViewById(R.id.img_avt_shop);
        rcv_new_products = (RecyclerView) findViewById(R.id.rcv_new_products);
        btn_add_cart = (AppCompatImageButton) findViewById(R.id.btn_add_cart);
        btn_chat = (AppCompatImageButton) findViewById(R.id.btn_chat);
        btn_buy_now = (AppCompatButton) findViewById(R.id.btn_buy_now);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_return_home:
                startActivity(new Intent(ProductDetailActivity.this, HomeActivity.class));
                break;
            case R.id.action_report:
                Toast.makeText(this, "Báo cáo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_help:
                Toast.makeText(this, "Hỗ trợ", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}