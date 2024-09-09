package com.example.quizappassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class QuizHome extends AppCompatActivity {

    private int qno = 0;
    private int totalScore = 0;
    private boolean isShown = false;
    long timeleft = 2 * 60  * 1000; // in ms
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_home);

        // Hide the end exam button at the start
        Button endExam = findViewById(R.id.end_exam);
        endExam.setVisibility(View.GONE);

        LinearLayout mainlayout = findViewById(R.id.mainlayout);

        TextView timer = findViewById(R.id.timer);
        TextView scoreText = findViewById(R.id.scoreText);
        TextView question = findViewById(R.id.question);
        TextView score = findViewById(R.id.score);
        TextView result = findViewById(R.id.result);

        RadioGroup options = findViewById(R.id.options);
        RadioButton optionA = findViewById(R.id.a);
        RadioButton optionB = findViewById(R.id.b);
        RadioButton optionC = findViewById(R.id.c);
        RadioButton optionD = findViewById(R.id.d);

        Button next = findViewById(R.id.next);
        Button previous = findViewById(R.id.previous);
        Button show = findViewById(R.id.show);
        Button reset = findViewById(R.id.reset);

        String[] quiz_questions = getResources().getStringArray(R.array.quiz_questions);
        String[] quiz_answers = getResources().getStringArray(R.array.quiz_answers);

        int[] user_ans = new int[quiz_answers.length];
        Arrays.fill(user_ans, -1);
        score.setText("" + totalScore);

        question.setText(quiz_questions[qno]);
        optionA.setText(quiz_questions[qno + 1]);
        optionB.setText(quiz_questions[qno + 2]);
        optionC.setText(quiz_questions[qno + 3]);
        optionD.setText(quiz_questions[qno + 4]);


        CountDownTimer quiztimer = new CountDownTimer(timeleft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.format("Timer: %02d:%02d", (millisUntilFinished / 1000 ) / 60, (millisUntilFinished / 1000) % 60));
            }

            @Override
            public void onFinish() {

                endExam.performClick();
            }
        }.start();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionA.setEnabled(true);
                optionB.setEnabled(true);
                optionC.setEnabled(true);
                optionD.setEnabled(true);

                int selected = options.getCheckedRadioButtonId();

                // if option is selected and qno is not out of range
                if(selected != -1 && qno != quiz_questions.length) {

                    RadioButton sel = findViewById(selected);
                    String ans = sel.getText().toString();

                    if(quiz_answers[qno/5].equals(ans) && !isShown) {
                        totalScore += 5;
                    } else if(user_ans[qno / 5] == -1) {
                        totalScore -= 1;
                    }

                    if(user_ans[qno / 5] == -1) {
                        user_ans[qno / 5] = selected;
                    }

                    score.setText("" + totalScore);
                    options.clearCheck();



                    if(qno < quiz_questions.length) {
                        qno += 5;
                        if(qno != quiz_questions.length) {
                            question.setText(quiz_questions[qno]);
                            optionA.setText(quiz_questions[qno + 1]);
                            optionB.setText(quiz_questions[qno + 2]);
                            optionC.setText(quiz_questions[qno + 3]);
                            optionD.setText(quiz_questions[qno + 4]);
                        }
                    }
                    isShown = false;
                }
                else {Toast.makeText(QuizHome.this, "Please Select an Option", Toast.LENGTH_SHORT).show();
                }

                if (qno != quiz_questions.length && user_ans[qno / 5] != -1) {
                    RadioButton sel = findViewById(user_ans[qno / 5]);
                    sel.setChecked(true);
                    optionA.setEnabled(false);
                    optionB.setEnabled(false);
                    optionC.setEnabled(false);
                    optionD.setEnabled(false);
                    isShown = true;
                }
                if(qno == quiz_questions.length) {
                    endExam.setVisibility(View.VISIBLE);
                    optionA.setEnabled(false);
                    optionB.setEnabled(false);
                    optionC.setEnabled(false);
                    optionD.setEnabled(false);
                }

            }
        });
        endExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score.setVisibility(View.GONE);
                scoreText.setVisibility(View.GONE);
                question.setVisibility(View.GONE);
                options.setVisibility(View.GONE);
                optionA.setVisibility(View.GONE);
                optionB.setVisibility(View.GONE);
                optionC.setVisibility(View.GONE);
                optionD.setVisibility(View.GONE);
                next.setVisibility(View.GONE);
                previous.setVisibility(View.GONE);
                show.setVisibility(View.GONE);
                endExam.setVisibility(View.GONE);
                timer.setVisibility(View.GONE);

                quiztimer.cancel();
                result.setText(String.format("Total Score: %d/%d\nPercentage: %.2f%%", totalScore, 5 * quiz_answers.length, (totalScore / (float)(5 * quiz_answers.length)) * 100));
                result.setVisibility(View.VISIBLE);
                reset.setVisibility(View.VISIBLE);
                mainlayout.setGravity(Gravity.CENTER);
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qno == quiz_questions.length) return;
                // if answer not selected
//                optionA.setChecked(false);
//                optionB.setChecked(false);
//                optionC.setChecked(false);
//                optionD.setChecked(false);
//                if(options.getCheckedRadioButtonId() == -1) {
                    if (optionA.getText().toString().equals(quiz_answers[qno / 5])) {
                        optionA.setChecked(true);
                    } else if (optionB.getText().toString().equals(quiz_answers[qno / 5])) {
                        optionB.setChecked(true);
                    } else if (optionC.getText().toString().equals(quiz_answers[qno / 5])) {
                        optionC.setChecked(true);
                    } else if (optionD.getText().toString().equals(quiz_answers[qno / 5])) {
                        optionD.setChecked(true);
                    }
                    optionA.setEnabled(false);
                    optionB.setEnabled(false);
                    optionC.setEnabled(false);
                    optionD.setEnabled(false);
                    score.setText("" + totalScore);
                    isShown = true;
//                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qno = 0;
                totalScore = 0;
                timeleft = 2 * 60 * 1000;
                isShown = false;
                Arrays.fill(user_ans, -1);
                score.setVisibility(View.VISIBLE);
                scoreText.setVisibility(View.VISIBLE);
                question.setVisibility(View.VISIBLE);
                options.setVisibility(View.VISIBLE);
                optionA.setVisibility(View.VISIBLE);
                optionB.setVisibility(View.VISIBLE);
                optionC.setVisibility(View.VISIBLE);
                optionD.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                previous.setVisibility(View.VISIBLE);
                show.setVisibility(View.VISIBLE);
                endExam.setVisibility(View.VISIBLE);
                timer.setVisibility(View.VISIBLE);

                result.setVisibility(View.GONE);
                endExam.setVisibility(View.GONE);
                reset.setVisibility(View.GONE);
                mainlayout.setGravity(Gravity.START);
                score.setText("" + totalScore);

                question.setText(quiz_questions[qno]);
                optionA.setText(quiz_questions[qno + 1]);
                optionB.setText(quiz_questions[qno + 2]);
                optionC.setText(quiz_questions[qno + 3]);
                optionD.setText(quiz_questions[qno + 4]);
                optionA.setEnabled(true);
                optionB.setEnabled(true);
                optionC.setEnabled(true);
                optionD.setEnabled(true);
                quiztimer.start();
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

                if(user_ans[qno / 5] != -1) {
                    RadioButton sel = findViewById(user_ans[qno / 5]);
                    sel.setChecked(true);
                    optionA.setEnabled(false);
                    optionB.setEnabled(false);
                    optionC.setEnabled(false);
                    optionD.setEnabled(false);

                    isShown = true;
                }
            }
        });
    }
}