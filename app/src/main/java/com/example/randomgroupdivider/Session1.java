package com.example.randomgroupdivider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Session1 extends AppCompatActivity {

    Button nextBtn, prevBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_session_1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nextBtn = findViewById(R.id.nextBtn_1);

        nextBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, Session2.class));
        });

        prevBtn = findViewById(R.id.prvBtn_);
        prevBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, Home.class));
        });

    }
}