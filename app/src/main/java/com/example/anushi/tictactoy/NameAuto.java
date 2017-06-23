package com.example.anushi.tictactoy;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

public class NameAuto extends AppCompatActivity {

    Button b;
    TextView tv;
    EditText et;
    TableLayout tl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_auto);

        b = (Button)findViewById(R.id.b);
        tv = (TextView)findViewById(R.id.tv);
        et = (EditText)findViewById(R.id.et);
        tl = (TableLayout)findViewById(R.id.tl);
    }



    public void buClick(View view){

        String p1=et.getText().toString();
        Intent intent=new Intent(getApplicationContext(),Auto.class);
        intent.putExtra("player1",p1);
        startActivity(intent);

    }
}
