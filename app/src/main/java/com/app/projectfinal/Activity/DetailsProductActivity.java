//package com.app.projectfinal.Activity;
//
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBar;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.bumptech.glide.Glide;
//import com.google.android.material.appbar.CollapsingToolbarLayout;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import com.app.projectfinal.DataLocal.DataLocalManager;
//import com.app.projectfinal.Model.SanPham;
//import com.app.projectfinal.Model.User;
//import com.app.projectfinal.R;
//
//public class DetailsProductActivity extends AppCompatActivity {
//
//    private static final String TAG = "TAG";
//    private Toolbar mToolbar;
//    private ActionBar mActionBar;
//    private ProgressBar progressBar;
//
//    // Volley Variables
//    private RequestQueue mRequestQueue;
//    private StringRequest mStringRequest;
//
//    private Bundle bundle;
//    private int idSPBundle, idLSP, idUser, soLuongSP, idSP;
//    private String tenSP, giaSP, status, donVi, shop, loaiSP, moTa, ngayDang, hinhSP;
//
//    private CollapsingToolbarLayout ctl;
//
//    private TextView tvName, tvPrice, tvDonVi, tvStatus, tvMoTa, tvNgayDang, tvSlOld, tvSlNew;
//    private ImageView image, imgAnim;
//
//    private SanPham sanPham;
//    private User user;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_details_product);
//
//        initView();
//
//        ctl = findViewById(R.id.collapsing_toolbar);
//        mToolbar = findViewById(R.id.toolbarDetail);
//        setSupportActionBar(mToolbar);
//        progressBar = findViewById(R.id.progressbarDetail);
//
//        // Setting up action bar
//        mActionBar = getSupportActionBar();
//        mActionBar.setDisplayHomeAsUpEnabled(true);
//        mActionBar.setTitle(null);
//        mActionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back));
//
//        user = DataLocalManager.getUser();
//
//        bundle = getIntent().getExtras();
//
//        idSPBundle = bundle.getInt("idSP");
////        idLSP = bundle.getInt("idSP");
////        idUser = bundle.getInt("idUser");
////        soLuong = bundle.getInt("soLuong");
////        giaSP = bundle.getString("giaSP");
////        tinhTrang = bundle.getString("tinhTrang");
////        shop = bundle.getString("shop");
////        loaiSP = bundle.getString("loaiSP");
////        tenSP = bundle.getString("tenSP");
////        moTa = bundle.getString("moTa");
////        donVi = bundle.getString("donVi");
////        ngayDang = bundle.getString("ngayDang");
////        hinhSP = bundle.getString("hinhSP");
////        tenSP = bundle.getString("tenSP");
//
//        getSP(String.valueOf(idSPBundle));
//
//    }
//
//    private void initView() {
//        tvName = findViewById(R.id.tvDetailName);
//        tvPrice = findViewById(R.id.tvDetailPrice);
//        tvDonVi = findViewById(R.id.tvDetailDonVi);
//        tvSlNew = findViewById(R.id.tvSlNew);
//        tvStatus = findViewById(R.id.tvDetailStatus);
//        tvMoTa = findViewById(R.id.tvDetailMoTa);
//        tvNgayDang = findViewById(R.id.tvDetailDate);
//        tvSlOld = findViewById(R.id.tvSlOld);
//        image = findViewById(R.id.imgDetailSP);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_detail, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_return_home:
//                startActivity(new Intent(DetailsProductActivity.this, HomeActivity.class));
//                break;
//            case R.id.action_report:
//                Toast.makeText(this, "Báo cáo", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.action_help:
//                Toast.makeText(this, "Hỗ trợ", Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    public void AddCart(View view) {
//        cart(user.getId_user(), idSP, 1, giaSP);
//    }
//
//    public void Buy(View view) {
//    }
//
//    private void getSP(String id) {
//
//        progressBar.setVisibility(View.VISIBLE);
//        // Initializing Request queue
//        mRequestQueue = Volley.newRequestQueue(DetailsProductActivity.this);
//
//        mStringRequest = new StringRequest(Request.Method.POST,
//                getBaseUrl(), new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d("ttttttt response", response);
//                progressBar.setVisibility(View.GONE);
//                try {
//                    JSONArray array = new JSONArray(response);
//                    for (int i = 0; i < array.length(); i++){
//                        JSONObject object = array.getJSONObject(i);
//                        Log.d("objecttttttttttt", String.valueOf(object));
//
//                        tenSP = object.getString("ten_san_pham");
//                        hinhSP = object.getString("hinh_san_pham");
//                        donVi = object.getString("don_vi");
//                        status = object.getString("tinh_trang");
//                        giaSP = object.getString("gia_san_pham");
//                        soLuongSP = object.getInt("so_luong");
//                        idUser = object.getInt("id_user");
//                        idSP = object.getInt("id_san_pham");
//                        idLSP = object.getInt("id_loai_san_pham");
//                        loaiSP = object.getString("ten_loai");
//                        moTa = object.getString("mo_ta_san_pham");
//                        shop = object.getString("shop");
//                        ngayDang = object.getString("ngay_dang");
//
//                    }
//                    Glide.with(DetailsProductActivity.this).load(hinhSP).into(image);
//                    tvName.setText(tenSP);
//                    tvPrice.setText(giaSP);
//                    tvDonVi.setText(donVi);
//                    tvSlOld.setText(String.valueOf(soLuongSP));
//                    tvMoTa.setText(moTa);
//                    tvNgayDang.setText(ngayDang);
//                    tvStatus.setText(status);
//                    if (status.matches("Còn hàng")) {
//                        tvStatus.setTextColor(Color.BLUE);
//                    } else {
//                        tvStatus.setTextColor(Color.RED);
//                    }
//                    ctl.setTitle(shop);
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                progressBar.setVisibility(View.GONE);
//                Toast.makeText(DetailsProductActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("id_san_pham", id);
//                return params;
//            }
//        };
//        mStringRequest.setShouldCache(false);
//        mRequestQueue.add(mStringRequest);
//    }
//
//    private void cart(int idUser, int idSP, int so_luong, String tong) {
//        progressBar.setVisibility(View.VISIBLE);
//        // Initializing Request queue
//        mRequestQueue = Volley.newRequestQueue(DetailsProductActivity.this);
//
//        mStringRequest = new StringRequest(Request.Method.POST,
//                getBaseUrlCart(), new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    Log.d(TAG, "onResponse: " + response);
//                    JSONObject jsonObject = new JSONObject(response);
//
//                    int success = jsonObject.getInt("success");
//                    String message = jsonObject.getString("message");
//                    if (success == 1) {
//                        progressBar.setVisibility(View.GONE);
//                        Toast.makeText(DetailsProductActivity.this,message,Toast.LENGTH_SHORT).show();
//                        // Finish
//                        finish();
//                    }
//                    if (success == 0) {
//                        progressBar.setVisibility(View.GONE);
//                        Toast.makeText(DetailsProductActivity.this,message,Toast.LENGTH_SHORT).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    progressBar.setVisibility(View.GONE);
//                    Toast.makeText(DetailsProductActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                progressBar.setVisibility(View.GONE);
//                Toast.makeText(DetailsProductActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("id_user", String.valueOf(idUser));
//                params.put("id_san_pham", String.valueOf(idSP));
//                params.put("so_luong", String.valueOf(so_luong));
//                params.put("tong", tong);
//                return params;
//            }
//        };
//        mStringRequest.setShouldCache(false);
//        mRequestQueue.add(mStringRequest);
//    }
//
//    private String getBaseUrlCart (){
//        return "http://"+getResources().getString(R.string.machine_ip_address)+"/du_an_1/cart.php";
//    }
//
//    private String getBaseUrl (){
//        return "http://"+getResources().getString(R.string.machine_ip_address)+"/du_an_1/getDetailSanPham.php";
//    }
//}