package com.example.homework3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView tvLastGuess, tvRemainingAttempts, tvHint;
    private EditText etGuess;
    private Button btnConfirm;

    private int digits;
    private int targetNumber;
    private int remainingAttempts = 10;
    private final ArrayList<Integer> guesses = new ArrayList<>();
    private final Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Get difficulty level
        digits = getIntent().getIntExtra("DIGITS", 4);

        initializeViews();
        generateTargetNumber();
        setupClickListeners();
        updateUI();
    }

    private void initializeViews() {
        tvLastGuess = findViewById(R.id.tvLastGuess);
        tvRemainingAttempts = findViewById(R.id.tvRemainingAttempts);
        tvHint = findViewById(R.id.tvHint);
        etGuess = findViewById(R.id.etGuess);
        btnConfirm = findViewById(R.id.btnConfirm);
    }

    private void generateTargetNumber() {
        int min = (int) Math.pow(10, digits - 1);
        int max = (int) Math.pow(10, digits) - 1;
        targetNumber = random.nextInt(max - min + 1) + min;
    }

    private void setupClickListeners() {
        btnConfirm.setOnClickListener(v -> processGuess());
    }

    private void processGuess() {
        String guessText = etGuess.getText().toString().trim();

        // Validate input
        if (guessText.isEmpty()) {
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();
            return;
        }

        if (guessText.length() != digits) {
            Toast.makeText(this, "Please enter a " + digits + "-digit number", Toast.LENGTH_SHORT).show();
            return;
        }

        int guess;
        try {
            guess = Integer.parseInt(guessText);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
            return;
        }

        // Process the guess
        guesses.add(guess);
        remainingAttempts--;

        if (guess == targetNumber) {
            // Player won!
            showWinDialog();
        } else if (remainingAttempts == 0) {
            // Game over
            showGameOverScreen();
        } else {
            // Continue game
            updateHint(guess);
            updateUI();
            etGuess.setText("");
        }
    }

    @SuppressLint("SetTextI18n")
    private void updateHint(int guess) {
        if (guess < targetNumber) {
            tvHint.setText("Try a bigger number");
            tvHint.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        } else {
            tvHint.setText("Try a smaller number");
            tvHint.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }
        tvHint.setVisibility(View.VISIBLE);
    }

    @SuppressLint("SetTextI18n")
    private void updateUI() {
        if (!guesses.isEmpty()) {
            tvLastGuess.setText("Your last guess: " + guesses.get(guesses.size() - 1));
        }
        tvRemainingAttempts.setText("Remaining attempts: " + remainingAttempts);
    }

    private void showWinDialog() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("WON", true);
        intent.putExtra("TARGET_NUMBER", targetNumber);
        intent.putExtra("GUESSES", guesses);
        intent.putExtra("DIGITS", digits);
        startActivity(intent);
        finish();
    }

    private void showGameOverScreen() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("WON", false);
        intent.putExtra("TARGET_NUMBER", targetNumber);
        intent.putExtra("GUESSES", guesses);
        intent.putExtra("DIGITS", digits);
        startActivity(intent);
        finish();
    }
}
