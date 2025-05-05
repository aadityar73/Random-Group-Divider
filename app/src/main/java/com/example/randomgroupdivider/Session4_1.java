package com.example.randomgroupdivider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Session4_1 extends AppCompatActivity {
    Button nextBtn, nextBtn2, prevBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_session_4_1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nextBtn = findViewById(R.id.switchActivityNextBtn);

        nextBtn.setOnClickListener(view -> {
            Intent nextActivity = new Intent(Session4_1.this, Session4_2.class);
            nextActivity.putExtra("userId", 123);
            nextActivity.putExtra("email", "abc@somaiya.edu");
            startActivity(nextActivity);
        });

        nextBtn2 = findViewById(R.id.nextBtn_4);
        prevBtn = findViewById(R.id.prvBtn_9);

        nextBtn2.setOnClickListener(view -> {
            startActivity(new Intent(this, Email.class));
        });

        prevBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, BasicCalculator.class));
        });
    }
}