package com.lifelinemadproject.lifelinehospital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lifelinemadproject.lifelinehospital.Appointment.Appointment;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;

public class MakeAppointmentUI extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Context mContext = this;
    EditText problem,date,time;
    String uName,key;

    Appointment appointment;

    DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_appointment_ui);

        Intent intent = getIntent();
        uName = intent.getStringExtra(PatientMainUI.EXTRA_USERNAME);

        problem = (EditText)findViewById(R.id.etPatProblem);
        date = (EditText)findViewById(R.id.etSelectedDate);
        time = (EditText)findViewById(R.id.etPickTime);

        appointment = new Appointment();

        dbReference = FirebaseDatabase.getInstance().getReference().child("Appointment");

        Button btnSelectDate = (Button)findViewById(R.id.btnSelectDate);
        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"Date Picker");
            }
        });

        Button btnPickTime = (Button)findViewById(R.id.btnSelectTime);

        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        btnPickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        EditText timeText = (EditText)findViewById(R.id.etPickTime);
                        timeText.setText(hourOfDay + " : " + minute);
                    }
                },hour,minute,android.text.format.DateFormat.is24HourFormat(mContext));
                timePickerDialog.show();
            }
        });


        Button btnRequestAppointment = (Button)findViewById(R.id.btnRequestAppointment);

        btnRequestAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(problem.getText().toString()) || TextUtils.isEmpty(date.getText().toString())
                    || TextUtils.isEmpty(time.getText().toString())) {
                    customToastError("Fill out all the fields.");
                }
                else {

                    appointment.setUsername(uName);
                    appointment.setProblem(problem.getText().toString());
                    appointment.setDate(date.getText().toString());
                    appointment.setTime(time.getText().toString().trim());

                    //ASSIGNING THE USERNAME AND NIC NUMBER AS THE KEY
                    key = uName;

                    //INSERT INTO DATABASE - WITH AUTO INCREMENT
                    dbReference.child(key).setValue(appointment);

                    showAppointmentRequestedToast();
                    finish();
                }


            }
        });

    }

    private void showAppointmentRequestedToast() {
        Toast toast = new Toast(getApplicationContext());
        View view = LayoutInflater.from(this).inflate(R.layout.toast_layout,null);
        TextView toastTextView  = view.findViewById(R.id.textViewToast);
        toastTextView.setText("Appointment Request Successful");
        toast.setView(view);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        TextView selectedDate = (TextView)findViewById(R.id.etSelectedDate);
        selectedDate.setText(currentDateString);
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

}
