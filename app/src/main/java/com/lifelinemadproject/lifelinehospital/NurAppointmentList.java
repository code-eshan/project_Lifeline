package com.lifelinemadproject.lifelinehospital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lifelinemadproject.lifelinehospital.Doctor.Doctor;
import com.lifelinemadproject.lifelinehospital.Nurse.Nurse;

import java.util.ArrayList;

public class NurAppointmentList extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String>list;
    ArrayAdapter<String> adapter;
    Doctor doctor;

    ListView ListView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_nur_appointment_list);

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
