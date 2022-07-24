package com.app.projectfinal.activity;

import static com.app.projectfinal.utils.Constant.CATEGORY;
import static com.app.projectfinal.utils.Constant.PRODUCTS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.projectfinal.R;
import com.app.projectfinal.adapter.ProductAdapter;
import com.app.projectfinal.model.Category;
import com.app.projectfinal.model.Product;
import com.app.projectfinal.utils.CheckStateNetwork;
import com.app.projectfinal.utils.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private List<Product> products;
    private List<Category> categories;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        products = new ArrayList<>();

        loadData();
    }

    private void loadData() {
        if (CheckStateNetwork.isNetworkAvailable(this)){
            //Network connected
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    getProducts();
                    getCategories();
                    finish();
                }
            }, 3000);
        } else {
            //Network disconnected
            Toast.makeText(this, "Network disconnected", Toast.LENGTH_SHORT).show();
        }
    }

    private void getProducts() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, PRODUCTS, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject jsonObject = response.getJSONObject("data");
                    JSONArray jsonArray = jsonObject.getJSONArray("products");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id = object.getString("id");
                        String store_id = object.getString("storeId");
                        String category_id = object.getString("categoryId");
                        String unit_id = object.getString("unitId");
                        String quantity = object.getString("quantity");
                        String price = object.getString("price");
                        String product_name = object.getString("productName");
                        String img_thumb = object.getString("image1");
                        String img_large_1 = object.getString("image2");
                        String img_large_2 = object.getString("image3");
                        String img_large_3 = object.getString("image4");
                        String img_large_4 = object.getString("image5");
                        List<Object> list_img = new ArrayList<>();
                        list_img.add(img_thumb);
                        list_img.add(img_large_1);
                        list_img.add(img_large_2);
                        list_img.add(img_large_3);
                        list_img.add(img_large_4);
                        String description = object.getString("description");
                        String status = object.getString("status");
                        String created_at = object.getString("createdAt");
                        String store_name = object.getString("storeName");
                        String category_name = object.getString("categoryName");
                        String unit_name = object.getString("unitName");

                        products.add(new Product(id, store_id, category_id, unit_id, quantity, price, product_name, description, status, created_at, store_name, category_name, unit_name, list_img));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(SplashActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SplashActivity.this, error.toString(), Toast.LENGTH_LONG).show();

            }
        });
        VolleySingleton.getInstance(SplashActivity.this).getRequestQueue().add(jsonObjectRequest);

    }

    private void getCategories() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, PRODUCTS, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject jsonObject = response.getJSONObject("data");
                    JSONArray jsonArray = jsonObject.getJSONArray("products");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id = object.getString("id");
                        String name = object.getString("name");
                        String image = object.getString("image1");

                        categories.add(new Category(id, name, image));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(SplashActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SplashActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        VolleySingleton.getInstance(SplashActivity.this).getRequestQueue().add(jsonObjectRequest);
    }
}