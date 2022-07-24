package com.app.projectfinal.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import static com.app.projectfinal.utils.Constant.REGISTER;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.projectfinal.R;
import com.app.projectfinal.utils.Constant;
import com.app.projectfinal.utils.VolleySingleton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {

    private String TAG = "RegisterActivity";
    private AppCompatButton btn_register;
    private TextView tv_login;
    private TextInputEditText edt_phone;
    private TextInputEditText edt_acc;
    private TextInputEditText edt_pass;
    private TextInputEditText edt_re_pass;
    private String country_code = "+84";

    //firebase
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;

    private boolean valid = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        initView();
        changeScreenLogin();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_pass.getText().toString().trim().equals(edt_re_pass.getText().toString())){
                    registerServer(Objects.requireNonNull(edt_phone.getText()).toString().trim(), Objects.requireNonNull(edt_pass.getText()).toString().trim(), Objects.requireNonNull(edt_acc.getText()).toString().trim()) ;
                    registerFirebase(Objects.requireNonNull(edt_acc.getText()).toString().trim(), Objects.requireNonNull(edt_phone.getText()).toString().trim(), Objects.requireNonNull(edt_pass.getText()).toString().trim());
                }else {
                    Toast.makeText(RegisterActivity.this, "Nhập lại mật khẩu", Toast.LENGTH_LONG).show();

                }

            }
        });
    }
    private void sendMessageEmail(String email){
        ActionCodeSettings actionCodeSettings =
                ActionCodeSettings.newBuilder()
                        .setHandleCodeInApp(true)
                        .setAndroidPackageName(
                                "com.app.projectfinal",
                                true, /* installIfNotAvailable */
                                "12"    /* minimumVersion */)
                        .build();
        fAuth.sendSignInLinkToEmail(email, actionCodeSettings);
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

    private void registerServer(final String phone, final String pass, String name){
        JSONObject user = new JSONObject();
        try {
            user.put("phone", phone);
            user.put("password", pass);
            user.put("userName", name);

            JSONObject data = new JSONObject();
            data.put("user", user);
            JSONObject datas = new JSONObject();
            datas.put("data", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, REGISTER, user, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, "" + error.toString(), Toast.LENGTH_LONG).show();
                Log.e(TAG, "onErrorResponse: " + error );
            }
        });
        VolleySingleton.getInstance(getApplicationContext()).getRequestQueue().add(jsonObjectRequest);
    }

    public boolean checkField(EditText textField){
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String checkPhoneNumber = "^[0-9]{10}$";
        if (textField.getText().toString().isEmpty()){
            textField.setError("Không được để trống");
            valid = false;
        } else {
            valid = true;
        }
        return valid;
    }

    private void registerFirebase(String username, String phone_number, String password){
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String checkPhoneNumber = "^[0-9]{9,10}$";
        if (phone_number.matches(checkEmail)){
            fAuth.createUserWithEmailAndPassword(username, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Log.d(TAG, "onSuccess: "+ authResult);
                    FirebaseUser user = fAuth.getCurrentUser();
                    DocumentReference df = fStore.collection("Users").document(user.getUid());
                    Map<String, Object> userInfo = new HashMap<>();
                    userInfo.put("username", username);
                    userInfo.put("phone_number", phone_number);
                    userInfo.put("password", password);
                    df.set(userInfo);
                    //finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        } else if (phone_number.matches(checkPhoneNumber)){
            checkVerifyPhoneNumber(username, phone_number, password);
        }

    }

    private void checkVerifyPhoneNumber(String username, String phone_number, String password){
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(fAuth)
                        .setPhoneNumber(country_code + phone_number.substring(1)) // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(username, phoneAuthCredential, password);
                            }
                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(RegisterActivity.this,
                                        "VerificationFailed" + e, Toast.LENGTH_SHORT).show();
                                Log.e(TAG, "onVerificationFailed: "+ e);
                                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                                    // Invalid request
                                } else if (e instanceof FirebaseTooManyRequestsException) {
                                    // The SMS quota for the project has been exceeded
                                }
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verificationId, forceResendingToken);
                                goToEnterOtpActivity(phone_number, verificationId);
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(String username, PhoneAuthCredential credential, String password) {
        fAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "signInWithCredential:success");
                            //FirebaseUser fUser = task.getResult().getUser();
                            FirebaseUser user = fAuth.getCurrentUser();
                            DocumentReference df = fStore.collection("Users").document(user.getUid());
                            Map<String, Object> userInfo = new HashMap<>();
                            userInfo.put("username", username);
                            userInfo.put("phone_number", Objects.requireNonNull(edt_phone.getText()).toString());
                            userInfo.put("password", password);
                            df.set(userInfo);
                            goToMainActivity(user.getPhoneNumber());

                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(RegisterActivity.this,
                                        "The verification code entered was invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void goToMainActivity(String phone_number) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("phone_number", phone_number);
        startActivity(intent);
    }

    private void goToEnterOtpActivity(String phone_number, String verification_id) {
        Intent intent = new Intent(this, EnterOtpActivity.class);
        intent.putExtra("phone_number", phone_number);
        intent.putExtra("username", Objects.requireNonNull(edt_acc.getText()).toString().trim());
        intent.putExtra("password", Objects.requireNonNull(edt_pass.getText()).toString().trim());
        intent.putExtra("verification_id", verification_id);
        startActivity(intent);
    }

}