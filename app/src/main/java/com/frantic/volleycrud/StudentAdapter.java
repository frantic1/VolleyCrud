package com.frantic.volleycrud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Frantic on 9/7/2016.
 */
public class StudentAdapter extends ArrayAdapter<Student> {
    Context context;
    List<Student> studentList;
    public StudentAdapter(Context context, int resource, List<Student> objects) {
        super(context, resource, objects);
        this.context=context;
        this.studentList=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_item,parent,false);
        TextView name,fac,add;
        name = (TextView) convertView.findViewById(R.id.name);
        fac = (TextView) convertView.findViewById(R.id.faculty);
        add = (TextView) convertView.findViewById(R.id.address);
        Student student = studentList.get(position);
        name.setText(student.getName());
        fac.setText(student.getFaculty());
        add.setText(student.getAddress());
        return convertView;
    }
}
