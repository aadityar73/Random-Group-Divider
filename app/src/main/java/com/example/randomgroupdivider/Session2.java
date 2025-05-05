package com.example.randomgroupdivider;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Session2 extends AppCompatActivity {
    //    Used for variable declarations
    public static final String event = "Activity Lifecycle";
    Button nextBtn, prevBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_2);
        Log.d(event, "onCreate(): activity created");
        Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show();

        nextBtn = findViewById(R.id.nextBtn_2);
        prevBtn = findViewById(R.id.prvBtn_11);

        nextBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, BasicCalculator.class));
        });

        prevBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, Session1.class));
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(event, "onStart() - activity started");
        Toast.makeText(this, "onStart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(event, "onRestart(): activity restarted");
        Toast.makeText(this, "onRestart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(event, "onPause() - activity paused");
        Toast.makeText(this, "onPause()", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(event, "onDestroy(): activity destroyed");
        Toast.makeText(this, "onDestroy()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(event, "onStop(): activity stopped");
        Toast.makeText(this, "onStop()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(event, "onResume(): activity resumed");
        Toast.makeText(this, "onResume()", Toast.LENGTH_SHORT).show();
    }



}