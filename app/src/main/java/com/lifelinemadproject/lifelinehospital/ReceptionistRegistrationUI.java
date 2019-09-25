package com.lifelinemadproject.lifelinehospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lifelinemadproject.lifelinehospital.Receptionist.Receptionist;

public class ReceptionistRegistrationUI extends AppCompatActivity  {

    EditText sID;
    EditText first;
    EditText last;
    EditText username;
    EditText password;
    EditText age;
    EditText contNum;

    Button btnRecReg;

    DatabaseReference dbRef;
    Receptionist receptionist;

    public static  String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptionist_registration_ui);

        sID = (EditText)findViewById(R.id.etRecStaffID);
        first = (EditText)findViewById(R.id.etRecFirstName);
        last =(EditText)findViewById(R.id.etRecLastName);
        contNum = (EditText)findViewById(R.id.etRecContactNumber) ;
        age = (EditText)findViewById(R.id.etRecAge);
        username = (EditText)findViewById(R.id.etRecUsername);
        password =(EditText)findViewById(R.id.etRecPassword);

        btnRecReg = (Button)findViewById(R.id.btnRegisterReceptionist);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Receptionist");

        receptionist = new Receptionist();

        btnRecReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(sID.getText().toString()) || TextUtils.isEmpty(first.getText().toString().trim()) || TextUtils.isEmpty(last.getText().toString())  || TextUtils.isEmpty(contNum.getText().toString()) || TextUtils.isEmpty(age.getText().toString())|| TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString()))
                {
                    customToastUserTypeError("Please fill all the fields.");
                }
                else {

                    try {

                        receptionist.setsID(sID.getText().toString().trim());
                        receptionist.setfName(first.getText().toString().trim());
                        receptionist.setlName(last.getText().toString().trim());
                        receptionist.setUserName(username.getText().toString().trim());
                        receptionist.setPassword(password.getText().toString().trim());
                        receptionist.setContNum(contNum.getText().toString().trim());
                        receptionist.setAge(Integer.parseInt(age.getText().toString().trim()));
                        //INSERT INTO DATABASE - NORMAL METHOD
                        //dbRef.push().setValue(patient);

                        //ASSIGNING THE USERNAME AND NIC NUMBER AS THE KEY
                        key = username.getText().toString().trim();
                        //INSERT INTO DATABASE - WITH AUTO INCREMENT
                        dbRef.child(key).setValue(receptionist);

                        customToastShow();
                        goBackLogin();

                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"ERROR !",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
    private void customToastUserTypeError(String message) {
        Toast toast = new Toast(getApplicationContext());
        View view = LayoutInflater.from(this).inflate(R.layout.toast_error_layout,null);
        TextView toastTextView  = view.findViewById(R.id.textViewToast);
        toastTextView.setText(message);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    private void goBackLogin() {
        Intent intent = new Intent(ReceptionistRegistrationUI.this,LifeLineLogin.class);
        startActivity(intent);
    }

    private void customToastShow() {
        Toast toast = new Toast(getApplicationContext());
        View view = LayoutInflater.from(this).inflate(R.layout.toast_layout,null);
        TextView toastTextView  = view.findViewById(R.id.textViewToast);
        toastTextView.setText("Patient Registered Successfully");
        toast.setView(view);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }


}
