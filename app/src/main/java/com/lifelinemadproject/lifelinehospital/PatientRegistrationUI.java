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
import com.lifelinemadproject.lifelinehospital.Patient.Patient;

import org.w3c.dom.Text;


public class PatientRegistrationUI extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText nic,first,last,contNum,username,password,age;
    Spinner nationality,sex;

    Button btnPatReg;

    DatabaseReference dbRef;
    Patient patient;

    public static String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration_ui);

        //INITIALIZING ALL FIELDS
        nic = (EditText)findViewById(R.id.etPatNIC);
        first = (EditText)findViewById(R.id.etPatFirstName);
        last = (EditText)findViewById(R.id.etPatLastName);
        contNum = (EditText)findViewById(R.id.etPatContactNumber);
        username = (EditText)findViewById(R.id.etPatUsername);
        password = (EditText)findViewById(R.id.etPatPassword);
        age = (EditText)findViewById(R.id.etPatAge);

        //NATIONALITY SPINNER
         nationality = (Spinner)findViewById(R.id.spPatNationality);
        ArrayAdapter<CharSequence> Nadapter = ArrayAdapter.createFromResource(this,R.array.nationality,android.R.layout.simple_spinner_item);
        Nadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nationality.setAdapter(Nadapter);

        nationality.setOnItemSelectedListener(this);

        //SEX SPINNER
         sex = (Spinner)findViewById(R.id.spPatSex);
        ArrayAdapter<CharSequence> Sadapter = ArrayAdapter.createFromResource(this,R.array.sex,android.R.layout.simple_spinner_item);
        Sadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sex.setAdapter(Sadapter);

        sex.setOnItemSelectedListener(this);

        btnPatReg = (Button)findViewById(R.id.btnRegisterPatient);

        patient = new Patient();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Patient");

        btnPatReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(TextUtils.isEmpty(nic.getText().toString()) || TextUtils.isEmpty(first.getText().toString().trim())
                        || TextUtils.isEmpty(last.getText().toString()) || TextUtils.isEmpty(contNum.getText().toString())
                        || TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())
                        || nationality.getSelectedItemPosition() == 0 || sex.getSelectedItemPosition() == 0 || TextUtils.isEmpty(age.getText().toString()))
                    {
                        customToastUserTypeError("Please fill all the fields.");
                    }
                    else {

                        try {
                            patient.setNIC(nic.getText().toString().trim());
                            patient.setfName(first.getText().toString().trim());
                            patient.setlName(last.getText().toString().trim());
                            patient.setContNum(contNum.getText().toString().trim());
                            patient.setUserName(username.getText().toString().trim());
                            patient.setPassword(password.getText().toString().trim());
                            patient.setNationality(nationality.getSelectedItem().toString().trim());
                            patient.setSex(sex.getSelectedItem().toString().trim());
                            patient.setAge(Integer.parseInt(age.getText().toString().trim()));

                            //INSERT INTO DATABASE - NORMAL METHOD
                            //dbRef.push().setValue(patient);

                            //ASSIGNING THE USERNAME AND NIC NUMBER AS THE KEY
                            key = username.getText().toString().trim();
                            //INSERT INTO DATABASE - WITH AUTO INCREMENT
                            dbRef.child(key).setValue(patient);

                            customToastShow("Patient Registered Successfully");
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
        Intent intent = new Intent(PatientRegistrationUI.this,LifeLineLogin.class);
        startActivity(intent);
    }

    private void customToastShow(String message) {
        Toast toast = new Toast(getApplicationContext());
        View view = LayoutInflater.from(this).inflate(R.layout.toast_layout,null);
        TextView toastTextView  = view.findViewById(R.id.textViewToast);
        toastTextView.setText(message);
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
