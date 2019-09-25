package com.lifelinemadproject.lifelinehospital;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReceptionistUI extends AppCompatActivity {

    TextView recUsername;

    String uName;

    public static final String EXTRA = "username pass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptionist_ui);

        Intent intent = getIntent();
        uName = intent.getStringExtra(LifeLineLogin.EXTRA_MESSAGE);

        recUsername = (TextView)findViewById(R.id.tvRecUsername);
        recUsername.setText(uName);

        Button btnProfileRec = (Button)findViewById(R.id.btnRecProfile);
        Button btnViewAppointments = (Button)findViewById(R.id.btnRecViewAppointments);
        Button btnViewDoctorList = (Button)findViewById(R.id.btnRecViewDoctors);

        btnProfileRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openReceptionistProfile();
            }
        });

        btnViewAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAppointments();
            }
        });

        btnViewDoctorList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDocList();
            }
        });
    }

    private void openDocList() {
        Intent intent = new Intent(ReceptionistUI.this,DoctorList.class);
        startActivity(intent);
    }

    private void openAppointments() {
        Intent intent = new Intent(ReceptionistUI.this,RecAppointments.class);
        startActivity(intent);
    }

    private void openReceptionistProfile() {
        Intent intent = new Intent(ReceptionistUI.this,ReceptionistProfile.class);
        intent.putExtra(EXTRA,uName);
        startActivity(intent);
    }
}
