package com.example.employeedetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText employee_name, employee_age, employee_tech_skill, employee_contact;
    Button add_record, view_record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        employee_name = findViewById(R.id.name);
        employee_age = findViewById(R.id.age);
        employee_tech_skill = findViewById(R.id.tech_skill);
        employee_contact = findViewById(R.id.contact);

        add_record = findViewById(R.id.add_button);
        view_record = findViewById(R.id.view_button);

        view_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), ViewEmployees.class);
                startActivity(intent);
            }
        });
        add_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
    }

    public void insert()
    {
        try
        {
            String name = employee_name.getText().toString();
            String age = employee_age.getText().toString();
            String tech_skill = employee_tech_skill.getText().toString();
            String contact = employee_contact.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("EmployeeDb", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS employee_details(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,age VARCHAR,tech_skill VARCHAR, contact VARCHAR)");

            String sql = "insert into employee_details(name, age, tech_skill, contact)values(?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,name);
            statement.bindString(2,age);
            statement.bindString(3,tech_skill);
            statement.bindString(4,contact);
            statement.execute();
            Toast.makeText(this,"Record added",Toast.LENGTH_LONG).show();

            employee_name.setText("");
            employee_age.setText("");
            employee_tech_skill.setText("");
            employee_contact.setText("");
            employee_name.requestFocus();
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Record insert Failed",Toast.LENGTH_LONG).show();
        }
    }
}