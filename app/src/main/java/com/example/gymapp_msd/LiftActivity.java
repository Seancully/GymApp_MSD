package com.example.gymapp_msd;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymapp_msd.database.AppDatabase;
import com.example.gymapp_msd.entities.WorkoutEntity;

import java.util.List;

// Activity class for displaying and managing workouts
public class LiftActivity extends Activity {
    private WorkoutAdapter workoutAdapter;
    private RecyclerView workoutRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lift);

        // Setup for the RecyclerView to display workouts
        workoutRecyclerView = findViewById(R.id.workoutRecyclerView);
        workoutRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        workoutAdapter = new WorkoutAdapter();
        workoutRecyclerView.setAdapter(workoutAdapter);

        // Initialize UI components and load workouts from database
        setupUI();
        loadWorkouts();
    }

    private void setupUI() {
        // Setting up the colored title using SpannableString
        TextView title = findViewById(R.id.titleHealthHarbor);
        String text = "HealthHarbor";
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.WHITE), 6, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        title.setText(spannableString);

        // Button to add a new workout
        Button addWorkoutButton = findViewById(R.id.addWorkoutButton);
        addWorkoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(LiftActivity.this, AddWorkout.class);
            startActivity(intent);
        });

        // Back button to return to the main activity
        ImageButton imageButton = findViewById(R.id.backButton3);
        imageButton.setOnClickListener(v -> {
            Intent intent = new Intent(LiftActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload workouts when the activity is resumed
        loadWorkouts();
    }

    // Asynchronous task to load workouts from the database
    // operation that runs on a background thread, separate from the main thread of execution.
    private void loadWorkouts() {
        new AsyncTask<Void, Void, List<WorkoutEntity>>() {
            @Override
            protected List<WorkoutEntity> doInBackground(Void... voids) {
                AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                return db.workoutDao().getAll();
            }

            @Override
            protected void onPostExecute(List<WorkoutEntity> workoutEntities) {
                workoutAdapter.setWorkouts(workoutEntities);
            }
        }.execute();
    }

    // Handles menu item selection (like back button in the toolbar)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
