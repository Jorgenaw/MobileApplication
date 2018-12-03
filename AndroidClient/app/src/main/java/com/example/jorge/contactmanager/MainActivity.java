package com.example.jorge.contactmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText firstNameTxt, lastNameTxt, phoneTxt, emailTxt, addressTxt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        configureGoBackButton();
        configureTextFields();
        configureAddContactButton();




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

    public void addContact(String fName, String lName, String phone, String email, String address){



        //TODO send contact to server
    }
}
