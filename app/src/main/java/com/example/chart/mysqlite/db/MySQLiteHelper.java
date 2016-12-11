package com.example.chart.mysqlite.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Random;

/**
 * Created by Wojtek on 2016-12-03.
 */

public class MySQLiteHelper {
    private static final String DEBUG_TAG = "MySQLiteHelper";

    private static final int DB_VERSION = 12;
    private static final String DB_NAME = "students.db";
    //tables
    public static final String DB_STUDENT_TABLE = "student";
    public static final String DB_GROUP_TABLE = "group_";
    public static final String DB_STUDENT_GROUP_TABLE = "studentGroup";
    //columns student
    public static final String KEY_STUDENT_ID = "id_student";
    public static final String STUDENT_ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final int STUDENT_ID_COLUMN = 0;
    public static final String KEY_STUDENT_NAME = "name";
    public static final String STUDENT_NAME_OPTIONS = "TEXT NOT NULL";
    public static final int STUDENT_NAME_COLUMN = 1;
    public static final String KEY_STUDENT_SURNAME = "surname";
    public static final String STUDENT_SURNAME_OPTIONS = "TEXT NOT NULL";
    public static final int STUDENT_SURNAME_COLUMN = 2;
    //columns group
    public static final String KEY_GROUP_ID = "id_group";
    public static final String GROUP_ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final int GROUP_ID_COLUMN = 0;
    public static final String KEY_GROUP_NAME = "name";
    public static final String GROUP_NAME_OPTIONS = "TEXT NOT NULL";
    public static final int GROUP_NAME_COLUMN = 1;
    //columns studentGroup
    public static final String KEY_STUDENT_GROUP_ID = "id_student_group";
    public static final String STUDENT_GROUP_ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final int STUDENT_GROUP_ID_COLUMN = 0;
    public static final String KEY_FK_GROUP_ID = "id_group";
    public static final String FK_GROUP_ID_OPTIONS = "INTEGER";
    public static final String FK_GROUP_ID_CONSTRAINTS = "FOREIGN KEY (" + KEY_GROUP_ID + ") " +
            "REFERENCES " + DB_GROUP_TABLE + "(" + KEY_GROUP_ID + ")";
    public static final int FK_GROUP_ID_COLUMN = 1;
    public static final String KEY_FK_STUDENT_ID = "id_student";
    public static final String FK_STUDENT_ID_OPTIONS = "INTEGER";
    public static final String FK_STUDENT_ID_CONSTRAINTS = "FOREIGN KEY (" + KEY_STUDENT_ID + ") " +
            "REFERENCES " + DB_STUDENT_TABLE + "(" + KEY_STUDENT_ID + ")";
    public static final int FK_STUDENT_ID_COLUMN = 2;
    //SQL queries
    private static final String DB_CREATE_STUDENT_TABLE =
            "CREATE TABLE " + DB_STUDENT_TABLE + "( " +
                    KEY_STUDENT_ID + " " + STUDENT_ID_OPTIONS + ", " +
                    KEY_STUDENT_NAME + " " + STUDENT_NAME_OPTIONS + ", " +
                    KEY_STUDENT_SURNAME + " " + STUDENT_SURNAME_OPTIONS + ");";
    private static final String DROP_STUDENT_TABLE =
            "DROP TABLE IF EXISTS " + DB_STUDENT_TABLE;
    private static final String DB_CREATE_GROUP_TABLE =
            "CREATE TABLE " + DB_GROUP_TABLE + "( " +
                    KEY_GROUP_ID + " " + GROUP_ID_OPTIONS + ", " +
                    KEY_GROUP_NAME + " " + GROUP_NAME_OPTIONS + ");";
    private static final String DROP_GROUP_TABLE =
            "DROP TABLE IF EXISTS " + DB_GROUP_TABLE;
    private static final String DB_CREATE_STUDENT_GROUP_TABLE =
            "CREATE TABLE " + DB_STUDENT_GROUP_TABLE + "( " +
                    KEY_STUDENT_GROUP_ID + " " + STUDENT_GROUP_ID_OPTIONS + ", " +
                    KEY_FK_STUDENT_ID + " " + FK_STUDENT_ID_OPTIONS + ", " +
                    KEY_FK_GROUP_ID + " " + FK_GROUP_ID_OPTIONS + "," + FK_STUDENT_ID_CONSTRAINTS + " " +
                    FK_GROUP_ID_CONSTRAINTS + ");";
    private static final String DROP_STUDENT_GROUP_TABLE =
            "DROP TABLE IF EXISTS " + DB_STUDENT_GROUP_TABLE;




    private SQLiteDatabase db;
    private Context context;
    private DatabaseHelper dbHelper;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            createDataBase(db, DB_CREATE_STUDENT_TABLE, DB_CREATE_GROUP_TABLE, DB_CREATE_STUDENT_GROUP_TABLE);
            Log.d(DEBUG_TAG, "Database creating...");
            Log.d(DEBUG_TAG, "Ver." + DB_VERSION + " created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d(DEBUG_TAG, "Database updating...");
            Log.d(DEBUG_TAG, "Updated from ver." + oldVersion + " to ver." + newVersion);
            createDataBase(db, DROP_STUDENT_TABLE, DROP_GROUP_TABLE, DROP_STUDENT_GROUP_TABLE);
            Log.d(DEBUG_TAG, "Previously version has been removed.");
            onCreate(db);
            Log.d(DEBUG_TAG, "The newest version has been created.");
        }

        private void createDataBase(SQLiteDatabase db, String dropStudentTable, String dropGroupTable, String dropStudentGroupTable) {
            db.execSQL(dropStudentTable);
            db.execSQL(dropGroupTable);
            db.execSQL(dropStudentGroupTable);
        }
    }

    public MySQLiteHelper(Context context) {
        this.context = context;
    }

    public MySQLiteHelper openWritableMode(){
        dbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public MySQLiteHelper openReadableMode(){
        dbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
        try {
            db = dbHelper.getReadableDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public SQLiteDatabase getDb() {
        return db;
    }


}
