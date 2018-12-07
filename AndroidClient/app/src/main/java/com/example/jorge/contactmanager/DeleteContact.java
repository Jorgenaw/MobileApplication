package com.example.jorge.contactmanager;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DeleteContact extends AsyncTask<Long, Void, Void> {


    private String URL_LINK = "http://192.168.1.139:8080/ContactManagerServer/webresources/com.mycompany.contactmanagerserver.contact/";

    @Override
    protected Void doInBackground(Long... longs) {

        URL url;
        HttpURLConnection con;
        Long id = longs[0];

        try{
            url = new URL(URL_LINK + Long.toString(id));
            con = (HttpURLConnection) url.openConnection();
            //con.getDoInput(true);
            con.setRequestMethod("DELETE");
            con.setUseCaches(false);
            con.connect();
            Log.i("Simendelete", "Http request init");
            int responseCode = con.getResponseCode();
            Log.i("Simen", "Response code: " + Integer.toString(responseCode));

            con.disconnect();






        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
