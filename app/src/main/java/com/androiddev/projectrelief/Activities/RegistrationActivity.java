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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    TextView haveAcc;
    EditText inputEmail,inputPass,confirmPass,name;
    Button btnRegister;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        haveAcc = findViewById(R.id.txtHaveAcc);
        name = findViewById(R.id.edtName);
        inputEmail = findViewById(R.id.edtEmailAddress);
        confirmPass = findViewById(R.id.confirmPass);
        inputPass = findViewById(R.id.edtSetPassword);
        btnRegister = findViewById(R.id.btnRegister);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();
        sharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE);

        haveAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performAuth();
            }
        });
    }

    private void performAuth() {
        String email = inputEmail.getText().toString();
        String password = inputPass.getText().toString();
        String confirmPassword = confirmPass.getText().toString();
        username = name.getText().toString();

        if(!email.matches(emailPattern)){
            inputEmail.setError("Enter correct email!");
        }else if(password.isEmpty()||password.length()<6){
            inputPass.setError("Enter proper password!");
        }else if(!password.equals(confirmPassword)){
            confirmPass.setError("Password not matched!");
        }else{
            progressDialog.setMessage("Please wait while registration...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>(){

                @Override
                public void onSuccess(AuthResult authResult) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(Email,email);
                    editor.putString(Password,password);
                    editor.putString(Name,username);
                    editor.apply();
                    FirebaseUser user = authResult.getUser();
                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                            .setDisplayName(username)
                                    .build();
                    user.updateProfile(profileChangeRequest).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(RegistrationActivity.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            progressDialog.setMessage("saving user data...");
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(RegistrationActivity.this,"Failed to save user data",Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    });
                    sendUserToNextActivity();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegistrationActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);
        intent.putExtra("name",username);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}