package com.example.anushi.tictactoy;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class NameAuto extends AppCompatActivity {

    Button b;
    TextView tv;
    AutoCompleteTextView et;
    TableLayout tl;
    String[] name={"Anushi ","Tushar","Anubhav","Dhruv","Kusum","Angel","Yash","Tanu","Yogesh","Shreya","Abhishek",
    "Aditya","Anirudh","Ashutosh","Amit","Mansi","Charu","Ritika","Tanisha","Sanjiv","Gaurav","Garima","Pooja","Rupali","Himanshu",
    "Ayush","Shivam"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_auto);

        b = (Button)findViewById(R.id.b);
        tv = (TextView)findViewById(R.id.tv);
        et = (AutoCompleteTextView) findViewById(R.id.et);
        tl = (TableLayout)findViewById(R.id.tl);

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,name);
        et.setAdapter(adapter);
        et.setThreshold(1);
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
                tl.setBackgroundColor(Color.parseColor("#90A4AE"));
                et.setTextColor(Color.parseColor("#B71C1C"));
                tv.setTextColor(Color.parseColor("#B71C1C"));
                b.setBackgroundColor(Color.parseColor("#B71C1C"));
                b.setTextColor(Color.parseColor("#000000"));
                return true;
            case R.id.t2:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundColor(Color.parseColor("#D7CCC8"));
                et.setTextColor(Color.parseColor("#795548"));
                tv.setTextColor(Color.parseColor("#795548"));
                b.setBackgroundColor(Color.parseColor("#795548"));
                b.setTextColor(Color.parseColor("#FFFF00"));
                return true;
            case R.id.t3:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundColor(Color.parseColor("#E6EE9C"));
                et.setTextColor(Color.parseColor("#009688"));
                tv.setTextColor(Color.parseColor("#009688"));
                b.setBackgroundColor(Color.parseColor("#009688"));
                b.setTextColor(Color.parseColor("#ffffff"));
                return true;

            case R.id.t4:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundColor(Color.parseColor("#F48FB1"));
                et.setTextColor(Color.parseColor("#880E4F"));
                tv.setTextColor(Color.parseColor("#880E4F"));
                b.setBackgroundColor(Color.parseColor("#880E4F"));
                b.setTextColor(Color.parseColor("#000000"));
                return true;

            case R.id.t5:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundColor(Color.parseColor("#EEEEEE"));
                et.setTextColor(Color.parseColor("#4A148C"));
                tv.setTextColor(Color.parseColor("#4A148C"));
                b.setBackgroundColor(Color.parseColor("#4A148C"));
                b.setTextColor(Color.parseColor("#FFFFFF"));
                return true;

            case R.id.home:
                finish();
                return true;

            case  R.id.th1:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th1);
                et.setTextColor(Color.parseColor("#000000"));
                tv.setTextColor(Color.parseColor("#000000"));
                b.setBackgroundColor(Color.parseColor("#4A148C"));
                b.setTextColor(Color.parseColor("#FFFFFF"));
                return true;

            case  R.id.th2:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th2);
                et.setTextColor(Color.parseColor("#FFFFFF"));
                tv.setTextColor(Color.parseColor("#FFFFFF"));
                b.setBackgroundColor(Color.parseColor("#4A148C"));
                b.setTextColor(Color.parseColor("#FFFFFF"));
                return true;

            case  R.id.th3:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th3);
                et.setTextColor(Color.parseColor("#880E4F"));
                tv.setTextColor(Color.parseColor("#880E4F"));
                b.setBackgroundColor(Color.parseColor("#4A148C"));
                b.setTextColor(Color.parseColor("#FFFFFF"));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void buClick(View view){

        String p1=et.getText().toString();
        Intent intent=new Intent(getApplicationContext(),Auto.class);
        intent.putExtra("player1",p1);
        startActivity(intent);

    }

}
