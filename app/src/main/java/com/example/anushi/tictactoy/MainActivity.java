package com.example.anushi.tictactoy;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import static android.R.attr.tag;

public class MainActivity extends AppCompatActivity {


    TextView tv1,tv2;
    int counts=0;
    TableLayout tl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1=(TextView)findViewById(R.id.tv1);
        tv2=(TextView) findViewById(R.id.tv2);
        Bundle b = getIntent().getExtras();
        String pname1 = b.getString("player1");
        String pname2 = b.getString("player2");
        tv1.setText(pname1);
        tv2.setText(pname2);
        tl=(TableLayout)findViewById(R.id.tl);
    }
    public void buClick(View view){
        Button buSelected = (Button) view;

        int CellId=0;
        counts +=1;
        switch ((buSelected.getId())){

            case R.id.b1: CellId=1;
                break;

            case R.id.b2: CellId=2;
                break;

            case R.id.b3: CellId=3;
                break;

            case R.id.b4: CellId=4;
                break;

            case R.id.b5: CellId=5;
                break;

            case R.id.b6: CellId=6;
                break;

            case R.id.b7: CellId=7;
                break;

            case R.id.b8: CellId=8;
                break;

            case R.id.b9: CellId=9;
                break;


        }
       PlayGame(CellId,buSelected);

    }

    int ActivePlayer = 1;
    ArrayList<Integer> Player1 = new ArrayList<Integer>();
    ArrayList<Integer> Player2 = new ArrayList<Integer>();


    void PlayGame(int CellId, Button buSelected){

        Log.d("Player:",String.valueOf(CellId));

        if(ActivePlayer==1){
            buSelected.setText("X");
            buSelected.setBackgroundColor(Color.RED);
            Player1.add(CellId);
            ActivePlayer=2;
        }

        else if(ActivePlayer==2){
            buSelected.setText("O");
            buSelected.setBackgroundColor(Color.BLUE);
            Player2.add(CellId);
            ActivePlayer=1;
        }

        buSelected.setEnabled(false);
        CheckWinner(counts);

    }


    void CheckWinner(int counts){
         int Winner=-1;
        /// row 1
        if(Player1.contains(1) && Player1.contains(2) && Player1.contains(3)){
            Winner=1;
        }
        else if(Player2.contains(1) && Player2.contains(2) && Player2.contains(3)){
            Winner=2;
        }

        ///row  2
        else if(Player1.contains(4) && Player1.contains(5) && Player1.contains(6)){
            Winner=1;
        }
        else if(Player2.contains(4) && Player2.contains(5) && Player2.contains(6)){
            Winner=2;
        }


        ///row 3
        else if(Player1.contains(7) && Player1.contains(8) && Player1.contains(9)){
            Winner=1;
        }
        else if(Player2.contains(7) && Player2.contains(8) && Player2.contains(9)){
            Winner=2;
        }

        ///column 1
        else if(Player1.contains(1) && Player1.contains(4) && Player1.contains(7)){
            Winner=1;
        }
        else if(Player2.contains(1) && Player2.contains(4) && Player2.contains(7)){
            Winner=2;
        }

        ///column2
        else if(Player1.contains(2) && Player1.contains(5) && Player1.contains(8)){
            Winner=1;
        }
        else if(Player2.contains(2) && Player2.contains(5) && Player2.contains(8)){
            Winner=2;
        }

        ///column 3
        else if(Player1.contains(3) && Player1.contains(6) && Player1.contains(9)){
            Winner=1;
        }
        else if(Player2.contains(3) && Player2.contains(6) && Player2.contains(9)){
            Winner=2;
        }

        ///diagonal 1
        else if(Player1.contains(1) && Player1.contains(5) && Player1.contains(9)){
            Winner=1;
        }
        else if(Player2.contains(1) && Player2.contains(5) && Player2.contains(9)){
            Winner=2;
        }

        ///diagonal 2
        else if(Player1.contains(3) && Player1.contains(5) && Player1.contains(7)){
            Winner=1;
        }
        else if(Player2.contains(3) && Player2.contains(5) && Player2.contains(7)){
            Winner=2;
        }

        if(Winner !=-1){

            if(Winner == 1){
                String s = tv1.getText().toString();
                Toast.makeText(getApplicationContext(),s+" is winner",Toast.LENGTH_LONG).show();
                finish();
            }
            else {
                String s = tv2.getText().toString();
                Toast.makeText(getApplicationContext(), s + " is Winner", Toast.LENGTH_LONG).show();
                finish();
            }
        }

        if(Winner==-1 && counts==9){
            Toast.makeText(this,"DRAW",Toast.LENGTH_LONG).show();
            finish();
        }

    }


    public void BackButton(View view) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setIcon(R.drawable.warning);
        alert.setMessage("Are you sure you want to quit?");
        alert.setTitle("WARNING");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"You clicked YES",Toast.LENGTH_LONG).show();
                finish();
            }
        });

        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"You clicked NO",Toast.LENGTH_LONG).show();
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
                tv1.setTextColor(Color.parseColor("#880E4F"));
                tv2.setTextColor(Color.parseColor("#880E4F"));
                return true;

            case R.id.th2:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th2);
                tv1.setTextColor(Color.parseColor("#FBC02D"));
                tv2.setTextColor(Color.parseColor("#FBC02D"));
                return true;

            case R.id.th3:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th3);
                tv1.setTextColor(Color.parseColor("#E57373"));
                tv2.setTextColor(Color.parseColor("#E57373"));
                return true;

            case R.id.th4:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th4);
                tv1.setTextColor(Color.parseColor("#FFCC80"));
                tv2.setTextColor(Color.parseColor("#FFCC80"));
                return true;

            case R.id.th5:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th5);
                tv1.setTextColor(Color.parseColor("#BDBDBD"));
                tv2.setTextColor(Color.parseColor("#BDBDBD"));
                return true;

            case R.id.th6:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th6);
                tv1.setTextColor(Color.parseColor("#880E4F"));
                tv2.setTextColor(Color.parseColor("#880E4F"));
                return true;
            case R.id.th7:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th7);
                tv1.setTextColor(Color.parseColor("#FBC02D"));
                tv2.setTextColor(Color.parseColor("#FBC02D"));
                return true;
            case R.id.th9:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th9);
                tv1.setTextColor(Color.parseColor("#E57373"));
                tv2.setTextColor(Color.parseColor("#E57373"));
                return true;

            case R.id.th10:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th10);
                tv1.setTextColor(Color.parseColor("#FFCC80"));
                tv2.setTextColor(Color.parseColor("#FFCC80"));
                return true;

            case R.id.th11:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th11);
                tv1.setTextColor(Color.parseColor("#BDBDBD"));
                tv2.setTextColor(Color.parseColor("#BDBDBD"));
                return true;

            case R.id.th12:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                tl.setBackgroundResource(R.drawable.th12);
                tv1.setTextColor(Color.parseColor("#BDBDBD"));
                tv2.setTextColor(Color.parseColor("#BDBDBD"));
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
