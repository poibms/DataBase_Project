package com.example.dbproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CryptActivity extends AppCompatActivity {
    private EditText nameEdit;
    private TextView status, resultView;
    private String cryptName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypt);
        nameEdit = findViewById(R.id.nameEdit);
        status = findViewById(R.id.status);
        resultView = findViewById(R.id.result);
    }

    public void Search_btn(View view) {
        cryptName = nameEdit.getText().toString();
        String readyurl = "https://min-api.cryptocompare.com/data/price?fsym=" + cryptName + "&tsyms=USD,RUB";

        try {
            new GetURLData().execute(readyurl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class GetURLData extends AsyncTask<String, String, String> {


        protected void onPreExecute() {
            super.onPreExecute();
            //вьюшка для результата
            status.setText("Загружаю данные");
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null)
                    buffer.append(line).append("\n");

                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null)
                    connection.disconnect();

                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject jsonObject = new JSONObject(result);
                status.setText("");
                resultView.setText(cryptName + ":" + "" +jsonObject.getString("USD") + "" + "USD;" + jsonObject.getString("RUB") + "" + "RUB;");
            } catch (Exception e) {

            }
        }
    }
}