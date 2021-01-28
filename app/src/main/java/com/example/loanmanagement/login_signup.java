package com.example.loanmanagement;

//First page create or login

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class login_signup extends AppCompatActivity {
    TextView showStatus;
    EditText loanamount, loantime;
    Button saveStatus;
    static String currentuser;

    DatabaseHelper myDb;

    static int status = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
        myDb = new DatabaseHelper(this);
         status = Integer.parseInt(getIntent().getStringExtra("Status"));
        currentuser = getIntent().getStringExtra("USER");

        final String statusLog[] = {"PERSONAL_LOAN","EDUCATION_LOAN","HOME_LOAN"};

        saveStatus = findViewById(R.id.save);
        loanamount = findViewById(R.id.loanamu);
        loantime = findViewById(R.id.loantim);

        Toast.makeText(getApplicationContext(),"STATUS "+statusLog[status], Toast.LENGTH_LONG).show();

        editUser();







    }
    public void editUser() {
        saveStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loanamount.getText().toString().isEmpty() && loantime.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Input Details Correctly",Toast.LENGTH_LONG).show();
                }
                else{
                    SharedPreferences sharedpreferences = getSharedPreferences(activity_login.MyPREFERENCES, Context.MODE_PRIVATE);

                    String name = sharedpreferences.getString("name", "");


                    Toast.makeText(getApplicationContext(), "Enter Details Correctly", Toast.LENGTH_LONG).show();
                    DateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
                    Calendar calobj = Calendar.getInstance();


                    boolean isupdate = myDb.appliedLoan(name, status, formatter.format(calobj.getTime()));

                    Log.d("is", name + status + formatter.format(calobj.getTime()));
                    if (isupdate == true) {
                        Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),home.class);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Not Updated", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}