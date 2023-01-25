package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonAdditional;
    private Button buttonSubtraction;
    private Button buttonMultiplication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdditional = findViewById(R.id.buttonAddition);
        buttonSubtraction = findViewById(R.id.buttonSubtraction);
        buttonMultiplication = findViewById(R.id.buttonMultiplication);
        buttonAdditional.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Game.class);
            startActivity(intent);
            finish();
        });
    }
}