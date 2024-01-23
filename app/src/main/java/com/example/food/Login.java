package com.example.food;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText email,pwd;
    Button btnLogin;
    TextView btnReg;
    String useremail,userpwd;
    String emailpattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin=findViewById(R.id.button3);

        email=findViewById(R.id.editTextText);

        pwd=findViewById(R.id.editTextText4);
        btnReg=findViewById(R.id.TextView14);
        auth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view){
                useremail=email.getText().toString();
                userpwd=pwd.getText().toString();
                if(!TextUtils.isEmpty(useremail)){
                    if(useremail.matches(emailpattern)){
                        if(!TextUtils.isEmpty(userpwd)){
                            LogInUser();
                        }else{
                            pwd.setError("Password is wrong");
                        }
                    }else{
                        email.setError("Enter valid Email address");
                    }

                }else{
                    email.setError("Email field cannot be empty");
                }
            }
        });
    }

    private void LogInUser() {
        btnLogin.setVisibility(View.VISIBLE);
        btnReg.setVisibility(View.INVISIBLE);
        auth.signInWithEmailAndPassword(useremail,userpwd).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(Login.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Login.this, Activityhome.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this,"Error"+e.getMessage(),Toast.LENGTH_SHORT).show();
                btnLogin.setVisibility(View.INVISIBLE);
                btnReg.setVisibility(View.VISIBLE);
            }
        });
    }
}