package com.example.textilemart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class details2 extends AppCompatActivity {
    TextInputEditText pname,pprice,pqty;
    Button pcontinuebtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        pname=findViewById(R.id.pname);
        pqty=findViewById(R.id.pqty);
        pprice=findViewById(R.id.pprice);
        pcontinuebtn=findViewById(R.id.pcontinuebtn);
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        pcontinuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,Object> m = new HashMap<String,Object>();
                m.put("ProductName",pname.getText().toString());
                m.put("Quantity",pqty.getText().toString());
                m.put("Price",pprice.getText().toString());
                Toast.makeText(details2.this, "data here", Toast.LENGTH_SHORT).show();
                FirebaseDatabase.getInstance().getReference().child("users").child(currentuser).child("product").child(pname.getText().toString()).setValue(m);
                Toast.makeText(details2.this, "now intent", Toast.LENGTH_SHORT).show();
                FirebaseDatabase.getInstance().getReference().child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(details2.this, "nnnn", Toast.LENGTH_SHORT).show();
                for (DataSnapshot s : snapshot.getChildren()) {

                    String userid = s.child("userid").getValue().toString();
                    Toast.makeText(details2.this,userid,Toast.LENGTH_SHORT).show();
                    if(!userid.equals(currentuser))
                    {
                        FirebaseDatabase.getInstance().getReference().child("users").child(userid).child("product").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot s2:snapshot.getChildren())
                                {
                                    String productname = s2.child("ProductName").getValue().toString();
                                    String price = s2.child("Price").getValue().toString();
                                    String quantity = s2.child("Quantity").getValue().toString();
                                    HashMap<String,Object> m = new HashMap<String, Object>();;
                                    m.put("ProductName",productname);
                                    m.put("Price",price);
                                    m.put("Quantity",quantity);
                                    m.put("userid",userid);
                                    FirebaseDatabase.getInstance().getReference().child("product").child(currentuser).child(productname).updateChildren(m);
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }


                }
            }
            @Override
            public void onCancelled (@NonNull DatabaseError error){

            }
        });
                startActivity(new Intent(details2.this,Homepage.class));


            }
        });

    }
}