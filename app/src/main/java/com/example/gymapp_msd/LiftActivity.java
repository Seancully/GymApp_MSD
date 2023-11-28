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

public class LiftActivity extends Activity {
    private WorkoutAdapter workoutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lift);

        setupUI();
        loadWorkouts();
    }

    private void setupUI() {
        // Setting up the title with SpannableString for color formatting
        TextView title = findViewById(R.id.titleHealthHarbor);
        String text = "HealthHarbor";
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // "Health" in orange
        spannableString.setSpan(new ForegroundColorSpan(Color.WHITE), 6, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // "Harbor" in black
        title.setText(spannableString);

        Button addWorkoutButton = findViewById(R.id.addWorkoutButton);
        addWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LiftActivity.this, AddWorkout.class);
                startActivity(intent);
            }
        });

        // Gesture/callback and intent to go back to previous page
        ImageButton imageButton = findViewById(R.id.backButton3);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LiftActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        RecyclerView workoutRecyclerView = findViewById(R.id.workoutRecyclerView);
        workoutAdapter = new WorkoutAdapter();
        workoutRecyclerView.setAdapter(workoutAdapter);
        workoutRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadWorkouts() {
        new FetchWorkoutsTask().execute();
    }

    private class FetchWorkoutsTask extends AsyncTask<Void, Void, List<WorkoutEntity>> {
        @Override
        protected List<WorkoutEntity> doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            return db.workoutDao().getAllWorkouts(); // This should return List<WorkoutEntity>
        }

        @Override
        protected void onPostExecute(List<WorkoutEntity> workouts) {
            workoutAdapter.setWorkouts(workouts);
            workoutAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
