package com.example.anushi.tictactoy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by anushi on 16/6/17.
 */

public class Auto extends AppCompatActivity{

    TextView tv;
    int Winner=-1;
    int counts=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto);

        tv = (TextView)findViewById(R.id.tv);
        Bundle b = getIntent().getExtras();
        String pname1 = b.getString("player1");
        tv.setText(pname1);
    }

    public void buClick(View view){
        Button buSelected = (Button) view;

        int CellId=0;
        counts=counts+1;
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
            buSelected.setBackgroundColor(Color.YELLOW);
            Player1.add(CellId);
            ActivePlayer=2;
            AutoPlay();
        }

        else if(ActivePlayer==2){
            buSelected.setText("O");
            buSelected.setBackgroundColor(Color.MAGENTA);
            Player2.add(CellId);
            ActivePlayer=1;
        }

        buSelected.setEnabled(false);
        CheckWinner(counts);

    }


    void CheckWinner(int counts){
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

            if(Winner == 1) {
                String s = tv.getText().toString();
                Toast.makeText(getApplicationContext(), s+" is Winner", Toast.LENGTH_LONG).show();
                finish();
            }
            else  {
                Toast.makeText(this, "Device is Winner", Toast.LENGTH_LONG).show();
                finish();
            }
        }
        if(Winner==-1 && counts==9){
            Toast.makeText(this,"DRAW",Toast.LENGTH_LONG).show();
            finish();
        }
    }

    void AutoPlay(){

        ArrayList<Integer> EmptyCells = new ArrayList<Integer>();

        for(int CellId=1; CellId<10;CellId++)
            if(!(Player1.contains(CellId) || Player2.contains(CellId)))
                EmptyCells.add(CellId);

        Random r = new Random();
        int RandIndex = r.nextInt(EmptyCells.size()-0)+0;
        int cellId = EmptyCells.get(RandIndex);

        Button buSelected;
        switch (cellId){

            case 1:
                buSelected =(Button)findViewById(R.id.b1);
                break;

            case 2:
                buSelected =(Button)findViewById(R.id.b2);
                break;

            case 3:
                buSelected =(Button)findViewById(R.id.b3);
                break;

            case 4:
                buSelected =(Button)findViewById(R.id.b4);
                break;

            case 5:
                buSelected =(Button)findViewById(R.id.b5);
                break;

            case 6:
                buSelected =(Button)findViewById(R.id.b6);
                break;

            case 7:
                buSelected =(Button)findViewById(R.id.b7);
                break;

            case 8:
                buSelected =(Button)findViewById(R.id.b8);
                break;

            case 9:
                buSelected =(Button)findViewById(R.id.b9);
                break;
            default:
                buSelected =(Button)findViewById(R.id.b1);
                break;

        }
        PlayGame(cellId,buSelected);

    }
}


