package com.example.chart.mysqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Wojtek on 2016-12-09.
 */

public class MyCustomAdapter2 extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public MyCustomAdapter2(Context context, String[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.my_list_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.list_item_text);
        textView.setText(values[position]);

        return rowView;
    }

}
