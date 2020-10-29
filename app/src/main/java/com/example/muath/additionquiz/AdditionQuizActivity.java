package com.example.muath.additionquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class AdditionQuizActivity extends AppCompatActivity {

    private Spinner sp_level;

    private Button bt_start;
    private TextView tv_report;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_addition_quiz);
        sp_level = findViewById(R.id.sp_level);
        bt_start = findViewById(R.id.bt_start);
        tv_report = findViewById(R.id.tv_report);

        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });

        Intent intent = getIntent();
        String report = intent.getStringExtra("report");
        tv_report.setText(report);
        System.out.println(report);
    }

    private void startQuiz() {
        int level = sp_level.getSelectedItemPosition() + 1;
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("level", level);
        startActivity(intent);
        finish();
    }
}
