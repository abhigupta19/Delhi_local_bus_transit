package com.sar.user.bus_data;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.io.IOException;
import java.sql.Array;

public class MainActivity extends AppCompatActivity {
     static  AutoCompleteTextView s;
     static SQLiteDatabase  sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s=findViewById(R.id.autoCompleteTextView);

            sqLiteDatabase=new Databasehelper(this).openDatabase();
            setbuslist();
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(MainActivity.this,Root.class));
            }
        });


    }

    private void setbuslist() {
        Cursor localCursor = this.sqLiteDatabase.rawQuery("select distinct busNumber from dtc_bus", null);
        String[] arrayOfString=null;
        if ((localCursor != null) && (localCursor.getCount() > 0)) {
            arrayOfString = new String[localCursor.getCount()];
        }
        for (int i = 0;; i++)
        {
            if (!localCursor.moveToNext())
            {
                this.s.setThreshold(1);
                ArrayAdapter<String> adapter;
                adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayOfString);
                this.s.setAdapter(adapter);
                localCursor.close();
                return;
            }
            arrayOfString[i] = localCursor.getString(localCursor.getColumnIndex("busNumber"));

        }


    }
}
