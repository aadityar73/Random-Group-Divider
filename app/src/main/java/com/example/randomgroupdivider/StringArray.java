package com.example.randomgroupdivider;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StringArray extends AppCompatActivity {

    String[] countries = {"India", "Australia", "UK", "USA"};
    Resources res;
    TextView result;
    Button nextBtn, prevBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_string_array);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nextBtn = findViewById(R.id.nextBtn_11);
        prevBtn = findViewById(R.id.prvBtn_2);

        nextBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, ContextMenu.class));
        });

        prevBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, SqliteDb.class));
        });

        result = findViewById(R.id.resultStrArr);

        res = getResources();

        String[] courses = res.getStringArray(R.array.courses);

        StringBuilder output = new StringBuilder("List of courses: \n");
        for(String course : courses){
            output.append(course).append("\n");
        }
        result.setText(output);

    }
}