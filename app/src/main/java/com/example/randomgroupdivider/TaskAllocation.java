package com.example.randomgroupdivider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

public class TaskAllocation extends AppCompatActivity {

    EditText namesInp, tasksInp;
    Button assignBtn, homeBtn;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task_allocation);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        namesInp = findViewById(R.id.namesInp2);
        tasksInp = findViewById(R.id.tasksInp);
        assignBtn = findViewById(R.id.assignTasksBtn);
        homeBtn = findViewById(R.id.homeBtn3);
        recyclerView = findViewById(R.id.taskRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        homeBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, Home.class));
        });

        assignBtn.setOnClickListener(view -> {
            String[] nameArray = namesInp.getText().toString().trim().split("\\s+");
            String[] taskArray = tasksInp.getText().toString().trim().split("\\s+");

            if (nameArray.length == 0 || taskArray.length == 0) {
                Toast.makeText(this, "Please enter both names and tasks", Toast.LENGTH_SHORT).show();
                return;
            }

            if (taskArray.length > nameArray.length) {
                Toast.makeText(this, "Tasks cannot be more than names", Toast.LENGTH_SHORT).show();
                return;
            }

            List<String> names = new ArrayList<>(Arrays.asList(nameArray));
            List<String> tasks = new ArrayList<>(Arrays.asList(taskArray));
            Map<String, List<String>> groups = new HashMap<>();

            for (String task : tasks) {
                groups.put(task, new ArrayList<>());
            }

            int maxGroupSize = (int) Math.ceil((double) names.size() / tasks.size());

            while (!names.isEmpty()) {
                int randomNameIndex = (int) (Math.random() * names.size());
                int randomTaskIndex = (int) (Math.random() * tasks.size());

                String name = names.get(randomNameIndex);
                String task = tasks.get(randomTaskIndex);

                if (groups.get(task).size() < maxGroupSize) {
                    groups.get(task).add(name);
                    names.remove(randomNameIndex);
                }
            }

            List<TaskItem> result = new ArrayList<>();
            for (String task : groups.keySet()) {
                result.add(new TaskItem(task, groups.get(task)));
            }

            recyclerView.setAdapter(new TaskAdapter(result));
        });
    }
}
