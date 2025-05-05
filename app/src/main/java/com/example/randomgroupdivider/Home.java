package com.example.randomgroupdivider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.*;

public class Home extends AppCompatActivity {

    Button groupDividerBtn, numGeneratorBtn, sessionsBtn;

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

        groupDividerBtn = findViewById(R.id.groupDividerBtn);
        numGeneratorBtn = findViewById(R.id.numGeneratorBtn);
        sessionsBtn = findViewById(R.id.sessionsBtn);

        groupDividerBtn.setOnClickListener(view -> {
            Intent groupDividerActivity = new Intent(Home.this, GroupDivider.class);
            startActivity(groupDividerActivity);
        });

        numGeneratorBtn.setOnClickListener(view -> {
            Intent numGeneratorActivity = new Intent(Home.this, NumGenerator.class);
            startActivity(numGeneratorActivity);
        });

        sessionsBtn.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(Home.this, sessionsBtn);

            popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
            popupMenu.show();

            popupMenu.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();

                if (id == R.id.session_1) {
                    startActivity(new Intent(this, Session1.class));
                    return true;
                } else if (id == R.id.session_2) {
                    startActivity(new Intent(this, Session2.class));
                    return true;
                } else if (id == R.id.basic_calculator) {
                    startActivity(new Intent(this, BasicCalculator.class));
                    return true;
                } else if (id == R.id.session_4_1) {
                    startActivity(new Intent(this, Session4_1.class));
                    return true;
                } else if (id == R.id.email) {
                    startActivity(new Intent(this, Email.class));
                    return true;
                } else if (id == R.id.wallpaper_call) {
                    startActivity(new Intent(this, WallpaperCall.class));
                    return true;
                } else if (id == R.id.ui_controls_1) {
                    startActivity(new Intent(this, UIControls_1.class));
                    return true;
                } else if (id == R.id.ui_controls_2) {
                    startActivity(new Intent(this, UIControls_2.class));
                    return true;
                } else if (id == R.id.ui_controls_3) {
                    startActivity(new Intent(this, UIControls_3.class));
                    return true;
                } else if (id == R.id.sqlite_db) {
                    startActivity(new Intent(this, SqliteDb.class));
                    return true;
                } else if (id == R.id.string_array) {
                    startActivity(new Intent(this, StringArray.class));
                    return true;
                } else if (id == R.id.context_menu) {
                    startActivity(new Intent(this, ContextMenu.class));
                    return true;
                }

                return false;
            });

        });
    }
}
