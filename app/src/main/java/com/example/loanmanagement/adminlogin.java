package com.example.loanmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class adminlogin extends AppCompatActivity {
    EditText adminname,adminpass;
    Button Log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        adminname = (EditText)findViewById(R.id.adname);
        adminpass = (EditText)findViewById(R.id.adpass);
        Log = (Button)findViewById(R.id.adlog);

        Log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = adminname.getText().toString(),pass = adminpass.getText().toString() ;
                if (name.equals("admin") && pass.equals("pass")) {
                    Toast.makeText(adminlogin.this, "Successfully Logged In", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), admin.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(adminlogin.this, "Wrong Password", Toast.LENGTH_LONG).show();

            }
        });
    }
}