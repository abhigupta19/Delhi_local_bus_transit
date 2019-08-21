package com.sar.user.bus_data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Root extends AppCompatActivity {
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);
       textView=findViewById(R.id.textView);
       textView.setText("");
       if(!MainActivity.s.getText().equals(""))
       {
           show();
       }

    }

    private void show() {
        try {
            String s1=MainActivity.s.getText().toString();
            String[] string={"stops"};
            String str2="select stops from bus_stops where busNumber = '" + s1 + "'";
            Cursor localCursor = MainActivity.sqLiteDatabase.rawQuery(str2, null);
            localCursor.getCount();
            int i = localCursor.getColumnIndex("stops");
            for (int j = 1;; j++)
            {
                if (!localCursor.moveToNext())
                {
                    localCursor.close();
                    return;
                }
                this.textView.append(j + ". " + localCursor.getString(i) + "\n");
            }
        }
        catch (Exception localException){}
    }

    }

