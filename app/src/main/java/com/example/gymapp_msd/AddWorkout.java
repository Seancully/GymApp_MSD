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
import com.example.gymapp_msd.entities.ExerciseEntity;
import com.example.gymapp_msd.entities.WorkoutEntity;

import java.util.ArrayList;
import java.util.List;

public class AddWorkout extends AppCompatActivity {

    private EditText exerciseNameInput, weightInput, setsInput, repsInput;
    private Button addExerciseButton, completeWorkoutButton;
    private List<ExerciseEntity> exercises = new ArrayList<>();

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

        addExerciseButton.setOnClickListener(v -> addExercise());
        completeWorkoutButton.setOnClickListener(v -> new SaveWorkoutTask().execute());

        // Setting up the title with SpannableString for color formatting
        TextView title = findViewById(R.id.titleHealthHarbor);
        String text = "HealthHarbor";
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // "Health" in orange
        spannableString.setSpan(new ForegroundColorSpan(Color.WHITE), 6, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // "Harbor" in black
        title.setText(spannableString);

        // Gesture/callback and intent to go back to previous page
        ImageButton imageButton = findViewById(R.id.backButton5);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddWorkout.this, LiftActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void addExercise() {
        try {
            String exerciseName = exerciseNameInput.getText().toString();
            float weight = Float.parseFloat(weightInput.getText().toString());
            int sets = Integer.parseInt(setsInput.getText().toString());
            int reps = Integer.parseInt(repsInput.getText().toString());

            if(exerciseName.isEmpty()) throw new IllegalArgumentException("Exercise name required");

            ExerciseEntity exercise = new ExerciseEntity();
            exercise.name = exerciseName;
            exercise.weight = weight;
            exercise.sets = sets;
            exercise.reps = reps;

            exercises.add(exercise);
            clearInputFields();
        } catch (IllegalArgumentException e) {
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
        protected Void doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            WorkoutEntity currentWorkout = new WorkoutEntity();

            long workoutId = db.workoutDao().insertWorkout(currentWorkout);

            for (ExerciseEntity exercise : exercises) {
                exercise.workoutId = (int) workoutId;
                db.exerciseDao().insertExercise(exercise);
            }

            return null;
        }

        protected void onPostExecute(Void aVoid) {
            finish(); // Return to previous activity
        }
    }
}
