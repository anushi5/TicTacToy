package com.example.anushi.tictactoy;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import static android.R.attr.tag;

public class Invitation extends AppCompatActivity {

    EditText etmail,fmail;
    EditText etmymail;
    Button blogin;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    String myEmail;
    String uid;
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation);

        etmail = (EditText)findViewById(R.id.etmail);
        fmail = (EditText)findViewById(R.id.femail);
        etmymail = (EditText)findViewById(R.id.etmymail);
        blogin =(Button)findViewById(R.id.blogin);

        Bundle b = getIntent().getExtras();
        String pname1 = b.getString("MyMail");
        etmymail.setText(pname1);

        // Obtain the FirebaseAnalytics instance.
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            public static final String TAG ="Login";

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    uid=user.getUid();
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + uid);
                    myEmail =  user.getEmail();
                    blogin.setEnabled(false);
                    etmymail.setText(myEmail);
                    myRef.child("Users").child(BeforeAt(myEmail)).child("Request").setValue(uid);
                    IncommingRequest();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

    }

    String BeforeAt(String Email){

        String[] split = Email.split("@");
        return split[0];
    }

    public void binvite(View view){

        Log.d("Invite",etmail.getText().toString());
        myRef.child("Users").child(BeforeAt(etmail.getText().toString())).child("Request").push().setValue(myEmail);
        StartGame(BeforeAt(etmail.getText().toString())+":"+BeforeAt(myEmail));

    }

    void IncommingRequest(){

        // Read from the database
        myRef.child("Users").child(BeforeAt(myEmail)).child("Request").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try{

                    HashMap<String,Object> td = (HashMap<String,Object>) dataSnapshot.getValue();
                    if(td!=null){

                        String value;
                        for(String key:td.keySet()){
                            value = (String) td.get(key);
                            Log.d("User request",value);
                            etmail.setText(value);
                            ButtonColour();
                            myRef.child("Users").child(BeforeAt(myEmail)).child("Request").setValue(uid);
                            break;
                        }
                    }

                }catch(Exception ex){

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {


            }
        });
    }

    void ButtonColour(){
        etmail.setBackgroundColor(Color.CYAN);
    }

    public void baccept(View view) {

        Log.d("Accept",fmail.getText().toString());
        myRef.child("Users").child(BeforeAt(fmail.getText().toString())).child("Request").push().setValue(myEmail);
        StartGame(BeforeAt(BeforeAt(myEmail)+":" + fmail.getText().toString()));
    }

    void StartGame(String PlayerGameId){
        myRef.child("Playing").child(PlayerGameId).removeValue();
    }
}
