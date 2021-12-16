package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText nameEdit, typeEdit, descEdit, priceEdit, idEdit;

    private final Uri MEDICINE_URI =
            Uri.parse("content://com.example.dbproject.MedicineContentProvider");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idEdit = findViewById(R.id.editTextTextPersonName5);
        nameEdit = findViewById(R.id.editTextTextPersonName);
        typeEdit = findViewById(R.id.editTextTextPersonName2);
        descEdit = findViewById(R.id.editTextTextPersonName3);
        priceEdit = findViewById(R.id.editTextTextPersonName4);


    }


    public void addMedicine(View view) {
        String name = nameEdit.getText().toString();
        String type = typeEdit.getText().toString();
        String desc = descEdit.getText().toString();
        Integer price = Integer.parseInt(priceEdit.getText().toString());
        Boolean isPrescript = false;

        try {
            ContentValues values = new ContentValues();

            values.put("_id", Integer.parseInt(idEdit.getText().toString()));
            values.put("NAME", name);
            values.put("MEDICINETYPE", type);
            values.put("DESCRIPTION", desc);
            values.put("PRICE", price);
            values.put("ISPRESCRIPTION", isPrescript);

            getContentResolver().insert(MEDICINE_URI, values);

            Toast.makeText(this, "Студент успешно добавлен", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show();

        }

    }
}