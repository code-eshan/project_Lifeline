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
import com.lifelinemadproject.lifelinehospital.Doctor.Doctor;

import java.util.ArrayList;

public class PatDocListUI extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Doctor doctor;

    ListView ListView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pat_doc_list_ui);

        doctor =  new Doctor();
        ListView1 = (ListView)findViewById(R.id.ListView1);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Doctor");

        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.activity_nurappoints,R.id.doctor_user_info,list);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot da:dataSnapshot.getChildren()) {
                    doctor = da.getValue(Doctor.class);
                    list.add("Name: " + doctor.getfName() + "\n" + "Contact No: " + doctor.getContNum() );
                }
                ListView1.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
