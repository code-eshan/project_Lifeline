package com.lifelinemadproject.lifelinehospital;

import androidx.appcompat.app.AppCompatActivity;

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
import com.lifelinemadproject.lifelinehospital.Nurse.Nurse;
import com.lifelinemadproject.lifelinehospital.Patient.Patient;

public class NurseRegistrationUI extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //DECLARING FIELDS
    EditText sid,first,last,username,password;

    Button btnNurReg;

    DatabaseReference dbRef;
    Nurse Nurse;

    public static String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_registration_ui);

        //INITIALIZING FIELDS
        sid = (EditText)findViewById(R.id.etNurStaffID);
        first = (EditText)findViewById(R.id.etNurFirstName);
        last = (EditText)findViewById(R.id.etNurLastName);
        username = (EditText)findViewById(R.id.etNurUsername);
        password = (EditText)findViewById(R.id.etNurPassword);


        //NATIONALITY SPINNER
      //  nationality = (Spinner)findViewById(R.id.spPatNationality);
        //ArrayAdapter<CharSequence> Nadapter = ArrayAdapter.createFromResource(this,R.array.nationality,android.R.layout.simple_spinner_item);
      //  Nadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      //  nationality.setAdapter(Nadapter);

      //  nationality.setOnItemSelectedListener(this);

        //SEX SPINNER
      //  sex = (Spinner)findViewById(R.id.spPatSex);
      //  ArrayAdapter<CharSequence> Sadapter = ArrayAdapter.createFromResource(this,R.array.sex,android.R.layout.simple_spinner_item);
       // Sadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       // sex.setAdapter(Sadapter);

       // sex.setOnItemSelectedListener(this);

        btnNurReg = (Button)findViewById(R.id.btnRegisterNurse);

        //NEW NURSE OBJECT
        Nurse = new Nurse();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Nurse");

        btnNurReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(sid.getText().toString()) || TextUtils.isEmpty(first.getText().toString().trim())
                        || TextUtils.isEmpty(last.getText().toString()) || TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())
                        )
                {
                    customToastUserTypeError("Please fill all the fields.");
                }
                else {

                    try {
                        Nurse.setSid(sid.getText().toString().trim());
                        Nurse.setfName(first.getText().toString().trim());
                        Nurse.setlName(last.getText().toString().trim());
                        Nurse.setUserName(username.getText().toString().trim());
                        Nurse.setPassword(password.getText().toString().trim());


                        //INSERT INTO DATABASE - NORMAL METHOD
                        //dbRef.push().setValue(Nurse);

                        //ASSIGNING THE USERNAME AND NIC NUMBER AS THE KEY
                        key = username.getText().toString().trim();
                        //INSERT INTO DATABASE - WITH AUTO INCREMENT
                        dbRef.child(key).setValue(Nurse);

                        customToastShow();
                        goBackLogin();

                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"ERROR !",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }
        );

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
        Intent intent = new Intent(NurseRegistrationUI.this,LifeLineLogin.class);
        startActivity(intent);
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
