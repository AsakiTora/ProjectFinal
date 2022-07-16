package com.app.projectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class signUp extends AppCompatActivity {
    Button btnSignUp ;
    TextView txtSignIn , txtDieuKhoan ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnSignUp = findViewById(R.id.btn_SignUp);
        txtSignIn = findViewById(R.id.txt_signIn);
        txtDieuKhoan = findViewById(R.id.txt_dieuKhoan);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signUp.this,signin.class));
            }
        });
        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signUp.this,signin.class));
            }
        });
        txtDieuKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signUp.this,dieuKhoan.class));
            }
        });

    }
}