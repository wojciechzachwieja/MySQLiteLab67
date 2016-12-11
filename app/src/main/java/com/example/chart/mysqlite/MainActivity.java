package com.example.chart.mysqlite;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.chart.mysqlite.db.MySQLiteHelper;

import java.util.Random;

import static com.example.chart.mysqlite.db.MySQLiteHelper.DB_GROUP_TABLE;
import static com.example.chart.mysqlite.db.MySQLiteHelper.DB_STUDENT_GROUP_TABLE;
import static com.example.chart.mysqlite.db.MySQLiteHelper.DB_STUDENT_TABLE;
import static com.example.chart.mysqlite.db.MySQLiteHelper.KEY_GROUP_NAME;
import static com.example.chart.mysqlite.db.MySQLiteHelper.KEY_STUDENT_NAME;
import static com.example.chart.mysqlite.db.MySQLiteHelper.KEY_STUDENT_SURNAME;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    MySQLiteHelper mySQLiteHelper;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initEditText();
        initDB();
    }

    public void goToNewActivity(View view) {
        Intent myIntent = new Intent(context, MyListActivity.class);
        startActivity(myIntent);
    }

    private void initEditText() {
        editText = (EditText) findViewById(R.id.editText);
        SharedPreferences sharedPref =  getPreferences(Context.MODE_PRIVATE);
        editText.setText(sharedPref.getString("data", ""));
        context = getApplicationContext();
    }

    public void save(View view) {
        SharedPreferences sharedPref =  getPreferences(Context.MODE_PRIVATE);
        sharedPref.edit().putString("data", editText.getText().toString()).apply();
    }
    private String getInsertStudentQuery(int position) {
        return "INSERT INTO " + DB_STUDENT_TABLE + " (name, surname) VALUES ('" + KEY_STUDENT_NAME + position +
                "','" + KEY_STUDENT_SURNAME + position + "')";
    }

    private String getInsertGroupQuery(int position) {
        return "INSERT INTO " + DB_GROUP_TABLE + " (name) VALUES ('" + KEY_GROUP_NAME + position + "')";
    }

    private String getInsertStudentGroupQuery(int position, int position2) {
        return "INSERT INTO " + DB_STUDENT_GROUP_TABLE + " (id_student, id_group) VALUES (" + position +
                "," + position2 + ")";
    }
    private void insertData(SQLiteDatabase db) {
        for (int i = 0;i < 8;++i) {
            db.execSQL(getInsertStudentQuery(i));
            db.execSQL(getInsertGroupQuery(i));
        }
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0;i < 30;++i) {
            db.execSQL(getInsertStudentGroupQuery(random.nextInt(8), random.nextInt(8)));
        }
    }
    private void initDB() {
        mySQLiteHelper = new MySQLiteHelper(context);
        mySQLiteHelper.openWritableMode();
        //insertData(mySQLiteHelper.getDb());
    }

    @Override
    protected void onDestroy() {
        if(mySQLiteHelper != null)
            mySQLiteHelper.close();
        super.onDestroy();
    }
}
