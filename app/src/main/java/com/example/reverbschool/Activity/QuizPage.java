package com.example.reverbschool.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reverbschool.R;

import java.util.Objects;

public class QuizPage extends AppCompatActivity implements View.OnClickListener {
    //widgets vars
    RadioGroup q1, q2;
    RadioButton q1ans, q2ans;
    CheckBox q31, q32, q33;
    Spinner q4;
    Button submit;
    TextView textScore;
    //variables
    int score;
    String[] q4Array = {"title", "body", "html", "head"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);



        //radioGrp1
        q1 = (RadioGroup) findViewById(R.id.qs1_radGrp);
        q1ans=(RadioButton)findViewById(R.id.ques1rdbtn2);//ans rdbtn
        //radioGrp2
        q2 = (RadioGroup) findViewById(R.id.qs2_radGrp);
        q2ans = (RadioButton) findViewById(R.id.ques2rdbtn3);//ans rdbtn
        //checkbox
        q31 = (CheckBox) findViewById(R.id.qs3_checkBx1);
        q32 = (CheckBox) findViewById(R.id.qs3_checkBx2);
        q33 = (CheckBox) findViewById(R.id.qs3_checkBx3);
        //spinner
        q4 = (Spinner) findViewById(R.id.qs4_spinner);
        submit = (Button) findViewById(R.id.quizSubmitBtn);
        textScore = (TextView) findViewById(R.id.scoreText);


        ArrayAdapter aa = new ArrayAdapter(QuizPage.this, R.layout.support_spinner, q4Array);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        q4.setAdapter(aa);


        //button submit
        submit.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.quizSubmitBtn:

                /*Log.e("scoreval", "onClick: "+score );*/
                //q1 check
                if(q1ans.isChecked())
                    score++;
                //q2 check
                if (q2ans.isChecked()) {
                    score++;
                }
                //q3 check
                if (q31.isChecked() && q33.isChecked() && !q32.isChecked())
                    score++;
                //q4 check
                if (q4.getSelectedItemPosition() == 3)
                    score++;

                textScore.setText(String.valueOf(score));
                Toast.makeText(QuizPage.this, "You are done!", Toast.LENGTH_LONG).show();

                score = 0;

                break;

        }


    }
}
