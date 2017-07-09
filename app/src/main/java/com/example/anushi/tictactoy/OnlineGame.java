package com.example.anushi.tictactoy;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OnlineGame extends AppCompatActivity {

    int counts=0;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    TableLayout tl;
    FirebaseAuth auth;
    FirebaseUser user;
    int turn=0;
    String playgame;
    String mykey;
    String p1,p2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);

        tl=(TableLayout)findViewById(R.id.tl);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        Bundle b=getIntent().getExtras();
        playgame=b.getString("playgame");
        mykey=b.getString("keyofplayer");
        String[] split=playgame.split(":");
        p1=split[0];
        Player1.clear();
        Player2.clear();
        turn=0;
        p2=split[1];
       // myRef.child("playgame").child(playgame).child("game id").setValue(playgame);
        //myRef.child("playgame").child(playgame).child("winner").setValue(playgame);
       myRef.child("playgame").child(playgame).child("move").setValue(playgame);
        //myRef.child("playgame").child(playgame).child("turn").setValue(0);
        Toast.makeText(getApplicationContext(),"game begin",Toast.LENGTH_SHORT).show();




        // TODO: 24/6/17 to add listner for moves so that it color the button
        myRef.child("playgame").child(playgame).child("move").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String move=(String) dataSnapshot.getValue();
                 int cid=0;

                if(!move.matches(playgame))
                {
                    //Toast.makeText(getApplicationContext(),"making a move",Toast.LENGTH_SHORT).show();
                    String[] split=move.split("@");
                    if(split[1].matches(p1))
                    {
                        Button buSelected=(Button) findViewById(R.id.b1);
                        switch (split[0]){

                            case "1" : buSelected=(Button) findViewById(R.id.b1);
                                       cid=1;
                                break;

                            case "2" : buSelected=(Button)findViewById(R.id.b2);
                                       cid=2;
                                break;
                            case "3" : buSelected=(Button)findViewById(R.id.b3);
                                cid=3;
                                break;
                            case "4" : buSelected=(Button)findViewById(R.id.b4);
                                cid=4;
                                break;
                            case "5" : buSelected=(Button)findViewById(R.id.b5);
                                cid=5;
                                break;
                            case "6" : buSelected=(Button)findViewById(R.id.b6);
                                cid=6;
                                break;
                            case "7" : buSelected=(Button)findViewById(R.id.b7);
                                cid=7;
                                break;
                            case "8" : buSelected=(Button)findViewById(R.id.b8);
                                cid=8;
                                break;
                            case "9" : buSelected=(Button)findViewById(R.id.b9);
                                cid=9;
                                break;



                        }

                            PlayGame(cid,buSelected,split[1]);

                    }
                    else
                    {
                         Button buSelected=(Button) findViewById(R.id.b1);


                        switch (split[0]) {

                            case "1" : buSelected=(Button) findViewById(R.id.b1);
                                cid=1;
                                break;

                            case "2" : buSelected=(Button)findViewById(R.id.b2);
                                cid=2;
                                break;
                            case "3" : buSelected=(Button)findViewById(R.id.b3);
                                cid=3;
                                break;
                            case "4" : buSelected=(Button)findViewById(R.id.b4);
                                cid=4;
                                break;
                            case "5" : buSelected=(Button)findViewById(R.id.b5);
                                cid=5;
                                break;
                            case "6" : buSelected=(Button)findViewById(R.id.b6);
                                cid=6;
                                break;
                            case "7" : buSelected=(Button)findViewById(R.id.b7);
                                cid=7;
                                break;
                            case "8" : buSelected=(Button)findViewById(R.id.b8);
                                cid=8;
                                break;
                            case "9" : buSelected=(Button)findViewById(R.id.b9);
                                cid=9;
                                break;

                        }
                            PlayGame(cid,buSelected,split[1]);

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    //View view=this.findViewById(android.R.id.content).getRootView();
    //if()
    public String remove(String e_mail)
    {
        String[] split= e_mail.split("@");
        return split[0];

    }

    public void buClick(View view){
        Button buSelected = (Button) view;

        int CellId=0;
        counts +=1;
       // enable(view);
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
        turn=turn+1;
        myRef.child("playgame").child(playgame).child("move").setValue(CellId+"@"+remove(user.getEmail()));
        /*if(turn%2!=0 && remove(user.getEmail()).matches(p1))
        {
            view.setEnabled(false);
            turn =turn +1;
        }
        else
        {
            view.setEnabled(false);
            turn =turn +1;
        }*/



    }


    int ActivePlayer = 1;
    ArrayList<Integer> Player1 = new ArrayList<Integer>();
    ArrayList<Integer> Player2 = new ArrayList<Integer>();


    void PlayGame(int CellId, Button buSelected,String name) {


        if(name.matches(p1)){
            buSelected.setBackgroundResource(R.drawable.kaatag);
            Player1.add(CellId);
            tl.setEnabled(false);
            ActivePlayer=2;
        }

        else if(name.matches(p2)){
            buSelected.setBackgroundResource(R.drawable.zerog);
            Player2.add(CellId);
            tl.setEnabled(false);
            ActivePlayer = 1;

        }

        buSelected.setEnabled(false);
        CheckWinner();

    }


    void CheckWinner(){
        int Winner=-1;
        /// row 1
        if(Player1.contains(1) && Player1.contains(2) && Player1.contains(3)){
            Winner=1;
           // myRef.child("playgame").child(playgame).child("winner").setValue("1");

        }
        else if(Player2.contains(1) && Player2.contains(2) && Player2.contains(3)){
            Winner=2;
            //myRef.child("playgame").child(playgame).child("winner").setValue("2");
        }

        ///row  2
        else if(Player1.contains(4) && Player1.contains(5) && Player1.contains(6)){
            Winner=1;
           // myRef.child("playgame").child(playgame).child("winner").setValue("1");

        }
        else if(Player2.contains(4) && Player2.contains(5) && Player2.contains(6)){
            Winner=2;
            //myRef.child("playgame").child(playgame).child("winner").setValue("2");
        }


        ///row 3
        else if(Player1.contains(7) && Player1.contains(8) && Player1.contains(9)){
            Winner=1;
            //myRef.child("playgame").child(playgame).child("winner").setValue("1");

        }
        else if(Player2.contains(7) && Player2.contains(8) && Player2.contains(9)){
            Winner=2;
           // myRef.child("playgame").child(playgame).child("winner").setValue(2);
         //   myRef.child("playgame").child(playgame).child("winner").setValue("2");
        }

        ///column 1
        else if(Player1.contains(1) && Player1.contains(4) && Player1.contains(7)){
            Winner=1;
           // myRef.child("playgame").child(playgame).child("winner").setValue("1");

        }
        else if(Player2.contains(1) && Player2.contains(4) && Player2.contains(7)){
            Winner=2;
            //myRef.child("playgame").child(playgame).child("winner").setValue("2");
        }

        ///column2
        else if(Player1.contains(2) && Player1.contains(5) && Player1.contains(8)){
            Winner=1;
            //myRef.child("playgame").child(playgame).child("winner").setValue("1");

        }
        else if(Player2.contains(2) && Player2.contains(5) && Player2.contains(8)){
            Winner=2;
           // myRef.child("playgame").child(playgame).child("winner").setValue("2");
        }

        ///column 3
        else if(Player1.contains(3) && Player1.contains(6) && Player1.contains(9)){
            Winner=1;
            //myRef.child("playgame").child(playgame).child("winner").setValue("1");

        }
        else if(Player2.contains(3) && Player2.contains(6) && Player2.contains(9)){
            Winner=2;
         //myRef.child("playgame").child(playgame).child("winner").setValue("2");
        }

        ///diagonal 1
        else if(Player1.contains(1) && Player1.contains(5) && Player1.contains(9)){
            Winner=1;
          //  myRef.child("playgame").child(playgame).child("winner").setValue("1");

        }
        else if(Player2.contains(1) && Player2.contains(5) && Player2.contains(9)){
            Winner=2;
           // myRef.child("playgame").child(playgame).child("winner").setValue("2");
        }

        ///diagonal 2
        else if(Player1.contains(3) && Player1.contains(5) && Player1.contains(7)){
            Winner=1;
//            myRef.child("playgame").child(playgame).child("winner").setValue("1");

        }
        else if(Player2.contains(3) && Player2.contains(5) && Player2.contains(7)){
            Winner=2;
   //         myRef.child("playgame").child(playgame).child("winner").setValue("2");
        }

        if(Winner !=-1){

            if(Winner == 1){
                Toast.makeText(getApplicationContext(),p1+" is Winner",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(),p2+" is Winner", Toast.LENGTH_SHORT).show();
            }
            myRef.child("Users").child(remove(user.getEmail())).child("playing").setValue("free");
            //myRef.child("Users").child(remove(user.getEmail())).child("with").setValue(user.getUid());
            Player1.clear();
            Player2.clear();
            if(remove(user.getEmail()).matches(p2))
            {
                myRef.child("Users").child(p2).child("request").child(mykey).removeValue();
            }
            /*if(remove(user.getEmail()).matches(p1))
            {
                myRef.child("playgame").child(playgame).child("move").removeEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String move=(String) dataSnapshot.getValue();
                        int cid=0;

                        if(!move.matches(playgame))
                        {
                            //Toast.makeText(getApplicationContext(),"making a move",Toast.LENGTH_SHORT).show();
                            String[] split=move.split("@");
                            if(split[1].matches(p1))
                            {
                                Button buSelected=(Button) findViewById(R.id.b1);
                                switch (split[0]){

                                    case "1" : buSelected=(Button) findViewById(R.id.b1);
                                        cid=1;
                                        break;

                                    case "2" : buSelected=(Button)findViewById(R.id.b2);
                                        cid=2;
                                        break;
                                    case "3" : buSelected=(Button)findViewById(R.id.b3);
                                        cid=3;
                                        break;
                                    case "4" : buSelected=(Button)findViewById(R.id.b4);
                                        cid=4;
                                        break;
                                    case "5" : buSelected=(Button)findViewById(R.id.b5);
                                        cid=5;
                                        break;
                                    case "6" : buSelected=(Button)findViewById(R.id.b6);
                                        cid=6;
                                        break;
                                    case "7" : buSelected=(Button)findViewById(R.id.b7);
                                        cid=7;
                                        break;
                                    case "8" : buSelected=(Button)findViewById(R.id.b8);
                                        cid=8;
                                        break;
                                    case "9" : buSelected=(Button)findViewById(R.id.b9);
                                        cid=9;
                                        break;



                                }
                                PlayGame(cid,buSelected,split[1]);
                            }
                            else
                            {
                                Button buSelected=(Button) findViewById(R.id.b1);


                                switch (split[0]) {

                                    case "1" : buSelected=(Button) findViewById(R.id.b1);
                                        cid=1;
                                        break;

                                    case "2" : buSelected=(Button)findViewById(R.id.b2);
                                        cid=2;
                                        break;
                                    case "3" : buSelected=(Button)findViewById(R.id.b3);
                                        cid=3;
                                        break;
                                    case "4" : buSelected=(Button)findViewById(R.id.b4);
                                        cid=4;
                                        break;
                                    case "5" : buSelected=(Button)findViewById(R.id.b5);
                                        cid=5;
                                        break;
                                    case "6" : buSelected=(Button)findViewById(R.id.b6);
                                        cid=6;
                                        break;
                                    case "7" : buSelected=(Button)findViewById(R.id.b7);
                                        cid=7;
                                        break;
                                    case "8" : buSelected=(Button)findViewById(R.id.b8);
                                        cid=8;
                                        break;
                                    case "9" : buSelected=(Button)findViewById(R.id.b9);
                                        cid=9;
                                        break;

                                }
                                PlayGame(cid,buSelected,split[1]);

                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                myRef.child("playgame").child(playgame).removeValue();
            }*/
            finish();

        }

        else if(Winner==-1 && turn==9){
            Toast.makeText(getApplicationContext(),"DRAW", Toast.LENGTH_SHORT).show();
            myRef.child("Users").child(remove(user.getEmail())).child("playing").setValue("free");
            //myRef.child("Users").child(remove(user.getEmail())).child("with").setValue(user.getUid());
            Player1.clear();
            Player2.clear();
            if(remove(user.getEmail()).matches(p2))
            {
                myRef.child("Users").child(p2).child("request").child(mykey).removeValue();
            }
           /* if(remove(user.getEmail()).matches(p1))
            {
                myRef.child("playgame").child(playgame).child("move").removeEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String move=(String) dataSnapshot.getValue();
                        int cid=0;

                        if(!move.matches(playgame))
                        {
                            //Toast.makeText(getApplicationContext(),"making a move",Toast.LENGTH_SHORT).show();
                            String[] split=move.split("@");
                            if(split[1].matches(p1))
                            {
                                Button buSelected=(Button) findViewById(R.id.b1);
                                switch (split[0]){

                                    case "1" : buSelected=(Button) findViewById(R.id.b1);
                                        cid=1;
                                        break;

                                    case "2" : buSelected=(Button)findViewById(R.id.b2);
                                        cid=2;
                                        break;
                                    case "3" : buSelected=(Button)findViewById(R.id.b3);
                                        cid=3;
                                        break;
                                    case "4" : buSelected=(Button)findViewById(R.id.b4);
                                        cid=4;
                                        break;
                                    case "5" : buSelected=(Button)findViewById(R.id.b5);
                                        cid=5;
                                        break;
                                    case "6" : buSelected=(Button)findViewById(R.id.b6);
                                        cid=6;
                                        break;
                                    case "7" : buSelected=(Button)findViewById(R.id.b7);
                                        cid=7;
                                        break;
                                    case "8" : buSelected=(Button)findViewById(R.id.b8);
                                        cid=8;
                                        break;
                                    case "9" : buSelected=(Button)findViewById(R.id.b9);
                                        cid=9;
                                        break;



                                }
                                PlayGame(cid,buSelected,split[1]);
                            }
                            else
                            {
                                Button buSelected=(Button) findViewById(R.id.b1);


                                switch (split[0]) {

                                    case "1" : buSelected=(Button) findViewById(R.id.b1);
                                        cid=1;
                                        break;

                                    case "2" : buSelected=(Button)findViewById(R.id.b2);
                                        cid=2;
                                        break;
                                    case "3" : buSelected=(Button)findViewById(R.id.b3);
                                        cid=3;
                                        break;
                                    case "4" : buSelected=(Button)findViewById(R.id.b4);
                                        cid=4;
                                        break;
                                    case "5" : buSelected=(Button)findViewById(R.id.b5);
                                        cid=5;
                                        break;
                                    case "6" : buSelected=(Button)findViewById(R.id.b6);
                                        cid=6;
                                        break;
                                    case "7" : buSelected=(Button)findViewById(R.id.b7);
                                        cid=7;
                                        break;
                                    case "8" : buSelected=(Button)findViewById(R.id.b8);
                                        cid=8;
                                        break;
                                    case "9" : buSelected=(Button)findViewById(R.id.b9);
                                        cid=9;
                                        break;

                                }
                                PlayGame(cid,buSelected,split[1]);

                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                myRef.child("playgame").child(playgame).removeValue();
            }*/
            finish();

        }

    }


}
