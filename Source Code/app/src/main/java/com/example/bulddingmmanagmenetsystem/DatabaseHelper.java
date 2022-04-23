package com.example.bulddingmmanagmenetsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.EditText;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    static  String dataBaseName = "BulddingMmanagmenetSystem";
    static  int    dataBaseversion =1;

    String createRENTERtable = " CREATE TABLE IF NOT EXISTS 'RENTER' ('ID' INTEGER  PRIMARY KEY AUTOINCREMENT,'FIRST_NAME' TEXT,'LAST_NAME' TEXT,'PASSWORD' TEXT,'MOBILE_NUMBER' INTEGER,'BUILDING_NAME' TEXT,'FLAT_NUMBER' TEXT,'GENDER' TEXT)";
    String createCOMPLAINTStable = " CREATE TABLE IF NOT EXISTS 'COMPLAINT' ('ID' INTEGER  PRIMARY KEY AUTOINCREMENT,'MOBILE_NUMBER' TEXT  ,'BUILDING_NAME' TEXT ,'FLAT_NUMBER' TEXT, 'STATUS' TEXT , 'COMPLAINT_DESCRIPTION' TEXT) ";



    public DatabaseHelper(Context context) {
        super(context, dataBaseName, null, dataBaseversion);
        getWritableDatabase().execSQL(createRENTERtable);
        getWritableDatabase().execSQL(createCOMPLAINTStable);
    }


    public void insertRenter(ContentValues contentValues ){
        getWritableDatabase().insert("RENTER","",contentValues);
    }

    public void insertComplaint(ContentValues contentValues ){
        getWritableDatabase().insert("COMPLAINT","",contentValues);
    }

    public boolean isLoginValid(String firstname, int mobilenumber , String password ) {

        String sql = " SELECT COUNT(*) from RENTER WHERE MOBILE_NUMBER ='"+mobilenumber+"' and PASSWORD='"+password+"' and FIRST_NAME='"+firstname+"' ";

        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);
        long l = statement.simpleQueryForLong();
        statement.close();

        if(l == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    public ArrayList getAllrenterComplaints(){

        ArrayList arrayList  = new ArrayList();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT ID,MOBILE_NUMBER,BUILDING_NAME,FLAT_NUMBER,STATUS,COMPLAINT_DESCRIPTION FROM COMPLAINT",null);
        res.moveToFirst();

        while ( res.isAfterLast() == false ) {
            String t1 = res.getString(0);
            String t2 = res.getString(1);
            String t3 = res.getString(2);
            String t4 = res.getString(3);
            String t5 = res.getString(4);
            String t6 = res.getString(5);

            arrayList.add("Complaint Number: "+t1+"\n"+"Mobile Number: "+t2+"\n"+"Building Name: "+t3+"\n"+"Flat Number: "+t4+"\n"+"Complaint Status: "+t5+"\n"+"Complaint description: "+t6);
            res.moveToNext();
        }
        return  arrayList;
    }

    public ArrayList getRenterComplaint(int mobileNumber){

        ArrayList arrayList  = new ArrayList();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT ID,MOBILE_NUMBER,BUILDING_NAME,FLAT_NUMBER,STATUS,COMPLAINT_DESCRIPTION FROM COMPLAINT WHERE MOBILE_NUMBER ='"+mobileNumber+"' ",null);
        res.moveToFirst();
        while ( res.isAfterLast() == false ) {

            String t1 = res.getString(0);
            String t2 = res.getString(1);
            String t3 = res.getString(2);
            String t4 = res.getString(3);
            String t5 = res.getString(4);
            String t6 = res.getString(5);

            arrayList.add("Complaint Number: "+t1+"\n"+"Mobile Number: "+t2+"\n"+"Building Name: "+t3+"\n"+"Flat Number: "+t4+"\n"+"Complaint Status: "+t5+"\n"+"Complaint description: "+t6);


            res.moveToNext();
        }
        return  arrayList;
    }


    public ArrayList getAllrenters(){

        ArrayList arrayList  = new ArrayList();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT FIRST_NAME,LAST_NAME,MOBILE_NUMBER,BUILDING_NAME,FLAT_NUMBER,GENDER FROM RENTER",null);
        res.moveToFirst();
        while ( res.isAfterLast() == false ) {
            String t1 = res.getString(0);
            String t2 = res.getString(1);
            String t3 = res.getString(2);
            String t4 = res.getString(3);
            String t5 = res.getString(4);
            String t6 = res.getString(5);


            arrayList.add("First Name: "+t1+"\n"+"Last Name: "+" "+t2+"\n"+"Mobile Number: "+t3+"\n"+"Building Name: "+t4+"\n"+"Flat Number: "+t5+"\n"+"Gender: "+t6);
            res.moveToNext();
        }
        return  arrayList;
    }

    public ArrayList getRenterInformation(int mobileNumber){

        ArrayList arrayList  = new ArrayList();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT FIRST_NAME,LAST_NAME,PASSWORD,MOBILE_NUMBER,BUILDING_NAME,FLAT_NUMBER,GENDER FROM RENTER WHERE MOBILE_NUMBER ='"+mobileNumber+"'",null);
        res.moveToFirst();
        while ( res.isAfterLast() == false ) {
            String t1 = res.getString(0);
            String t2 = res.getString(1);
            String t3 = res.getString(2);
            String t4 = res.getString(3);
            String t5 = res.getString(4);
            String t6 = res.getString(5);
            String t7 = res.getString(6);


            arrayList.add("First Name: "+t1+"\n"+"Last Name: "+" "+t2+"\n"+"Password: "+t3+"\n"+"Mobile Number: "+t4+"\n"+"Building Name: "+t5+"\n"+"Flat Number: "+t6+"\n"+"Gender: "+t7);
            res.moveToNext();
        }
        return  arrayList;
    }

    public  boolean updateComplaintStatus(String complaintid, String status){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("STATUS",status);

        db.update("COMPLAINT",contentValues,"ID= ?", new String[] {complaintid});

    return true;
    }

        public  boolean UpdateRenetrInformation(String newfirstname, String newlastname,String newmobilenumber, String newpassword){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("FIRST_NAME",newfirstname);
        contentValues.put("LAST_NAME",newlastname);
        contentValues.put("PASSWORD",newpassword);

         db.update("RENTER", contentValues, "MOBILE_NUMBER= ?", new String[] {newmobilenumber});

        return true;
    }








    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
