package com.example.loanmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Userdata.db";
    public static final String TABLE_NAME = "Usertable";
    public static final String col1 = "USERID";
    public static final String col2 = "USERNAME";
    public static final String col3 = "PASSWORD";
    public static final String col4 = "PHONENO";
    public static final String col5 = "LOANTYPE1";
    public static final String col6 = "LOANTYPE2";
    public static final String col7 = "LOANTYPE3";
    public static final String col8 = "DOL1";
    public static final String col9 = "DOL2";
    public static final String col10 = "DOL3";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME+"(USERID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,PASSWORD TEXT,PHONENO TEXT,LOANTYPE1 TEXT,LOANTYPE2 TEXT,LOANTYPE3 TEXT,DOL1 TEXT,DOL2 TEXT,DOL3 TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
    public boolean insertData(String name  ,String password,String phoneNumber){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2,name);
        contentValues.put(col3,password);
        contentValues.put(col4,phoneNumber);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return res;
    }
    public boolean updateData(String uid,String uname,String upass,String uphone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1,uid);
        contentValues.put(col2,uname);

        contentValues.put(col3,upass);
        contentValues.put(col4,uphone);
        //contentValues.put(col4,uacc);
        db.update(TABLE_NAME, contentValues,"USERID = ?",new String[]{uid});
        return true;
    }
    public Integer deleteData(String uid){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"USERID = ?",new String[]{uid});
    }

    public boolean checkUserPass(String username,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE USERNAME = ? AND PASSWORD = ? ",new String[]{username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;

    }
    public boolean appliedLoan(String uname,int type,String date){
        String ltvalue[] = {"PERSONAL-LOAN","EDUCATION-LOAN","HOME-LOAN"};
        String ltype[] = {col5,col6,col7};
        String dtype[] = {col8,col9,col10};
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2,uname);
        contentValues.put(ltype[type],ltvalue[type]);
        contentValues.put(dtype[type],date);
        Log.d("appliedloan",uname+type+date);
        //contentValues.put(col4,uacc);
        db.update(TABLE_NAME, contentValues,"USERNAME = ?",new String[]{uname});
        return true;
    }
    public String getName(String uname) {
        SQLiteDatabase db = this.getWritableDatabase();
        String rv = "not found";

        String whereclause = "USERNAME = ?";
        String[] whereargs = new String[]{uname};

        Cursor csr = db.query(TABLE_NAME,null,whereclause,whereargs,null,null,null);
        if (csr.moveToFirst()) {
            rv = "\t\t"+csr.getString(csr.getColumnIndex(col5))+"\n"+"\t\t"+csr.getString(csr.getColumnIndex(col6))+"\n"+"\t\t"+csr.getString(csr.getColumnIndex(col7));
            return rv.replaceAll("null"," ");
        }
        return rv;
    }
    public Cursor getUData(String uname) {


        SQLiteDatabase db = this.getWritableDatabase();
        String whereclause = "USERNAME=?";
        String[] whereargs = new String[]{uname};
        Cursor csr = db.query(TABLE_NAME,null,whereclause,whereargs,null,null,null);

        return csr;
    }


}
