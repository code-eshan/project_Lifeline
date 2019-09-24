package com.lifelinemadproject.lifelinehospital;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lifelinemadproject.lifelinehospital.Doctor.Doctor;


public class DoctorRegistrationUI extends AppCompatActivity {

    EditText stf,nic,first,last,contNum,username,password;

    Button btnDocReg;

    DatabaseReference dbRef;
    Doctor doctor;

    public static String key;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registration_ui);

        //INITIALIZING ALL FIELDS
        stf = (EditText)findViewById(R.id.etDocStaffID);
        nic = (EditText)findViewById(R.id.etNIC);
        first = (EditText)findViewById(R.id.etDocFirstName);
        last = (EditText)findViewById(R.id.etDocLastName);
        contNum = (EditText)findViewById(R.id.etConNo);
        username = (EditText)findViewById(R.id.etDocUsername);
        password = (EditText)findViewById(R.id.etDocPassword);


        btnDocReg = (Button)findViewById(R.id.btnRegisterDoctor);

        doctor = new Doctor();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Doctor");

        btnDocReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //NURSELIST
                Intent intent =new Intent(DoctorRegistrationUI.this,NurseList.class);
                startActivity(intent);
                //END

                //Appointment list
                Intent intentn = new Intent(DoctorRegistrationUI.this,DocAppointmentList.class);
                startActivity(intentn);
                //end

                if(TextUtils.isEmpty(stf.getText().toString()) || TextUtils.isEmpty(nic.getText().toString()) || TextUtils.isEmpty(first.getText().toString().trim())
                        || TextUtils.isEmpty(last.getText().toString()) || TextUtils.isEmpty(contNum.getText().toString())
                        || TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString()))
                {
                    customToastUserTypeError("Please fill all the fields.");
                }
                else {

                    try {
                        doctor.setStfID(stf.getText().toString().trim());
                        doctor.setNIC(nic.getText().toString().trim());
                        doctor.setfName(first.getText().toString().trim());
                        doctor.setlName(last.getText().toString().trim());
                        doctor.setContNum(contNum.getText().toString().trim());
                        doctor.setUserName(username.getText().toString().trim());
                        doctor.setPassword(password.getText().toString().trim());


                        //INSERT INTO DATABASE - NORMAL METHOD
                        //dbRef.push().setValue(patient);

                        //ASSIGNING THE USERNAME AND NIC NUMBER AS THE KEY
                        key = username.getText().toString().trim();
                        //INSERT INTO DATABASE - WITH AUTO INCREMENT
                        dbRef.child(key).setValue(doctor);

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
        Intent intent = new Intent(DoctorRegistrationUI.this,LifeLineLogin.class);
        startActivity(intent);
    }

    private void customToastShow() {
        Toast toast = new Toast(getApplicationContext());
        View view = LayoutInflater.from(this).inflate(R.layout.toast_layout,null);
        TextView toastTextView  = view.findViewById(R.id.textViewToast);
        toastTextView.setText("Doctor Registered Successfully");
        toast.setView(view);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}
