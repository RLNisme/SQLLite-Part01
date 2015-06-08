package com.SQL_Lite;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MyActivity extends Activity implements OnClickListener{

    String fname,lname,email;
    EditText fn,ln,em;
    Button in,vi;
    SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        fn=(EditText)findViewById(R.id.fn);
        ln=(EditText)findViewById(R.id.ln);
        em=(EditText)findViewById(R.id.em);

        in=(Button)findViewById(R.id.in);
        in.setOnClickListener(this);

        vi=(Button)findViewById(R.id.vi);
        vi.setOnClickListener(this);

        db= openOrCreateDatabase("MYDB1",MODE_PRIVATE,null);  //this method will  new DB but if there is a DB call "MyDB1" then it will take that DB
                                                              // "MODE_PRIVATE" means it only visible to this activity
        db.execSQL("CREATE TABLE IF NOT EXISTS student(fname VARCHAR,lname VARCHAR,email VARCHAR);");//this method where we write query

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.in){
            inserData();
        }else if(v.getId()==R.id.vi){
            viewData();
        }
    }

    public void inserData(){
        fname = fn.getText().toString();
        lname = ln.getText().toString();
        email = em.getText().toString();

        db.execSQL("INSERT INTO student VALUES('"+fname+"','"+lname+"','"+email+"');");
        Log.d("--Msg--","Data inserted");

    }

    public void viewData() {
        Cursor c = db.rawQuery("SELECT * FROM student",null);// This cursor helps to get Data from the SQLLite DB
        int count = c.getCount(); //this method gives total row count
        c.moveToFirst(); // helps to select position to start

        for (int i=0;i<count;i++){

            int fnIndex = c.getColumnIndex("fname");
            int lnIndex = c.getColumnIndex("lname");
            int emIndex = c.getColumnIndex("email");

            String fname = c.getString(fnIndex);
            String lname = c.getString(lnIndex);
            String email = c.getString(emIndex);

            Log.d("D-"+i,"( "+fname+" || "+lname+" || "+email+" || )");

            c.moveToNext(); // this method move the cursor to the next row

        }

    }
}
