package com.example.quizappassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizHome extends AppCompatActivity {

    private int qno = 0;
    private int totalScore = 0;
    private CountDownTimer cdt;
    long timeleft = 2 * 60  * 1000; // in ms
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_home);

        TextView question = findViewById(R.id.question);
        TextView score = findViewById(R.id.score);

        RadioGroup options = findViewById(R.id.options);
        RadioButton optionA = findViewById(R.id.a);
        RadioButton optionB = findViewById(R.id.b);
        RadioButton optionC = findViewById(R.id.c);
        RadioButton optionD = findViewById(R.id.d);

        Button next = findViewById(R.id.next);
        Button previous = findViewById(R.id.previous);

        String[] quiz_questions = getResources().getStringArray(R.array.quiz_questions);
        String[] quiz_answers = getResources().getStringArray(R.array.quiz_answers);
        score.setText("" + totalScore);

        question.setText(quiz_questions[qno]);
        optionA.setText(quiz_questions[qno + 1]);
        optionB.setText(quiz_questions[qno + 2]);
        optionC.setText(quiz_questions[qno + 3]);
        optionD.setText(quiz_questions[qno + 4]);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected = options.getCheckedRadioButtonId();
                if(selected != -1 && qno != quiz_questions.length - 5) {

                    RadioButton sel = findViewById(selected);
                    String ans = sel.getText().toString();

                    if(quiz_answers[qno/5].equals(ans)) {
                        totalScore += 5;
                    } else {
                        totalScore -= 1;
                    }

                    score.setText("" + totalScore);
                    options.clearCheck();

                    if(qno < quiz_questions.length - 5) {
                        qno += 5;
                    }

                    question.setText(quiz_questions[qno]);
                    optionA.setText(quiz_questions[qno + 1]);
                    optionB.setText(quiz_questions[qno + 2]);
                    optionC.setText(quiz_questions[qno + 3]);
                    optionD.setText(quiz_questions[qno + 4]);
                } else {
                    Toast.makeText(QuizHome.this, "Please Select an Option", Toast.LENGTH_SHORT).show();
                }
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                options.clearCheck();
                if(qno >= 5) {
                    qno = qno - 5;
                }

                question.setText(quiz_questions[qno]);
                optionA.setText(quiz_questions[qno + 1]);
                optionB.setText(quiz_questions[qno + 2]);
                optionC.setText(quiz_questions[qno + 3]);
                optionD.setText(quiz_questions[qno + 4]);
            }
        });
    }
}