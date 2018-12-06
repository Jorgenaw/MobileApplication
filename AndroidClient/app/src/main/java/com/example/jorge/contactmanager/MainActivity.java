package com.example.jorge.contactmanager;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    EditText firstNameTxt, lastNameTxt, phoneTxt, emailTxt, addressTxt;
    ImageView contactImageImgView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        configureGoBackButton();
        configureTextFields();
        configureAddContactButton();

        contactImageImgView = (ImageView) findViewById(R.id.imgViewContactImage);

        contactImageImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), 1);
            }
        });
    }


    public void onActivityResult(int reqCode, int resCode, Intent data)
    {
        if (resCode == RESULT_OK) {
            if (reqCode == 1)
                contactImageImgView.setImageURI(data.getData());
        }
    }

    public void configureGoBackButton()
    {
        Button btnGoBack = (Button) findViewById(R.id.goBackButton);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListActivity.class));
            }
        });
    }

    public void configureTextFields()
    {
        firstNameTxt = (EditText) findViewById(R.id.txtFName);
        lastNameTxt = (EditText) findViewById(R.id.txtLName);
        phoneTxt = (EditText) findViewById(R.id.txtPhone);
        emailTxt = (EditText) findViewById(R.id.txtEmail);
        addressTxt = (EditText) findViewById(R.id.txtAddress);
    }

    public void configureAddContactButton()
    {
        final Button btnAddContact = (Button) findViewById(R.id.addContactBtn);

        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addContact(firstNameTxt.getText().toString(), lastNameTxt.getText().toString(), phoneTxt.getText().toString(), emailTxt.getText().toString(), addressTxt.getText().toString() );
                Toast.makeText(getApplicationContext(), firstNameTxt.getText().toString() + " has been added to your contacts", Toast.LENGTH_SHORT).show();
            }
        });


        firstNameTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnAddContact.setEnabled(!firstNameTxt.getText().toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) { }

        });

        lastNameTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnAddContact.setEnabled(!lastNameTxt.getText().toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    public void addContact(String fName, String lName, String phone, String email, String address) {

        Contact contact = new Contact(fName, lName, phone, email, address);


        List<Contact> contactList = ListActivity.contactList.getList();
        long highNum = 0;

        Iterator<Contact> it = contactList.iterator();

        while(it.hasNext()){
            highNum = it.next().getId() + 1;

        }


        Log.i("SimenId", Long.toString(highNum));
        contact.setId(highNum);
        String json = new Gson().toJson(contact);

        try {
            new SendContactInfo().execute(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO send contact to server
    }
}
