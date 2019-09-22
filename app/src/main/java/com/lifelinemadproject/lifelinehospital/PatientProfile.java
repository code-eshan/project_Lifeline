package com.lifelinemadproject.lifelinehospital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lifelinemadproject.lifelinehospital.Patient.Patient;

public class PatientProfile extends AppCompatActivity {

    DatabaseReference ref;

    EditText nic,fname,lname,contNum,age;

    Patient pat;

    String uName,nationality,sex,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        final Intent intent = getIntent();
        uName = intent.getStringExtra(PatientMainUI.EXTRA_USERNAME);

        pat = new Patient();

        nic = (EditText)findViewById(R.id.etPatNIC);
        fname = (EditText)findViewById(R.id.etPatFirstName);
        lname = (EditText)findViewById(R.id.etPatLastName);
        contNum = (EditText)findViewById(R.id.etPatContactNumber);
        age = (EditText)findViewById(R.id.etPatAge);

        Button btnUpdatePatient = (Button)findViewById(R.id.btnUpdatePatient);
        Button btnDeletePatient = (Button)findViewById(R.id.btnDeletePatient);

        ref = FirebaseDatabase.getInstance().getReference().child("Patient");

        ref.child(uName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try {
                    Patient patient = dataSnapshot.getValue(Patient.class);
                    nic.setText(patient.getNIC());
                    fname.setText(patient.getfName());
                    lname.setText(patient.getlName());
                    contNum.setText(patient.getContNum());
                    age.setText(patient.getAge().toString());

                    nationality = patient.getNationality();
                    sex = patient.getSex();
                    password = patient.getPassword();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //UPDATE DETAILS OF PATIENT
        btnUpdatePatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Patient");

                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try {

                            pat.setUserName(uName);
                            pat.setPassword(password);

                            pat.setNIC(nic.getText().toString().trim());
                            pat.setfName(fname.getText().toString().trim());
                            pat.setlName(lname.getText().toString().trim());
                            pat.setContNum(contNum.getText().toString().trim());
                            pat.setAge(Integer.parseInt(age.getText().toString().trim()));

                            pat.setNationality(nationality);
                            pat.setSex(sex);

                            ref = FirebaseDatabase.getInstance().getReference().child("Patient").child(uName);
                            ref.setValue(pat);

                            customToastShow("Update Successful.");
                            finish();

                        }
                        catch (Exception e) {
                            customToastError("Failed to update.");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnDeletePatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Patient");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.hasChild(uName)) {

                            ref = FirebaseDatabase.getInstance().getReference().child("Patient").child(uName);
                            ref.removeValue();
                            customToastShow("Patient Record Deleted Successfully.");
                            goBackLogin();
                        }
                        else {
                            customToastError("Error Deleting Patient.");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
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

    private void goBackLogin() {
        Intent mIntent = new Intent(PatientProfile.this,LifeLineLogin.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        }
        startActivity(mIntent);
    }
}
