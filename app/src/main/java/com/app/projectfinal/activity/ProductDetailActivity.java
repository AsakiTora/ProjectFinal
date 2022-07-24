package com.app.projectfinal.activity;

import static com.app.projectfinal.activity.SplashActivity.products;

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
import com.app.projectfinal.data.TinyDB;
import com.app.projectfinal.model.Cart;
import com.app.projectfinal.model.Product;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private Bundle bundle;
    private int position_product;
    private Product product;
    public static final List<Cart> carts = new ArrayList<>();
    private List<Object> list_img;
    private TinyDB tinyDB;

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

        getDataBundle();
        getDataProduct();

        addCart();

        tinyDB = new TinyDB(this);
    }

    private void getDataBundle(){
        bundle = getIntent().getExtras();
        position_product = bundle.getInt("position_product");
    }

    private void getDataProduct(){
        product = products.get(position_product);
        list_img = product.getImage();
        tv_name_product.setText(product.getName());
        tv_price.setText(product.getPrice());
        tv_name_shop.setText(product.getShop_name());
        //Glide.with(this).load(product.getImage()).into(vp2_img_details);
        tv_unit.setText(product.getUnit_name());
    }

    private void addCart(){
        btn_add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (carts != null){
                    for (int i = 0; i < carts.size(); i++){
                        if (carts.get(i).getId_product().equals(product.getId())){

                        }
                            //Integer.parseInt(carts.get(i).getAmount()) += 1;
                    }
                }
                carts.add(new Cart(product.getId(), product.getId_shop(), product.getName(), "1", product.getPrice(), (String) list_img.get(0)));
                Toast.makeText(ProductDetailActivity.this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();

            }
        });
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