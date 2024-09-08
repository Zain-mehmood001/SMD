package com.example.quizappassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;

public class QuizHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_home);

        System.out.println(R.string.q_1);

        TextView question = findViewById(R.id.question);
        RadioButton optionA = findViewById(R.id.a);
        RadioButton optionB = findViewById(R.id.b);
        RadioButton optionC = findViewById(R.id.c);
        RadioButton optionD = findViewById(R.id.d);

        question.setText(R.string.q_2);
        optionA.setText(R.string.q_2_option_a);
        optionB.setText(R.string.q_2_option_b);
        optionC.setText(R.string.q_2_option_c);
        optionD.setText(R.string.q_2_option_d);
    }
}