package com.example.converter;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button lengthConverterButton;
    private Button weightConverterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lengthConverterButton = findViewById(R.id.lengthConverterButton);
        weightConverterButton = findViewById(R.id.weightConverterButton);

        lengthConverterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLengthConverterActivity();
            }
        });

        weightConverterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeightConverterActivity();
            }
        });
    }

    private void openLengthConverterActivity() {
        Intent intent = new Intent(this, LengthConverterActivity.class);
        startActivity(intent);
    }

    private void openWeightConverterActivity() {
        Intent intent = new Intent(this, WeightConverterActivity.class);
        startActivity(intent);
    }
}
