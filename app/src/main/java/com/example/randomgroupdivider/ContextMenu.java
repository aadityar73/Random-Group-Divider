package com.example.randomgroupdivider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ContextMenu extends AppCompatActivity {

    TextView textView;
    Button prevBtn, homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_context_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        prevBtn = findViewById(R.id.prvBtn_1);

        prevBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, StringArray.class));
        });

        homeBtn = findViewById(R.id.homeBtn_);
        homeBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, Home.class));
        });

        textView = findViewById(R.id.contextMenuTextView);

        registerForContextMenu(textView);
    }

    @Override
    public void onCreateContextMenu(android.view.ContextMenu menu, View v, android.view.ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "You clicked: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        return true;
    }
}