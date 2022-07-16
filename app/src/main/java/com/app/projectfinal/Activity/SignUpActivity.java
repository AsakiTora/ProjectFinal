package com.app.projectfinal.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import fpoly.andoid.test2.R;


public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText account, pass, rePass;
    private TextView login;
    private Button signUp;

    // Volley variables
    private StringRequest mStringRequest;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        account = findViewById(R.id.edtAccount);
        pass = findViewById(R.id.edtPass);
        rePass = findViewById(R.id.edtRePass);
        login = findViewById(R.id.txtLogin);
        signUp = findViewById(R.id.btnSignUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pass.getText().toString().trim().matches("^([A-Z]){1}([\\w_.!@#$%^&*()]+){8,31}$")) {
                    createUser(account.getText().toString(),pass.getText().toString(),rePass.getText().toString());
                } else {
                    Toast.makeText(SignUpActivity.this, "Mật khẩu không đúng định dạng", Toast.LENGTH_SHORT).show();
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            }
        });

    }


    private void createUser(final String account, final String pass, final String rePass){

        mRequestQueue = Volley.newRequestQueue(SignUpActivity.this);
        // Progress
        signUp.setText("Tạo tài khoản...");

                mStringRequest = new StringRequest(Request.Method.POST, getBaseUrl(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("errorrrrrrrrrrrrrr", String.valueOf(response));
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            int success = jsonObject.getInt("success");
                            String message = jsonObject.getString("message");

                            if (success == 1) {
                                Toast.makeText(SignUpActivity.this,message,Toast.LENGTH_SHORT).show();
                                signUp.setText("Sign Up");
                            } else {
                                Toast.makeText(SignUpActivity.this,message,Toast.LENGTH_SHORT).show();
                                signUp.setText("Sign Up");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SignUpActivity.this,e.toString(),Toast.LENGTH_LONG).show();
                            signUp.setText("Sign Up");
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(SignUpActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        signUp.setText("Sign Up");
                        Log.d("errorrrrrrrrrrrrrr", String.valueOf(error));

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> params = new HashMap<>();
                        params.put("account",account);
                        params.put("password1",pass);
                        params.put("password2",rePass);

                        return params;
                    }
                };

        mStringRequest.setShouldCache(false);
        mRequestQueue.add(mStringRequest);
    }


    private String getBaseUrl (){
        return "http://"+getResources().getString(R.string.machine_ip_address)+"/du_an_1/register.php";
    }


}
