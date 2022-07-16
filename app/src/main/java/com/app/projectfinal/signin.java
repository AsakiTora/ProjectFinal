package com.app.projectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class signin extends AppCompatActivity {
    Button btnSignIn ;
    TextView txtSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        btnSignIn = findViewById(R.id.btn_SignIn);
        txtSignUp = findViewById(R.id.txt_signUp);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signin.this, MainActivity.class));
            }
        });
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signin.this, signUp.class));
            }
        });
    }
}