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
                Map<Integer, List<String>> groups = new HashMap<>();

                int numGroups = Integer.parseInt(numGroupsInp.getText().toString());

                for(String name : namesInp.getText().toString().split(" ")){
                    names.add(name);
                }

                if(numGroups > names.size()){
                    Toast.makeText(this, "The number of groups cannot exceed the number of names", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(numGroups <= 0){
                    Toast.makeText(this, "Number of groups must be at least 1", Toast.LENGTH_SHORT).show();
                    return;
                }

                int maxGroupSize = names.size() / numGroups;

                for(int i = 1; i <= numGroups; i++){
                    groups.put(i, new ArrayList<>());
                }

                while(!names.isEmpty()){
                    int randomNamesNum = (int)(Math.random() * names.size());
                    int randomGroupNum = (int) (Math.random() * numGroups) + 1;

                    if(groups.get(randomGroupNum).size() < maxGroupSize){
                        groups.get(randomGroupNum).add(names.get(randomNamesNum));
                        names.remove(randomNamesNum);
                    }
                }

                Toast.makeText(this, "Groups: " + groups + ", Names: " + names, Toast.LENGTH_LONG).show();
            }
        });
    }
}
