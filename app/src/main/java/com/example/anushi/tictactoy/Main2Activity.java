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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_about:{
                Intent i= new Intent(Main2Activity.this,About.class);
                startActivity(i);
            }
                return true;
            case R.id.menu_settings:
                return true;
            case R.id.menu_logout:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
