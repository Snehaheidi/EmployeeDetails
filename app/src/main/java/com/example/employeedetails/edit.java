package com.example.employeedetails;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class edit extends AppCompatActivity
{
    EditText emp_id,emp_name, emp_age, tech_skill, contact;
    Button edit, delete, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        emp_id = findViewById(R.id.id);
        emp_name = findViewById(R.id.name);
        emp_age = findViewById(R.id.age);
        tech_skill = findViewById(R.id.tech_skill);
        contact = findViewById(R.id.contact);

        edit = findViewById(R.id.edit_button);
        delete = findViewById(R.id.delete_button);
        back = findViewById(R.id.back_button);

        Intent intent = getIntent(); // one page to another page call values

        String t1 = intent.getStringExtra("id").toString();
        String t2 = intent.getStringExtra("name").toString();
        String t3 = intent.getStringExtra("age").toString();
        String t4 = intent.getStringExtra("tech_skill").toString();
        String t5 = intent.getStringExtra("contact").toString();

        emp_id.setText(t1);
        emp_name.setText(t2);
        emp_age.setText(t3);
        tech_skill.setText(t4);
        contact.setText(t5);

        // Delete
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete();
            }
        });

        //back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ViewEmployees.class);
                startActivity(intent);

            }
        });

        //edit
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edit();
            }
        });

    }

    public void Delete()
    {
        try
        {
            String id = emp_id.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("EmployeeDb",Context.MODE_PRIVATE,null);

            String sql = "delete from employee_details where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1,id);
            statement.execute();
            Toast.makeText(this,"Record Deleted",Toast.LENGTH_LONG).show();

            emp_id.setText("");
            emp_name.setText("");
            emp_age.setText("");
            tech_skill.setText("");
            contact.setText("");
            emp_name.requestFocus();
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Record Delete Fail",Toast.LENGTH_LONG).show();
        }
    }
    public void Edit()
    {
        try
        {
            String Id = emp_id.getText().toString();
            String name = emp_name.getText().toString();
            String age = emp_age.getText().toString();
            String tech = tech_skill.getText().toString();
            String Contact = contact.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("EmployeeDb", Context.MODE_PRIVATE,null);
            String sql = "update employee_details set name = ?,age=?,tech_skill=?,contact=? where id= ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1,name);
            statement.bindString(2,age);
            statement.bindString(3,tech);
            statement.bindString(4,Contact);
            statement.bindString(5,Id);
            statement.execute();
            Toast.makeText(this,"Record Updated.",Toast.LENGTH_LONG).show();

            emp_name.setText("");
            emp_age.setText("");
            tech_skill.setText("");
            contact.setText("");
            emp_name.requestFocus();
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Record Fail",Toast.LENGTH_LONG).show();
        }

    }

}
