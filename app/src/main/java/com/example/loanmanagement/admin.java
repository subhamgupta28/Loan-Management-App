package com.example.loanmanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class admin extends AppCompatActivity {


    DatabaseHelper myDb;
    EditText changename, changepass, changephone, chid;
    Button chcreateacc, chshowData, chupdate, chdele, backt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        myDb = new DatabaseHelper(this);

        changename = (EditText) findViewById(R.id.etname);
        changepass = (EditText) findViewById(R.id.etpass);
        changephone = (EditText) findViewById(R.id.etphone);
        chid = (EditText) findViewById(R.id.etid);

        chcreateacc = (Button) findViewById(R.id.adduser);
        chshowData = (Button) findViewById(R.id.showuser);
        chupdate = (Button) findViewById(R.id.edituser);
        chdele = (Button) findViewById(R.id.deluser);
        backt = (Button) findViewById(R.id.back);

        backt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        showUser();
        deleteUser();
        editUser();
        addUser();


    }

    public void deleteUser() {
        chdele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = chid.getText().toString();
                if (id.equals("")) {
                    Toast.makeText(admin.this, "Enter Id Correctly", Toast.LENGTH_LONG).show();

                }
                else {
                    Integer deletedRows = myDb.deleteData(id);
                    if (deletedRows > 0)
                        Toast.makeText(admin.this, "Deleted", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(admin.this, "Not Deleted", Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    public void editUser() {
        chupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = chid.getText().toString(),
                        chname = changename.getText().toString(),
                        chpass = changepass.getText().toString(),
                        chphone = changephone.getText().toString();
                if (id.equals("") || chname.equals("") || chpass.equals("") || chphone.equals("")){
                    Toast.makeText(admin.this, "Enter Details Correctly", Toast.LENGTH_LONG).show();
                }
                else {
                    boolean isupdate = myDb.updateData(id, chname, chpass, chphone);
                    if (isupdate == true)
                        Toast.makeText(admin.this, "Updated", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(admin.this, "Not Updated", Toast.LENGTH_LONG).show();
                }


            }
        });
    }


    public void addUser() {
        chcreateacc.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String chname = changename.getText().toString(),
                                chpass = changepass.getText().toString(),
                                chphone = changephone.getText().toString();
                        if ( chname.equals("") || chpass.equals("") || chphone.equals("")){
                            Toast.makeText(admin.this, "Enter Details Correctly", Toast.LENGTH_LONG).show();
                        }
                        else {
                            boolean isInserted = myDb.insertData(chname, chpass, chphone);
                            if (isInserted == true)
                                Toast.makeText(admin.this, "Account Created Succesfully", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(admin.this, "Account Not Created", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void showUser() {
        chshowData.setOnClickListener(
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
                            buffer.append("LOANTYPE1 : \n" +"\t\t"+ res.getString(4) +" YEAR "+res.getString(7)+ "\n\n");
                            buffer.append("LOANTYPE2 : \n" +"\t\t"+ res.getString(5) +" YEAR "+res.getString(8)+ "\n\n");
                            buffer.append("LOANTYPE3 : \n" +"\t\t" + res.getString(6) +" YEAR "+res.getString(9)+ "\n\n");
                        }

                        //show data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();


    }
}