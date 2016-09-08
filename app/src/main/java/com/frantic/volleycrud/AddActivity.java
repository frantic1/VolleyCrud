package com.frantic.volleycrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {
    EditText name,address,faculty,phone,email;
    Button btn;
    RequestQueue requestQueue;
    StringRequest request;
    private static final String url="http://ip_address/student/insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        name = (EditText) findViewById(R.id.ed_add_name);
        address = (EditText) findViewById(R.id.ed_add_address);
        faculty = (EditText) findViewById(R.id.ed_add_faculty);
        phone = (EditText) findViewById(R.id.ed_add_phone);
        email = (EditText) findViewById(R.id.ed_add_email);
        btn= (Button) findViewById(R.id.btn_add);
        requestQueue = Volley.newRequestQueue(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData(name.getText().toString(),address.getText().toString(),faculty.getText().toString(),
                        phone.getText().toString(),email.getText().toString());
                setResult(RESULT_OK);
                finish();

            }
        });


    }

    private void addData(final String name, final String address, final String faculty, final String phone, final String email) {
        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.length()>0){

                    Log.d("addResponse",response);
                    Toast.makeText(AddActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("name",name);
                params.put("faculty",faculty);
                params.put("email",email);
                params.put("address",address);
                params.put("phone",phone);
                return params;
            }
        };
        requestQueue.add(request);

    }
}
