package com.lifelinemadproject.lifelinehospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DoctorMainUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main_ui);

        Button btnDocProfile = (Button)findViewById(R.id.btnDocProfile);
        Button btnViewAppointments = (Button)findViewById(R.id.btnDocAppointments);
        Button btnNurseList = (Button)findViewById(R.id.btnDocViewNurse);
        Button btnAssignNurse = (Button)findViewById(R.id.btnDocAssignNurse);

        btnDocProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDoctorProfile();
            }
        });

        btnViewAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewDoctorAppointments();
            }
        });

        btnNurseList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNurseList();
            }
        });

        btnAssignNurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAssignNurse();
            }
        });
    }

    private void openAssignNurse() {
        Intent intent = new Intent(DoctorMainUI.this,DocAssignNurse.class);
        startActivity(intent);
    }

    private void openNurseList() {
        Intent intent = new Intent(DoctorMainUI.this,NurseList.class);
        startActivity(intent);
    }

    private void openViewDoctorAppointments() {
        Intent intent = new Intent(DoctorMainUI.this,DocAppointmentList.class);
        startActivity(intent);
    }

    private void openDoctorProfile() {
        Intent intent = new Intent(DoctorMainUI.this,DoctorProfile.class);
        startActivity(intent);
    }
}
