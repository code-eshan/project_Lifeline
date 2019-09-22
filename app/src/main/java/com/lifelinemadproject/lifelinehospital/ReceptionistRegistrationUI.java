package com.lifelinemadproject.lifelinehospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.lifelinemadproject.lifelinehospital.Receptionist.Receptionist;

public class ReceptionistRegistrationUI extends AppCompatActivity {

    EditText sID,first,last,username,password;

    Button btnRecReg;

    DatabaseReference dbRef;
    Receptionist receptionist;

    public static String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptionist_registration_ui);

        //Initiating All Fields
        sID = (EditText)findViewById(R.id.etRecStaffID);
        first = (EditText)findViewById(R.id.etRecFirstName);
        last = (EditText)findViewById(R.id.etRecLastName);
        username = (EditText)findViewById(R.id.etRecUsername);




        Button btnRecReg = (Button)findViewById(R.id.btnRegisterReceptionist);

        btnRecReg.setOnClickListener(new View.OnClickListener() {
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
        toastTextView.setText("Receptionist Registered Successfully");
        toast.setView(view);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    private void goBackLogin() {
        Intent intent = new Intent(ReceptionistRegistrationUI.this,LifeLineLogin.class);
        startActivity(intent);
    }
}
