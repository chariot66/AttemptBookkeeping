package com.example.attemptbookkeeping.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBTableHelper  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Notebook.db";
    private static final String TABLE_NAME = "notebook";
    private static final String COL_1 = "NAME";
    private static final String COL_2 = "INFO";

    private static final int DATABASED_VERSION = 1;

    public DBTableHelper(Context context) {
// super(context, name, factor, version)
        super(context, DATABASE_NAME, null, DATABASED_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME, INFO)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    // Method to insert a record to the database
    public boolean insertData(String name, String info) {
        SQLiteDatabase db = this.getWritableDatabase();
        // 判断是否有重名
        String selection = COL_1 + " = ?";
        String[] selectionArgs = { name };

        String sortOrder = COL_1 + " DESC";


        Cursor cursor = db.query(
                TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
        if(cursor.getCount() > 0){
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,info);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    // Method to show all the records
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
    // Method to update a record
    public boolean updateData(String name, String info)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,info);
        db.update(TABLE_NAME, contentValues, "NAME = ?", new String[] {name});
        return true;
    }
    // Method to delete a record
    public Integer deleteData (String Name) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "NAME = ?", new String[] {Name});
    }
}