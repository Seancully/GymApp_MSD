package com.example.gymapp_msd;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymapp_msd.database.AppDatabase;
import com.example.gymapp_msd.entities.WorkoutEntity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class AddWorkout extends AppCompatActivity {

    private EditText exerciseNameInput, weightInput, setsInput, repsInput;
    private Button addExerciseButton, completeWorkoutButton;
    private List<WorkoutEntity.Exercise> exercises = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);

        // Initialize UI components
        exerciseNameInput = findViewById(R.id.exerciseNameInput);
        weightInput = findViewById(R.id.weightInput);
        setsInput = findViewById(R.id.setsInput);
        repsInput = findViewById(R.id.repsInput);
        addExerciseButton = findViewById(R.id.addExerciseButton);
        completeWorkoutButton = findViewById(R.id.completeWorkoutButton);

        // Add Exercise button click listener
        addExerciseButton.setOnClickListener(v -> addExercise());

        // Complete Workout button click listener
        completeWorkoutButton.setOnClickListener(v -> new SaveWorkoutTask().execute());

        // Title with SpannableString for color formatting
        TextView title = findViewById(R.id.titleHealthHarbor);
        String text = "HealthHarbor";
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // "Health" in orange
        spannableString.setSpan(new ForegroundColorSpan(Color.WHITE), 6, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // "Harbor" in black
        title.setText(spannableString);

        // Back button logic
        ImageButton backButton = findViewById(R.id.backButton5);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(AddWorkout.this, LiftActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void addExercise() {
        try {
            String exerciseName = exerciseNameInput.getText().toString();
            String weight = weightInput.getText().toString();
            int sets = Integer.parseInt(setsInput.getText().toString());
            int reps = Integer.parseInt(repsInput.getText().toString());

            if (exerciseName.isEmpty() || weight.isEmpty()) {
                throw new IllegalArgumentException("All fields are required");
            }

            WorkoutEntity.Exercise exercise = new WorkoutEntity.Exercise(exerciseName, weight, sets, reps);
            exercises.add(exercise);
            clearInputFields();

            Toast.makeText(this, "Exercise added", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Invalid input: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void clearInputFields() {
        exerciseNameInput.setText("");
        weightInput.setText("");
        setsInput.setText("");
        repsInput.setText("");
    }

    private class SaveWorkoutTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            String workoutName = "Your Workout Name";
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            Gson gson = new Gson();
            String exercisesJson = gson.toJson(exercises);

            WorkoutEntity currentWorkout = new WorkoutEntity();
            currentWorkout.setWorkoutDetails(exercisesJson);
            currentWorkout.setWorkoutName(currentWorkout.workoutName);
            db.workoutDao().insert(currentWorkout);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(AddWorkout.this, "Workout saved", Toast.LENGTH_SHORT).show();
            finish(); // Return to previous activity
        }
    }
}
