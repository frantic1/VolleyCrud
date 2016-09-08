package com.frantic.volleycrud;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView name,add,faculty,phone,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        name = (TextView) findViewById(R.id.detail_name);
        add = (TextView) findViewById(R.id.detail_address);
        faculty = (TextView) findViewById(R.id.detail_faculty);
        phone = (TextView) findViewById(R.id.detail_phone);
        email = (TextView) findViewById(R.id.detail_email);

        final Intent intent = getIntent();
        name.setText("Name:    "+intent.getStringExtra("name"));
        add.setText("Address:  "+intent.getStringExtra("address"));
        faculty.setText("Faculty:  "+intent.getStringExtra("faculty"));
        phone.setText("Phone:    "+intent.getStringExtra("phone"));
        email.setText("Email:    "+intent.getStringExtra("email"));


        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(DetailActivity.this);
                alert.setTitle("Make a Call?")
                        .setMessage("Do you want to make a call?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                                intent1.setData(Uri.parse("tel:"+intent.getStringExtra("phone")));
                                startActivity(intent1);
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alert.setCancelable(true);
                            }
                        });
                alert.create();
                alert.show();
            }
        });
    }
}
