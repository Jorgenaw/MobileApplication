package com.example.jorge.contactmanager;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





public final class HTTPhandler {

    static URL url;

    static {
        try {
            url = new URL("http://192.168.1.139:8080/ContactManagerServer/webresources/com.mycompany.contactmanagerserver.contact");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    static HttpURLConnection con;



    public HTTPhandler() throws IOException {



    }

    public List getContactList() throws IOException {
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        List contacts = new ArrayList<Contact>();
        //TODO add HTTP
        return contacts;
    }

    public static void addContact(Contact contact) throws IOException {
        contact.setId(10);

         Gson gson = new Gson();


                try {


                    String json = gson.toJson(contact);
                    String jsonEncoded = URLEncoder.encode(json, "UTF-8");
                    int jsonLength = json.length();


                    con = (HttpURLConnection) url.openConnection();
                    //con.connect();
                    con.setDoOutput(true);
                    con.setInstanceFollowRedirects(false);
                    con.setRequestMethod("POST");
                    con.setRequestProperty("charset", "utf-8");
                    con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    con.setRequestProperty("Accept", "application/json");
                    con.setRequestProperty("Content-Length", Integer.toString(jsonLength));
                    con.setDoOutput(true);
                    con.setDoInput(true);


                    DataOutputStream os = new DataOutputStream(con.getOutputStream());
                    os.writeBytes(json);
                    os.flush();
                    os.close();
                    con.disconnect();
                } catch (Exception e) {


                }


    }

}
