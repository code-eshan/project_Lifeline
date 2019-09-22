package com.lifelinemadproject.lifelinehospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NurseMainUI extends AppCompatActivity {

    TextView welcomeUser;

    String uName;

    public static final String EXTRA_USERNAME = "username pass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_main_ui);

        Intent intent = getIntent();
        uName = intent.getStringExtra(LifeLineLogin.EXTRA_MESSAGE);


        //Accessing the Nurse Main
        welcomeUser = (TextView)findViewById(R.id.tvNurUserNAME);
        welcomeUser.setText(uName);



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


    private void openNurseProfile() {
        Intent intent = new Intent(NurseMainUI.this,NurseProfile.class);
        intent.putExtra(EXTRA_USERNAME,uName);
        startActivity(intent);
    }

    private void openNurseAppointmentList() {
        Intent intent = new Intent(NurseMainUI.this,NurAppointmentList.class);
        intent.putExtra(EXTRA_USERNAME,uName);
        startActivity(intent);
    }


}
