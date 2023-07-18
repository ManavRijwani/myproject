package com.example.textilemart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class owner_details extends AppCompatActivity {
TextView rname1,raddress1,rstore1,rphonenumber1;
    ImageView back_btn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_details);
        rname1=findViewById(R.id.rname1);
        raddress1=findViewById(R.id.raddress1);
       // back_btn=findViewById(R.id.r2_back_button);
        rstore1=findViewById(R.id.rstore1);
        rphonenumber1=findViewById(R.id.rphonenumber1);
        final String userID = getIntent().getStringExtra("userid");
        Toast.makeText(owner_details.this,userID,Toast.LENGTH_SHORT).show();
//        back_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(owner_details.this,Homepage.class));
//            }
//        });
        FirebaseDatabase.getInstance().getReference().child("users").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    String Rname1 = snapshot.child("name").getValue().toString();
                    String Raddress1 = snapshot.child("address").getValue().toString();
                    String Rstore1 = snapshot.child("store").getValue().toString();
                    String Rphonenumber1 = snapshot.child("phone").getValue().toString();
                    rname1.setText(Rname1);
                raddress1.setText(Raddress1);
                rstore1.setText(Rstore1);
                rphonenumber1.setText(Rphonenumber1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}