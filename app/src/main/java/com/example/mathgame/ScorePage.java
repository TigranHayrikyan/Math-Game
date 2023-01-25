package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ScorePage extends AppCompatActivity {
    TextView finishScore;
    Button playAgain;
    Button exit;
    int score;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_page);

        finishScore = findViewById(R.id.finishScore);
        playAgain = findViewById(R.id.buttonPlayAgain);
        exit = findViewById(R.id.buttonExit);
        Intent intent1 = getIntent();
        score = intent1.getIntExtra("score", 0);
        String userScore = String.valueOf(score);
        finishScore.setText("Your Score: " + userScore);


        playAgain.setOnClickListener(v -> {
            Intent intent = new Intent(ScorePage.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        exit.setOnClickListener(v -> {
            finish();
        });
    }
}