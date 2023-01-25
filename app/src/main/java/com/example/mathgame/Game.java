package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;
import java.util.Random;

public class Game extends AppCompatActivity {
    LinearLayout linearLayout;
    TextView score;
    TextView life;
    TextView time;
    TextView question;
    EditText answer;
    Button ok;
    Button next;
    Random random = new Random();
    int number1;
    int number2;
    int scoreCount = 0;
    int lifeCount = 3;
    int userAnswer;
    int realAnswer;
    CountDownTimer timer;
    private static final long START_TIMER_IN_MILIS = 10000;
    Boolean time_left;
    long time_left_in_milis = START_TIMER_IN_MILIS;


    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        score = findViewById(R.id.textViewScore);
        life = findViewById(R.id.textViewLife);
        time = findViewById(R.id.textViewTime);
        question = findViewById(R.id.textViewQuestion);
        answer = findViewById(R.id.editTextTextAnswer);
        ok = findViewById(R.id.buttonOk);
        next = findViewById(R.id.buttonNext);
        linearLayout = findViewById(R.id.linearContainer);
        gameContinue();
        ok.setOnClickListener(v -> {
            userAnswer = Integer.parseInt(answer.getText().toString());
            pauseTimer();
            if (userAnswer == 0) {
                Snackbar.make(linearLayout, "Please write answer!", Snackbar.LENGTH_SHORT).show();
            }else{
                if (userAnswer == realAnswer) {
                    question.setText("Nice! Your answer is correct!");
                    scoreCount += 10;
                    score.setText("" + scoreCount);
                } else {
                    question.setText("Oh! Your answer is wrong!");
                    lifeCount -= 1;
                    life.setText("" + lifeCount);

                }
            }
        });

        next.setOnClickListener(v -> {
            answer.setText("");
            resetTimer();

            if (lifeCount == 0) {
                Intent intent = new Intent(Game.this, ScorePage.class);
                intent.putExtra("score", scoreCount);
                startActivity(intent);
                finish();
            }else {
                gameContinue();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void gameContinue() {
        number1 = random.nextInt(100);
        number2 = random.nextInt(100);
        realAnswer = number1 + number2;
        question.setText(number1 + " + " + number2);
        startTimer();

    }

    public void startTimer() {
        timer = new CountDownTimer(time_left_in_milis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_left_in_milis = millisUntilFinished;
                updateText();
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                time_left = false;
                pauseTimer();
                resetTimer();
                updateText();
                lifeCount -= 1;
                life.setText("" + lifeCount);
                question.setText("Oh! Time is up!");
            }
        }.start();
        time_left = true;
    }

    private void updateText() {
        int second = (int) (time_left_in_milis / 1000) % 60;
        String time_left = String.format(Locale.getDefault(), "%02d", second);
        time.setText(time_left);
    }

    private void pauseTimer() {
        timer.cancel();
        time_left = false;
    }
    public void resetTimer(){
        time_left_in_milis = START_TIMER_IN_MILIS;
        updateText();
    }
}