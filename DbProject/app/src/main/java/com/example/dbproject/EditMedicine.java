package com.example.dbproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class EditMedicine extends AppCompatActivity {
    public static final String MEDICINEID = "MEDICINEID";
    public static final String MEDICINE_NAME = "MEDICINE_NAME";
    public static final String MEDICINE_TYPE = "MEDICINE_TYPE";
    public static final String MEDICINE_DESC = "MEDICINE_DESC";
    public static final String MEDICINE_PRICE = "MEDICINE_PRICE";
    public static final String MEDICINE_ISCHECKED = "MEDICINE_ISCHECKED";

    private EditText nameEdit, descrEdit, priceEdit;
    private CheckBox isPrescription;
    private Spinner typeMedicineSpinner;
    private String[] strTypesOfLastPeriod;
    private ArrayAdapter<String> typeOfLastPeriodAdapter;

    private int current_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_medicine);

        nameEdit = findViewById(R.id.nameEdit);
        descrEdit = findViewById(R.id.descriptionEdit);
        priceEdit = findViewById(R.id.priceEdit);
        isPrescription = findViewById(R.id.checkBox2);
        typeMedicineSpinner = findViewById(R.id.spnrMedicineType);

        strTypesOfLastPeriod = new String[] { "Таблетки", "Капли", "Капсулы", "Спрей"};
        typeOfLastPeriodAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, strTypesOfLastPeriod);
        typeMedicineSpinner.setAdapter(typeOfLastPeriodAdapter);

        Intent intent = this.getIntent();
        Bundle inputs = intent.getExtras();
        String name = null, description = null, type = null;
        Integer price = null, isChecked = null;

        if(intent.hasExtra(EditMedicine.MEDICINEID))
            this.current_id = inputs.getInt(EditMedicine.MEDICINEID);
        if(intent.hasExtra(EditMedicine.MEDICINE_NAME))
            name = inputs.getString(EditMedicine.MEDICINE_NAME);
        if(intent.hasExtra(EditMedicine.MEDICINE_TYPE))
            type = inputs.getString(EditMedicine.MEDICINE_TYPE);
        if(intent.hasExtra(EditMedicine.MEDICINE_DESC))
            description = inputs.getString(EditMedicine.MEDICINE_DESC);
        if(intent.hasExtra(EditMedicine.MEDICINE_PRICE))
            price = inputs.getInt(EditMedicine.MEDICINE_PRICE);
        if(intent.hasExtra(EditMedicine.MEDICINE_ISCHECKED))
            isChecked = inputs.getInt(EditMedicine.MEDICINE_ISCHECKED);

        this.updateElements(name, type, description,price, isChecked );

    }

    private void updateElements(String name, String type, String description, Integer price, Integer isChecked)
    {
        this.nameEdit.setText(name);
        this.descrEdit.setText(description);
        this.priceEdit.setText(price.toString());
        this.isPrescription.setChecked(isChecked == 1 ? true : false);
        int spinPos = typeOfLastPeriodAdapter.getPosition(type);
        typeMedicineSpinner.setSelection(spinPos);

    }

    public void deleteMedicine_btn(View view) {
        final ContentResolver cr = getContentResolver();
        cr.delete(MedicineContract.URI, MedicineContract._id + " = ?", new String[]{Integer.toString(this.current_id)});
        startActivity(new Intent(this, MainActivity.class));
    }

    public void changeMedicine_btn(View view) {
        String name = nameEdit.getText().toString();
        String typeMedicine = typeMedicineSpinner.getSelectedItem().toString();
        String descript = descrEdit.getText().toString();
        Integer price = Integer.valueOf(priceEdit.getText().toString());
        Boolean isPrescript = isPrescription.isChecked();

        final String columns[] = new String[]
                {
                        MedicineContract._id,
                        MedicineContract.NAME,
                        MedicineContract.MEDICINETYPE,
                        MedicineContract.DESCRIPTION,
                        MedicineContract.PRICE,
                        MedicineContract.ISPRESCRIPTION
                };
        final ContentResolver cr = getContentResolver();

        ContentValues newValues = new ContentValues();
        newValues.put(MedicineContract.NAME, name);
        newValues.put(MedicineContract.MEDICINETYPE, typeMedicine);
        newValues.put(MedicineContract.DESCRIPTION, descript);
        newValues.put(MedicineContract.PRICE, price);
        newValues.put(MedicineContract.ISPRESCRIPTION, isPrescript);
        cr.update(MedicineContract.URI, newValues, MedicineContract._id + " = ?", new String[]{Integer.toString(this.current_id)});
        startActivity(new Intent(this, MainActivity.class));
    }
}