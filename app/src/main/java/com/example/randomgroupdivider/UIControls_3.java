package com.example.randomgroupdivider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UIControls_3 extends AppCompatActivity {

    Button electiveBtn, nextBtn, prevBtn;
    AlertDialog.Builder electiveBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_uicontrols3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        electiveBtn = findViewById(R.id.electiveBtn);
        electiveBuilder = new AlertDialog.Builder(this);

        nextBtn = findViewById(R.id.nextBtn_9);
        prevBtn = findViewById(R.id.prvBtn_4);

        nextBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, SqliteDb.class));
        });

        prevBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, UIControls_2.class));
        });

        electiveBtn.setOnClickListener(view -> {
            String[] electiveList = {"Dev Ops", "Web Technology 2", "Computer Vision"};

            electiveBuilder.setTitle("Select your elective");

            electiveBuilder.setSingleChoiceItems(electiveList, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(UIControls_3.this, "You selected: " + electiveList[i], Toast.LENGTH_SHORT).show();
                }
            });

            electiveBuilder.create();
            electiveBuilder.show();

        });

    }
}