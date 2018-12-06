package com.example.jorge.contactmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class ContactView extends AppCompatActivity implements Serializable {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_view);
        Intent i = getIntent();

        Contact contact = (Contact)i.getSerializableExtra("Contact");
        configureTextFields(contact);
        configureGoBackButton();
        configureDeleteButton(contact);

    }





    private void configureDeleteButton(final Contact contact) {
        Button btnDelete = (Button) findViewById(R.id.deleteBtn);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                new DeleteContact().execute(contact.getId());
                                startActivity(new Intent(ContactView.this, ListActivity.class));
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Are you sure you want to delete this contact?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();







            }
        });
    }

    public void configureTextFields(Contact contact) {

        TextView name = (TextView) findViewById(R.id.contactViewName);
        name.setText(contact.getFirstName() + " " + contact.getLastName());
        TextView phone = (TextView) findViewById(R.id.contactViewPhone);
        phone.setText(contact.getPhoneNumber());
        TextView email = (TextView) findViewById(R.id.contactViewEmail);
        email.setText(contact.getEmail());
        TextView address = (TextView) findViewById(R.id.contactViewAddress);
        address.setText(contact.getAddress());

    }

    public void configureGoBackButton()
    {
        Button btnGoBack = (Button) findViewById(R.id.backBtn);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContactView.this, ListActivity.class));
            }
        });
    }
}
