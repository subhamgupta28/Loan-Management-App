package com.example.loanmanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class home extends AppCompatActivity {
    DatabaseHelper myDb;

    String currentuser ;
    String currentpass ;
    Button EduLoan,PersLoan,HomeLoan,EditUser,logOut,showbt;
    TextView userInfo,loaninfo;
    static String name;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        myDb = new DatabaseHelper(this);


        currentuser = getIntent().getStringExtra("USER");
        currentpass = getIntent().getStringExtra("PASSWORD");

        Toast.makeText(home.this,"LOGGED IN AS "+currentuser.toUpperCase(), Toast.LENGTH_LONG).show();


        EduLoan = (Button)findViewById(R.id.eduloan);

        PersLoan = (Button)findViewById(R.id.personalloan);

        HomeLoan = (Button)findViewById(R.id.homeloan);
        EditUser = (Button)findViewById(R.id.editdetails);
        userInfo = (TextView)findViewById(R.id.userinfo);
        loaninfo = (TextView)findViewById(R.id.linfo);
        logOut = (Button)findViewById(R.id.logout);
        userDetails(currentuser);
        viewData();
        logout();



        String loantype =  myDb.getName(currentuser);
        /*String[] ad1 = loantype.split("\\s+");
        String resu = ad1[0]+ " " + ad1[1] + " " + ad1[2] ;*/
        loaninfo.setText("You have taken \n"+loantype);

           //loaninfo.setText(ad1[0] + "\n" + ad1[1] + "\n" + ad1[2]);







        PersLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),login_signup.class);
                intent.putExtra("USER",name);
                intent.putExtra("Status","0");
                startActivity(intent);
            }
        });

        EduLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),login_signup.class);
                intent.putExtra("USER",name);
                intent.putExtra("Status","1");
                startActivity(intent);
            }
        });


        HomeLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),login_signup.class);
                intent.putExtra("USER",name);
                intent.putExtra("Status","2");
                startActivity(intent);
            }
        });






    }



    public void viewData(){
        EditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getUData(currentuser);
                if(res.getCount()==0){
                    //show message
                    showMessage("Error","No Data found");
                    return;
                }
                Log.d("viewdata method",currentuser);
                StringBuffer buffer= new StringBuffer();
                while(res.moveToNext()){
                    //buffer.append("USERID : " +res.getString(0)+"\n");
                    buffer.append("USERNAME : " +res.getString(1)+"\n");
                    buffer.append("PASSWORD : " +res.getString(2)+"\n");
                    buffer.append("PHONENO : " +res.getString(3)+"\n");
                    buffer.append("LOANTYPE1 : \n" +"\t\t"+ res.getString(4) +"  YEAR  "+res.getString(7)+ "\n\n");
                    buffer.append("LOANTYPE2 : \n" +"\t\t"+ res.getString(5) +"  YEAR  "+res.getString(8)+ "\n\n");
                    buffer.append("LOANTYPE3 : \n" +"\t\t" + res.getString(6) +"  YEAR  "+res.getString(9)+ "\n\n");
                }
                showMessage("Details",buffer.toString());



            }
        });
    }
    public void logout(){
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedpreferences = getSharedPreferences(activity_login.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();

                finish();
            }
        });


    }




    public void showMessage(String title,String message ){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void userDetails(String username){
        userInfo.setText("Welcome ! "+username);
    }
}