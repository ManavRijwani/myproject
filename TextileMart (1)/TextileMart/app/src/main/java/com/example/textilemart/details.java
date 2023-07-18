package com.example.textilemart;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class details extends AppCompatActivity {
TextInputEditText rname,raddress,rphonenumber,rstore;
Button rcontinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        rname=findViewById(R.id.rname);
        raddress=findViewById(R.id.raddress);
        rphonenumber=findViewById(R.id.rphonenumber);
        rstore=findViewById(R.id.rstore);
        rcontinue=findViewById(R.id.rcontbtn);
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        rcontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,Object> m = new HashMap<String,Object>();
                m.put("name",rname.getText().toString());
                m.put("address",raddress.getText().toString());
                m.put("phone",rphonenumber.getText().toString());
                m.put("store",rstore.getText().toString());
                m.put("userid",currentuser.toString());
                FirebaseDatabase.getInstance().getReference().child("users").child(currentuser).setValue(m);
                //FirebaseDatabase.getInstance().getReference().child("riders").child(currentuser).setValue(m);
                startActivity(new Intent(details.this,details2.class));
            }
        });
    }
}