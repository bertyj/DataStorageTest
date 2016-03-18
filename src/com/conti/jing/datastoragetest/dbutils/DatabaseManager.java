
package com.conti.jing.datastoragetest.dbutils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * This class can supply a database access object, used to perform the basic database operations
 * between the data source and the business logic. It is similar to the DAO in J2EE.
 */
public class DatabaseManager {
    private DatabaseHelper mDatabaseHelper;
    private final Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseManager(Context mContext) {
        this.mContext = mContext;
    }

    public DatabaseManager open() {
        mDatabaseHelper = new DatabaseHelper(mContext);
        mDatabase = mDatabaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDatabaseHelper.close();
    }

    public void insert(String subject, String description) {
        ContentValues contentValues = new ContentValues();
        // This map contains the initial column values for the row.
        // The keys should be the column names and the values the column values.
        contentValues.put(DatabaseHelper.TODO_SUBJECT, subject);
        contentValues.put(DatabaseHelper.TODO_DESCRIPTION, description);
        mDatabase.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }

    public void delete(long _id) {
        String deleteClause = DatabaseHelper._ID + " = " + _id;
        mDatabase.delete(DatabaseHelper.TABLE_NAME, deleteClause, null);
    }

    public int update(long _id, String subject, String description) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.TODO_SUBJECT, subject);
        contentValues.put(DatabaseHelper.TODO_DESCRIPTION, description);
        String updateClause = DatabaseHelper._ID + " = " + _id;
        int updateRowsNumber = mDatabase.update(DatabaseHelper.TABLE_NAME, contentValues, updateClause, null);
        return updateRowsNumber;
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.TODO_SUBJECT, DatabaseHelper.TODO_DESCRIPTION };
        // A Cursor object, which is positioned before the first entry.
        Cursor cursor = mDatabase.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

}
