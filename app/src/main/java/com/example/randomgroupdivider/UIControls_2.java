package com.example.randomgroupdivider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UIControls_2 extends AppCompatActivity {

    ListView listView;
    Spinner spinner;
    Button showBtn, nextBtn, prevBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_uicontrols2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.uiControlListView2);
        spinner = findViewById(R.id.uiControlSpinner2);
        showBtn = findViewById(R.id.uiControlShowBtn);

        nextBtn = findViewById(R.id.nextBtn_8);
        prevBtn = findViewById(R.id.prvBtn_4);

        nextBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, UIControls_3.class));
        });

        prevBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, UIControls_1.class));
        });

        showBtn.setOnClickListener(view -> {

            // Spinner
            String[] iplTeams = {"Mumbai Indians", "Punjab Kings", "Delhi Capitals", "Kolkata Knight Riders"};
            ArrayAdapter<String> iplTeamsAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, iplTeams);
            spinner.setAdapter(iplTeamsAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(UIControls_2.this, "Your favorite IPL Team: " + iplTeams[i], Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    Toast.makeText(UIControls_2.this, "Seems like you are a fan of some other sport!", Toast.LENGTH_SHORT).show();
                }
            });

            // ListView
            String[] programmingLanguages = {"JavaScript", "Java", "Kotlin", "Python", "C++"};

            ArrayAdapter<String> languagesAdapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, programmingLanguages);

            listView.setAdapter(languagesAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(UIControls_2.this, "You selected: " + programmingLanguages[i], Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}