package com.example.anushi.tictactoy;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by anushi on 16/6/17.
 */

public class Auto extends AppCompatActivity {

    TextView tv;
    Button quit;
    int Winner = -1;
    int counts = 0;
    TableLayout tl;
    MediaPlayer mysound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto);

        tv = (TextView) findViewById(R.id.tv);
        Bundle b = getIntent().getExtras();
        String pname1 = b.getString("player1");
        tv.setText(pname1);
        quit = (Button) findViewById(R.id.quit);
        tl = (TableLayout) findViewById(R.id.tl);
    }

    public void buClick(View view) {
        Button buSelected = (Button) view;

        int CellId = 0;
        counts = counts + 1;
        switch ((buSelected.getId())) {

            case R.id.b1:
                CellId = 1;
                mysound=MediaPlayer.create(Auto.this,R.raw.beep15);
                mysound.start();
                break;

            case R.id.b2:
                CellId = 2;
                mysound=MediaPlayer.create(Auto.this,R.raw.beep15);
                mysound.start();
                break;

            case R.id.b3:
                CellId = 3;
                mysound=MediaPlayer.create(Auto.this,R.raw.beep15);
                mysound.start();
                break;

            case R.id.b4:
                CellId = 4;
                mysound=MediaPlayer.create(Auto.this,R.raw.beep15);
                mysound.start();
                break;

            case R.id.b5:
                CellId = 5;
                mysound=MediaPlayer.create(Auto.this,R.raw.beep15);
                mysound.start();
                break;

            case R.id.b6:
                CellId = 6;
                mysound=MediaPlayer.create(Auto.this,R.raw.beep15);
                mysound.start();
                break;

            case R.id.b7:
                CellId = 7;
                mysound=MediaPlayer.create(Auto.this,R.raw.beep15);
                mysound.start();
                break;

            case R.id.b8:
                CellId = 8;
                mysound=MediaPlayer.create(Auto.this,R.raw.beep15);
                mysound.start();
                break;

            case R.id.b9:
                CellId = 9;
                mysound=MediaPlayer.create(Auto.this,R.raw.beep15);
                mysound.start();
                break;


        }
        mysound.release();
        PlayGame(CellId, buSelected);

    }

    int ActivePlayer = 1;
    ArrayList<Integer> Player1 = new ArrayList<Integer>();
    ArrayList<Integer> Player2 = new ArrayList<Integer>();


    void PlayGame(int CellId, Button buSelected) {

        Log.d("Player:", String.valueOf(CellId));

        if (ActivePlayer == 1) {
            buSelected.setBackgroundResource(R.drawable.kaatag);
            Player1.add(CellId);
            ActivePlayer = 2;
            AutoPlay();
        } else if (ActivePlayer == 2) {
            buSelected.setBackgroundResource(R.drawable.zerog);
            Player2.add(CellId);
            ActivePlayer = 1;
        }

        buSelected.setEnabled(false);
        CheckWinner(counts);

    }


    void CheckWinner(int counts) {
        /// row 1
        if (Player1.contains(1) && Player1.contains(2) && Player1.contains(3)) {
            Winner = 1;
        } else if (Player2.contains(1) && Player2.contains(2) && Player2.contains(3)) {
            Winner = 2;
        }

        ///row  2
        else if (Player1.contains(4) && Player1.contains(5) && Player1.contains(6)) {
            Winner = 1;
        } else if (Player2.contains(4) && Player2.contains(5) && Player2.contains(6)) {
            Winner = 2;
        }


        ///row 3
        else if (Player1.contains(7) && Player1.contains(8) && Player1.contains(9)) {
            Winner = 1;
        } else if (Player2.contains(7) && Player2.contains(8) && Player2.contains(9)) {
            Winner = 2;
        }

        ///column 1
        else if (Player1.contains(1) && Player1.contains(4) && Player1.contains(7)) {
            Winner = 1;
        } else if (Player2.contains(1) && Player2.contains(4) && Player2.contains(7)) {
            Winner = 2;
        }

        ///column2
        else if (Player1.contains(2) && Player1.contains(5) && Player1.contains(8)) {
            Winner = 1;
        } else if (Player2.contains(2) && Player2.contains(5) && Player2.contains(8)) {
            Winner = 2;
        }

        ///column 3
        else if (Player1.contains(3) && Player1.contains(6) && Player1.contains(9)) {
            Winner = 1;
        } else if (Player2.contains(3) && Player2.contains(6) && Player2.contains(9)) {
            Winner = 2;
        }

        ///diagonal 1
        else if (Player1.contains(1) && Player1.contains(5) && Player1.contains(9)) {
            Winner = 1;
        } else if (Player2.contains(1) && Player2.contains(5) && Player2.contains(9)) {
            Winner = 2;
        }

        ///diagonal 2
        else if (Player1.contains(3) && Player1.contains(5) && Player1.contains(7)) {
            Winner = 1;
        } else if (Player2.contains(3) && Player2.contains(5) && Player2.contains(7)) {
            Winner = 2;
        }


        if (Winner != -1) {

            if (Winner == 1) {
                String s = tv.getText().toString();
                Toast.makeText(getApplicationContext(), s + " is Winner", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "Device is Winner", Toast.LENGTH_LONG).show();
                finish();
            }
        }
        if (Winner == -1 && counts == 9) {
            Toast.makeText(this, "DRAW", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    void AutoPlay() {

        ArrayList<Integer> EmptyCells = new ArrayList<Integer>();

        for (int CellId = 1; CellId < 10; CellId++)
            if (!(Player1.contains(CellId) || Player2.contains(CellId)))
                EmptyCells.add(CellId);

     //   Random r = new Random();
     //  int RandIndex = r.nextInt(EmptyCells.size() - 0) + 0;
     //   int cellId = EmptyCells.get(RandIndex);

        int cellId=1;
        int obj;
        if(EmptyCells.contains(obj=1) || EmptyCells.contains(obj=3) || EmptyCells.contains(obj=7) || EmptyCells.contains(obj=9)) {
            cellId=obj;
        }
         else if(EmptyCells.contains(obj=2) || EmptyCells.contains(obj=4) || EmptyCells.contains(obj=5) || EmptyCells.contains(obj=8)||
                 EmptyCells.contains(obj=6))
             cellId=obj;

        if(EmptyCells.isEmpty()==true) {
            Toast.makeText(this, "DRAW", Toast.LENGTH_LONG).show();
            finish();
        }

        Button buSelected;
        switch (cellId) {

            case 1:
                buSelected = (Button) findViewById(R.id.b1);
                break;

            case 2:
                buSelected = (Button) findViewById(R.id.b2);
                break;

            case 3:
                buSelected = (Button) findViewById(R.id.b3);
                break;

            case 4:
                buSelected = (Button) findViewById(R.id.b4);
                break;

            case 5:
                buSelected = (Button) findViewById(R.id.b5);
                break;

            case 6:
                buSelected = (Button) findViewById(R.id.b6);
                break;

            case 7:
                buSelected = (Button) findViewById(R.id.b7);
                break;

            case 8:
                buSelected = (Button) findViewById(R.id.b8);
                break;

            case 9:
                buSelected = (Button) findViewById(R.id.b9);
                break;
            default:
                buSelected = (Button) findViewById(R.id.b1);
                break;

        }

        PlayGame(cellId, buSelected);

    }


    public void BackButton(View view) {

        mysound =MediaPlayer.create(Auto.this,R.raw.beep5);
        mysound.start();

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setIcon(R.drawable.warning);
        alert.setMessage("Are you sure you want to quit?");
        alert.setTitle("WARNING");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mysound.release();
                Toast.makeText(getApplicationContext(), "You clicked YES", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mysound.release();
                Toast.makeText(getApplicationContext(), "You clicked NO", Toast.LENGTH_LONG).show();
            }
        });

        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.th1:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th1);
                tv.setTextColor(Color.parseColor("#880E4F"));
                return true;
            case R.id.th2:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th2);
                tv.setTextColor(Color.parseColor("#FBC02D"));
                return true;
            case R.id.th3:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th3);
                tv.setTextColor(Color.parseColor("#E57373"));
                return true;

            case R.id.th4:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th4);
                tv.setTextColor(Color.parseColor("#FFCC80"));
                return true;

            case R.id.th5:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th5);
                tv.setTextColor(Color.parseColor("#BDBDBD"));
                return true;

            case R.id.th6:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th6);
                tv.setTextColor(Color.parseColor("#880E4F"));
                return true;
            case R.id.th7:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th7);
                tv.setTextColor(Color.parseColor("#FBC02D"));
                return true;
            case R.id.th9:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th9);
                tv.setTextColor(Color.parseColor("#E57373"));
                return true;

            case R.id.th10:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th10);
                tv.setTextColor(Color.parseColor("#FFCC80"));
                return true;

            case R.id.th11:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th11);
                tv.setTextColor(Color.parseColor("#BDBDBD"));
                return true;

            case R.id.th12:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th12);
                tv.setTextColor(Color.parseColor("#BDBDBD"));
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


