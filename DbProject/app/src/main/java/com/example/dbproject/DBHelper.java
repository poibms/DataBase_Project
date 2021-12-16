package com.example.dbproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "HOSPITAL";
    private static final String MEDICINES_TABLE = "MEDICINES";
    public DBHelper(Context context, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + MEDICINES_TABLE + "( "
                + "_id integer primary key autoincrement not null, "
                + "NAME text not null, "
                + "MEDICINETYPE text not null,"
                + "DESCRIPTION text not null, "
                + "PRICE INTEGER not null, "
                + "ISPRESCRIPTION BOOLEAN not null"
                +" );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS MEDICINES_TABLE");
        this.onCreate(db);
    }

}