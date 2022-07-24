package com.app.projectfinal.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.projectfinal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class EnterOtpActivity extends AppCompatActivity {

    private String TAG = EnterOtpActivity.class.getName();

    private EditText edt_enter_otp;
    private AppCompatButton btn_confirm_otp;
    private TextView tv_resent_otp;
    private String phone_number, verification_id, password, username, permission;

    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;

    private String country_code = "+84";

    PhoneAuthProvider.ForceResendingToken _forceResendingToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otp);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        getDataIntent();
        initView();
        onClickSentOtp();
        onClickReSentOtp();
    }

    private void initView() {
        edt_enter_otp = (EditText) findViewById(R.id.edt_enter_otp);
        btn_confirm_otp = (AppCompatButton) findViewById(R.id.btn_confirm_otp);
        tv_resent_otp = (TextView) findViewById(R.id.tv_resent_otp);
    }

    private void getDataIntent() {
        phone_number = getIntent().getStringExtra("phone_number");
        permission = getIntent().getStringExtra("permission");
        verification_id = getIntent().getStringExtra("verification_id");
        password = getIntent().getStringExtra("password");
        username = getIntent().getStringExtra("username");
    }

    private void onClickSentOtp() {
        btn_confirm_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = edt_enter_otp.getText().toString().trim();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verification_id, otp);
                signInWithPhoneAuthCredential(username, credential, password);
            }
        });
    }

    private void onClickReSentOtp() {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(fAuth)
                        .setPhoneNumber(country_code + phone_number.substring(1))       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setForceResendingToken(_forceResendingToken)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(username, phoneAuthCredential, password);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(EnterOtpActivity.this,
                                        "VerificationFailed", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verificationId, forceResendingToken);
                                verification_id = verificationId;
                                _forceResendingToken = forceResendingToken;
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(String username, PhoneAuthCredential credential, String password) {
        if (permission != null){
            fAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser fUser = task.getResult().getUser();
                                assert fUser != null;
                                goToMainActivity(fUser.getPhoneNumber());
                            } else {
                                // Sign in failed, display a message and update the UI
                                Log.w(TAG, "signInWithCredential:failure", task.getException());
                                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                    // The verification code entered was invalid
                                    Toast.makeText(EnterOtpActivity.this,
                                            "The verification code entered was invalid", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
        } else {
            fAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                //FirebaseUser fUser = task.getResult().getUser();
                                FirebaseUser user = fAuth.getCurrentUser();
                                DocumentReference df = fStore.collection("Users").document(user.getUid());
                                Map<String, Object> userInfo = new HashMap<>();
                                userInfo.put("username", username);
                                userInfo.put("phone_number", phone_number);
                                userInfo.put("password", password);
                                df.set(userInfo);
                                goToMainActivity(user.getPhoneNumber());

                                // Update UI
                            } else {
                                // Sign in failed, display a message and update the UI
                                Log.w(TAG, "signInWithCredential:failure", task.getException());
                                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                    // The verification code entered was invalid
                                    Toast.makeText(EnterOtpActivity.this,
                                            "The verification code entered was invalid", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
        }

    }

    private void goToMainActivity(String phone_number) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("phone_number", phone_number);
        startActivity(intent);
    }

}