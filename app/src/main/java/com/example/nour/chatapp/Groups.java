package com.example.nour.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Nour on 7/31/2017.
 */

public class Groups extends AppCompatActivity {

    Button gButton;
    EditText gArea;
    ListView listView;
    ArrayList<String> al = new ArrayList<>();
    int totalGroups = 0;
    TextView noGroupsText;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot().child("Groups");
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        setTitle("Groups");

        gButton = (Button) findViewById(R.id.gButton);
        gArea = (EditText) findViewById(R.id.gArea);
        listView = (ListView) findViewById(R.id.groupList);
        noGroupsText = (TextView)findViewById(R.id.noGroupsText);
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,al);

        listView.setAdapter(arrayAdapter);


        gButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String,Object> map = new HashMap<String, Object>();
                map.put(gArea.getText().toString(),"");
                root.updateChildren(map);
                totalGroups++;

            }
        });

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());
                }

                al.clear();
                al.addAll(set);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(),Chat_Room.class);
                intent.putExtra("room_name",((TextView)view).getText().toString());
                startActivity(intent);
            }
        });

        /* if(totalGroups <1){
            noGroupsText.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }
        else{
            noGroupsText.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }**/


    }


    }


