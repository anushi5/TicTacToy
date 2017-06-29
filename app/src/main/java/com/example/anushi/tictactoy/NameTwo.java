package com.example.anushi.tictactoy;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import static android.R.attr.itemBackground;
import static android.R.attr.name;

public class NameTwo extends AppCompatActivity {

    AutoCompleteTextView et1,et2;
    TextView tv;
    TableLayout tl;
    Button b1;
    String[] name={"Anushi ","Tushar","Anubhav","Dhruv","Kusum","Angel","Yash","Tanu","Yogesh","Shreya","Abhishek",
            "Aditya","Anirudh","Ashutosh","Amit","Mansi","Charu","Ritika","Tanisha","Sanjiv","Gaurav","Garima","Pooja","Rupali","Himanshu",
            "Ayush","Shivam"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_two);

        et1 = (AutoCompleteTextView) findViewById(R.id.et1);
        et2 = (AutoCompleteTextView)findViewById(R.id.et2);
        tv = (TextView)findViewById(R.id.tv);
        tl=(TableLayout) findViewById(R.id.tl);
        b1 = (Button)findViewById(R.id.b1);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,name);
        et1.setAdapter(adapter);
        et1.setThreshold(1);
        et2.setAdapter(adapter);
        et2.setThreshold(1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.innermenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.t1:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundColor(Color.parseColor("#9E9E9E"));
                et1.setTextColor(Color.parseColor("#880E4F"));
                et2.setTextColor(Color.parseColor("#880E4F"));
                tv.setTextColor(Color.parseColor("#880E4F"));
                b1.setBackgroundColor(Color.parseColor("#880E4F"));
                b1.setTextColor(Color.parseColor("#ffffff"));
                return true;
            case R.id.t2:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundColor(Color.parseColor("#9C27B0"));
                et1.setTextColor(Color.parseColor("#FBC02D"));
                et2.setTextColor(Color.parseColor("#FBC02D"));
                tv.setTextColor(Color.parseColor("#FBC02D"));
                b1.setBackgroundColor(Color.parseColor("#FBC02D"));
                b1.setTextColor(Color.parseColor("#000000"));
                return true;
            case R.id.t3:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundColor(Color.parseColor("#B2DFDB"));
                et1.setTextColor(Color.parseColor("#E57373"));
                et2.setTextColor(Color.parseColor("#E57373"));
                tv.setTextColor(Color.parseColor("#E57373"));
                b1.setBackgroundColor(Color.parseColor("#E57373"));
                b1.setTextColor(Color.parseColor("#000000"));
                return true;

            case R.id.t4:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundColor(Color.parseColor("#00838F"));
                et1.setTextColor(Color.parseColor("#FFCC80"));
                et2.setTextColor(Color.parseColor("#FFCC80"));
                tv.setTextColor(Color.parseColor("#FFCC80"));
                b1.setBackgroundColor(Color.parseColor("#FFCC80"));
                b1.setTextColor(Color.parseColor("#000000"));
                return true;

            case R.id.t5:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundColor(Color.parseColor("#424242"));
                et1.setTextColor(Color.parseColor("#BDBDBD"));
                et2.setTextColor(Color.parseColor("#BDBDBD"));
                tv.setTextColor(Color.parseColor("#BDBDBD"));
                b1.setBackgroundColor(Color.parseColor("#BDBDBD"));
                b1.setTextColor(Color.parseColor("#000000"));
                return true;

            case R.id.home:
                finish();
                return true;
            case  R.id.th1:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th1);
                tv.setTextColor(Color.parseColor("#000000"));
                return true;

            case  R.id.th2:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th2);
                tv.setTextColor(Color.parseColor("#FFFFFF"));
                return true;

            case  R.id.th3:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th3);
                tv.setTextColor(Color.parseColor("#880E4F"));
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void buClick(View view){

        String p1=et1.getText().toString();
        String p2=et2.getText().toString();
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        intent.putExtra("player1",p1);
        intent.putExtra("player2",p2);
        startActivity(intent);

    }
}
