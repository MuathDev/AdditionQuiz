package com.example.muath.additionquiz;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    private int level;

    private LinearLayout LL_questions;
    private Button bt_submit;

    private int[] xArr;
    private int[] yArr;
    private int[] ansArr;

    private TextView tv_timer;
    private CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        level = intent.getIntExtra("level",-1);
        Log.d("QuizActivity", level +"");

        LL_questions = findViewById(R.id.II_quastions);
        bt_submit = findViewById(R.id.bt_submit);

        tv_timer = findViewById(R.id.tv_timer);

        xArr = new int[LL_questions.getChildCount()];
        yArr = new int[LL_questions.getChildCount()];
        ansArr = new int[LL_questions.getChildCount()];

        loadQuestions();

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswers();
            }
        });

        timer = new CountDownTimer(40000,1000) {
            @Override
            public void onTick(long l) {

                tv_timer.setText(String.format("%02d",l/1000));

            }

            @Override
            public void onFinish() {

                checkAnswers();
            }
        };

        timer.start();

    }

    private void checkAnswers() {

        String report = " ";

        for (int i = 0; i<LL_questions.getChildCount(); i++){

            EditText et = (EditText) ((LinearLayout) LL_questions.getChildAt(i)).getChildAt(1);

            if (et.getText().toString().isEmpty()){
                report += "Q # " + (i+1) + "is wrong\n";
                continue;
            }
            int ans = Integer.parseInt(et.getText().toString());
            if (ans == xArr[i]+ yArr[i]){
                report +="Q #" + (i+1) + "is Correct\n";
            }
            else {
                report += "Q # " + (i+1) + "is wrong\n";
            }
        }

        timer.cancel();

        Intent intent = new Intent(this,AdditionQuizActivity.class);
        intent.putExtra("report", report);
        startActivity(intent);
        finish();

    }


    private void loadQuestions() {
        for (int i = 0; i<LL_questions.getChildCount(); i++){
            xArr[i] = (int)(Math.random() * Math.pow(10, level));
            yArr[i] = (int)(Math.random() * Math.pow(10, level));
            TextView tv = (TextView) ((LinearLayout) LL_questions.getChildAt(i)).getChildAt(0);
            tv.setText(String.format("what's %d + %d ?", xArr[i], yArr[i]));
        }
    }
}
