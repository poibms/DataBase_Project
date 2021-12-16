package com.example.dbproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = findViewById(R.id.medicineListView);
        String empty = "";
        medList(empty);

    }

    private void medList(String search)
    {
        final String columns[] = new String[]
                {
                        MedicineContract._id,
                        MedicineContract.NAME,
                        MedicineContract.MEDICINETYPE,
                        MedicineContract.DESCRIPTION,
                        MedicineContract.PRICE,
                        MedicineContract.ISPRESCRIPTION,
                };

        String colsToDisplay [] = new String[]
                {
                        MedicineContract.NAME,
                        MedicineContract.PRICE,
                };

        int[] colResIds = new int[]
                {
                        R.id.medicineName,
                        R.id.medicicnePrice
                };

        final ContentResolver cr = getContentResolver();
        Cursor c;
        if(search.isEmpty()) {
            c = cr.query(MedicineContract.URI,
                    columns,
                    null,
                    null,
                    null);
        } else {
            c = cr.query(MedicineContract.URI,
                    columns,
                    MedicineContract.NAME + " LIKE ? ",
                    new String[]{search+"%"},
                    null);
        }


        listView.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter_view, View v, int i, long l)
            {
                LinearLayout layout = (LinearLayout)((ViewGroup)v).getChildAt(0);
                String clicked_title = ((TextView)(layout.getChildAt(0))).getText().toString();
                Cursor tempCursor = cr.query(MedicineContract.URI, columns, MedicineContract.NAME + " LIKE ?", new String[]{clicked_title}, null);
                int id = -1;
                String type = null;
                String desc = null;
                Integer price = null;
                Integer isChecked  = null;
                if(tempCursor.moveToFirst())
                {
                    id = tempCursor.getInt(tempCursor.getColumnIndexOrThrow("_id"));
                    type = tempCursor.getString(tempCursor.getColumnIndexOrThrow("MEDICINETYPE"));
                    desc = tempCursor.getString(tempCursor.getColumnIndexOrThrow("DESCRIPTION"));
                    price = tempCursor.getInt(tempCursor.getColumnIndexOrThrow("PRICE"));
                    isChecked = tempCursor.getInt(tempCursor.getColumnIndexOrThrow("ISPRESCRIPTION"));

                }
                tempCursor.close();
                MainActivity.this.getClickedData(id, type, clicked_title, desc, price, isChecked);
            }
        });

        listView.setAdapter(new SimpleCursorAdapter(this,
                R.layout.item,
                c, colsToDisplay,
                colResIds, 0));
    }

    private void getClickedData(int _id, String type, String name, String desc, Integer price, Integer isChecked)
    {
        Intent intent = new Intent(this, EditMedicine.class);
        intent.putExtra(EditMedicine.MEDICINEID, _id);
        intent.putExtra(EditMedicine.MEDICINE_NAME, name);
        intent.putExtra(EditMedicine.MEDICINE_TYPE, type);
        intent.putExtra(EditMedicine.MEDICINE_DESC, desc);
        intent.putExtra(EditMedicine.MEDICINE_PRICE, price);
        intent.putExtra(EditMedicine.MEDICINE_ISCHECKED, isChecked);
        startActivity(intent);
    }

    public void medicineAct_btn(View view) {
        startActivity(new Intent(this, AddMedicine.class));
    }

    public void search_btn(View view) {
        EditText searchEdit = findViewById(R.id.searchEdit);
        String request = searchEdit.getText().toString();
        medList(request);
    }

    public void cryptAct_btn(View view) {
        startActivity(new Intent(this, CryptActivity.class));
    }
}