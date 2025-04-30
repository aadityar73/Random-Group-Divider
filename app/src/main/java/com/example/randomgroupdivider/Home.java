package com.example.randomgroupdivider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.*;

public class Home extends AppCompatActivity {

    EditText namesInp, numGroupsInp;
    Button assignBtn;
    TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        namesInp = findViewById(R.id.namesInp);
        numGroupsInp = findViewById(R.id.numGroupsInp);
        assignBtn = findViewById(R.id.assignBtn);
        outputText = findViewById(R.id.output);

        assignBtn.setOnClickListener(view -> {

            if(namesInp.getText().toString().isEmpty() || numGroupsInp.getText().toString().isEmpty()){
                Toast.makeText(this, "Please fill all the fields!", Toast.LENGTH_SHORT).show();
            } else{

                List<String> names = new ArrayList<>();
                Map<Integer, String> groups = new HashMap<>();

                for(String name : namesInp.getText().toString().split(" ")){
                    names.add(name);
                }

                int randomNamesNum = (int)(Math.random() * names.size()) + 1;
                int randomGroupNum = (int) (Math.random() * Integer.parseInt(numGroupsInp.getText().toString())) + 1;

                groups.put(randomGroupNum, names.get(randomNamesNum));
                names.remove(randomNamesNum);

                Toast.makeText(this, "Groups: " + groups + ", Names: " + names, Toast.LENGTH_SHORT).show();
            }
        });
    }
}