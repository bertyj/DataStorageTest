
package com.conti.jing.datastoragetest.dbutils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "DataStorageTest_Todos.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "Todos";
    public static final String _ID = "_id";
    public static final String TODO_SUBJECT = "subject";
    public static final String TODO_DESCRIPTION = "description";

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TODO_SUBJECT + " TEXT NOT NULL, " + TODO_DESCRIPTION + " TEXT);";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL(DROP_TABLE);
        onCreate(database);
    }

}
