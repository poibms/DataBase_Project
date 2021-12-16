package com.example.dbproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class AddMedicine extends AppCompatActivity {
    private EditText nameEdit, descrEdit, priceEdit;
    private CheckBox isPrescription;
    private Spinner typeMedicineSpinner;

    private String[] strTypesOfLastPeriod;
    List<MedicineContract> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        nameEdit = findViewById(R.id.nameEdit);
        descrEdit = findViewById(R.id.descriptionEdit);
        priceEdit = findViewById(R.id.priceEdit);
        isPrescription = findViewById(R.id.checkBox2);
        typeMedicineSpinner = findViewById(R.id.spnrMedicineType);


        strTypesOfLastPeriod = new String[] { "Таблетки", "Капли", "Капсулы", "Спрей"};
        ArrayAdapter<String> typeOfLastPeriodAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, strTypesOfLastPeriod);
        typeMedicineSpinner.setAdapter(typeOfLastPeriodAdapter);
    }

    public void addMedicine_btn(View view) {

        String name = nameEdit.getText().toString();
        String typeMedicine = typeMedicineSpinner.getSelectedItem().toString();
        String descript = descrEdit.getText().toString();
        Integer price = Integer.valueOf(priceEdit.getText().toString());
        Boolean isPrescript = isPrescription.isChecked();

        ContentValues values = new ContentValues();
        values.put(MedicineContract.NAME, name);
        values.put(MedicineContract.MEDICINETYPE, typeMedicine);
        values.put(MedicineContract.DESCRIPTION, descript);
        values.put(MedicineContract.PRICE, price);
        values.put(MedicineContract.ISPRESCRIPTION, isPrescript);
        getContentResolver().insert(MedicineContract.URI, values);

        startActivity(new Intent(this, MainActivity.class));
    }

}