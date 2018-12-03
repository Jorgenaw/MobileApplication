package com.example.jorge.contactmanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ContactList implements Serializable {

    private ArrayList<Contact> contactList = new ArrayList<Contact>();


    public ContactList(){

    }


    public Iterator getContactListIt() {
        Iterator<Contact> it = contactList.iterator();
        return it;
    }

    public void add(Contact contact){
        contactList.add(contact);
    }

    public Contact get(int position){
        Contact contact = contactList.get(position);

        return contact;
    }

    public List getList(){
        List list = Collections.unmodifiableList(contactList);

        return list;
    }
}
