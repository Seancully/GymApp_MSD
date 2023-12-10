package com.example.gymapp_msd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

// Activity class for tracking workout steps
public class WorkoutActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepCounterSensor;
    private int stepCount = 0;
    private TextView tvStepCount;
    private int initialStepCount = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        // TextView for displaying step count
        tvStepCount = findViewById(R.id.tvStepCount);

        // Initialize buttons for step increment and reset
        Button btnIncrementStep = findViewById(R.id.btnIncrementStep);
        Button btnResetStep = findViewById(R.id.btnResetStep);

        // Setup title with color formatting using SpannableString
        TextView title = findViewById(R.id.titleHealthHarbor);
        String text = "HealthHarbor";
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.WHITE), 6, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        title.setText(spannableString);

        // Sensor Manager initialization for step counter
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        // Register sensor listener if step counter sensor is available
        if (stepCounterSensor != null) {
            sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            // Code to handle the scenario where the device doesn't have a step counter sensor
        }

        // Back button to return to the main activity
        ImageButton backButton = findViewById(R.id.backButton4);
        backButton.setOnClickListener(v -> {
            startActivity(new Intent(WorkoutActivity.this, MainActivity.class));
            finish();
        });

        // Listener for manual step increment button
        btnIncrementStep.setOnClickListener(v -> {
            stepCount++;
            tvStepCount.setText("Steps: " + stepCount);
        });

        // Listener for step count reset button
        btnResetStep.setOnClickListener(v -> {
            stepCount = 0;
            initialStepCount = -1; // Reset the initial step count
            tvStepCount.setText("Steps: " + stepCount);
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Handle step counter changes
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            if (initialStepCount < 0) {
                // Set initial step count on first sensor change
                initialStepCount = (int) event.values[0];
            }

            // Update step count based on sensor data
            stepCount = (int) event.values[0] - initialStepCount;
            tvStepCount.setText("Steps: " + stepCount);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Handle changes in sensor accuracy if required
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister sensor listener to conserve battery
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Re-register sensor listener when activity resumes
        if (stepCounterSensor != null) {
            sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks like the back button
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
