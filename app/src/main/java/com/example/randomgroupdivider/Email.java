package com.example.randomgroupdivider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Email extends AppCompatActivity {
    EditText recipientEmail, subject, emailBody, cc;
    ImageButton sendEmailBtn;
    Button nextBtn, prevBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_email);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recipientEmail = findViewById(R.id.recipientEmail);
        cc = findViewById(R.id.cc);
        subject = findViewById(R.id.subject);
        emailBody = findViewById(R.id.emailBody);
        sendEmailBtn = findViewById(R.id.sendEmailBtn);

        sendEmailBtn.setOnClickListener(view ->{
            Intent sendEmail = new Intent(Intent.ACTION_SEND);
            sendEmail.setType("message/rfc822");
            sendEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{recipientEmail.getText().toString()});
            sendEmail.putExtra(Intent.EXTRA_CC, new String[]{cc.getText().toString()});
            sendEmail.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
            sendEmail.putExtra(Intent.EXTRA_TEXT, emailBody.getText().toString());

            startActivity(Intent.createChooser(sendEmail, "Select Email App: "));
        });

        nextBtn = findViewById(R.id.nextBtn_5);
        prevBtn = findViewById(R.id.prvBtn_8);

        nextBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, WallpaperCall.class));
        });

        prevBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, Session4_1.class));
        });

    }
}