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
import com.lifelinemadproject.lifelinehospital.Nurse.Nurse;


public class NurseProfile extends AppCompatActivity {

    DatabaseReference ref;

    EditText sid,first,last,username,password;

    Nurse nur;

    String uName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_profile);

        final Intent intent = getIntent();
        uName = intent.getStringExtra(NurseMainUI.EXTRA_USERNAME);

        nur = new Nurse();

        sid = (EditText)findViewById(R.id.etNurStaffID);
        first = (EditText)findViewById(R.id.etNurFirstName);
        last = (EditText)findViewById(R.id.etNurLastName);
        username = (EditText)findViewById(R.id.etNurUserName);
        password = (EditText)findViewById(R.id.etNurPassword);

        Button btnUpdateNurse = (Button)findViewById(R.id.btnUpdateNurse);
        Button btnDeleteNurse = (Button)findViewById(R.id.btnDeleteNurse);

        ref = FirebaseDatabase.getInstance().getReference().child("Nurse");

        ref.child(uName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try {
                    Nurse nurse = dataSnapshot.getValue(Nurse.class);
                    sid.setText(nurse.getSid());
                    first.setText(nurse.getfName());
                    last.setText(nurse.getlName());
                    username.setText(nurse.getUserName());
                    password.setText(nurse.getPassword());

                    //password = nur.getPassword();


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
        btnUpdateNurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Nurse");

                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try {
                                //This part
                            nur.setUserName(uName);
                          // nur.setPassword(password);

                            nur.setSid(sid.getText().toString().trim());
                            nur.setfName(first.getText().toString().trim());
                            nur.setlName(last.getText().toString().trim());
                            nur.setUserName(username.getText().toString().trim());
                            nur.setPassword(password.getText().toString().trim());


                            ref = FirebaseDatabase.getInstance().getReference().child("Nurse").child(uName);
                            ref.setValue(nur);

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

        btnDeleteNurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Nurse");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.hasChild(uName)) {

                            ref = FirebaseDatabase.getInstance().getReference().child("Nurse").child(uName);
                            ref.removeValue();
                            customToastShow("Nurse Record Deleted Successfully.");
                            goBackLogin();
                        }
                        else {
                            customToastError("Error Deleting Nurse.");
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
        Intent mIntent = new Intent(NurseProfile.this,LifeLineLogin.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        }
        startActivity(mIntent);
    }
}
