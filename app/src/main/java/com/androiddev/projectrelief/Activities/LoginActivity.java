package com.androiddev.projectrelief.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androiddev.projectrelief.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    TextView newAcc;
    EditText inputEmail,inputPass;
    Button btnLogin;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    String username;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore firebaseFirestore;
    CollectionReference reference;

    SharedPreferences sharedPreferences;

    public static final String fileName = "login";
    public static final String Email = "email";
    public static final String Password = "password";
    public static final String Name = "name";
//    LoadingDialogBox loadingDialogBox = new LoadingDialogBox(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        newAcc = findViewById(R.id.txtForgotPass);
        inputPass = findViewById(R.id.edtPassword);
        inputEmail = findViewById(R.id.edtEmailAddress);
        btnLogin = findViewById(R.id.btnLogin);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();
        sharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE);

        if(sharedPreferences.contains(Email)){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        newAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preformLogin();
            }
        });

    }

    private void preformLogin() {
        String email = inputEmail.getText().toString();
        String password = inputPass.getText().toString();
        if(!email.matches(emailPattern)){
            inputEmail.setError("Enter correct email!");
        }else if(password.isEmpty()||password.length()<6){
            inputPass.setError("Enter proper password!");
        }else {
            progressDialog.setMessage("Please wait while logging in...");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
//            loadingDialogBox.loadingAlertDialog();
//            Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    loadingDialogBox.dismissDialog();
//                }
//            },5000);

            mAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(@NonNull AuthResult authResult) {
//                    FirebaseFirestore db = FirebaseFirestore.getInstance();
//                    db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                            .get().addOnCompleteListener(task -> {
//                                if(task.isSuccessful() && task.getResult() != null){
//                                    name = task.getResult().getString("name");
//                                    //other stuff
//                                }else{
//                                    //deal with error
//                                }
//                            });
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        username = user.getDisplayName();
                        progressDialog.dismiss();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(Email,email);
                        editor.putString(Password,password);
                        editor.putString(Name,username);
                        editor.apply();
                        Toast.makeText(LoginActivity.this,username,Toast.LENGTH_SHORT).show();
                        sendUserToNextActivity();
                }
            });

        }
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        intent.putExtra("name",username);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}