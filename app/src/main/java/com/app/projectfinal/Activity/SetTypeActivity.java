//package com.app.projectfinal.Activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.AutoCompleteTextView;
//import android.widget.ProgressBar;
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
//import com.google.android.material.textfield.TextInputEditText;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.app.projectfinal.DataLocal.DataLocalManager;
//import com.app.projectfinal.Model.GroupUser;
//import com.app.projectfinal.Model.User;
//import com.app.projectfinal.R;
//
//public class SetTypeActivity extends AppCompatActivity {
//
//    private Toolbar mToolbar;
//    private ActionBar mActionBar;
//    private AutoCompleteTextView spnType;
//    private ArrayList<GroupUser> list;
//
//    private StringRequest mStringRequest;
//    private RequestQueue mRequestQueue;
//    private ProgressBar mProgress;
//    private TextInputEditText shop;
//    List<String> listGr;
//
//    String type;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_set_type);
//
//        mProgress = findViewById(R.id.progress);
//        spnType = findViewById(R.id.spnType);
//        shop = findViewById(R.id.edtShop);
//        mToolbar = findViewById(R.id.toolbarSetType);
//        setSupportActionBar(mToolbar);
//
//        // Setting up action bar
//        mActionBar = getSupportActionBar();
//        mActionBar.setDisplayHomeAsUpEnabled(true);
//        mActionBar.setTitle(getResources().getString(R.string.setType));
//        mActionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back));
//
//        list = new ArrayList<>();
//        listGr = new ArrayList<>();
//
//        getType();
//
//        Log.d("TAG", "onCreate: " + spnType.toString());
//
//        spnType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                type = String.valueOf(list.get(position).getId());
//                Log.d("TAG", "onItemClick: " +  list.get(position).getId());
//
//            }
//        });
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
//                Log.d("TAG..................", "onOptionsItemSelected: " + shop.getText());
//                if (type == null && shop.getText().equals("")) {
//                    Log.d("TAG..................", "onOptionsItemSelected: " + shop.getText());
//                    spnType.setError("Chọn loại tài khoản chuyển đổi.");
//                    shop.setError("Nhập tên gian hàng");
//
////                } else if (shop.getText().equals("")) {
////                    spnType.setError("Chọn loại tài khoản chuyển đổi.");
////                } else if (type == null) {
////                    shop.setError("Nhập tên gian hàng");
//                } else {
//
//                    updateType();
//                    User user = DataLocalManager.getUser();
//                    user.setId_group(Integer.parseInt(type));
//                    DataLocalManager.setUser(user);
//                    Log.d("TAG", "onOptionsItemSelected: ");
//
//                }
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void updateType(){
//
//        mRequestQueue = Volley.newRequestQueue(SetTypeActivity.this);
//        // Progress
//        mStringRequest = new StringRequest(Request.Method.POST, getBaseUpUrl(), new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d("TAG", "onResponse: response"+ response);
//                try {
//
//                    JSONObject jsonObject = new JSONObject(response);
//                    Log.d("TAG", "onResponse: jsonObject"+ jsonObject);
//                    int success = jsonObject.getInt("success");
//                    String message = jsonObject.getString("message");
//
//                    if (success == 1) {
//                        mProgress.setVisibility(View.GONE);
//                        Toast.makeText(SetTypeActivity.this,message,Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(SetTypeActivity.this, ShopActivity.class));
//                    } else {
//                        mProgress.setVisibility(View.GONE);
//                        Toast.makeText(SetTypeActivity.this,message,Toast.LENGTH_SHORT).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Toast.makeText(SetTypeActivity.this,e.toString(),Toast.LENGTH_LONG).show();
//                    Log.d("errorrrrrrrrrrrrrr", String.valueOf(e));
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(SetTypeActivity.this,error.toString(),Toast.LENGTH_LONG).show();
//                Log.d("errorrrrrrrrrrrrrr", String.valueOf(error));
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                User user = DataLocalManager.getUser();
//                Log.d("TAG", "getParams: user"+ user.getId_user());
//                params.put("id_user", String.valueOf(user.getId_user()));
//                params.put("id_group", type);
//                return params;
//            }
//        };
//
//        mStringRequest.setShouldCache(false);
//        mRequestQueue.add(mStringRequest);
//    }
//
//    private void getType() {
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, getBaseUrl(),
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
//                                int id = object.getInt("id_group");
//                                String name = object.getString("ten_group");
//
//                                GroupUser type = new GroupUser(id, name);
//                                list.add(type);
//
//                            }
//
//                        } catch (Exception e){
//                            Log.d("listsssssssssssssssss", String.valueOf(list.size()));
//                            Log.d("loiiiiiiiiiiiiiii", String.valueOf(e));
//                        }
//
//                        for (int i = 0; i < list.size(); i++) {
//                            listGr.add(list.get(i).getName());
//                            ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplication(), R.layout.item_spinner, listGr);
//                            spnType.setAdapter(adapter);
//                        }
//                        Log.d("listsssssssssssssssss", String.valueOf(listGr));
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
//    private String getBaseUrl (){
//        return "http://"+getResources().getString(R.string.machine_ip_address)+"/du_an_1/getGroupUser.php";
//    }
//    private String getBaseUpUrl (){
//        return "http://"+getResources().getString(R.string.machine_ip_address)+"/du_an_1/updateGroup.php";
//    }
//}