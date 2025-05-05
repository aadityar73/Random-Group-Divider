package com.example.randomgroupdivider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BasicCalculator extends AppCompatActivity {

    TextView output;
    EditText operation;
    EditText num1;
    EditText num2;
    Button btn, nextBtn, prevBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_basic_calculator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn = findViewById(R.id.getValBtn);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        operation = findViewById(R.id.operation);
        output = findViewById(R.id.calcOutput);

        nextBtn = findViewById(R.id.nextBtn_3);
        prevBtn = findViewById(R.id.prvBtn_10);

        nextBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, Session4_1.class));
        });

        prevBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, Session2.class));
        });

        btn.setOnClickListener(view -> {
            String operationValue = operation.getText().toString();
            int num1Value = Integer.parseInt(num1.getText().toString());
            int num2Value = Integer.parseInt(num2.getText().toString());

            switch (operationValue) {
                case "+":
                    output.setText(String.valueOf(num1Value + num2Value));
                    break;
                case "-":
                    output.setText(String.valueOf(num1Value - num2Value));
                    break;
                case "*":
                    output.setText(String.valueOf(num1Value * num2Value));
                    break;
                case "/":
                    output.setText(String.valueOf(num1Value / num2Value));
                    break;
                default:
                    output.setText("Invalid operation!");
                    break;
            }
        });
    }

}