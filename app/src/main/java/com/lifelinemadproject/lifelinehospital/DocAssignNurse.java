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
import com.lifelinemadproject.lifelinehospital.Appointment.Appointment;

import java.util.ArrayList;

public class DocAssignNurse extends AppCompatActivity {

    ListView listViewAss;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Appointment appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_assign_nurse);

        appointment = new Appointment();
        listViewAss = (ListView) findViewById(R.id.listViewAssign);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Appointment");

        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.doc_assign_nurse_user_info, R.id.doc_assign_nurse_user_info, list);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    appointment = ds.getValue(Appointment.class);
                    list.add("Name: " + appointment.getUsername() + "\n" + "Problem: " + appointment.getProblem().toString() + "\n" + "Date: " + appointment.getDate().toString() + "\n" + "Time: " + appointment.getTime().toString());
                }
                listViewAss.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}