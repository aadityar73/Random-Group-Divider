package com.example.randomgroupdivider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UIControls_1 extends AppCompatActivity {

    EditText comment, dateInp, multiLineInp;
    CheckBox checkBox1, checkBox2;
    RadioGroup radioGroup;
    RadioButton radioBtn1, radioBtn2, radioBtn3;

    TextView output;
    Button registerBtn;
    AutoCompleteTextView autoCompleteTextView;
    MultiAutoCompleteTextView multiAutoCompleteTextView;
    Button nextBtn, prevBtn;
    StringBuilder details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_uicontrols1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        comment = findViewById(R.id.uiControlComment);
        dateInp = findViewById(R.id.dateInp);
        multiLineInp = findViewById(R.id.multiLineInp);
        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        radioGroup = findViewById(R.id.radioGroup);
        radioBtn1 = findViewById(R.id.radioBtn1);
        radioBtn2 = findViewById(R.id.radioBtn2);
        radioBtn3 = findViewById(R.id.radioBtn3);
        output = findViewById(R.id.uiControlOutput);
        registerBtn = findViewById(R.id.uiControlRegisterBtn);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        multiAutoCompleteTextView = findViewById(R.id.multiAutoCompleteTextView);
        details = new StringBuilder();

        nextBtn = findViewById(R.id.nextBtn_7);
        prevBtn = findViewById(R.id.prvBtn_6);

        nextBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, UIControls_2.class));
        });

        prevBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, WallpaperCall.class));
        });

        registerBtn.setOnClickListener(view -> {
            if(checkBox1.isChecked()){
                details.append("Campus: ").append(checkBox1.getText().toString());
            } else{
                details.append(" , ");
            }

            if(checkBox2.isChecked()){
                details.append(checkBox2.getText().toString());
            }

            int selectedId = radioGroup.getCheckedRadioButtonId();
            RadioButton selected = findViewById(selectedId);

            details.append("\n Type of employee: " + selected.getText().toString());
            details.append("\n Comment: " + comment.getText().toString());

            output.setText(details);
        });

        String[] songs = {"RED", "WARCRY", "Rollercoaster"};

        ArrayAdapter<String> songsAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, songs);

        autoCompleteTextView.setAdapter(songsAdapter);
        autoCompleteTextView.setThreshold(3);


        String[] musicArtists = {"Seedhe Maut", "Raftaar", "Emiway"};

        ArrayAdapter<String> artistsAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, musicArtists);

        multiAutoCompleteTextView.setAdapter(artistsAdapter);
        multiAutoCompleteTextView.setThreshold(3);
        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}