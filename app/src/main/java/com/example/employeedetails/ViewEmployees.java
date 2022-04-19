package com.example.employeedetails;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ViewEmployees extends AppCompatActivity {
    ListView listView;
    ArrayList<String> employeeList = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        SQLiteDatabase db = openOrCreateDatabase("EmployeeDb", Context.MODE_PRIVATE,null);

        listView = findViewById(R.id.lst1);
        final Cursor c = db.rawQuery("select * from employee_details",null);
        int id = c.getColumnIndex("id");
        int name = c.getColumnIndex("name");
        int age = c.getColumnIndex("age");
        int tech_skill = c.getColumnIndex("tech_skill");
        int contact = c.getColumnIndex("contact");
        employeeList.clear();

        arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, employeeList);
        listView.setAdapter(arrayAdapter);

        final  ArrayList<Employee> stud = new ArrayList<Employee>();

        if(c.moveToFirst())
        {
            do{
                Employee stu = new Employee();
                stu.id = c.getString(id);
                stu.name = c.getString(name);
                stu.age = c.getString(age);
                stu.tech_skill = c.getString(tech_skill);
                stu.contact = c.getString(contact);
                stud.add(stu);

                employeeList.add(c.getString(id) + "\t" + c.getString(name) + " \t "  + c.getString(age) + " \t\t "  + c.getString(tech_skill) + " \t\t " + c.getString(contact));

            }
            while(c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            listView.invalidateViews();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aa = employeeList.get(position).toString();
                Employee stu = stud.get(position);
                Intent intent = new Intent(getApplicationContext(),edit.class);
                intent.putExtra("id",stu.id);
                intent.putExtra("name",stu.name);
                intent.putExtra("age",stu.age);
                intent.putExtra("tech_skill",stu.tech_skill);
                intent.putExtra("contact",stu.contact);
                startActivity(intent);
            }
        });
    }
}
