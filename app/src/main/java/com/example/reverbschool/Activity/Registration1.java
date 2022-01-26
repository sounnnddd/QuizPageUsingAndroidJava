package com.example.reverbschool.Activity;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reverbschool.Database.Student;
import com.example.reverbschool.ModelClass.Student_details;
import com.example.reverbschool.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

public class Registration1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    //for vars
    public static EditText fname, sname, rno, username, password;
    Spinner spin_class, spin_section;
    RadioGroup radgrp;
    RadioButton btn_rad;//for getting the selected radbtn
    Button btn, btn_date;
    TextView dobText, ageText;
    String[] classs = {"10", "11", "12"};
    String[] section = {"A", "B", "C", "D"};
    public static int age = 0;//this is for getting the age of user from the datepicker
    boolean uflag = true, pflag = true;
    public static boolean flag = true;
    Student_details details;
    List<Student_details> detailsList = new ArrayList<>();
    int class_value, rno_value;
    Student studentdatabse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration1);
        studentdatabse = new Student(Registration1.this);
        //initialize
        fname = (EditText) findViewById(R.id.edit_fname);
        sname = (EditText) findViewById(R.id.edit_sname);
        spin_class = (Spinner) findViewById(R.id.spin_class);
        spin_section = (Spinner) findViewById(R.id.spin_section);
        rno = (EditText) findViewById(R.id.edit_rno);
        radgrp = (RadioGroup) findViewById(R.id.radgrp);
        username = (EditText) findViewById(R.id.second_uname);
        password = (EditText) findViewById(R.id.second_pwd);
        btn = (Button) findViewById(R.id.submit);
        dobText = (TextView) findViewById(R.id.text_dob_choose);
        ageText = (TextView) findViewById(R.id.text_age);
        btn_date = (Button) findViewById(R.id.btn_date);
        //Spinner Class
        ArrayAdapter aa = new ArrayAdapter(this, R.layout.support_spinner, classs);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_class.setAdapter(aa);
        //Spinner Section
        ArrayAdapter aa1 = new ArrayAdapter(this, R.layout.support_spinner, section);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_section.setAdapter(aa1);
        //btnDate click
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Registration1.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dobText.setText(dayOfMonth + "." + (month + 1) + "." + year);
                        datepicker(year, (month + 1), dayOfMonth);
                    }
                }, 0, 0, 0);
                datePickerDialog.show();
            }
        });
        //btnSubmit click
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedBtn = radgrp.getCheckedRadioButtonId();
                btn_rad = (RadioButton) findViewById(selectedBtn);

                final String unameRegEx = "^[a-zA-Z]\\w+@school.com$";
                if (!Pattern.matches(unameRegEx, username.getText().toString()))
                    uflag = false;
                final String pwdRegEx = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{5,10}";
                if (!Pattern.matches(pwdRegEx, password.getText().toString()))
                    pflag = false;
                if (!(uflag && pflag && flag)) {
                    Toast.makeText(Registration1.this, ("Please check your username, password and DOB...there seems to be an error!"), Toast.LENGTH_LONG).show();
                    if (!flag)
                        Toast.makeText(Registration1.this, ("Age calculation is not possible" + age), Toast.LENGTH_LONG).show();
                } else {
                    class_value = Integer.parseInt(spin_class.getSelectedItem().toString());
                    rno_value = Integer.parseInt(rno.getText().toString());
                    //section_value= Integer.parseInt(spin_section.getSelectedItem().toString());
                    //addHandler() to be inserted here
                    details = new Student_details(fname.getText().toString(), sname.getText().toString(), class_value, spin_section.getSelectedItem().toString(), btn_rad.getText().toString(), username.getText().toString(), password.getText().toString(), rno_value, age);
                    detailsList.add(details);
                    studentdatabse.addHandler1(details);
                    Toast.makeText(Registration1.this, "You are Done!", Toast.LENGTH_LONG).show();
                    flag=true;
                    uflag=true;
                    pflag=true;
                }


            }
        });


    }

    public void datepicker(int dobYear, int dobMonth, int dobDay) {
        //obtaining age
        final int presentYear = 2019;
        final int presentMonth = 6;
        final int presentDay = 14;

        if (dobYear >= presentYear) {
            flag = false;
        } else {
            age = -dobYear + presentYear;
            if ((dobMonth > presentMonth) || ((dobMonth == presentMonth) && (dobDay > presentDay)))
                age--;
        }
        ageText.setText("" + age);//age and flag are important variables here


    }


}
