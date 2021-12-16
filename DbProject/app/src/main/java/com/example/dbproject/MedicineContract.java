package com.example.dbproject;

import android.net.Uri;

public class MedicineContract
{
    public static final String AUTHORITY = "com.example.dbproject.MedicineContentProvider";
    public static final Uri URI =
            Uri.parse("content://" + AUTHORITY + "/recipes");
    public static final String _id = "_id";
    public static final String NAME = "NAME";
    public static final String MEDICINETYPE = "MEDICINETYPE";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String PRICE = "PRICE";
    public static final String ISPRESCRIPTION = "ISPRESCRIPTION";
}
