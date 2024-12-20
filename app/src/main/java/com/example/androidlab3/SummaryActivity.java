package com.example.androidlab3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TextView totalCostTextView = findViewById(R.id.totalCostTextView);
        Button backButton = findViewById(R.id.backButton);

        int totalCost = getIntent().getIntExtra("totalCost", 0);
        totalCostTextView.setText(String.format("Итоговая стоимость: %d ₽", totalCost));

        backButton.setOnClickListener(v -> finish());
    }
}
