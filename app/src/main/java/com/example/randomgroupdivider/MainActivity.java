package com.example.randomgroupdivider;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private static final String PREFS_NAME = "language_prefs";
    private static final String KEY_LANGUAGE = "Selected Language";
    EditText loginEmailInp, loginPasswordInp;
    Button loginBtn, signUpPageBtn, changeLanguageBtn;
    DatabaseHelper databaseHelper;
    AlertDialog.Builder languageBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String selectedLanguage = preferences.getString(KEY_LANGUAGE, "en");
        setLocale(selectedLanguage);

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

        changeLanguageBtn = findViewById(R.id.changeLanguageBtn2);
        languageBuilder = new AlertDialog.Builder(this);

        String[] languages = {"English", "हिंदी", "मराठी", "ગુજરાતી"};
        String[] langCodes = {"en", "hi", "mr", "guj"};

        changeLanguageBtn.setOnClickListener(view -> {
            languageBuilder.setTitle("Select the Language: ");

            languageBuilder.setSingleChoiceItems(languages, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    setLocale(langCodes[i]);
                    recreate();
                    dialogInterface.dismiss();
                }
            });

            languageBuilder.create().show();
        });


        loginBtn.setOnClickListener(view -> {

            if(loginEmailInp.getText().toString().isEmpty() || loginPasswordInp.getText().toString().isEmpty()){
                Toast.makeText(this, getString(R.string.missing_fields), Toast.LENGTH_SHORT).show();
            }

            Cursor cursor = databaseHelper.searchUser(loginEmailInp.getText().toString(), loginPasswordInp.getText().toString());

            if (cursor.moveToFirst()) {

                Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_SHORT).show();

                Intent homeActivity = new Intent(MainActivity.this, Home.class);
                startActivity(homeActivity);
            } else {
                Toast.makeText(this, getString(R.string.user_not_found), Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        });

        signUpPageBtn.setOnClickListener(view -> {
            Intent signUpActivity = new Intent(MainActivity.this, SignUp.class);
            startActivity(signUpActivity);
        });
    }

    private void setLocale(String langCode){
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_LANGUAGE, langCode);
        editor.apply();
    }
}