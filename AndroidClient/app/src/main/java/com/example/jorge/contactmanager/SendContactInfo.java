package com.example.jorge.contactmanager;

import android.os.AsyncTask;
import android.util.Log;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;






public class SendContactInfo extends AsyncTask<String, Void, String> {

    private URL url;
    private String json;




    public SendContactInfo() throws IOException {


    }




    @Override
    protected String doInBackground(String... json) {


        String data = "";

        HttpURLConnection con = null;
        Log.i("Simenpost", json[0]);


        try {

            url = new URL("http://192.168.1.139:8080/ContactManagerServer/webresources/com.mycompany.contactmanagerserver.contact");
            con = (HttpURLConnection) url.openConnection();
            //con.setReadTimeout(60000 /* milliseconds */);
            //con.setConnectTimeout(60000 /* milliseconds */);

            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");

            Log.i("Simenpost", "HTTP request init");
            /*
            con.setInstanceFollowRedirects(false);
            con.setRequestProperty("charset", "utf-8");


            con.setDoInput(true);
            */
            //
            con.connect();
            OutputStream os = con.getOutputStream();
            Log.i("Simen", "outputstream init");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            Log.i("Simen", "Writer init");
            writer.write(json[0]);

            writer.flush();
            writer.close();
            os.close();

            Log.i("Simen", "os closed");

            int responseCode=con.getResponseCode();

            Log.i("Simen", Integer.toString(responseCode));

            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                con.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line = "";

                while ((line = in.readLine()) != null) {

                    sb.append(line);
                    break;
                }
                Log.e("Simen: ", sb.toString());
                in.close();
            }

            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }


        }
        Log.e("tag", data);
        return data;

    }
}
