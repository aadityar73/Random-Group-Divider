package com.example.randomgroupdivider;

import android.content.Intent;
import android.database.Cursor;
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

public class SqliteDb extends AppCompatActivity {

    EditText emailInp, passwordInp, userTypeInp, rollNoInp;
    Button insertBtn, showBtn, searchBtn, updateBtn, deleteBtn, nextBtn, prevBtn;
    TextView displayRecords;
    DatabaseSession databaseSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sqlite_db);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        emailInp = findViewById(R.id.emailInpSqlite);
        passwordInp = findViewById(R.id.passwordInpSqlite);
        userTypeInp = findViewById(R.id.userTypeInpSqlite);
        insertBtn = findViewById(R.id.insertBtnSqlite);
        showBtn = findViewById(R.id.showBtnSqlite);
        rollNoInp = findViewById(R.id.rollNoInpSqlite);
        searchBtn = findViewById(R.id.searchBtnSqlite);
        updateBtn = findViewById(R.id.updateBtnSqlite);
        deleteBtn = findViewById(R.id.deleteBtnSqlite);
        displayRecords = findViewById(R.id.displayRecordsSqlite);

        nextBtn = findViewById(R.id.nextBtn_10);
        prevBtn = findViewById(R.id.prvBtn_3);

        nextBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, StringArray.class));
        });

        prevBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, UIControls_3.class));
        });

        databaseSession = new DatabaseSession(this);

        insertBtn.setOnClickListener(view -> {
            if (!emailInp.getText().toString().isEmpty() && !passwordInp.getText().toString().isEmpty() && !userTypeInp.getText().toString().isEmpty()) {
                boolean res = databaseSession.insertRecord(emailInp.getText().toString(), passwordInp.getText().toString(), userTypeInp.getText().toString());

                if (res) {
                    Toast.makeText(SqliteDb.this, "Record inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SqliteDb.this, "Insert failed", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(SqliteDb.this, "All fields are mandatory", Toast.LENGTH_LONG).show();
            }
        });

        showBtn.setOnClickListener(view -> {
            Cursor cursor = databaseSession.getAllRecords();
            StringBuilder records = new StringBuilder();
            if (cursor.moveToFirst()) {
                do {
                    records.append("Roll No: ").append(cursor.getInt(0)).append("\n");
                    records.append("Email: ").append(cursor.getString(1)).append("\n");
                    records.append("Password: ").append(cursor.getString(2)).append("\n");
                    records.append("Usertype: ").append(cursor.getString(3)).append("\n\n");
                } while (cursor.moveToNext());
            } else {
                records.append("No records found");
            }
            cursor.close();
            displayRecords.setText(records.toString());
        });

        searchBtn.setOnClickListener(view ->{
            Cursor cursor = databaseSession.searchUser(rollNoInp.getText().toString());
            StringBuilder record = new StringBuilder();
            if (cursor.moveToFirst()) {
                record.append("Roll No: ").append(cursor.getInt(0)).append("\n");
                record.append("Email: ").append(cursor.getString(1)).append("\n");
                record.append("Password: ").append(cursor.getString(2)).append("\n");
                record.append("Usertype: ").append(cursor.getString(3)).append("\n\n");
            } else {
                record.append("No records found");
            }
            cursor.close();
            displayRecords.setText(record.toString());
        });

        updateBtn.setOnClickListener(view -> {
            boolean result = databaseSession.updateRecord(rollNoInp.getText().toString(), emailInp.getText().toString(), passwordInp.getText().toString(), userTypeInp.getText().toString());

            if(result){
                Toast.makeText(this, "Record updated", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this, "Record not updated", Toast.LENGTH_SHORT).show();
            }
        });

        deleteBtn.setOnClickListener(view -> {
            boolean result = databaseSession.deleteRecord(rollNoInp.getText().toString());

            if(result){
                Toast.makeText(this, "Record deleted", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this, "Record not deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}