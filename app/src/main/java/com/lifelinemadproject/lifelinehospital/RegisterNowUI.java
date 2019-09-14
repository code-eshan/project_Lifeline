package com.lifelinemadproject.lifelinehospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterNowUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_now_ui);

        Button btnPatReg = (Button)findViewById(R.id.btnPatient);
        Button btnDocReg = (Button)findViewById(R.id.btnDoctor);
        Button btnNurReg = (Button)findViewById(R.id.btnNurse);
        Button btnRecReg = (Button)findViewById(R.id.btnReceptionist);

        btnPatReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPatientRegistration();
            }
        });
        btnDocReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDoctorRegistration();
            }
        });
        btnNurReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNurseRegistration();
            }
        });
        btnRecReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openReceptionistRegistration();
            }
        });

    }

    private void openReceptionistRegistration() {
        Intent intent = new Intent(RegisterNowUI.this,ReceptionistRegistrationUI.class);
        startActivity(intent);
    }

    private void openNurseRegistration() {
        Intent intent = new Intent(RegisterNowUI.this,NurseRegistrationUI.class);
        startActivity(intent);
    }

    private void openDoctorRegistration() {
        Intent intent = new Intent(RegisterNowUI.this,DoctorRegistrationUI.class);
        startActivity(intent);
    }

    private void openPatientRegistration() {
        Intent intent = new Intent(RegisterNowUI.this,PatientRegistrationUI.class);
        startActivity(intent);
    }
}
