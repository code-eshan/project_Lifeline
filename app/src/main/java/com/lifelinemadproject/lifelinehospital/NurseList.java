package com.lifelinemadproject.lifelinehospital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lifelinemadproject.lifelinehospital.Nurse.Nurse;

import java.util.ArrayList;

public class NurseList extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Nurse nurse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_list);

        nurse = new Nurse();
        listView = (ListView)findViewById(R.id.listView);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Nurse");

        list=new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.nurse_user_info,R.id.nurse_user_info,list);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    nurse = ds.getValue(Nurse.class);
                    list.add("ID: "+nurse.getSid() + "\n" + "Name: " +nurse.getfName().toString() + " " + nurse.getlName().toString());
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
