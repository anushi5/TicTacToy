package com.example.anushi.tictactoy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
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
    ArrayList<RequestList> mylist=new ArrayList<RequestList>();
    ArrayList<RequestList> templist=new ArrayList<RequestList>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite__accept);
        Toast.makeText(getApplicationContext()," working 0 ",Toast.LENGTH_SHORT).show();
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        ed1=(EditText) findViewById(R.id.edinvite);
        lv=(ListView) findViewById(R.id.lvitem);
        tv=(TextView) findViewById((R.id.tvbar)) ;
        Toast.makeText(getApplicationContext()," working 1 ",Toast.LENGTH_SHORT).show();
        // ye first error h ab ye dekh pta nhi kyu aa rha h dekha na ?
        Toast.makeText(getApplicationContext()," trying to get list ",Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext()," list got  ",Toast.LENGTH_SHORT).show();

        /// firebase listner
        myRef.child("Users").child(remove(user.getEmail())).child("request").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                templist.clear();;
                templist=getlist(dataSnapshot);
                Adapter=new myAdapter(templist);
                lv.setAdapter(Adapter);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               final RequestList s= mylist.get(position);
                myRef.child("Users").child(s.username).child("playing").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                           String playing=(String) dataSnapshot.getValue();
                        if(playing.matches("NO"))
                        {
                            Intent intent=new Intent(getApplicationContext(),OnlineGame.class);

                            myRef.child("Users").child(s.username).child("playing").setValue("YES");
                            myRef.child("Users").child(remove(user.getEmail())).child("playing").setValue("YES");
                            startActivity(intent);
                            myRef.child("Users").child(s.username).child("playing").setValue("NO");
                            myRef.child("Users").child(remove(user.getEmail())).child("playing").setValue("NO");

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"CANNOT START THE GAME PLAYER IS BUSY",Toast.LENGTH_SHORT).show();


                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                //StartGame(remove(user.getEmail()) + ":"+ remove(s.username));


               // MySample="O";
            }
        });

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

            tv.setText(s.username);
            return myview;
        }

    }

    ArrayList<RequestList> getlist(DataSnapshot dataSnapshot)
    {

        try{
            HashMap<String,Object> td=(HashMap<String,Object>) dataSnapshot.getValue();
            if (td!=null){

                String value;
                for(String key:td.keySet()){
                    value=(String) td.get(key);

                    mylist.add(new RequestList(remove(value)));
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
