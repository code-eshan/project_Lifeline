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
import com.lifelinemadproject.lifelinehospital.Doctor.Doctor;
import com.lifelinemadproject.lifelinehospital.Patient.Patient;

public class DoctorProfile extends AppCompatActivity {

    DatabaseReference ref;

    EditText stf,nic,fname,lname,contNum;

    Doctor doctor;

    String uName,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        //username = (EditText)findViewById(R.id.etDocUsername);

        final Intent intent = getIntent();
        uName = intent.getStringExtra(DoctorMainUI.EXTRA_USERNAME);
        //username.setText(uName);


        doctor = new Doctor();

        stf = (EditText)findViewById(R.id.etDocSTF);
        nic = (EditText)findViewById(R.id.etDocNIC);
        fname = (EditText)findViewById(R.id.etDocFirstName);
        lname = (EditText)findViewById(R.id.etDocLastName);
        contNum = (EditText)findViewById(R.id.etDocContactNumber);


        Button btnUpdateDoctor = (Button)findViewById(R.id.btnUpdateDoctor);
        Button btnDeleteDoctor = (Button)findViewById(R.id.btnDeleteDoctor);

        ref = FirebaseDatabase.getInstance().getReference().child("Doctor");

        ref.child(uName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try {
                    Doctor doctor = dataSnapshot.getValue(Doctor.class);
                    stf.setText(doctor.getStfID());
                    nic.setText(doctor.getNIC());
                    fname.setText(doctor.getfName());
                    lname.setText(doctor.getlName());
                    contNum.setText(doctor.getContNum());
                    password = doctor.getPassword();
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
        btnUpdateDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Doctor");

                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try {
                            doctor.setStfID(stf.getText().toString().trim());
                            doctor.setUserName(uName);
                            doctor.setPassword(password);
                            doctor.setNIC(nic.getText().toString().trim());
                            doctor.setfName(fname.getText().toString().trim());
                            doctor.setlName(lname.getText().toString().trim());
                            doctor.setContNum(contNum.getText().toString().trim());

                            ref = FirebaseDatabase.getInstance().getReference().child("Doctor").child(uName);
                            ref.setValue(doctor);

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

        btnDeleteDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Doctor");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        if(dataSnapshot.hasChild(uName)) {

                            ref = FirebaseDatabase.getInstance().getReference().child("Doctor").child(uName);
                            ref.removeValue();
                            customToastShow("Doctor Record Deleted Successfully.");
                            goBackLogin();
                        }
                        else {
                            customToastError("Error Deleting Doctor.");
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
        Intent mIntent = new Intent(DoctorProfile.this,LifeLineLogin.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        }
        startActivity(mIntent);
    }


}
