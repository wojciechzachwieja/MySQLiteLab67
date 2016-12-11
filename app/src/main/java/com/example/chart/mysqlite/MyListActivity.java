package com.example.chart.mysqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import layout.BlankFragment;

public class MyListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, new BlankFragment()).commit();
        if (findViewById(R.id.fragment) != null) {

            if (savedInstanceState != null) {
                return;
            }

            BlankFragment firstFragment = BlankFragment.newInstance("wojtek", "123");

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment, firstFragment).commit();
        }
    }



}
