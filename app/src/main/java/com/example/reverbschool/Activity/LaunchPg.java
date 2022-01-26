package com.example.reverbschool.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reverbschool.Database.Student;
import com.example.reverbschool.R;

import java.util.regex.Pattern;

public class LaunchPg extends AppCompatActivity {
    TextView signIn;
    EditText username, password;
    Button loginBtn;
    Student student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_pg);
        //vars
        signIn = (TextView) findViewById(R.id.textViewSignIn);
        username = (EditText) findViewById(R.id.edit_username);
        password = (EditText) findViewById(R.id.edit_password);
        loginBtn = (Button) findViewById(R.id.btn_login);
        student=new Student(this);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checking for the equivalence with the regEx patterns : regCheck
                String patternU = "^[a-zA-Z]\\w+@school.com$";
                String patternP = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{5,10}";
                boolean regCheck = (Pattern.matches(patternU, username.getText().toString())
                        && Pattern.matches(patternP, password.getText().toString()));
        /*if regCheck is true, then check if username corresponds to the password from the dbms. else, then prepare toast msg
          if there's a match, then proceed to the quiz page
          else, then prepare a toast message */
                if (regCheck) {
                    boolean handlerFlag = student.checkHandler(username.getText().toString(), password.getText().toString());
                    if (handlerFlag) {
                        Intent i=new Intent(LaunchPg.this,QuizPage.class);
                        startActivity(i);
                        Toast.makeText(LaunchPg.this, "Your login is successful!", Toast.LENGTH_LONG).show();
                    } else
                    {

//                        password.setError("ERROR! Check your username,password!");
//                        username.setError("ERROR! Check your username,password!");
                        Toast.makeText(LaunchPg.this, "ERROR! Check your username,password!", Toast.LENGTH_LONG).show();
                    handlerFlag=true;
                    }


                } else
                {Toast.makeText(LaunchPg.this, "ERROR! Check your username,password!", Toast.LENGTH_LONG).show();
//                    password.setError("ERROR! Check your username,password!");
//                    username.setError("ERROR! Check your username,password!");
                regCheck=true;}






            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaunchPg.this, Registration1.class);
                startActivity(intent);

            }
        });
    }
}
