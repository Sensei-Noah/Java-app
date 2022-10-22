package com.example.javaprojectnojus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;

public class SearchActivity extends AppCompatActivity {
    public static final String API= "https://covid19-api.weedmark.systems/api/v1/stats";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        AsyncFetch asyncFetch = new AsyncFetch();
        asyncFetch.execute();
    }

    private class AsyncFetch extends AsyncTask<String, String, JSONObject>{
        ProgressDialog pdLoading = new ProgressDialog(SearchActivity.this);

        @Override
        protected JSONObject doInBackground(String... params) {
            try {
                JSONObject jsonObject = JSON.readJsonFromUrl(API);
                return jsonObject;
            } catch (JSONException | IOException e1){
                Toast.makeText(
                        SearchActivity.this,
                        getResources().getText(R.string.search_error_reading_data) + e1.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
                return null;
            }

        }


        @Override
        protected void onPostExecute(JSONObject json){
            //this method will be running on UI thread
            pdLoading.dismiss();

            int statusCode= 0;
            try {
                statusCode = (Integer) json.get("statusCode");
            }catch (JSONException e){
                Toast.makeText(
                        SearchActivity.this,
                        getResources().getText(R.string.search_error_reading_data) + e.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
            if(statusCode == HttpURLConnection.HTTP_OK){
                System.err.println(json.toString());
                Toast.makeText(SearchActivity.this, json.toString(), Toast.LENGTH_LONG).show();
            } else { //something went wrong
                String message = null;
                try {
                    message = (String) json.get("message");
                }catch (JSONException e){
                    Toast.makeText(
                            SearchActivity.this,
                            getResources().getText(R.string.search_error_reading_data) + e.getMessage(),
                            Toast.LENGTH_SHORT
                    ).show();
                }
                Toast.makeText(
                        SearchActivity.this,
                        getResources().getText(R.string.search_error_reading_data) + message,
                        Toast.LENGTH_LONG
                ).show();
            }//else
        }//onPostExecute
    }//AsyncFetch class
}