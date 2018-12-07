package com.example.jorge.contactmanager;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListActivity extends AppCompatActivity implements Serializable {

    public static ContactList contactList;
    ListView contactListView;
    List<Contact> sortedContactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);



        configureAddContactBtn();
        createContactList();
        //populateListTest();
        sortedContactList = contactList.getList();
        contactListView = (ListView) findViewById(R.id.contactList);
        contactListView.setOnItemClickListener(listClick);

        refreshList();
        populateList();

    }



    private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, ContactView.class);

                Contact contact = contactList.get(position);


                intent.putExtra("Contact", (Serializable) contact);

                startActivity(intent);
                finish();
            }
        };


        private void populateList () {
            ArrayAdapter<Contact> adapter = new ContactListAdapter();
            contactListView.setAdapter(adapter);
        }


        private class ContactListAdapter extends ArrayAdapter<Contact> {

            public ContactListAdapter() {
                super(ListActivity.this, R.layout.activity_list_item, contactList.getList());
            }

            @Override
            public View getView(int position, View view, ViewGroup parent) {
                if (view == null) {
                    view = getLayoutInflater().inflate(R.layout.activity_list_item, parent, false);

                    Contact currentContact = contactList.get(position);

                    TextView name = (TextView) view.findViewById(R.id.contactViewName);
                    name.setText(currentContact.getFirstName() + " " + currentContact.getLastName());
                    TextView phone = (TextView) view.findViewById(R.id.contactViewPhone);
                    phone.setText(currentContact.getPhoneNumber());
                    TextView email = (TextView) view.findViewById(R.id.contactViewEmail);
                    email.setText(currentContact.getEmail());
                    TextView address = (TextView) view.findViewById(R.id.contactViewAddress);
                    address.setText(currentContact.getAddress());

                }


                return view;
            }


        }


        public void configureAddContactBtn ()
        {
            Button btnAddContact = (Button) findViewById(R.id.addContactBtn);
            btnAddContact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ListActivity.this, MainActivity.class));
                }
            });
        }

        public void createContactList () {
            if (contactList == null) {
                contactList = new ContactList();
            }
            //TODO fill contact list
        }



        public void refreshList(){
            String jsonString = null;
            GetContactList httpGet;
            try {
                httpGet = new GetContactList();
                jsonString = httpGet.execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Log.i("Simengson", jsonString);

            Gson gson = new Gson();



            TypeToken<List<Contact>> token = new TypeToken<List<Contact>>() {};
            List<Contact> gotContactList = gson.fromJson(jsonString, token.getType());


            contactList = new ContactList();

            for(Contact contact: gotContactList){
                contactList.add(contact);
            }


        }


    }

