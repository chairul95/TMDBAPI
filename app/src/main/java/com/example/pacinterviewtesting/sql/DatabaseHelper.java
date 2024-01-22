package com.example.pacinterviewtesting.sql;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DBNAME = "db_USER";
    private static final String TABLENAME = "tbUSER";

    private static String colID = "id";
    private static String colUserName = "username";
    private static String colPassword = "password";

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLENAME + " (" +
                colID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + colUserName + " TEXT," +
                colPassword + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        onCreate(db);
    }

    public void insertData(String username, String password) {
        String insertData = "INSERT INTO " + TABLENAME + " (" + colUserName + "," + colPassword + ") VALUES ('" + username + "', '" + password + "')";
        this.getWritableDatabase().execSQL(insertData);
    }

    public void deleteData(int id) {
        String deleteData = "DELETE FROM " + TABLENAME + " WHERE id=" + id;
        this.getWritableDatabase().execSQL(deleteData);
    }

    @SuppressLint("Range")
    public List<UserModel> getAll() {
        List<UserModel> model = new ArrayList<>();
        String selectData = "SELECT * FROM " + TABLENAME;
        Cursor data = this.getWritableDatabase().rawQuery(selectData, null);
        if (data.moveToFirst()) {
            do {
                model.add(new UserModel(Integer.parseInt(data.getString(data.getColumnIndex(colID))),
                        data.getString(data.getColumnIndex(colUserName)), data.getString(data.getColumnIndex(colPassword))));
            } while (data.moveToNext());
        }
        return model;
    }

    @SuppressLint("Range")
    public List<UserModel> getUser(String id) {
        List<UserModel> models = new ArrayList<>();
        String selectData = "SELECT * FROM " + TABLENAME + " WHERE " + colUserName + " = '" + id + "'";
        Cursor data = this.getWritableDatabase().rawQuery(selectData, null);
        if (data.moveToFirst()) {
            do {
                models.add(new UserModel(Integer.parseInt(data.getString(data.getColumnIndex(colID))),
                        data.getString(data.getColumnIndex(colUserName)), data.getString(data.getColumnIndex(colPassword))));
            } while (data.moveToNext());
        }
        return models;
    }

    @SuppressLint("Range")
    public List<UserModel> getPassword(String id) {
        List<UserModel> models = new ArrayList<>();
        String selectData = "SELECT * FROM " + TABLENAME + " WHERE " + colPassword + " = '" + id + "'";
        Cursor data = this.getWritableDatabase().rawQuery(selectData, null);
        if (data.moveToFirst()) {
            do {
                models.add(new UserModel(Integer.parseInt(data.getString(data.getColumnIndex(colID))),
                        data.getString(data.getColumnIndex(colUserName)), data.getString(data.getColumnIndex(colPassword))));
            } while (data.moveToNext());
        }
        return models;
    }
}
