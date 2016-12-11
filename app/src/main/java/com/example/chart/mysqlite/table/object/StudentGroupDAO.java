package com.example.chart.mysqlite.table.object;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.chart.mysqlite.db.MySQLiteHelper;
import java.util.ArrayList;

public class StudentGroupDAO extends StudentGroup {
    private SQLiteDatabase database;
    private static final String WHERE_ID_EQUALS = MySQLiteHelper.KEY_STUDENT_GROUP_ID
            + " = ?";

    public StudentGroupDAO(Context context) {
        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(context);
        mySQLiteHelper.openWritableMode();
        database = mySQLiteHelper.getDb();
    }

    public long save(StudentGroup studentGroup) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.KEY_FK_STUDENT_ID, studentGroup.getIdStudent());
        values.put(MySQLiteHelper.KEY_FK_GROUP_ID, studentGroup.getIdGroup());
        Log.d("Create Result: ", "StudentGroup");
        return database.insert(MySQLiteHelper.DB_STUDENT_GROUP_TABLE, null, values);
    }

    public long save(SQLiteDatabase sqLiteDatabase, StudentGroup studentGroup) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.KEY_FK_STUDENT_ID, studentGroup.getIdStudent());
        values.put(MySQLiteHelper.KEY_FK_GROUP_ID, studentGroup.getIdGroup());
        Log.d("Create Result: ", "StudentGroup");
        return sqLiteDatabase.insert(MySQLiteHelper.DB_STUDENT_GROUP_TABLE, null, values);
    }

    public long update(StudentGroup studentGroup) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.KEY_FK_STUDENT_ID, studentGroup.getIdStudent());
        values.put(MySQLiteHelper.KEY_FK_GROUP_ID, studentGroup.getIdGroup());

        long result = database.update(MySQLiteHelper.DB_STUDENT_GROUP_TABLE, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(studentGroup.getIdStudent()) });
        Log.d("Update Result:", "=" + result);
        return result;
    }

    public int delete(StudentGroup studentGroup) {
        return database.delete(MySQLiteHelper.DB_STUDENT_GROUP_TABLE, WHERE_ID_EQUALS,
                new String[] { studentGroup.getIdStudent() + "" });
    }

    //USING query() method
    public ArrayList<StudentGroup> getStudentsGroup() {
        ArrayList<StudentGroup> students = new ArrayList<StudentGroup>();

        Cursor cursor = database.query(MySQLiteHelper.DB_STUDENT_GROUP_TABLE,
                new String[] { MySQLiteHelper.KEY_STUDENT_GROUP_ID,
                        MySQLiteHelper.KEY_FK_STUDENT_ID,
                        MySQLiteHelper.KEY_FK_GROUP_ID }, null, null, null,
                null, null);

        while (cursor.moveToNext()) {
            StudentGroup studentGroup = new StudentGroup();
            studentGroup.setIdStudentGroup(cursor.getLong(0));
            studentGroup.setIdStudent(cursor.getLong(1));
            studentGroup.setIdGroup(cursor.getLong(2));
            students.add(studentGroup);
        }
        return students;
    }

    public StudentGroup getStudentGroup(long id) {
        StudentGroup studentGroup = null;

        String sql = "SELECT * FROM " + MySQLiteHelper.DB_STUDENT_GROUP_TABLE
                + " WHERE " + MySQLiteHelper.KEY_STUDENT_GROUP_ID + " = ?";

        Cursor cursor = database.rawQuery(sql, new String[] { id + "" });

        if (cursor.moveToNext()) {
            studentGroup = new StudentGroup();
            studentGroup.setIdStudentGroup(cursor.getLong(0));
            studentGroup.setIdStudent(cursor.getLong(1));
            studentGroup.setIdGroup(cursor.getLong(2));
        }
        return studentGroup;
    }
}
