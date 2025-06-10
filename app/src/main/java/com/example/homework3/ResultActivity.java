package com.example.homework3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private TextView tvTitle, tvMessage, tvGuesses, tvTargetNumber;
    private Button btnYes, btnNo;

    private boolean won;
    private int targetNumber;
    private ArrayList<Integer> guesses;
    private int digits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Get data from intent
        won = getIntent().getBooleanExtra("WON", false);
        targetNumber = getIntent().getIntExtra("TARGET_NUMBER", 0);
        guesses = (ArrayList<Integer>) getIntent().getSerializableExtra("GUESSES");
        digits = getIntent().getIntExtra("DIGITS", 4);

        initializeViews();
        setupUI();
        setupClickListeners();
    }

    private void initializeViews() {
        tvTitle = findViewById(R.id.tvTitle);
        tvMessage = findViewById(R.id.tvMessage);
        tvGuesses = findViewById(R.id.tvGuesses);
        tvTargetNumber = findViewById(R.id.tvTargetNumber);
        btnYes = findViewById(R.id.btnYes);
        btnNo = findViewById(R.id.btnNo);
    }

    private void setupUI() {
        if (won) {
            tvTitle.setText("Congratulations!");
            tvMessage.setText("You guessed the correct number!");
            tvTargetNumber.setText("My number was: " + targetNumber);
        } else {
            tvTitle.setText("Game Over");
            tvMessage.setText("You've used all your attempts.");
            tvTargetNumber.setText("My number was: " + targetNumber);
        }

        // Display all guesses
        StringBuilder guessText = new StringBuilder();
        if (guesses != null && !guesses.isEmpty()) {
            guessText.append("Your guesses: ");
            for (int i = 0; i < guesses.size(); i++) {
                guessText.append(guesses.get(i));
                if (i < guesses.size() - 1) {
                    guessText.append(", ");
                }
            }
        }
        tvGuesses.setText(guessText.toString());
    }

    private void setupClickListeners() {
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start new game
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Exit the app
                finishAffinity();
            }
        });
    }
}
