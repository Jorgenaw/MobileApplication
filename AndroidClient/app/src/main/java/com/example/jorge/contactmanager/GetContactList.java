package com.example.jorge.contactmanager;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetContactList extends AsyncTask<String, Void, String> {

    private URL url;
    private String json;


    @Override
    protected String doInBackground(String... strings) {

        HttpURLConnection con = null;
        StringBuffer response = null;
        StringBuilder stringBuilder = new StringBuilder();

        try{
            url = new URL("http://192.168.1.139:8080/ContactManagerServer/webresources/com.mycompany.contactmanagerserver.contact");
            con = (HttpURLConnection) url.openConnection();
            //con.getDoInput(true);
            con.setRequestMethod("GET");
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type","application/json; charset=utf-8");
            con.setRequestProperty("Accept","application/json; charset=utf-8");
            con.connect();
            Log.i("Simenget", "Http request init");
            int responseCode = con.getResponseCode();
            Log.i("Simen", "Response code: " + Integer.toString(responseCode));

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null){
                stringBuilder.append(inputLine);
            }
            in.close();

            Log.i("Simen", "Data: " + stringBuilder.toString());






        }catch (Exception e){
            e.printStackTrace();
        }








        return stringBuilder.toString();
    }
}
