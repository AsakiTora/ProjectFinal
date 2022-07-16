package com.app.projectfinal.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.projectfinal.Adapter.SanPhamAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.projectfinal.Model.SanPham;
import fpoly.andoid.test2.R;

public class ShowLSPActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private ActionBar mActionBar;

    private StringRequest mStringRequest;
    private RequestQueue mRequestQueue;

    private int id;
    private String ten_loai;

    private List<SanPham> listSP;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView rcvSP;
    private RecyclerView.LayoutManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_lspactivity);
        mToolbar = findViewById(R.id.toolbarShowLSP);

        listSP = new ArrayList<>();
        rcvSP = findViewById(R.id.rcvShowLSP);
        manager = new GridLayoutManager(getApplicationContext(), 2);
        rcvSP.setLayoutManager(manager);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");
        ten_loai = bundle.getString("ten_loai");
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle(ten_loai);
        mActionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back));
        show();
    }

    private void show(){

        mRequestQueue = Volley.newRequestQueue(ShowLSPActivity.this);
        // Progress
        mStringRequest = new StringRequest(Request.Method.POST, getBaseUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", "onResponse: response"+ response);
                try {

                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++){

                        JSONObject object = array.getJSONObject(i);
                        Log.d("objecttttttttttt", String.valueOf(object));

                        String tenSP = object.getString("ten_san_pham");
                        String hinhSP = object.getString("hinh_san_pham");
                        String donVi = object.getString("don_vi");
                        String status = object.getString("tinh_trang");
                        String giaSP = object.getString("gia_san_pham");
                        int soLuongSP = object.getInt("so_luong");
                        int idUser = object.getInt("id_user");
                        int idSP = object.getInt("id_san_pham");
                        int idLSP = object.getInt("id_loai_san_pham");
                        String loaiSP = object.getString("ten_loai");
                        String moTa = object.getString("mo_ta_san_pham");
                        String shop = object.getString("shop");
                        String ngayDang = object.getString("ngay_dang");

                        SanPham sanPham = new SanPham(idSP, idLSP, loaiSP, giaSP, tenSP, moTa, ngayDang, hinhSP, donVi, status, shop, soLuongSP, idUser);
                        listSP.add(sanPham);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ShowLSPActivity.this,e.toString(),Toast.LENGTH_LONG).show();
                    Log.d("errorrrrrrrrrrrrrr", String.valueOf(e));
                }

                if (listSP == null) {
                    rcvSP.setTag("Không có sản phẩm cần tìm");
                } else {
                    mAdapter = new SanPhamAdapter(getApplicationContext(), listSP);
                    rcvSP.setAdapter(mAdapter);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ShowLSPActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                Log.d("errorrrrrrrrrrrrrr", String.valueOf(error));
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_loai_san_pham", String.valueOf(id));
                return params;
            }
        };

        mStringRequest.setShouldCache(false);
        mRequestQueue.add(mStringRequest);
    }

    private String getBaseUrl (){
        return "http://"+getResources().getString(R.string.machine_ip_address)+"/du_an_1/getSPtoLSP.php";
    }
}