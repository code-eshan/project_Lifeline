package com.lifelinemadproject.lifelinehospital;

import  android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lifelinemadproject.lifelinehospital.Receptionist.Receptionist;

public class ReceptionistProfile extends AppCompatActivity {

    DatabaseReference ref;

    EditText sID, fname, lname,contNum,age;

    Receptionist Rec;

    String uName,staffID, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptionist_profile);

        final Intent intent = getIntent();
        uName = intent.getStringExtra(ReceptionistUI.EXTRA);

        Rec = new Receptionist();

        sID = (EditText) findViewById(R.id.etRecStaffID);
        fname = (EditText) findViewById(R.id.etRecFirstName);
        lname = (EditText) findViewById(R.id.etRecLastName);
        contNum = (EditText)findViewById(R.id.etRecContactNumber);
        age = (EditText)findViewById(R.id.etRecAge);

        Button btnUpdateReceptionist = (Button) findViewById(R.id.btnUpdateReceptionist);
        Button btnDeleteReceptionist = (Button) findViewById(R.id.btnDeleteReceptionist);

        ref = FirebaseDatabase.getInstance().getReference().child("Receptionist");

        ref.child(uName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try {
                    Receptionist receptionist = dataSnapshot.getValue(Receptionist.class);
                    sID.setText(receptionist.getsID());
                    fname.setText(receptionist.getfName());
                    lname.setText(receptionist.getlName());
                    contNum.setText(receptionist.getContNum());
                    age.setText(receptionist.getAge().toString());
                    Password = receptionist.getPassword();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btnUpdateReceptionist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Receptionist");

                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try {

                            Rec.setUserName(uName);
                            Rec.setsID(sID.getText().toString().trim());
                            Rec.setPassword(Password);
                            Rec.setfName(fname.getText().toString().trim());
                            Rec.setlName(lname.getText().toString().trim());
                            Rec.setContNum(contNum.getText().toString().trim());
                            Rec.setAge(Integer.parseInt(age.getText().toString().trim()));

                            ref = FirebaseDatabase.getInstance().getReference().child("Receptionist").child(uName);
                            ref.setValue(Rec);

                            customToastShow("Update Successful.");
                            finish();

                        } catch (Exception e) {
                            customToastError("Failed to update.");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        btnDeleteReceptionist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Receptionist");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.hasChild(uName)) {

                            ref = FirebaseDatabase.getInstance().getReference().child("Receptionist").child(uName);
                            ref.removeValue();
                            customToastShow("Receptionist Record Deleted Successfully.");
                            goBackLogin();
                        } else {
                            customToastError("Error Deleting Patient");
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
        View view = LayoutInflater.from(this).inflate(R.layout.toast_layout, null);
        TextView toastTextView = view.findViewById(R.id.textViewToast);
        toastTextView.setText(message);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private void customToastError(String message) {
        Toast toast = new Toast(getApplicationContext());
        View view = LayoutInflater.from(this).inflate(R.layout.toast_error_layout, null);
        TextView toastTextView = view.findViewById(R.id.textViewToast);
        toastTextView.setText(message);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private void goBackLogin() {
        Intent mIntent = new Intent(ReceptionistProfile.this, LifeLineLogin.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        }
        startActivity(mIntent);

    }
}






