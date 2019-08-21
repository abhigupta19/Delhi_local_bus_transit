package com.sar.user.bus_data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Databasehelper {
    public static final String Database_Name="dtc.db";
    File dbfile;
    Context context;
    private MainActivity c;
    public Databasehelper(MainActivity mainActivity)
    {
        c=mainActivity;
    }
    public SQLiteDatabase openDatabase()  {
        this.dbfile=c.getDatabasePath("dtc.db");
        if(!this.dbfile.exists()){}
        try {
            this.dbfile.getParentFile().mkdir();
            


            copy(this.dbfile);
            return SQLiteDatabase.openDatabase(this.dbfile.getPath(), null, 268435472);
        }
        catch (IOException localIOException)
        {
            throw new RuntimeException("Error creating source database", localIOException);

        }

    }

    private void copy(File dbfile) throws IOException {
        InputStream localInputStream = this.c.getAssets().open("dtcdb.db");
        System.out.println(dbfile.getPath());
        FileOutputStream localFileOutputStream = new FileOutputStream(dbfile);
        byte[] arrayOfByte = new byte[1024];
        for (;;)
        {
            if (localInputStream.read(arrayOfByte) <= 0)
            {
                localFileOutputStream.flush();
                localFileOutputStream.close();
                localInputStream.close();
                return;
            }
            localFileOutputStream.write(arrayOfByte);
        }


    }
}

