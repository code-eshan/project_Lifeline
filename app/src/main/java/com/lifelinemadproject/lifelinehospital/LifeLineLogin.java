package com.lifelinemadproject.lifelinehospital;

import androidx.annotation.NonNull;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lifelinemadproject.lifelinehospital.Nurse.Nurse;
import com.lifelinemadproject.lifelinehospital.Patient.Patient;

public class LifeLineLogin extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner typeSpinner;
    EditText username,password;

    public static String userTypeValue;

    private DatabaseReference ref;

    String uName,pWord;

    public static final String EXTRA_MESSAGE = "USERNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lifeline_login);

        typeSpinner = (Spinner)findViewById(R.id.spUserType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.userTypes,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);

        typeSpinner.setOnItemSelectedListener(this);

        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        Button btnRegNow = (Button)findViewById(R.id.btnRegister);

        username = (EditText)findViewById(R.id.etUsername);
        password = (EditText)findViewById(R.id.etPassword);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString()) || userTypeValue.equals("NULL")) {
                    if (TextUtils.isEmpty(username.getText().toString())) {
                        customToastError("Enter a username");
                    }
                    else if (TextUtils.isEmpty(password.getText().toString())) {
                        customToastError("Enter a password");
                    }
                    else {
                        customToastError("Select a user type.");
                    }
                }
                else {

                    if(userTypeValue.equals("Patient")) {

                        ref = FirebaseDatabase.getInstance().getReference().child("Patient");

                        uName = username.getText().toString();
                        pWord = password.getText().toString();


                            ref.child(uName).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    try {
                                        Patient patient = dataSnapshot.getValue(Patient.class);
                                        if (pWord.equals(patient.getPassword())) {
                                            customToastShow("Login Successful.");
                                            openPatientMainUI();
                                        }
                                        else {
                                            customToastError("Invalid Credentials.");
                                        }
                                    }
                                    catch (NullPointerException e) {
                                        customToastError("Patient Record Not Found.");
                                        clearControls();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                    }
                    else if(userTypeValue.equals("Doctor")) {
                        openDoctorMainUI();
                    }














                    else if(userTypeValue.equals("Nurse")) {

                        ref = FirebaseDatabase.getInstance().getReference().child("Nurse");

                        uName = username.getText().toString();
                        pWord = password.getText().toString();


                        ref.child(uName).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                try {
                                    Nurse nurse = dataSnapshot.getValue(Nurse.class);
                                    if (pWord.equals(nurse.getPassword())) {
                                        customToastShow("Login Successful.");
                                        openNurseMainUI();
                                    }
                                    else {
                                        customToastError("Invalid Credentials.");
                                    }
                                }
                                catch (NullPointerException e) {
                                    customToastError("Nurse Record Not Found.");
                                    clearControls();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                    else if(userTypeValue.equals("Receptionist")) {
                        openReceptionistUI();
                    }
                }
            }
        });


        btnRegNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterNowUI();
            }
        });

    }

    private void openReceptionistUI(){
        Intent intent = new Intent(LifeLineLogin.this,ReceptionistUI.class);
        startActivity(intent);
    }
    private void openNurseMainUI() {
        Intent intent = new Intent(LifeLineLogin.this,NurseMainUI.class);
        intent.putExtra(EXTRA_MESSAGE,uName);
        startActivity(intent);
    }

    private void openDoctorMainUI() {
        Intent intent = new Intent(LifeLineLogin.this,DoctorMainUI.class);
        startActivity(intent);
    }

    private void openRegisterNowUI() {
        Intent intent = new Intent(LifeLineLogin.this,RegisterNowUI.class);
        startActivity(intent);
    }

    public void openPatientMainUI() {

        Intent intent = new Intent(LifeLineLogin.this,PatientMainUI.class);
        intent.putExtra(EXTRA_MESSAGE,uName);
        startActivity(intent);

    }

    private void clearControls() {
        username.setText("");
        password.setText("");
        userTypeValue.equals("NULL");
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

    private void customToastError(String message) {
        Toast toast = new Toast(getApplicationContext());
        View view = LayoutInflater.from(this).inflate(R.layout.toast_error_layout,null);
        TextView toastTextView  = view.findViewById(R.id.textViewToast);
        toastTextView.setText(message);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        if (parent.getItemAtPosition(position).toString().equals("Select User")) {
            //Do Nothing
            userTypeValue = "NULL";
        }
        else if (parent.getItemAtPosition(position).toString().equals("Patient")) {
            String text = parent.getItemAtPosition(position).toString();
            userTypeValue = text;
            Toast.makeText(parent.getContext(),"Selected User : " + text,Toast.LENGTH_SHORT).show();
        }
        else if (parent.getItemAtPosition(position).toString().equals("Doctor")) {
            String text = parent.getItemAtPosition(position).toString();
            userTypeValue = text;
            Toast.makeText(parent.getContext(),"Selected User : " + text,Toast.LENGTH_SHORT).show();

        }
        else if (parent.getItemAtPosition(position).toString().equals("Nurse")) {
            String text = parent.getItemAtPosition(position).toString();
            userTypeValue = text;
            Toast.makeText(parent.getContext(),"Selected User : " + text,Toast.LENGTH_SHORT).show();

        }
        else if (parent.getItemAtPosition(position).toString().equals("Receptionist")) {
            String text = parent.getItemAtPosition(position).toString();
            userTypeValue = text;
            Toast.makeText(parent.getContext(),"Selected User : " + text,Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
