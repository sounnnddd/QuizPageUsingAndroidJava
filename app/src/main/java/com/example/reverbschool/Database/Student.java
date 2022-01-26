package com.example.reverbschool.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.reverbschool.ModelClass.Student_details;

//dbms name- student
public class Student extends SQLiteOpenHelper{

    //fields
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Student";
    private static final String TABLE_NAME = "Student_details";
    private static final String COL_fname="FirstName";
    private static final String COL_sname="SecondName";
    private static final String COL_class="Class";
    private static final String COL_section="Section";
    private static final String COL_rno="Rollno";
    private static final String COL_sex="Sex";
    private static final String COL_age="Age";
    private static final String COL_userName="Username";
    private static final String COL_pwd="Password";
    private Context context;
    private boolean loginFlag=false;

    //    public Student( Context context,  String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
    public Student(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create="Create table " + TABLE_NAME + "(" + COL_fname + " text,"
                + COL_sname + " Text,"
               + COL_class + " integer not null,"
                + COL_section + " text not null,"
                + COL_rno + " integer,"
               + COL_sex + " text not null,"
                + COL_age + " integer,"
                + COL_userName + " text primary key,"
               + COL_pwd + " text not null" + ")";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {

            db.execSQL(TABLE_NAME);
            onCreate(db);
        }catch (Exception e) {

        }

    }

    public void addHandler(String fname,String sname,int classStudent,String section, int rno,String gender, String username, String password, int age) {
        ContentValues values = new ContentValues();
        values.put(COL_userName, username);
        values.put(COL_pwd, password);
        values.put(COL_fname, fname);
        values.put(COL_sname, sname);
        values.put(COL_class, classStudent);
        values.put(COL_section, section);
        values.put(COL_rno, rno);
        values.put(COL_sex,gender);
        values.put(COL_age,age);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void addHandler1(Student_details details) {
        ContentValues values = new ContentValues();
        values.put(COL_userName, details.getUsername());
        values.put(COL_pwd, details.getPassword());
        values.put(COL_fname, details.getFname());
        values.put(COL_sname, details.getSname());
        values.put(COL_class, details.getClassOfStudent());
        values.put(COL_section, details.getSection());
        values.put(COL_rno, details.getRno());
        values.put(COL_sex,details.getSexOfStudent());
        values.put(COL_age,details.getAge());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public boolean checkHandler(String username,String password)//get this from the launch pg username and pwd editText
    {
        String check="Select " + COL_pwd + " from " + TABLE_NAME + " where " + COL_userName
                + "= \"" + username + "\";";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(check,null);
        while (cursor.moveToNext())
        {
            if (cursor.getString(0).equals(password))
                loginFlag=true;
        }
        cursor.close();
        db.close();
        return loginFlag;

    }

}

