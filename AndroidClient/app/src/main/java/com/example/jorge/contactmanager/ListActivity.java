package com.example.jorge.contactmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class ListActivity extends AppCompatActivity implements Serializable{

    private ContactList contactList;
    ListView contactListView;
    List<Contact> sortedContactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        configureAddContactBtn();
        createContactList();
        populateListTest();
        sortedContactList = contactList.getList();
        contactListView = (ListView) findViewById(R.id.contactList);
        contactListView.setOnItemClickListener(listClick);


        populateList();

    }

    private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener () {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent (ListActivity.this, ContactView.class);

            Contact contact = sortedContactList.get(position);


            intent.putExtra("Contact", (Serializable) contact);

            startActivity(intent);
            finish();
        }
    };





    private void populateList(){
        ArrayAdapter<Contact> adapter = new ContactListAdapter();
        contactListView.setAdapter(adapter);
    }


    private class ContactListAdapter extends ArrayAdapter<Contact> {

        public ContactListAdapter() {
            super (ListActivity.this, R.layout.activity_list_item, contactList.getList());
        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null){
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



    public void configureAddContactBtn()
    {
        Button btnAddContact = (Button) findViewById(R.id.addContactBtn);
        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListActivity.this, MainActivity.class));
            }
        });
    }

    public void createContactList(){
        if(contactList == null){
            contactList = new ContactList();
        }
        //TODO fill contact list
    }

    public void populateListTest(){


        for(int i = 0; i < 5; i++) {

            Contact jorgen = new Contact(
                    "Jørgen",
                    "Wærås",
                    "98005125",
                    "jorgenaw@hotmail.com",
                    "Berg erikstien 10"
            );
            contactList.add(jorgen);

            Contact simen = new Contact(
                    "Simen",
                    "Grøvdal",
                    "98082793",
                    "sim2901@hotmail.com",
                    "Paulineplassen 10"
            );
            contactList.add(simen);
        }
    }
}