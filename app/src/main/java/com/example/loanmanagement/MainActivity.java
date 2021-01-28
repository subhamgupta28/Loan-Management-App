package com.example.loanmanagement;

//create account

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText username,password,phonenum,id;
    Button createacc;
    Button showData;
    Button update;
    Button dele;
    activity_login activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        //Intent intent = new Intent(getApplicationContext(),login_signup.class);
        //startActivity(intent);



        username =(EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.passw);
        phonenum = (EditText)findViewById(R.id.phoneno);

        createacc = (Button)findViewById(R.id.next);

        /*update = (Button)findViewById(R.id.upgrade);
        dele = (Button)findViewById(R.id.del);*/
        AddData();
        //viewData();
        //updateData();
        //DeleteData();
    }


   /* public void DeleteData(){
        dele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedRows = myDb.deleteData(id.getText().toString());
                if (deletedRows>0)
                    Toast.makeText(MainActivity.this,"Deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Not Deleted",Toast.LENGTH_LONG).show();

            }
        });
    }
    public void updateData(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isupdate = myDb.updateData(id.getText().toString(),
                        username.getText().toString(),
                        password.getText().toString(),
                        phonenum.getText().toString());
                if(isupdate==true)
                    Toast.makeText(MainActivity.this,"Updated",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Not Updated",Toast.LENGTH_LONG).show();


            }
        });
    }*/


    public void AddData(){
        createacc.setOnClickListener(
            new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    String user = username.getText().toString();
                    String pass = password.getText().toString();
                    String phone = phonenum.getText().toString();

                    if (user.equals("")||pass.equals("")||phone.equals("")) {
                        Toast.makeText(MainActivity.this, "Enter All Details Correctly", Toast.LENGTH_LONG).show();
                    }
                    else{

                        boolean isInserted = myDb.insertData(user, pass, phone);
                        if (isInserted == true){
                            Toast.makeText(MainActivity.this, "Account Created Succesfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(),activity_login.class);
                            startActivity(intent);
                        }


                        else
                            Toast.makeText(MainActivity.this, "Account Not Created", Toast.LENGTH_LONG).show();
                    }
                }
            }
        );
    }
    /*public void viewData(){
        showData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDb.getData();
                        if(res.getCount()==0){
                            //show message
                            showMessage("Error","No Data found");
                            return;
                        }
                        StringBuffer buffer= new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("USERID : " +res.getString(0)+"\n");
                            buffer.append("USERNAME : " +res.getString(1)+"\n");
                            buffer.append("PASSWORD : " +res.getString(2)+"\n");
                            buffer.append("PHONENO : " +res.getString(3)+"\n");
                            buffer.append("ACCOUNTNO : " +res.getString(4)+"\n\n");
                        }

                        //show data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String message ){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }*/









}