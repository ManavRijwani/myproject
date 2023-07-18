package com.example.textilemart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    Button cloginBtn;
    TextInputEditText clemail,clpwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cloginBtn=findViewById(R.id.cloginBtn);
        clemail=findViewById(R.id.clemail);
        clpwd=findViewById(R.id.clpwd);
        cloginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = clemail.getText().toString();
                final String password = clpwd.getText().toString();
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(),"Please enter email!!",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(),"Please enter password!!",Toast.LENGTH_LONG).show();
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            String uid = task.getResult().getUser().getUid();
                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

                            firebaseDatabase.getReference().child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String role = snapshot.getValue(String.class);
                                        Intent intent = new Intent(login.this, Homepage.class);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(getApplicationContext(),"Login successful!!", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                        else
                        {
                            Toast.makeText(login.this, "sign up error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });

    }
    }
