package com.example.practical_6b_alertdilog_datepickerdilog_timepicker_dialog;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    CharSequence[] items = {"Android", "Security", "Cloud"};
    boolean[] itemsChecked = new boolean[items.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickDialog(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("This is a dialog with simple Text...");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getBaseContext(), "OK Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getBaseContext(), "CANCEL Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setMultiChoiceItems(items, itemsChecked, new DialogInterface.OnMultiChoiceClickListener() {
            public void onClick(DialogInterface dialog, int id, boolean isChecked) {
                Toast.makeText(getBaseContext(), items[id] + (isChecked ? " checked!" : " unchecked!"), Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @SuppressWarnings("deprecation")
    public void onClickProgressDialog(View v) {
        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pDialog.setMessage("Loading.....");
        pDialog.incrementProgressBy(20);
        pDialog.setButton(Dialog.BUTTON_POSITIVE, "Stop Progress", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        pDialog.show();
    }

    public void onClickDateDialog(View v) {
        final int mYear, mMonth, mDay;
        final TextView DateDisplay = (TextView) findViewById(R.id.textView_date);
        DatePickerDialog dateDialog;
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        final DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int month, int day) {
                DateDisplay.setText(new StringBuilder().append("Date: ").append(month + 1).append(".").append(day).append("-").append(year));
            }
        };
        dateDialog = new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
        dateDialog.show();
    }

    public void onClickTimeDialog(View v) {
        final int mHour, mMinute;
        final TextView TimeDisplay = findViewById(R.id.textView_time);
        TimePickerDialog timeDialog;
        Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hour, int minute) {
                TimeDisplay.setText(new StringBuilder().append("Time: ").append(hour).append(":").append(minute));
            }
        };
        timeDialog = new TimePickerDialog(this, mTimeSetListener, mHour, mMinute, true);
        timeDialog.show();
    }
}
