package com.example.chart.mysqlite.table.object;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.chart.mysqlite.db.MySQLiteHelper;

import java.util.ArrayList;

/**
 * Created by Wojtek on 2016-12-08.
 */

public class GroupDAO extends Group {
    private SQLiteDatabase database;
    private static final String WHERE_ID_EQUALS = MySQLiteHelper.KEY_GROUP_ID
            + " =?";

    public GroupDAO(Context context) {
        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(context);
        mySQLiteHelper.openWritableMode();
        database = mySQLiteHelper.getDb();
    }

    public long save(Group group) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.KEY_GROUP_NAME, group.getName());
        Log.d("Create Result: ", group.getName());
        return database.insert(MySQLiteHelper.DB_GROUP_TABLE, null, values);
    }

    public long save(SQLiteDatabase sqLiteDatabase, Group group) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.KEY_GROUP_NAME, group.getName());
        Log.d("Create Result: ", group.getName());
        return sqLiteDatabase.insert(MySQLiteHelper.DB_GROUP_TABLE, null, values);
    }

    public long update(Group group) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.KEY_GROUP_NAME, group.getName());

        long result = database.update(MySQLiteHelper.DB_GROUP_TABLE, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(group.getIdGroup()) });
        Log.d("Update Result:", "=" + result);
        return result;

    }

    public int delete(Group group) {
        return database.delete(MySQLiteHelper.DB_GROUP_TABLE, WHERE_ID_EQUALS,
                new String[] { group.getIdGroup() + "" });
    }

    //USING query() method
    public ArrayList<Group> getGroups() {
        ArrayList<Group> groups = new ArrayList<Group>();
        Cursor cursor = database.query(MySQLiteHelper.DB_GROUP_TABLE,
                new String[] { MySQLiteHelper.KEY_GROUP_ID,
                        MySQLiteHelper.KEY_GROUP_NAME}, null, null, null,
                null, null);
        while (cursor.moveToNext()) {
            Group group = new Group();
            group.setIdGroup(cursor.getLong(0));
            group.setName(cursor.getString(1));

            groups.add(group);
        }
        return groups;
    }

    public Group getGroup(long id) {
        Group group = null;

        String sql = "SELECT * FROM " + MySQLiteHelper.DB_GROUP_TABLE
                + " WHERE " + MySQLiteHelper.KEY_GROUP_ID + " = ?";

        Cursor cursor = database.rawQuery(sql, new String[] { id + "" });

        if (cursor.moveToNext()) {
            group = new Group();
            group.setIdGroup(cursor.getLong(0));
            group.setName(cursor.getString(1));
        }
        return group;
    }
}
