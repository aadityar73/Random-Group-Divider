package com.example.randomgroupdivider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NumGenerator extends AppCompatActivity {

    EditText numInp;
    Button generateBtn;
    TextView outputTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_num_generator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        numInp = findViewById(R.id.numInp);
        generateBtn = findViewById(R.id.generateNumBtn);
        outputTxt = findViewById(R.id.outputTxt);

        generateBtn.setOnClickListener(view -> {
            int num = Integer.parseInt(numInp.getText().toString());
            int randomNum = (int) (Math.random() * num) + 1;

            outputTxt.setText(String.valueOf(randomNum));
        });
    }
}