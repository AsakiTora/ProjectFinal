package com.app.projectfinal.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.projectfinal.Activity.NotificationsActivity;
import com.app.projectfinal.Adapter.LoaiSanPhamAdapter;
import com.app.projectfinal.Adapter.SanPhamAdapter;
import com.app.projectfinal.DataLocal.DataLocalManager;
import com.app.projectfinal.Model.LoaiSanPham;
import com.app.projectfinal.Model.SanPham;
import com.app.projectfinal.Model.User;
import com.app.projectfinal.R;

public class HomeFragment extends Fragment {

    private static final String TAG = "TAG";
    private ViewFlipper viewFlipper;
    ListView lv_main;
    NavigationView nav_main;

    private StringRequest mStringRequest;
    private RequestQueue mRequestQueue;

    private RecyclerView rcvSP, rcvLSP;
    private RecyclerView.LayoutManager manager, loaiSanPhamManager;
    private RecyclerView.Adapter mAdapter, loaiSanPhamAdapter;
    private ArrayList<LoaiSanPham> listLSP;
    private List<SanPham> listSP;
    private ProgressBar progressBar;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_carts:
                startActivity(new Intent(getContext(), NotificationsActivity.class));
                break;
        }

        return true;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.dashboard_menu,menu);

        super.onCreateOptionsMenu(menu, inflater);
    }


    public HomeFragment() {
        // Required empty public constructor


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initView(view);
//        ActionBar();
        ActionViewFlipper();

        progressBar = view.findViewById(R.id.progressbar);

        rcvSP = view.findViewById(R.id.rcvSP);
        manager = new GridLayoutManager(getContext(), 2);
        rcvSP.setLayoutManager(manager);
        listSP = new ArrayList<SanPham>();
        listLSP = new ArrayList<LoaiSanPham>();

        rcvLSP = view.findViewById(R.id.rcvLSP);
        loaiSanPhamManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false);
        rcvLSP.setLayoutManager(loaiSanPhamManager);

        getSP();
        getLSP();
        return view;
    }

    private void getLSP() {
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, getBaseUrl2(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressBar.setVisibility(View.GONE);

                        try {

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++){

                                JSONObject object = array.getJSONObject(i);
                                Log.d("objecttttttttttt", String.valueOf(object));

                                int idLSP = object.getInt("id_loai_san_pham");
                                String tenLSP = object.getString("ten_loai");
                                String hinhLSP = object.getString("hinh_loai");

                                LoaiSanPham lSP = new LoaiSanPham(idLSP, tenLSP, hinhLSP);
                                listLSP.add(lSP);
                                Log.d("listsssssssssssssssss", String.valueOf(listLSP.size()));
                            }

                        }catch (Exception e){
                            Log.d("listsssssssssssssssss", String.valueOf(listLSP.size()));
                            Log.d("loiiiiiiiiiiiiiii", String.valueOf(e));
                        }

                        loaiSanPhamAdapter = new LoaiSanPhamAdapter(getContext(), listLSP);
                        rcvLSP.setAdapter(loaiSanPhamAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), error.toString(),Toast.LENGTH_LONG).show();
                Log.d("errorrrrrrrrrrrrrreeeee", String.valueOf(error));
            }
        });

        Volley.newRequestQueue(getContext()).add(stringRequest);

    }

    private void getSP() {
        mRequestQueue = Volley.newRequestQueue(getActivity());
        progressBar.setVisibility(View.VISIBLE);
        mStringRequest = new StringRequest(Request.Method.POST, getBaseUrl(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "onResponse: " + response);
                        progressBar.setVisibility(View.GONE);
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

                                Log.d("listsssssssssssssssssppppppp", String.valueOf(listSP.size()));
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        mAdapter = new SanPhamAdapter(getContext(), listSP);
                        rcvSP.setAdapter(mAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), error.toString(),Toast.LENGTH_LONG).show();
                Log.d("errorrrrrrrrrrrrrreeeee", String.valueOf(error));
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                User user = DataLocalManager.getUser();
                params.put("id_user", String.valueOf(user.getId_user()));
                return params;
            }
        };

        mStringRequest.setShouldCache(false);
        mRequestQueue.add(mStringRequest);
    }

    private void ActionViewFlipper() {
        ArrayList<String> listAds = new ArrayList<>();
        listAds.add("https://vinmec-prod.s3.amazonaws.com/images/20210310_043054_473634_qua-vai-thieu.max-800x800.jpg");
        listAds.add("https://nhavuonhieule.com/wp-content/uploads/2017/08/1438076053-cui3.jpg");
        listAds.add("https://i.khoahoc.tv/photos/image/2016/03/25/dua-hau-4.jpg");
        listAds.add("https://hoaquafuji.com/storage/app/media/gia-nho-sua-han-quoc-fuji-7.jpg");
        listAds.add("https://cdn.tgdd.vn/2021/01/CookProduct/tl1-1200x676.jpg");

        for (int i = 0; i < listAds.size(); i++) {
            ImageView img = new ImageView(getContext());
            Picasso.get().load(listAds.get(i)).into(img);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(img);
        }

        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);

        Animation anim_slide_in = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_right);
        Animation anim_slide_out = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_right);

        viewFlipper.setInAnimation(anim_slide_in);
        viewFlipper.setOutAnimation(anim_slide_out);
    }

//    private void convertImageBase64() {
//        byte[] decodedString = Base64.decode(s, Base64.DEFAULT);
//        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//        addImg.setImageBitmap(decodedByte);
//    }

    private void initView(View view) {
//        toolbar = findViewById(R.id.toolbar);
//        lv_main = findViewById(R.id.lv_main);
        viewFlipper = view.findViewById(R.id.viewFlipper);
//        nav_main = findViewById(R.id.nav_main);

    }

    private String getBaseUrl (){
        return "http://"+"/du_an_1/getSanPham.php";
    }

    private String getBaseUrl2 (){
        return "http://"+"/du_an_1/getLoaiSanPham.php";
    }

}