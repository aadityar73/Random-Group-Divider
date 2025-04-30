package com.example.randomgroupdivider;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText loginEmailInp, loginPasswordInp;
    Button loginBtn, signUpPageBtn;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loginEmailInp = findViewById(R.id.signUpEmailInp);
        loginPasswordInp = findViewById(R.id.signUpPasswordInp);
        loginBtn = findViewById(R.id.signUpBtn);
        signUpPageBtn = findViewById(R.id.loginPage);
        databaseHelper = new DatabaseHelper(this);

        loginBtn.setOnClickListener(view -> {

            if(loginEmailInp.getText().toString().isEmpty() || loginPasswordInp.getText().toString().isEmpty()){
                Toast.makeText(this, "Please fill all the fields!", Toast.LENGTH_SHORT).show();
            }

            Cursor cursor = databaseHelper.searchUser(loginEmailInp.getText().toString(), loginPasswordInp.getText().toString());

            if (cursor.moveToFirst()) {

                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();

                Intent homeActivity = new Intent(MainActivity.this, Home.class);
                startActivity(homeActivity);
            } else {
                Toast.makeText(this, "No user found!", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        });

        signUpPageBtn.setOnClickListener(view -> {
            Intent signUpActivity = new Intent(MainActivity.this, SignUp.class);
            startActivity(signUpActivity);
        });
    }
}