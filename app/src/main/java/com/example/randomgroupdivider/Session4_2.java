package com.example.randomgroupdivider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Session4_2 extends AppCompatActivity {

    Button previousBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_session_4_2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        previousBtn = findViewById(R.id.swtichActivityPreviousBtn);

        Intent intent = getIntent();
        int userId = intent.getIntExtra("userId", 0);
        String email = intent.getStringExtra("email");

        Toast.makeText(this, "User ID: " + userId + ", Email: " + email, Toast.LENGTH_SHORT).show();

        previousBtn.setOnClickListener(view -> {
            Intent previousActivity = new Intent(Session4_2.this, Session4_1.class);
            startActivity(previousActivity);
        });
    }
}