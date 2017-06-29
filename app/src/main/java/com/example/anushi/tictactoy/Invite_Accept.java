package com.example.anushi.tictactoy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


public class Invite_Accept extends AppCompatActivity {

    EditText ed1;
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    FirebaseAuth auth;
    FirebaseUser user;
    myAdapter Adapter;
    ListView lv;
    TextView tv;
    private boolean isInFront;

    ArrayList<RequestList> mylist=new ArrayList<RequestList>();
    ArrayList<RequestList> templist=new ArrayList<RequestList>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite__accept);
        //Toast.makeText(getApplicationContext()," working 0 ",Toast.LENGTH_SHORT).show();
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        ed1=(EditText) findViewById(R.id.edinvite);
        lv=(ListView) findViewById(R.id.lvitem);
        tv=(TextView) findViewById((R.id.tvbar)) ;
        //Toast.makeText(getApplicationContext()," working 1 ",Toast.LENGTH_SHORT).show();
        // ye first error h ab ye dekh pta nhi kyu aa rha h dekha na ?
        //Toast.makeText(getApplicationContext()," trying to get list ",Toast.LENGTH_SHORT).show();

        //Toast.makeText(getApplicationContext()," list got  ",Toast.LENGTH_SHORT).show();

        /// firebase listner

        myRef.child("Users").child(remove(user.getEmail())).child("request").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                templist.clear();;
                templist=getlist(dataSnapshot);
                Adapter=new myAdapter(templist);
                if(isInFront)
                    lv.setAdapter(Adapter);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });


        ///  listner on with
        myRef.child("Users").child(remove(user.getEmail())).child("with").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String start=(String) dataSnapshot.getValue();
                //Toast.makeText(getApplicationContext(),"starting the game ",Toast.LENGTH_SHORT).show();
                String a=user.getUid();
                //Toast.makeText(getApplicationContext(),"request accepted ",Toast.LENGTH_SHORT).show();
                if(!a.matches(start))
                {
                    myRef.child("Users").child(remove(user.getEmail())).child("with").setValue(user.getUid());
                    myRef.child("Users").child(remove(user.getEmail())).child("playing").setValue("busy");
                    //myRef.child("Users").child(remove(a)).child("with").setValue(remove(user.getEmail()));
                    Toast.makeText(getApplicationContext(),"request accepted ",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), OnlineGame.class);
                    intent.putExtra("playgame",remove(user.getEmail())+":"+remove(start));
                    startActivity(intent);
                    // finish();

                    // Todo later myRef.child("Users").child(remove(user.getEmail())).child("playing").setValue("free");
                    //Todo later myRef.child("Users").child(remove(user.getEmail())).child("with").setValue(user.getUid());

                    ///StartGame(remove(user.getEmail())+start);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        isInFront = true;
    }
    @Override
    public  void onStart(){
        super.onStart();
        isInFront=true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isInFront = false;
    }


    public void busend_invite(View view)
    {
        if(!ed1.getText().toString().matches(""))
        {
            myRef.child("Users").child(remove(ed1.getText().toString())).child("request").push().setValue(user.getEmail());

        }
    }


    public String remove(String e_mail)
    {
        String[] split= e_mail.split("@");
        return split[0];

    }

    private class myAdapter extends BaseAdapter
    {
        public ArrayList<RequestList> requestitem;

        public myAdapter(ArrayList<RequestList> requestitem)
        {
            this.requestitem=requestitem;
        }

        @Override
        public int getCount()
        {
            return requestitem.size();
        }

        @Override
        public String getItem(int position)
        {
            return null;
        }
        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater myinflater=getLayoutInflater();
            View myview=myinflater.inflate(R.layout.listitem,null); // second error ab ye dekh

            final RequestList s=requestitem.get(position);
            TextView tv=(TextView) myview.findViewById(R.id.tvrequestlist); //thrid error ab ye dekh
            final TextView bustatus=(TextView)  myview.findViewById(R.id.bustatus);
            final Button buacc=(Button) myview.findViewById(R.id.buacc);
            //listen(s.username,buacc,bustatus);
            myRef.child("Users").child(s.username).child("playing").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String playing=(String) dataSnapshot.getValue();
                    String k=bustatus.getText().toString();
                    //Toast.makeText(getApplicationContext(),k +" "+ playing,Toast.LENGTH_SHORT).show();
                    if(playing.matches("free"))
                    {

                        bustatus.setText("free");
                        //myRef.child("Users").child(s.username).child("playing").setValue("free");
                        buacc.setEnabled(true);


                        // Toast.makeText(getApplicationContext(),"CANNOT START THE GAME PLAYER IS BUSY",Toast.LENGTH_SHORT).show();


                    }
                    else
                    {
                        bustatus.setText("busy");
                        //myRef.child("Users").child(s.username).child("playing").setValue("busy");
                        buacc.setEnabled(false);

                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            buacc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    myRef.child("Users").child(s.username).child("with").setValue(remove(user.getEmail()));
                    myRef.child("Users").child(remove(user.getEmail())).child("playing").setValue("busy");

                    //Toast.makeText(getApplicationContext(),"starting the game ",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),OnlineGame.class);
                    intent.putExtra("playgame",s.username+":"+remove(user.getEmail()));
                    intent.putExtra("keyofplayer",s.key);
                    startActivity(intent);
                    //finish();
                    Toast.makeText(getApplicationContext(),"game ended",Toast.LENGTH_SHORT).show();
                    // todo later myRef.child("Users").child(remove(user.getEmail())).child("playing").setValue("free");
                    // myRef.child("Users").child(remove(user.getEmail())).child("with").setValue(user.getUid());



                }
            });
            tv.setText(s.username);

            return myview;
        }

    }
    void listen(final String name,final Button bucc,final TextView bus)
    {
        myRef.child("Users").child(name).child("playing").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String playing=(String) dataSnapshot.getValue();
                String k=bus.getText().toString();
                //Toast.makeText(getApplicationContext(),k +" "+ playing,Toast.LENGTH_SHORT).show();
                if(playing.matches(k))
                {

                    bus.setText("free");
                    myRef.child("Users").child(name).child("playing").setValue("free");
                    bucc.setEnabled(true);



                    // Toast.makeText(getApplicationContext(),"CANNOT START THE GAME PLAYER IS BUSY",Toast.LENGTH_SHORT).show();


                }
                else
                {
                    bus.setText("busy");
                    myRef.child("Users").child(name).child("playing").setValue("free");
                    bucc.setEnabled(false);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    ArrayList<RequestList> getlist(DataSnapshot dataSnapshot)
    {

        try{
            HashMap<String,Object> td=(HashMap<String,Object>) dataSnapshot.getValue();
            if (td!=null){

                String value;
                String myKey;
                for(String key:td.keySet()){
                    value=(String) td.get(key);
                    myKey=key;


                    if(!value.matches(user.getUid()))
                    {
                        mylist.add(new RequestList(remove(value),key));
                    }

                }
            }

        }catch (Exception ex){}

        if(mylist.isEmpty())
        {
            tv.setText(" NO REQUEST");

        }
        else
        {
            tv.setText("REQUEST LIST");
        }

        return mylist;
    }



    // startgame
    public void StartGame(String PlaySession)
    {

        Toast.makeText(getApplicationContext(),PlaySession,Toast.LENGTH_SHORT).show();

    }

}