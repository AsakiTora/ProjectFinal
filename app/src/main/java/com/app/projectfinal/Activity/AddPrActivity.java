//package com.app.projectfinal.Activity;
//
//import android.Manifest;
//import android.app.Dialog;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.util.Base64;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.AutoCompleteTextView;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
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
//import com.karumi.dexter.Dexter;
//import com.karumi.dexter.PermissionToken;
//import com.karumi.dexter.listener.PermissionDeniedResponse;
//import com.karumi.dexter.listener.PermissionGrantedResponse;
//import com.karumi.dexter.listener.PermissionRequest;
//import com.karumi.dexter.listener.single.PermissionListener;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.ByteArrayOutputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.app.projectfinal.DataLocal.DataLocalManager;
//import com.app.projectfinal.Model.LoaiSanPham;
//import com.app.projectfinal.Model.User;
//import com.app.projectfinal.R;
//
//public class AddPrActivity extends AppCompatActivity {
//
//    private Toolbar mToolbar;
//    private ActionBar mActionBar;
//
//    private ImageView addImg;
//    private EditText name, moTa, sl, donVi, gia;
//    private AutoCompleteTextView spnLSP;
//
//    private Bitmap bitmap;
//    private String encodeImageString;
//
//    public static final String TAG = "MY_TAG";
//
//    private ArrayList<LoaiSanPham> list;
//    private List<String> listLSP;
//    private String idLSP;
//
//    private StringRequest mStringRequest;
//    private RequestQueue mRequestQueue;
//
//    String upLoadServerUri = null;
//
//    /**********  File Path *************/
//    final String uploadFilePath = "/mnt/sdcard/";
//    final String uploadFileName = "service_lifecycle.png";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_pr);
//
//        initView();
//
//        mToolbar = findViewById(R.id.toolbarAddPr);
//        setSupportActionBar(mToolbar);
//
//        // Setting up action bar
//        mActionBar = getSupportActionBar();
//        mActionBar.setDisplayHomeAsUpEnabled(true);
//        mActionBar.setTitle(getResources().getString(R.string.addProduct));
//        mActionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back));
//
//        setAddImg();
//        getLSP();
//        getSpn();
//        list = new ArrayList<>();
//        listLSP = new ArrayList<>();
//
//        /************* Php script path ****************/
//        upLoadServerUri = "http://"+getResources().getString(R.string.machine_ip_address)+"/du_an_1/upSanPham.php";
//    }
//
//    private void initView() {
//        addImg = findViewById(R.id.imgSelect);
//        name = findViewById(R.id.edtTenSP);
//        moTa = findViewById(R.id.edtMoTaSP);
//        sl = findViewById(R.id.edtSl);
//        donVi = findViewById(R.id.edtDonVi);
//        gia = findViewById(R.id.edtPrice);
//        spnLSP = findViewById(R.id.spnLSP);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.apply_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.act_apply:
//                upSanPham();
//
//                Log.d("TAG", "onOptionsItemSelected: ");
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void setAddImg() {
//        addImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Dialog dialog = new Dialog(AddPrActivity.this);
//                dialog.setContentView(R.layout.dialog_upload_img);
//                TextView chup = dialog.findViewById(R.id.tvChup);
//                TextView up = dialog.findViewById(R.id.tvUp);
//                chup.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Dexter.withActivity(AddPrActivity.this)
//                                .withPermission(Manifest.permission.CAMERA)
//                                .withListener(new PermissionListener() {
//                                    @Override
//                                    public void onPermissionGranted(PermissionGrantedResponse response) {
//                                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                                        startActivityForResult(Intent.createChooser(intent, "Chọn thư mục ảnh"), 0);
//                                        dialog.dismiss();
//                                    }
//
//                                    @Override
//                                    public void onPermissionDenied(PermissionDeniedResponse response) {
//
//                                    }
//
//                                    @Override
//                                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
//                                        token.continuePermissionRequest();
//                                    }
//                                }).check();
//                    }
//                });
//                up.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Dexter.withActivity(AddPrActivity.this)
//                                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
//                                .withListener(new PermissionListener() {
//                                    @Override
//                                    public void onPermissionGranted(PermissionGrantedResponse response) {
//                                        Intent intent = new Intent(Intent.ACTION_PICK);
//                                        intent.setType("image/*");
//                                        startActivityForResult(Intent.createChooser(intent, "Chọn thư mục ảnh"), 1);
//                                        dialog.dismiss();
//                                    }
//
//                                    @Override
//                                    public void onPermissionDenied(PermissionDeniedResponse response) {
//
//                                    }
//
//                                    @Override
//                                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
//                                        token.continuePermissionRequest();
//                                    }
//                                }).check();
//                    }
//                });
//                dialog.show();
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (requestCode == 0 && resultCode == RESULT_OK && data != null) {
//            bitmap = (Bitmap) data.getExtras().get("data");
//            addImg.setImageBitmap(bitmap);
//            encodeBitmapImage(bitmap);
//        }
//        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
//            Uri uri = data.getData();
//            try {
//                InputStream inputStream = getContentResolver().openInputStream(uri);
//                bitmap = BitmapFactory.decodeStream(inputStream);
//                addImg.setImageBitmap(bitmap);
//                encodeBitmapImage(bitmap);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//
//    }
//
//    private void encodeBitmapImage(Bitmap bitmap) {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
//        byte[] bytes = byteArrayOutputStream.toByteArray();
//        encodeImageString = android.util.Base64.encodeToString(bytes, Base64.DEFAULT);
//    }
//
//    private void upSanPham() {
//        mRequestQueue = Volley.newRequestQueue(AddPrActivity.this);
//        mStringRequest = new StringRequest(Request.Method.POST, getBaseUrl(), new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    Log.d(TAG, "onResponse1: " + jsonObject);
//
//                    int success = jsonObject.getInt("success");
//                    String message = jsonObject.getString("message");
//
//                    if (success == 1) {
//                        Toast.makeText(AddPrActivity.this,message,Toast.LENGTH_SHORT).show();
//                    }
//
//                } catch (JSONException e) {
//                    Toast.makeText(AddPrActivity.this,e.toString(),Toast.LENGTH_LONG).show();
//                    Log.d("errorrrrrrrrrrrrrr", String.valueOf(e));
//                    Log.d(TAG, "getParams: " + idLSP);
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
//            }
//        })
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                User user = DataLocalManager.getUser();
//                Log.d(TAG, "getParams: " + idLSP);
//                Map<String,String> map = new HashMap<String, String>();
//
//                map.put("id_loai_san_pham", idLSP);
//                map.put("id_user", String.valueOf(user.getId_user()));
//                map.put("ten_san_pham", String.valueOf(name.getText()));
//                map.put("so_luong", String.valueOf(sl.getText()));
//                map.put("gia_san_pham", String.valueOf(gia.getText()));
//                map.put("don_vi", String.valueOf(donVi.getText()));
//                map.put("mo_ta_san_pham", String.valueOf(moTa.getText()));
//                map.put("hinh_san_pham", encodeImageString);
//                String tinhTrang;
//                if (sl.getText().equals("0")) {
//                    tinhTrang = "Hết hàng";
//                } else {
//                    tinhTrang = "Còn hàng";
//                }
//                map.put("tinh_trang",tinhTrang);
//
//                return map;
//            }
//        };
//
//        mStringRequest.setShouldCache(false);
//        mRequestQueue.add(mStringRequest);
//    }
//
//    private void getSpn() {
//        spnLSP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                idLSP = String.valueOf(list.get(position).getIdLSP());
//                Log.d("TAG", "onItemClick: " +  list.get(position).getIdLSP());
//                Log.d("TAG", "onItemClick: " +  idLSP);
//            }
//        });
//    }
//
//    private void getLSP() {
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, getUrlLSP(),
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        try {
//
//                            JSONArray array = new JSONArray(response);
//                            for (int i = 0; i < array.length(); i++){
//                                Log.d("objectttttttttttarrrrr", String.valueOf(array));
//                                JSONObject object = array.getJSONObject(i);
//                                Log.d("objectttttttttttaaaaaaa", String.valueOf(object));
//
//                                int id = object.getInt("id_loai_san_pham");
//                                String name = object.getString("ten_loai");
//
//                                LoaiSanPham lsp = new LoaiSanPham(name, id);
//                                list.add(lsp);
//
//                            }
//
//                        } catch (Exception e){
//                            e.printStackTrace();
//                        }
//                        Log.d("objectttttttttttaaaaaaaaaa", String.valueOf(list));
//
//                        for (int i = 0; i < list.size(); i++) {
//                            listLSP.add(list.get(i).getTen_loai());
//                            ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplication(), R.layout.item_spinner, listLSP);
//                            spnLSP.setAdapter(adapter);
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Toast.makeText(getApplicationContext(), error.toString(),Toast.LENGTH_LONG).show();
//                Log.d("errorrrrrrrrrrrrrreeeee", String.valueOf(error));
//            }
//        });
//
//        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
//
//    }
//
//    private String getUrlLSP (){
//        return "http://"+getResources().getString(R.string.machine_ip_address)+"/du_an_1/getLoaiSanPham.php";
//    }
//
//    private String getBaseUrl (){
//        return "http://"+getResources().getString(R.string.machine_ip_address)+"/du_an_1/upSanPham.php";
//    }
//}