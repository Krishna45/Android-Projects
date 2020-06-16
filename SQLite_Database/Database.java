package com.example.testpurpose;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "freshers.db";
    public static final String TABLE_NAME = "freshers_table";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String BRANCH = "class";
    public static final String GENDER = "gender";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //String myquery = "CREATE TABLE "+ TABLE_NAME+"("+ID+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"+NAME+ " VARCHAR , "+BRANCH+ " VARCHAR , "+GENDER +" INTEGER)";
         String myquery="CREATE TABLE TABLE_NAME(ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME VARCHAR , BRANCH VARCHAR , GENDER VARCHAR)";
        sqLiteDatabase.execSQL(myquery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name, String branch, String gender) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, name);
        cv.put(BRANCH, branch);
        cv.put(GENDER, gender);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, cv);
        if (result == 0)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor res = sqLiteDatabase.query(TABLE_NAME, null, null, null, null, null, null);
        return res;
    }

    public boolean updateData(String id, String name, String branch, String gender) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ID, id);
        cv.put(NAME, name);
        cv.put(BRANCH, gender);
        cv.put(GENDER, gender);
        sqLiteDatabase.update(TABLE_NAME, cv, "ID=?", new String[]{id});
        return true;
    }

    public int deleteData(String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, "ID=?", new String[]{id});
        sqLiteDatabase.execSQL("UPDATE SQLITE_SEQUENCE SET seq=0 WHERE NAME='" + TABLE_NAME + "'");
        return 1;
    }

    public void deleteAllData() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, null, null);
        sqLiteDatabase.execSQL("UPDATE SQLITE_SEQUENCE SET seq=0 where NAME ='" + TABLE_NAME + "'");

    }

}