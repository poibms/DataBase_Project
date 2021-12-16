package com.example.dbproject;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class MedicineContentProvider extends ContentProvider
{
    private DBHelper helper;

    private static final UriMatcher matcher;

    static
    {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(MedicineContract.AUTHORITY, "medicines", 1);
        matcher.addURI(MedicineContract.AUTHORITY, "medicines/#", 2);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        String tableName;
        switch(matcher.match(uri))
        {
            case 1:
            default:
                tableName = "MEDICINES";
                break;
        }
        int ret = db.delete(tableName, selection, selectionArgs);
        db.close();
        getContext().getContentResolver().notifyChange(uri, null);
        return ret;
    }

    @Override
    public String getType(Uri uri)
    {
        if (uri.getLastPathSegment()==null)
        {
            return "vnd.android.cursor.dir/MedicineContentProvider.data.text";
        }
        else
        {
            return "vnd.android.cursor.item/MedicineContentProvider.data.text";
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        String tableName;
        switch(matcher.match(uri))
        {
            case 1:
            default:
                tableName = "MEDICINES";
                break;
        }
        long id = db.insert(tableName, null, values);
        db.close();
        Uri nu = ContentUris.withAppendedId(uri, id);
        getContext().getContentResolver().notifyChange(nu, null);
        return nu;
    }

    @Override
    public boolean onCreate()
    {
        this.helper = new DBHelper(this.getContext(), null, 1);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        switch(matcher.match(uri))
        {
            case 1:
            default:
                return this.helper.getWritableDatabase().query("MEDICINES", projection, selection, selectionArgs, null, null, sortOrder);
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        String tableName;
        switch(matcher.match(uri))
        {
            case 1:
            default:
                tableName = "MEDICINES";
                break;
        }
        int ret = db.update(tableName, values, selection, selectionArgs);
        db.close();
        getContext().getContentResolver().notifyChange(uri, null);
        return ret;
    }
}
