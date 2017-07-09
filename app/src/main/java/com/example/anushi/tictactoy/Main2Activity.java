package com.example.anushi.tictactoy;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import static com.example.anushi.tictactoy.R.id.menu_logout;


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
        item.setIcon(R.drawable.home);
        switch (item.getItemId()) {
            case R.id.menu_about:{
                Intent i= new Intent(Main2Activity.this,About.class);
                startActivity(i);
            }
                return true;
            case R.id.menu_settings:
                Toast.makeText(Main2Activity.this,"No settings to show",Toast.LENGTH_LONG).show();
                return true;
            case menu_logout:{
                Toast.makeText(Main2Activity.this,"Logged out",Toast.LENGTH_LONG).show();
                logout();
            }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void logout(){
        FirebaseAuth.getInstance().signOut();
    }
}
