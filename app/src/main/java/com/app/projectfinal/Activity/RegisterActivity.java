package com.app.projectfinal.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.projectfinal.R;
import com.app.projectfinal.utils.Constant;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private StringRequest mStringRequest;
    private RequestQueue mRequestQueue;

    AppCompatButton btn_register;
    TextView tv_login;
    TextInputEditText edt_phone;
    TextInputEditText edt_acc;
    TextInputEditText edt_pass;
    TextInputEditText edt_re_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        changeScreenLogin();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register(Objects.requireNonNull(edt_phone.getText()).toString(), edt_pass.getText().toString());
            }
        });
    }

    private void changeScreenLogin(){
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    private void initView(){
        btn_register = (AppCompatButton) findViewById(R.id.btn_register);
        edt_phone = (TextInputEditText) findViewById(R.id.edt_phone);
        edt_acc = (TextInputEditText) findViewById(R.id.edt_acc);
        edt_pass = (TextInputEditText) findViewById(R.id.edt_pass);
        edt_re_pass = (TextInputEditText) findViewById(R.id.edt_re_pass);
        tv_login = (TextView) findViewById(R.id.tv_login);
    }

    private void Register(final String phone, final String pass){

        mRequestQueue = Volley.newRequestQueue(RegisterActivity.this);

        mStringRequest = new StringRequest(Request.Method.POST, Constant.REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    Toast.makeText(RegisterActivity.this, "ok",Toast.LENGTH_SHORT).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(RegisterActivity.this, e.toString(),Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(RegisterActivity.this,error.toString(),Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("phone", phone);
                params.put("password", pass);

                return params;
            }
        };

        mStringRequest.setShouldCache(false);
        mRequestQueue.add(mStringRequest);
    }
}