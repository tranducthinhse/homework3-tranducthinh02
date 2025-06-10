package com.example.homework3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton twoDigit, threeDigit, fourDigit;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        radioGroup = findViewById(R.id.radioGroup);
        twoDigit = findViewById(R.id.radioTwoDigit);
        threeDigit = findViewById(R.id.radioThreeDigit);
        fourDigit = findViewById(R.id.radioFourDigit);
        startButton = findViewById(R.id.btnStart);
    }

    private void setupClickListeners() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();

                if (selectedId == -1) {
                    Toast.makeText(MainActivity.this, "Please select a difficulty level", Toast.LENGTH_SHORT).show();
                    return;
                }

                int digits = 0;
                if (selectedId == R.id.radioTwoDigit) {
                    digits = 2;
                } else if (selectedId == R.id.radioThreeDigit) {
                    digits = 3;
                } else if (selectedId == R.id.radioFourDigit) {
                    digits = 4;
                }

                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("DIGITS", digits);
                startActivity(intent);
            }
        });
    }
}