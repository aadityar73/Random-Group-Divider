package com.example.randomgroupdivider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WallpaperCall extends AppCompatActivity {
    Button setWallpaperBtn, callBtn, nextBtn, prevBtn;
    EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_wallpaper_call);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        phone = findViewById(R.id.phone);
        setWallpaperBtn = findViewById(R.id.setWallpaperBtn);
        callBtn = findViewById(R.id.callBtn);

        callBtn.setOnClickListener(view -> {
            Intent dial = new Intent(Intent.ACTION_DIAL);
            dial.setData(Uri.parse("tel:" + phone.getText().toString()));
            startActivity(dial);
        });

        setWallpaperBtn.setOnClickListener(view -> {
            Intent setWallpaper = new Intent(Intent.ACTION_SET_WALLPAPER);
            startActivity(Intent.createChooser(setWallpaper, "Select your wallpaper: "));
        });

        nextBtn = findViewById(R.id.nextBtn_6);
        prevBtn = findViewById(R.id.prvBtn_7);

        nextBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, UIControls_1.class));
        });

        prevBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, Email.class));
        });
    }
}