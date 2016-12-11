package com.example.chart.mysqlite.table.object;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.chart.mysqlite.db.MySQLiteHelper;

public class StudentDAO extends Student {
    private SQLiteDatabase database;
    private static final String WHERE_ID_EQUALS = MySQLiteHelper.KEY_STUDENT_ID
            + " =?";
    
    public StudentDAO(Context context) {
        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(context);
        mySQLiteHelper.openWritableMode();
        database = mySQLiteHelper.getDb();
    }

    public long save(Student student) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.KEY_STUDENT_NAME, student.getName());
        values.put(MySQLiteHelper.KEY_STUDENT_SURNAME, student.getSurname());
        Log.d("Create Result: ", student.getName());
        return database.insert(MySQLiteHelper.DB_STUDENT_TABLE, null, values);
    }

    public long save(SQLiteDatabase sqLiteDatabase,Student student) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.KEY_STUDENT_NAME, student.getName());
        values.put(MySQLiteHelper.KEY_STUDENT_SURNAME, student.getSurname());
        Log.d("Create Result: ", student.getName());
        return sqLiteDatabase.insert(MySQLiteHelper.DB_STUDENT_TABLE, null, values);
    }

    public long update(Student student) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.KEY_STUDENT_NAME, student.getName());
        values.put(MySQLiteHelper.KEY_STUDENT_SURNAME, student.getSurname());

        long result = database.update(MySQLiteHelper.DB_STUDENT_TABLE, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(student.getIdStudent()) });
        Log.d("Update Result:", "=" + result);
        return result;

    }

    public int delete(Student student) {
        return database.delete(MySQLiteHelper.DB_STUDENT_TABLE, WHERE_ID_EQUALS,
                new String[] { student.getIdStudent() + "" });
    }

    //USING query() method
    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<Student>();

        Cursor cursor = database.query(MySQLiteHelper.DB_STUDENT_TABLE,
                new String[] { MySQLiteHelper.KEY_STUDENT_ID,
                        MySQLiteHelper.KEY_STUDENT_NAME,
                        MySQLiteHelper.KEY_STUDENT_SURNAME }, null, null, null,
                null, null);

        while (cursor.moveToNext()) {
            Student student = new Student();
            student.setIdStudent(cursor.getLong(0));
            student.setName(cursor.getString(1));
            student.setSurname(cursor.getString(2));

            students.add(student);
        }
        return students;
    }

    public Student getStudent(long id) {
        Student student = null;

        String sql = "SELECT * FROM " + MySQLiteHelper.DB_STUDENT_TABLE
                + " WHERE " + MySQLiteHelper.KEY_STUDENT_ID + " = ?";

        Cursor cursor = database.rawQuery(sql, new String[] { id + "" });

        if (cursor.moveToNext()) {
            student = new Student();
            student.setIdStudent(cursor.getLong(0));
            student.setName(cursor.getString(1));
            student.setSurname(cursor.getString(2));
        }
        return student;
    }
}
