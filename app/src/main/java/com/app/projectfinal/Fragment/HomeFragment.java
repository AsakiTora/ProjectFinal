package com.app.projectfinal.fragment;

import static com.app.projectfinal.utils.Constant.PRODUCTS;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.app.projectfinal.R;
import com.app.projectfinal.adapter.ProductAdapter;
import com.app.projectfinal.model.Product;
import com.app.projectfinal.utils.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private View view;
    private RecyclerView rcvProduct;
    private ProductAdapter productAdapter;
    private List<Product> products;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_carts:
                break;
        }

        return true;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.dashboard_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    public HomeFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null)
            view = inflater.inflate(R.layout.fragment_home, container, false);
        initView();
        products = new ArrayList<>();
        showProducts();
        return view;
    }

    private void initView() {
        rcvProduct = view.findViewById(R.id.rcvProduct);

    }

    private void showProducts() {
        LinearLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rcvProduct.setLayoutManager(layoutManager);

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
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();

                }
                productAdapter = new ProductAdapter(products, getContext());
                rcvProduct.setAdapter(productAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();

            }
        });
        VolleySingleton.getInstance(getContext()).getRequestQueue().add(jsonObjectRequest);

    }
}