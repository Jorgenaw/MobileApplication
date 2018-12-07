package com.example.jorge.contactmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
        configureDialPhone(contact);

    }


    private void configureDialPhone(final Contact contact) {
        findViewById(R.id.contactViewPhone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                dialContactPhone(contact.getPhoneNumber());
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Call " + contact.getFirstName() + " ?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();

            }
        });
    }

    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
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
