package com.example.testpurpose;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    Database mydb;
    TextView tv;
    EditText t1,t2,t3,t4;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        tv=findViewById(R.id.tv);
        mydb=new Database(this);
        tv.setShadowLayer(30,0,0, Color.GREEN);
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        t4=findViewById(R.id.t4);

    }
    public void AddData(View view)
    {
        boolean isInserted=mydb.insertData(t1.getText().toString(),t2.getText().toString(),t3.getText().toString());
        if(isInserted==true)
            Toast.makeText(this,"Successfully Registered",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Some Error Occured",Toast.LENGTH_SHORT).show();
        t1.setText("");
        t2.setText("");
        t3.setText("");
    }
    public void fetch(View view)
    {
        Cursor result=mydb.getAllData();
        if(result.getCount()==0)
            showMessage("Error","No Data");
        else
        {
            String data="";
            while(result.moveToNext())
            {
                data=data+"ID ; "+ result.getString(0)+"\n"+"Name : "+result.getString(1)+"\n"+"Branch : "+result.getString(2)+"\n"+"Gender : "+result.getString(3)+"\n\n";
            }
            showMessage("Data",data);
        }
    }
    public void showMessage(String title,String Message)
    {
        AlertDialog.Builder ab=new AlertDialog.Builder(this);
        ab.setCancelable(true);
        ab.setTitle(title);
        ab.setMessage(Message);
        ab.show();
    }
    public void update(View view)
    {
        boolean isUpdate=mydb.updateData(t4.getText().toString(),t1.getText().toString(),t2.getText().toString(),t3.getText().toString());
        if(isUpdate==true)
            Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Not Updated", Toast.LENGTH_SHORT).show();
    }
    public void delete(View view)
    {
        int deleterows=mydb.deleteData(t4.getText().toString());
        if(deleterows>0)
            Toast.makeText(this, "Data Deleted", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
    }
    public void deleteAll(View view)
    {
        mydb.deleteAllData();
    }

}
