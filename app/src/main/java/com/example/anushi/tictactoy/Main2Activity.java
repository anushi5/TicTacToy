package com.example.anushi.tictactoy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    Button auto,two,online,profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        auto = (Button) findViewById(R.id.bauto);
        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main2Activity.this, NameAuto.class);
                startActivity(i);
            }
        });
        two = (Button) findViewById(R.id.btwo);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main2Activity.this, NameTwo.class);
                startActivity(i);
            }
        });

        online = (Button)findViewById(R.id.bonline);
        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main2Activity.this, Login.class);
                startActivity(i);
            }
        });

        profile = (Button)findViewById(R.id.bprofile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent i = new Intent(Main2Activity.this, ProfileAct.class);
                startActivity(i);
            }
        });
    }
}
