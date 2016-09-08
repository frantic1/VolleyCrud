package com.frantic.volleycrud;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Student> studentList;
    StringRequest request;
    RequestQueue requestQueue;
    ListView listView;
    ProgressBar bar;
    StudentAdapter adapter;
    private static final String url="http://ip_address/student/students.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lister);
        bar = (ProgressBar) findViewById(R.id.progress);
        bar.setVisibility(View.VISIBLE);
        studentList= new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddActivity.class);
                startActivityForResult(intent,1);
            }
        });

        volleyget();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student=studentList.get(position);
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("name",student.getName());
                intent.putExtra("address",student.getAddress());
                intent.putExtra("faculty",student.getFaculty());
                intent.putExtra("phone",student.getPhone());
                intent.putExtra("email",student.getEmail());
                startActivity(intent);
            }
        });



    }

    private void volleyget() {
        request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response",response);
                try {
                    bar.setVisibility(View.GONE);
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("student");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject =array.getJSONObject(i);
                        Student student = new Student();
                        student.setName(jsonObject.getString("name"));
                        student.setFaculty(jsonObject.getString("faculty"));
                        student.setAddress(jsonObject.getString("address"));
                        student.setEmail(jsonObject.getString("email"));
                        student.setPhone(jsonObject.getString("phone"));
                        studentList.add(student);
                    }
                    adapter = new StudentAdapter(MainActivity.this, R.layout.list_item,studentList);
                    listView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if (resultCode==RESULT_OK){
                studentList.clear();
                volleyget();
                //adapter.notifyDataSetChanged();
            }
        }
    }
}
