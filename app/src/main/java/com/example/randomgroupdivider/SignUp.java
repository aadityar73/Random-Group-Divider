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

public class SignUp extends AppCompatActivity {

    EditText signUpUsernameInp, signUpEmailInp, signUpPasswordInp;
    Button signUpBtn, loginPageBtn;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        signUpUsernameInp = findViewById(R.id.signUpUsernameInp);
        signUpEmailInp = findViewById(R.id.signUpEmailInp);
        signUpPasswordInp = findViewById(R.id.signUpPasswordInp);
        signUpBtn = findViewById(R.id.signUpBtn);
        loginPageBtn = findViewById(R.id.loginPage);

        databaseHelper = new DatabaseHelper(this);

        signUpBtn.setOnClickListener(view -> {
            if (!signUpUsernameInp.getText().toString().isEmpty() && !signUpEmailInp.getText().toString().isEmpty() && !signUpPasswordInp.getText().toString().isEmpty()) {
                boolean res = databaseHelper.insertRecord(signUpUsernameInp.getText().toString(), signUpEmailInp.getText().toString(), signUpPasswordInp.getText().toString());

                if (res) {
                    Toast.makeText(SignUp.this, "Account created successfully!", Toast.LENGTH_SHORT).show();

                    Intent loginActivity = new Intent(SignUp.this, MainActivity.class);
                    startActivity(loginActivity);
                } else {
                    Toast.makeText(SignUp.this, "Account not created!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(SignUp.this, "All fields are mandatory", Toast.LENGTH_LONG).show();
            }
        });

        loginPageBtn.setOnClickListener(view -> {
            Intent loginActivity = new Intent(SignUp.this, MainActivity.class);
            startActivity(loginActivity);
        });
    }
}