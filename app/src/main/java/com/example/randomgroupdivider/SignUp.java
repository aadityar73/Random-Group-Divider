package com.example.randomgroupdivider;

import static android.media.MediaFormat.KEY_LANGUAGE;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
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

public class SignUp extends AppCompatActivity {

    private SharedPreferences preferences;
    private static final String PREFS_NAME = "language_prefs";
    private static final String KEY_LANGUAGE = "Selected Language";
    EditText signUpUsernameInp, signUpEmailInp, signUpPasswordInp;
    Button signUpBtn, loginPageBtn, changeLanguageBtn;
    DatabaseHelper databaseHelper;
    AlertDialog.Builder languageBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String selectedLanguage = preferences.getString(KEY_LANGUAGE, "en");
        setLocale(selectedLanguage);

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
        changeLanguageBtn = findViewById(R.id.changeLanguageBtn);

        databaseHelper = new DatabaseHelper(this);
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


        signUpBtn.setOnClickListener(view -> {
            if (!signUpUsernameInp.getText().toString().isEmpty() && !signUpEmailInp.getText().toString().isEmpty() && !signUpPasswordInp.getText().toString().isEmpty()) {
                boolean res = databaseHelper.insertRecord(signUpUsernameInp.getText().toString(), signUpEmailInp.getText().toString(), signUpPasswordInp.getText().toString());

                if (res) {
                    Toast.makeText(SignUp.this, getString(R.string.signup_success), Toast.LENGTH_SHORT).show();

                    Intent loginActivity = new Intent(SignUp.this, Home.class);
                    startActivity(loginActivity);
                } else {
                    Toast.makeText(SignUp.this, getString(R.string.signup_fail), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(SignUp.this, getString(R.string.missing_fields), Toast.LENGTH_LONG).show();
            }
        });

        loginPageBtn.setOnClickListener(view -> {
            Intent loginActivity = new Intent(SignUp.this, MainActivity.class);
            startActivity(loginActivity);
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