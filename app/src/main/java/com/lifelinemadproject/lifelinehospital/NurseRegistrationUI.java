package com.lifelinemadproject.lifelinehospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NurseRegistrationUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_registration_ui);

        Button btnNurReg = (Button)findViewById(R.id.btnRegisterNurse);

        btnNurReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customToastShow();
                goBackLogin();
            }
        });
    }

    private void customToastShow() {
        Toast toast = new Toast(getApplicationContext());
        View view = LayoutInflater.from(this).inflate(R.layout.toast_layout,null);
        TextView toastTextView  = view.findViewById(R.id.textViewToast);
        toastTextView.setText("Nurse Registered Successfully");
        toast.setView(view);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
    private void goBackLogin()  {
        Intent intent = new Intent(NurseRegistrationUI.this,LifeLineLogin.class);
        startActivity(intent);
    }
}
