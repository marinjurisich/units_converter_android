package com.example.converter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.HashMap;
import java.util.Map;

public class LengthConverterActivity extends AppCompatActivity {

    public Boolean REVERSE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length_converter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Fetch metric units
        String[] metricUnitsLabels = getResources().getStringArray(R.array.metric_units_labels);
        // Fetch imperial units
        String[] imperialUnitsLabels = getResources().getStringArray(R.array.imperial_units_labels);

        // First Spinner
        Spinner dropdown1 = findViewById(R.id.dropdown1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, metricUnitsLabels);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown1.setAdapter(adapter1);

        // Second Spinner
        Spinner dropdown2 = findViewById(R.id.dropdown2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, imperialUnitsLabels);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown2.setAdapter(adapter2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle toolbar back button click
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Calculate function for Convert button
    public void calculate(View view) {

        // Fetch and map metric units
        String[] metricUnitsLabels = getResources().getStringArray(R.array.metric_units_labels);
        String[] metricUnitsValues = getResources().getStringArray(R.array.metric_units_values);
        Map<String, Double> metricUnitsMap = new HashMap<>();
        for(int i = 0; i < metricUnitsLabels.length; i++) {
            metricUnitsMap.put(metricUnitsLabels[i], Double.parseDouble(metricUnitsValues[i]));
        }

        // Fetch and map imperial units
        String[] imperialUnitsLabels = getResources().getStringArray(R.array.imperial_units_labels);
        String[] imperialUnitsValues = getResources().getStringArray(R.array.imperial_units_values);
        Map<String, Double> imperialUnitsMap = new HashMap<>();
        for(int i = 0; i < imperialUnitsLabels.length; i++) {
            imperialUnitsMap.put(imperialUnitsLabels[i], Double.parseDouble(imperialUnitsValues[i]));
        }

        //Get values from view
        Spinner metricUnits = findViewById(R.id.dropdown1);
        Spinner imperialUnits = findViewById(R.id.dropdown2);
        EditText inputField = findViewById(R.id.numberInput1);
        EditText outputField = findViewById(R.id.numberInput2);


        if (REVERSE == true) {

            String selectedInputUnit = imperialUnits.getSelectedItem().toString();
            String selectedOutputUnit = metricUnits.getSelectedItem().toString();

            Double inputValue = Double.parseDouble(inputField.getText().toString());
            Double inputUnit = metricUnitsMap.get(selectedInputUnit);
            Double outputUnit = imperialUnitsMap.get(selectedOutputUnit);

            // Perform conversion and set result
            Double calculatedOutputValue = inputValue * inputUnit * outputUnit;
            Double reversedValue = 1 / calculatedOutputValue;
            outputField.setText(String.format("%.2f", reversedValue));

        } else {

            String selectedInputUnit = metricUnits.getSelectedItem().toString();
            String selectedOutputUnit = imperialUnits.getSelectedItem().toString();

            Double inputValue = Double.parseDouble(inputField.getText().toString());
            Double inputUnit = metricUnitsMap.get(selectedInputUnit);
            Double outputUnit = imperialUnitsMap.get(selectedOutputUnit);

            // Perform conversion and set result
            Double calculatedOutputValue = inputValue * inputUnit * outputUnit;
            outputField.setText(String.format("%.2f", calculatedOutputValue));
        }


    }

    // switchUnits function for Switch units button
    public void switchUnits(View view) {

        // Fetch metric units
        String[] metricUnitsLabels = getResources().getStringArray(R.array.metric_units_labels);
        // Fetch imperial units
        String[] imperialUnitsLabels = getResources().getStringArray(R.array.imperial_units_labels);
        Spinner dropdown1 = findViewById(R.id.dropdown1);
        Spinner dropdown2 = findViewById(R.id.dropdown2);

        if(REVERSE) {
            //if REVERSE is true, set units to default and change REVERSE value
            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, metricUnitsLabels);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dropdown1.setAdapter(adapter1);

            ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, imperialUnitsLabels);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dropdown2.setAdapter(adapter2);

            REVERSE = false;
        } else {
            //if REVERSE is false, switch dropdowns and change REVERSE value
            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, imperialUnitsLabels);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dropdown1.setAdapter(adapter1);

            ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, metricUnitsLabels);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dropdown2.setAdapter(adapter2);

            REVERSE = true;
        }



    }

}
