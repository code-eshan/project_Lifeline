package com.lifelinemadproject.lifelinehospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PatientMainUI extends AppCompatActivity {

    TextView welcomeUser;

    String uName;

    public static final String EXTRA_USERNAME = "username pass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_main_ui);


        Intent intent = getIntent();
        uName = intent.getStringExtra(LifeLineLogin.EXTRA_MESSAGE);


        welcomeUser = (TextView)findViewById(R.id.tvPatUserNAME);
        welcomeUser.setText(uName);

        Button btnPatProfile = (Button)findViewById(R.id.btnPatProfile);
        Button btnPatCreateAppointment = (Button)findViewById(R.id.btnPatMakeAppointment);
        Button btnViewDoctorslist = (Button)findViewById(R.id.btnViewDoctorList);

        btnPatCreateAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreateAppointmentUI();
            }
        });

        btnViewDoctorslist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDoctorsList();
            }
        });

        btnPatProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPatientProfile();
            }
        });
    }

    private void openDoctorsList() {
        Intent intent = new Intent(PatientMainUI.this,PatDocListUI.class);
        startActivity(intent);
    }
    private void openCreateAppointmentUI() {
        Intent intent = new Intent(PatientMainUI.this,MakeAppointmentUI.class);
        intent.putExtra(EXTRA_USERNAME,uName);
        startActivity(intent);
    }

    private void openPatientProfile() {
        Intent intent = new Intent(PatientMainUI.this,PatientProfile.class);
        intent.putExtra(EXTRA_USERNAME,uName);
        startActivity(intent);
    }
}
