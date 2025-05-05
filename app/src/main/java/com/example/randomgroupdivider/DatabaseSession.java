package com.example.randomgroupdivider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
public class DatabaseSession extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mca";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "madl";
    private static final String COLUMN_ID = "rollno";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_USERTYPE = "usertype";

    SQLiteDatabase sqLiteDatabase;

    public DatabaseSession(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_USERTYPE + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertRecord(String email, String password, String userType) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_PASSWORD, password);
        cv.put(COLUMN_USERTYPE, userType);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, cv);
        return result != -1;
    }

    public Cursor getAllRecords() {
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public Cursor searchUser(String rollNo){
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE rollno = " + rollNo, null);
    }

    public boolean updateRecord(String rollNo, String email, String password, String userType){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_PASSWORD, password);
        cv.put(COLUMN_USERTYPE, userType);
        long result = sqLiteDatabase.update(TABLE_NAME, cv, "rollno=?", new String[]{rollNo});

        return result != -1;
    }

    public boolean deleteRecord(String rollNo){
        ContentValues cv = new ContentValues();
        long result = sqLiteDatabase.delete(TABLE_NAME, "rollno=?", new String[]{rollNo});

        return result != -1;
    }
}