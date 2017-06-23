package com.example.anushi.tictactoy;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import static android.R.attr.name;

public class NameTwo extends AppCompatActivity {

    EditText et1,et2;
    TextView tv;
    TableLayout tl;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_two);

        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);
        tv = (TextView)findViewById(R.id.tv);
         tl=(TableLayout) findViewById(R.id.tl);
        b1 = (Button)findViewById(R.id.b1);
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
