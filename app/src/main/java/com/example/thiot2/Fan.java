package com.example.thiot2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Fan extends AppCompatActivity {
    SwitchCompat switchCompat;
    SwitchCompat myListView;
    ArrayList<Float> myArrayList = new ArrayList<>();
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan);

        databaseReference = FirebaseDatabase.getInstance().getReference("Fan");
        myListView = (SwitchCompat) findViewById(R.id.fan1);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @com.google.firebase.database.annotations.Nullable String s) {
                String str;
                Float value = dataSnapshot.getValue(Float.class);
                myArrayList.add(value);
                switchCompat = (androidx.appcompat.widget.SwitchCompat) findViewById(R.id.fan1);
                if (value > 0){
                    str = myListView.getTextOff().toString();
                    switchCompat.setChecked(false);
                }
                else {
                    str = myListView.getTextOn().toString();
                    switchCompat.setChecked(true);

                }
                Toast.makeText(getApplicationContext(), "Fan - " + str , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @com.google.firebase.database.annotations.Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @com.google.firebase.database.annotations.Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
    }
}