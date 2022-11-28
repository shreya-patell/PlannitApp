package com.example.anson_tu__100655482__mobile_final_assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "plannit.db";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(username TEXT primary key, password TEXT)");
        db.execSQL("create table events(name TEXT primary key, description TEXT, date TEXT, startTime TEXT, endTime TEXT, location, invites, username)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists users");
        db.execSQL("drop table if exists events");
    }

    public Boolean insertUserData(String username, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        long result = db.insert("users", null, values);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean insertEventData(String name, String description, String date, String startTime, String endTime, String location, String invites, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("description", description);
        values.put("date", date);
        values.put("startTime", startTime);
        values.put("endTime", endTime);
        values.put("location", location);
        values.put("invites", invites);
        values.put("username", username);
        long result = db.insert("events", null, values);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public String[][] getEvents(String username) {
        int eventIdx = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from events where username=?", new String[]{username});
        Log.i("EVENTS_0", cursor.getCount() + "");
        String[][] events = new String[cursor.getCount()][7];
        while (cursor.moveToNext()) {
            Log.i("EVENTS_1", cursor.getString(0));
            events[eventIdx][0] = cursor.getString(0);
            events[eventIdx][1] = cursor.getString(1);
            events[eventIdx][2] = cursor.getString(2);
            events[eventIdx][3] = cursor.getString(3);
            events[eventIdx][4] = cursor.getString(4);
            events[eventIdx][5] = cursor.getString(5);
            events[eventIdx][6] = cursor.getString(6);
            eventIdx++;
        }
        return events;
    }
}
