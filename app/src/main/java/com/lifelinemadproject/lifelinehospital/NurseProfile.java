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

public class NurseProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_profile);

        Button btnUpdateNurse = (Button)findViewById(R.id.btnUpdateNurse);
        Button btnDeleteNurse = (Button)findViewById(R.id.btnDeleteNurse);

        btnUpdateNurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUpdatedToast();
                Intent intent = new Intent(NurseProfile.this,NurseMainUI.class);
                startActivity(intent);
            }
        });

        btnDeleteNurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeletedToast();
                Intent intent = new Intent(NurseProfile.this,LifeLineLogin.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void showDeletedToast() {
        Toast toast = new Toast(getApplicationContext());
        View view = LayoutInflater.from(this).inflate(R.layout.toast_error_layout,null);
        TextView toastTextView  = view.findViewById(R.id.textViewToast);
        toastTextView.setText("Deleted Record.");
        toast.setView(view);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    private void showUpdatedToast() {
        Toast toast = new Toast(getApplicationContext());
        View view = LayoutInflater.from(this).inflate(R.layout.toast_layout,null);
        TextView toastTextView  = view.findViewById(R.id.textViewToast);
        toastTextView.setText("Update Successful.");
        toast.setView(view);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}
