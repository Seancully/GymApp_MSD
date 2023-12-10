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

// Activity class to add a new workout
public class AddWorkout extends AppCompatActivity {

    // UI components
    private EditText exerciseNameInput, weightInput, setsInput, repsInput;
    private Button addExerciseButton, completeWorkoutButton;
    private List<WorkoutEntity.Exercise> exercises = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);

        // Initialize UI components from the layout
        exerciseNameInput = findViewById(R.id.exerciseNameInput);
        weightInput = findViewById(R.id.weightInput);
        setsInput = findViewById(R.id.setsInput);
        repsInput = findViewById(R.id.repsInput);
        addExerciseButton = findViewById(R.id.addExerciseButton);
        completeWorkoutButton = findViewById(R.id.completeWorkoutButton);

        // Setup listener for adding an exercise
        addExerciseButton.setOnClickListener(v -> addExercise());

        // Setup listener to complete and save the workout
        completeWorkoutButton.setOnClickListener(v -> new SaveWorkoutTask().execute());

        // Set formatted title text
        TextView title = findViewById(R.id.titleHealthHarbor);
        String text = "HealthHarbor";
        SpannableString spannableString = new SpannableString(text);
        // Apply color to the title parts
        spannableString.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.WHITE), 6, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        title.setText(spannableString);

        // Back button setup to return to the previous activity
        ImageButton backButton = findViewById(R.id.backButton5);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(AddWorkout.this, LiftActivity.class);
            startActivity(intent);
            finish();
        });
    }

    // Method to add an exercise to the list
    private void addExercise() {
        try {
            // Extract input data from UI
            // Reference: The following code is aided by https://developer.android.com/develop/connectivity/network-ops/xml
            String exerciseName = exerciseNameInput.getText().toString();
            String weight = weightInput.getText().toString();
            int sets = Integer.parseInt(setsInput.getText().toString());
            int reps = Integer.parseInt(repsInput.getText().toString());
            // Reference complete

            // Validate input fields
            if (exerciseName.isEmpty() || weight.isEmpty()) {
                throw new IllegalArgumentException("All fields are required");
            }

            // Create a new Exercise object and add it to the list
            WorkoutEntity.Exercise exercise = new WorkoutEntity.Exercise(exerciseName, weight, sets, reps);
            exercises.add(exercise);
            // Clear the input fields for new entries
            clearInputFields();

            // Notify user of successful addition
            Toast.makeText(this, "Exercise added", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // Handle any input errors
            Toast.makeText(this, "Invalid input: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // Method to clear input fields after adding an exercise
    private void clearInputFields() {
        exerciseNameInput.setText("");
        weightInput.setText("");
        setsInput.setText("");
        repsInput.setText("");
    }

    // AsyncTask to save the current workout
    // operation that runs on a background thread, separate from the main thread of execution.
    // Reference: The following code is aided by https://developer.android.com/reference/android/os/AsyncTask
    private class SaveWorkoutTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            // Get the database instance
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            // Convert exercises list to JSON
            Gson gson = new Gson();
            String exercisesJson = gson.toJson(exercises);

            // Create a new WorkoutEntity and save it to the database
            WorkoutEntity currentWorkout = new WorkoutEntity();
            currentWorkout.setWorkoutDetails(exercisesJson);
            db.workoutDao().insert(currentWorkout);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // Notify user of successful save and finish the activity
            Toast.makeText(AddWorkout.this, "Workout saved", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    // Reference complete
}
