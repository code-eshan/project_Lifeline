package com.lifelinemadproject.lifelinehospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NurseMainUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_main_ui);

        Button btnNurseProfile = (Button)findViewById(R.id.btnNurProfile);
        Button btnNurseViewAppointments = (Button)findViewById(R.id.btnNurAppointments);

        btnNurseProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNurseProfile();
            }
        });

        btnNurseViewAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNurseAppointmentList();
            }
        });

    }

    private void openNurseAppointmentList() {
        Intent intent = new Intent(NurseMainUI.this,NurAppointmentList.class);
        startActivity(intent);
    }

    private void openNurseProfile() {
        Intent intent = new Intent(NurseMainUI.this,NurseProfile.class);
        startActivity(intent);
    }
}
