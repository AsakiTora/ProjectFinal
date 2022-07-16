//package com.app.projectfinal.Activity;
//
//import android.content.Intent;
//import android.icu.util.Calendar;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
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
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.app.projectfinal.DataLocal.DataLocalManager;
//import com.app.projectfinal.Model.User;
//import com.app.projectfinal.R;
//
//public class MainActivity extends AppCompatActivity {
//
//    // Variable declarations
//    private TextInputEditText mAccount, mPassword;
//    private TextView mSignUp;
//    private Button mSignIn;
//    private ProgressBar mProgress;
//
//    // Volley Variables
//    private RequestQueue mRequestQueue;
//    private StringRequest mStringRequest;
//
//    private List<User> list;
//
//    ImageView sun, dayland, nightland;
//    View daySky, nightSky;
//    int translationY;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//
//        // Getting UI views from our xml file
//
//        initView();
//        checkTime();
//        sun.animate().translationY(translationY);
//        Log.d("TAG", "onCreate: translationY" + translationY);
//
//        mSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startActivity(new Intent(MainActivity.this,SignUpActivity.class));
//
//            }
//        });
//
//
//
//        mSignIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
////                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
////                intent.putExtra("account",mAccount.getText().toString());
////                startActivity(intent);
//
//                signIn(mAccount.getText().toString(), mPassword.getText().toString());
//            }
//        });
//
//    }
//
//    private void initView() {
//        sun = findViewById(R.id.sun);
//        dayland = findViewById(R.id.day_landscape);
//        nightland = findViewById(R.id.night_landscape);
//        daySky = findViewById(R.id.day_bg);
//        nightSky = findViewById(R.id.night_bg);
//
//        mAccount = findViewById(R.id.txtAccount);
//        mPassword = findViewById(R.id.txtPassword);
//        mSignIn = findViewById(R.id.btnSignIn);
//        mSignUp = findViewById(R.id.txtSignUp);
//        mProgress = findViewById(R.id.progress);
//    }
//
//    private int checkTime() {
//        Calendar objCal = Calendar.getInstance();
//        objCal.setTimeInMillis( System.currentTimeMillis() );
//
//        int h = objCal.get(Calendar.HOUR_OF_DAY);
//        Log.d("TAG", "onCreate: H = " + h);
//
//        if (h >= 5 && h <= 15) {
//            sun.animate().translationY(-110).setDuration(1000);
//            dayland.animate().alpha(1).setDuration(1300);
//            daySky.animate().alpha(1).setDuration(1300);
//            translationY = -110;
//        } else {
//            sun.animate().translationY(110).setDuration(1000);
//            dayland.animate().alpha(0).setDuration(1300);
//            daySky.animate().alpha(0).setDuration(1300);
//            translationY = 110;
//        }
//        Log.d("TAG", "checkTime: translationY" + translationY);
//        return translationY;
//
//    }
//
//
//    private void signIn(final String account, final String password) {
//
//        mProgress.setVisibility(View.VISIBLE);
//        // Initializing Request queue
//        mRequestQueue = Volley.newRequestQueue(MainActivity.this);
//
//        mStringRequest = new StringRequest(Request.Method.POST,
//                getBaseUrl(), new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d("ttttttt response", response); // ghi log o day de biet servẻ tra ve cai gi
//                try {
//
//                    JSONObject objData = new JSONObject(response);
//                    Log.d("objecttttttttttt 2", objData.toString());
//
//                    JSONArray array = objData.getJSONArray("data"); // nó tương ứng cái bảng trong php đó
//                    Log.d("objecttttttttttt 2 array ", array.toString());
//
//                    for (int i = 0; i < array.length(); i++) {
//
//                        JSONObject object = array.getJSONObject(i);
//                        Log.d("objecttttttttttt 3", String.valueOf(object));
//
//                        int id_user = object.getInt("id_user");
//                        int id_group = object.getInt("id_group");
//                        String name = object.getString("name");
//                        String avatar = object.getString("avatar");
//                        String phone = object.getString("phone");
//                        String account = object.getString("account");
//                        String email = object.getString("email");
//                        String birth = object.getString("birth");
//                        String intro = object.getString("intro");
//                        String address = object.getString("address");
//                        String shop = object.getString("shop");
//                        String avt_shop = object.getString("avt_shop");
//
//                        User user = new User(id_user, id_group, phone, account, avatar, name, email, address, birth, intro, shop, avt_shop);
//                        DataLocalManager.setUser(user);
//                        Log.d("objecttttttttttt user", String.valueOf(user));
//                    }
//
//
//                    int success = objData.getInt("success"); // là số thì để kiểu số nguyên, đừng để chuỗi, nó nông dân
//                    String message = objData.getString("message");
////                    String success = jsonObject.getString("success");
////                    String message = jsonObject.getString("message");
//                    Log.d("objecttttttttttt 4", String.valueOf(success));
//                    Log.d("objecttttttttttt", String.valueOf(message));
//                    if (success == 1) {
//
//                        mProgress.setVisibility(View.GONE);
//                        Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
//                        // Finish
//                        finish();
//                        // Start activity dashboard
//                        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
////                        intent.putExtra("account", mAccount.getText().toString());
//                        startActivity(intent);
////                        startActivity(new Intent(MainActivity.this,HomeActivity.class));
//                    }
//                    if (success == 0) {
//
//                        mProgress.setVisibility(View.GONE);
//                        Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
//                    }
//
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace(); // ghi ra log
//                    mProgress.setVisibility(View.GONE);
//                    Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
//
//                    Log.d("aaaaaaaaaaaaaaaaaaa", String.valueOf(e));
//
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                mProgress.setVisibility(View.GONE);
//                Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
//
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String, String> params = new HashMap<>();
//                params.put("account",account);
//                params.put("password",password);
//
//                return params;
//            }
//        };
//
//        mStringRequest.setShouldCache(false);
//        mRequestQueue.add(mStringRequest);
//    }
//
//
//    private String getBaseUrl (){
//        return "http://"+getResources().getString(R.string.machine_ip_address)+"/du_an_1/sign_in.php";
//    }
//
//
//}
