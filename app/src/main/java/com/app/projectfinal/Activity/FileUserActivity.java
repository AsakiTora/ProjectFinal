//package com.app.projectfinal.Activity;
//
//import android.app.DatePickerDialog;
//import android.app.Dialog;
//import android.content.DialogInterface;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.ProgressBar;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.ActionBar;
//import androidx.appcompat.app.AlertDialog;
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
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.Map;
//
//import com.app.projectfinal.DataLocal.DataLocalManager;
//import com.app.projectfinal.Model.User;
//import com.app.projectfinal.R;
//
//public class FileUserActivity extends AppCompatActivity {
//
//    private static final String TAG = "My tag";
//    private Toolbar mToolbar;
//    private ActionBar mActionBar;
//    private RelativeLayout rlNotificationUser;
//    private LinearLayout llShowName, llShowAccount, llShowIntro, llShowSex, llShowBirth, llShowPhoneNumber, llShowEmail, llSetPass;
//    private TextView txtShowName, txtShowAccount, txtShowIntro, txtShowSex, txtShowBirth, txtShowPhoneNumber, txtShowEmail, txtSetPass;
//
//    private ProgressBar mProgress;
//    private RequestQueue mRequestQueue;
//    private StringRequest mStringRequest;
//
//    private SimpleDateFormat simpleDateFormat;
//
//    private TextView title;
//    private TextView mission;
//    private EditText data;
//
//    private User user;
//    private Button apply;
//
//    private Dialog dialog;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_file_user);
//
//        initView();
//        // Finding UI widgets
//        mToolbar = findViewById(R.id.toolbarSetFileUser);
//        setSupportActionBar(mToolbar);
//
//        // Setting up action bar
//        mActionBar = getSupportActionBar();
//        mActionBar.setDisplayHomeAsUpEnabled(true);
//        mActionBar.setTitle(getResources().getString(R.string.fileUserDisplay));
//        mActionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back));
//
//        user = DataLocalManager.getUser();
//        txtShowAccount.setText(user.getAccount());
//        txtShowName.setText(user.getName());
//        txtShowBirth.setText(user.getBirth());
//        txtShowEmail.setText(user.getEmail());
//        txtShowIntro.setText(user.getIntro());
//        txtShowPhoneNumber.setText(user.getPhone());
////        Log.d(TAG, "onCreateeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee: " + user.getEmail());
////        if (user.getName() == null){
////            txtShowName.setText("Thiết lập ngay");
////        } else {
////            txtShowName.setText(user.getName());
////        }
////        if (user.getBirth() == null){
////            txtShowBirth.setText("Thiết lập ngay");
////        } else {
////            txtShowBirth.setText(user.getBirth());
////        }
////        if (user.getEmail() == null){
////            txtShowEmail.setText("Thiết lập ngay");
////        } else {
////            txtShowEmail.setText(user.getEmail());
////        }
////        if (user.getPhone() == null){
////            txtShowPhoneNumber.setText("Thiết lập ngay");
////        } else {
////            txtShowEmail.setText(user.getEmail());
////        }
////        if (user.getIntro() == null){
////            txtShowIntro.setText("Thiết lập ngay");
////        } else {
////            txtShowIntro.setText(user.getIntro());
////        }
//
//        updatePhone();
//        updateBirth();
//        updateEmail();
//        updateName();
//    }
//
//    private void initView() {
//        txtShowName = findViewById(R.id.txtShowName);
//        txtShowAccount = findViewById(R.id.txtShowAccount);
//        txtShowIntro = findViewById(R.id.txtShowIntro);
//        txtShowBirth = findViewById(R.id.txtShowBirth);
//        txtShowPhoneNumber = findViewById(R.id.txtShowPhoneNumber);
//        txtShowEmail = findViewById(R.id.txtShowEmail);
//        txtSetPass = findViewById(R.id.txtSetPass);
//
//        llShowName = findViewById(R.id.llName);
//        llShowAccount = findViewById(R.id.llAccount);
//        llShowIntro = findViewById(R.id.llIntro);
//        llShowBirth = findViewById(R.id.llBirth);
//        llShowPhoneNumber = findViewById(R.id.llPhoneNumber);
//        llShowEmail = findViewById(R.id.llEmail);
//        llSetPass = findViewById(R.id.llSetPass);
//
//        mProgress = findViewById(R.id.progress);
//    }
//
//    private void updateName() {
//        llShowName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openDialog();
//                title.setText("Cập nhật tên");
//                mission.setText("Nhập họ và tên của bạn");
//                data.setHint("Họ tên");
//                apply.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mProgress.setVisibility(View.VISIBLE);
//                        updateNameAPI(String.valueOf(user.getId_user()), data.getText().toString());
//                        user.setName(data.getText().toString());
//                        DataLocalManager.setUser(user);
//                        dialog.dismiss();
//                    }
//                });
//                mProgress.setVisibility(View.GONE);
//            }
//        });
//    }
//
//    private void updateEmail() {
//        llShowEmail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openDialog();
//                title.setText("Cập nhật Email");
//                mission.setText("Nhập Email của bạn");
//                data.setHint("Email");
//                apply.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (data.getText().toString().trim().matches("^([a-zA-Z0-9_.\\-])+@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$")){
//                            mProgress.setVisibility(View.VISIBLE);
//                            updateEmailAPI(String.valueOf(user.getId_user()), data.getText().toString());
//                            user.setEmail(data.getText().toString());
//                            DataLocalManager.setUser(user);
//                            dialog.dismiss();
//                        } else {
//                            Toast.makeText(FileUserActivity.this, "Email không đúng định dạng. Vui lòng kiểm tra lại!", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//            }
//        });
//    }
//
//    private void updatePhone() {
//        llShowPhoneNumber.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openDialog();
//                title.setText("Cập nhật số điện thoại");
//                mission.setText("Nhập số điện thoại của bạn");
//                data.setHint("Số điện thoại");
//                apply.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mProgress.setVisibility(View.VISIBLE);
//                        updatePhoneAPI(String.valueOf(user.getId_user()), data.getText().toString());
//                        user.setPhone(data.getText().toString());
//                        DataLocalManager.setUser(user);
//                        dialog.dismiss();
//                    }
//                });
//            }
//        });
//    }
//
//    private void updateBirth() {
//        llShowBirth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ChonNgay();
//                mProgress.setVisibility(View.GONE);
//            }
//        });
//    }
//
//    private void openDialog() {
//        dialog = new Dialog(FileUserActivity.this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.custom_dialog);
//
//        Window window = dialog.getWindow();
//        if (window == null) {
//            return;
//        }
//
//        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//        //Xac dinh vi tri cua dialog
//        WindowManager.LayoutParams windowAttributes = window.getAttributes();
//        windowAttributes.gravity = Gravity.CENTER;
//        window.setAttributes(windowAttributes);
//
//        //Click ngoai dialog
//        dialog.setCancelable(false);
//
//        title = dialog.findViewById(R.id.tvTitleDialog);
//        mission = dialog.findViewById(R.id.tvMissionDialog);
//        data = dialog.findViewById(R.id.edtDialog);
//        Button back = dialog.findViewById(R.id.btnBackDialog);
//        apply = dialog.findViewById(R.id.btnApplyDialog);
//
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//    }
//
//    private void ChonNgay() {
//        Calendar calendar = Calendar.getInstance();
//        int ngay = calendar.get(java.util.Calendar.DATE);
//        int thang = calendar.get(java.util.Calendar.MONTH);
//        int nam = calendar.get(Calendar.YEAR);
//        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
//                calendar.set(year, month, dayOfMonth);
//                AlertDialog.Builder builder = new AlertDialog.Builder(FileUserActivity.this);
//                builder.setTitle("Cập nhật ngày sinh");
//                builder.setMessage("Bạn có muốn lưu thay đổi?");
//                builder.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        mProgress.setVisibility(View.VISIBLE);
//                        String birth = simpleDateFormat.format(calendar.getTime());
//                        String id = String.valueOf(user.getId_user());
//                        user.setBirth(birth);
//                        updateBirthAPI(id, birth);
//                        DataLocalManager.setUser(user);
//                        txtShowBirth.setText(birth);
//                    }
//                });
//                AlertDialog dialog = builder.create();
//                dialog.show();
//            }
//        }, nam, thang, ngay);
//        datePickerDialog.show();
//    }
//
//    private void updateBirthAPI(String id, String data) {
//        mProgress.setVisibility(View.VISIBLE);
//        // Initializing Request queue
//        mRequestQueue = Volley.newRequestQueue(FileUserActivity.this);
//
//        mStringRequest = new StringRequest(Request.Method.POST,
//                getBaseUrlBirth(), new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    Log.d(TAG, "onResponse: " + response);
//                    JSONObject jsonObject = new JSONObject(response);
//
//                    int success = jsonObject.getInt("success");
//                    String message = jsonObject.getString("message");
//                    if (success == 1) {
//                        mProgress.setVisibility(View.GONE);
//                        Toast.makeText(FileUserActivity.this,message,Toast.LENGTH_SHORT).show();
//                        // Finish
//                        finish();
//                    }
//                    if (success == 0) {
//                        mProgress.setVisibility(View.GONE);
//                        Toast.makeText(FileUserActivity.this,message,Toast.LENGTH_SHORT).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    mProgress.setVisibility(View.GONE);
//                    Toast.makeText(FileUserActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                mProgress.setVisibility(View.GONE);
//                Toast.makeText(FileUserActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("id_user", id);
//                params.put("birth", data);
//                return params;
//            }
//        };
//        mStringRequest.setShouldCache(false);
//        mRequestQueue.add(mStringRequest);
//    }
//
//    private void updateEmailAPI(String id, String data) {
//        mProgress.setVisibility(View.VISIBLE);
//        // Initializing Request queue
//        mRequestQueue = Volley.newRequestQueue(FileUserActivity.this);
//
//        mStringRequest = new StringRequest(Request.Method.POST,
//                getBaseUrlEmail(), new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    Log.d(TAG, "onResponse: " + response);
//                    JSONObject jsonObject = new JSONObject(response);
//
//                    int success = jsonObject.getInt("success");
//                    String message = jsonObject.getString("message");
//                    if (success == 1) {
//                        mProgress.setVisibility(View.GONE);
//                        Toast.makeText(FileUserActivity.this,message,Toast.LENGTH_SHORT).show();
//                        // Finish
//                        finish();
//                    }
//                    if (success == 0) {
//                        mProgress.setVisibility(View.GONE);
//                        Toast.makeText(FileUserActivity.this,message,Toast.LENGTH_SHORT).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    mProgress.setVisibility(View.GONE);
//                    Toast.makeText(FileUserActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                mProgress.setVisibility(View.GONE);
//                Toast.makeText(FileUserActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("id_user", id);
//                params.put("email", data);
//                return params;
//            }
//        };
//        mStringRequest.setShouldCache(false);
//        mRequestQueue.add(mStringRequest);
//    }
//
//    private void updatePhoneAPI(String id, String data) {
//        mProgress.setVisibility(View.VISIBLE);
//        // Initializing Request queue
//        mRequestQueue = Volley.newRequestQueue(FileUserActivity.this);
//
//        mStringRequest = new StringRequest(Request.Method.POST,
//                getBaseUrlPhone(), new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    Log.d(TAG, "onResponse: " + response);
//                    JSONObject jsonObject = new JSONObject(response);
//
//                    int success = jsonObject.getInt("success");
//                    String message = jsonObject.getString("message");
//                    if (success == 1) {
//                        mProgress.setVisibility(View.GONE);
//                        Toast.makeText(FileUserActivity.this,message,Toast.LENGTH_SHORT).show();
//                        // Finish
//                        finish();
//                    }
//                    if (success == 0) {
//                        mProgress.setVisibility(View.GONE);
//                        Toast.makeText(FileUserActivity.this,message,Toast.LENGTH_SHORT).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    mProgress.setVisibility(View.GONE);
//                    Toast.makeText(FileUserActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                mProgress.setVisibility(View.GONE);
//                Toast.makeText(FileUserActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("id_user", id);
//                params.put("phone", data);
//                return params;
//            }
//        };
//        mStringRequest.setShouldCache(false);
//        mRequestQueue.add(mStringRequest);
//    }
//
//    private void updateNameAPI(String id, String data) {
//        mProgress.setVisibility(View.VISIBLE);
//        // Initializing Request queue
//        mRequestQueue = Volley.newRequestQueue(FileUserActivity.this);
//
//        mStringRequest = new StringRequest(Request.Method.POST,
//                getBaseUrlName(), new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    Log.d(TAG, "onResponse: " + response);
//                    JSONObject jsonObject = new JSONObject(response);
//
//                    int success = jsonObject.getInt("success");
//                    String message = jsonObject.getString("message");
//                    if (success == 1) {
//                        mProgress.setVisibility(View.GONE);
//                        Toast.makeText(FileUserActivity.this,message,Toast.LENGTH_SHORT).show();
//                        // Finish
//                        finish();
//                    }
//                    if (success == 0) {
//                        mProgress.setVisibility(View.GONE);
//                        Toast.makeText(FileUserActivity.this,message,Toast.LENGTH_SHORT).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    mProgress.setVisibility(View.GONE);
//                    Toast.makeText(FileUserActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                mProgress.setVisibility(View.GONE);
//                Toast.makeText(FileUserActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("id_user", id);
//                params.put("name", data);
//                return params;
//            }
//        };
//        mStringRequest.setShouldCache(false);
//        mRequestQueue.add(mStringRequest);
//    }
//
//    private String getBaseUrlBirth (){
//        return "http://"+getResources().getString(R.string.machine_ip_address)+"/du_an_1/updateBirth.php";
//    }
//
//    private String getBaseUrlEmail (){
//        return "http://"+getResources().getString(R.string.machine_ip_address)+"/du_an_1/updateEmail.php";
//    }
//
//    private String getBaseUrlPhone (){
//        return "http://"+getResources().getString(R.string.machine_ip_address)+"/du_an_1/updatePhone.php";
//    }
//
//    private String getBaseUrlName (){
//        return "http://"+getResources().getString(R.string.machine_ip_address)+"/du_an_1/updateName.php";
//    }
//
//
//}