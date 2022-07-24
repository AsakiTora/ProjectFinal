package com.app.projectfinal.fragment;

import static com.app.projectfinal.activity.SplashActivity.categories;
import static com.app.projectfinal.activity.SplashActivity.products;
import static com.app.projectfinal.utils.Constant.PRODUCTS;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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
import com.app.projectfinal.adapter.CategoryAdapter;
import com.app.projectfinal.adapter.ProductAdapter;
import com.app.projectfinal.model.Category;
import com.app.projectfinal.model.Product;
import com.app.projectfinal.utils.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private View view;
    private RecyclerView rcv_products, rcv_categories;
    private ProductAdapter productAdapter;
    private CategoryAdapter categoryAdapter;
    private RecyclerView.LayoutManager layout_category, layout_product;
    private List<Product> list_products;
    private List<Category> list_categories;

    private Bundle bundle;

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
        layout_product = new GridLayoutManager(getContext(), 2);
        rcv_products.setLayoutManager(layout_product);
        productAdapter = new ProductAdapter(products, getContext());
        rcv_products.setAdapter(productAdapter);

        layout_category = new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false);
        rcv_categories.setLayoutManager(layout_category);
        categoryAdapter = new CategoryAdapter(getContext(), categories);
        rcv_categories.setAdapter(categoryAdapter);

        return view;
    }

    private void initView() {
        rcv_products = view.findViewById(R.id.rcv_products);
        rcv_categories = view.findViewById(R.id.rcv_categories);
    }



}