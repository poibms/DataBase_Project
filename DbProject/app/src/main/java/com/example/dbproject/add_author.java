package com.example.dbproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;

public class add_author extends AppCompatActivity {
    private EditText nameEdit, bornEdit;
    private CheckBox isDocktor;
    private List<User> users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_author);

        nameEdit = findViewById(R.id.nameEdit);
        bornEdit = findViewById(R.id.ageEdit);
        isDocktor = findViewById(R.id.checkBox);
        users = new ArrayList<>();

    }

    public void addAuthor_btn(View view) {
        String name = nameEdit.getText().toString();
        int ageOfBorn = Integer.parseInt(bornEdit.getText().toString());

        User user = new User(name, ageOfBorn, isDocktor.isChecked());
        users.add(user);
        toJson();
        startActivity(new Intent(this, MainActivity.class));
    }

    public void toJson() {
        boolean result = JSONHelper.exportToJSON(this, users);
        if(result){
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Не удалось сохранить данные", Toast.LENGTH_LONG).show();
        }
    }

}
