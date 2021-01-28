package com.example.loanmanagement;

//1st page

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_login extends AppCompatActivity {
    EditText username ,password;

    Button btnlogin,adminLogin,createAccount;
    SharedPreferences sharedpreferences;
    DatabaseHelper myDb;
    home hm;
    private int c=0;
   public static final String keepUser ="name";
   public static final String keepPass ="pass";
    public static final String MyPREFERENCES = "MyPrefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myDb = new DatabaseHelper(this);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        username = (EditText) findViewById(R.id.userlog);
        password = (EditText) findViewById(R.id.passlog);

        //repassword = (EditText) findViewById(R.id.repas);
        btnlogin = (Button) findViewById(R.id.login);
        adminLogin = (Button) findViewById(R.id.adminlog);
        createAccount = (Button) findViewById(R.id.signin);
        check();



        goToAdminPage();
        goToCreateAccount();
        autoLogin(sharedpreferences.getString(keepUser,""),sharedpreferences.getString(keepPass,""));

    }



        public void check(){
            btnlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = username.getText().toString();
                    String pass = password.getText().toString();
                    //String repass = repassword.getText().toString();

                    if (name.equals("")||pass.equals(""))//repass.equals("")
                        Toast.makeText(activity_login.this,"Enter All details correctly",Toast.LENGTH_LONG).show();
                    else {

                        if (pass!=null) {//pass.equals(repass)
                            boolean checkUser = myDb.checkUserPass(name, pass);
                            if (checkUser == false)
                                Toast.makeText(activity_login.this, "User Does Not Exist", Toast.LENGTH_LONG).show();


                            else{
                                Toast.makeText(activity_login.this, "Login Successful", Toast.LENGTH_LONG).show();
                                SharedPreferences.Editor editor = sharedpreferences.edit();

                                editor.putString(keepUser, name);
                                editor.putString(keepPass, pass);
                                editor.apply();
                                Intent intent = new Intent(getApplicationContext(), home.class);

                                intent.putExtra("USER",name);
                                intent.putExtra("PASSWORD",pass);

                                startActivity(intent);

                            }
                        }
                    }

                }
            });

        }


        public void goToAdminPage(){
            adminLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    c++;

                    if(c==7) {

                        Toast.makeText(activity_login.this, "Your Now Admin", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), adminlogin.class);
                        c=0;
                        startActivity(intent);

                    }
                    else
                        Toast.makeText(activity_login.this,"Click again", Toast.LENGTH_LONG).show();

                }



            });
        }


    public void goToCreateAccount(){
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);



            }



        });
    }
    public void autoLogin(String name,String pass){
        String user= sharedpreferences.getString(keepUser,"");
        if(user.equals("")){
            Toast.makeText(getApplicationContext(),"No logged In User",Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(getApplicationContext(), home.class);

            intent.putExtra("USER",name);
            intent.putExtra("PASSWORD",pass);

            startActivity(intent);

        }
    }





}





        //db = new DatabaseHelper(this);










