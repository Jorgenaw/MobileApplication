package com.example.jorge.contactmanager;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class HTTPhandler {

    static URL url;

    static {
        try {
            url = new URL("127.0.0.1");
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
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        Map<String, String> parameters = new HashMap<>();
        parameters.put("param1", contact.getFirstName());
        parameters.put("param2", contact.getLastName());
        parameters.put("param3", contact.getPhoneNumber());
        parameters.put("param4", contact.getEmail());
        parameters.put("param5", contact.getAddress());

        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
        out.flush();
        out.close();
        con.disconnect();

    }

}
