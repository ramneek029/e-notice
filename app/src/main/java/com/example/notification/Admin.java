package com.example.notification;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class Admin extends AppCompatActivity {

    FloatingActionButton floating_button;
    final ArrayList<String> list=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listView=(ListView)findViewById(R.id.listview);

        floating_button = findViewById(R.id.floatingActionButton);
        floating_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Admin.this, com.example.notification.AddActivity.class);
                startActivity(intent);
            }
        });

        FirebaseDatabase.getInstance().getReference()
                .child("Events")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            String s = "";
                            s = snapshot.child("subject").getValue().toString()+"\n" +
                                    snapshot.child("date").getValue().toString()+"\n" +
                                    snapshot.child("priority").getValue().toString()+"\n"+
                                    snapshot.child("branch").getValue().toString()+"\n"+
                                    snapshot.child("details").getValue().toString();

                            list.add(s);


                        }
                        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(Admin.this, android.R
                                .layout.simple_list_item_activated_1,list);
                        listView.setAdapter(arrayAdapter);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



    }

}