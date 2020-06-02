package com.ktu.ld1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    private static final String PREFS_FILE = "Prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences prefs = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);

        String name = prefs.getString("username", "Name");
        int age = prefs.getInt("userage", 1);
        int language = prefs.getInt("language", 0);
        boolean colormode = prefs.getBoolean("colormode", false);

        EditText usernameField = findViewById(R.id.username);
        EditText userageField = findViewById(R.id.userage);
        Spinner languageSpinner = findViewById(R.id.spinner_language);
        Switch colormodeSwitch = findViewById(R.id.switch_colormode);

        usernameField.setText(name);
        userageField.setText(Integer.toString(age));
        languageSpinner.setSelection(language);
        colormodeSwitch.setChecked(colormode);
    }

    public void saveClick(View view)
    {
        SharedPreferences.Editor prefsEditor = getSharedPreferences(PREFS_FILE, MODE_PRIVATE).edit();

        EditText usernameField = findViewById(R.id.username);
        EditText userageField = findViewById(R.id.userage);
        Spinner languageSpinner = findViewById(R.id.spinner_language);
        Switch colormodeSwitch = findViewById(R.id.switch_colormode);

        String name = usernameField.getText().toString();
        int age = Integer.parseInt(userageField.getText().toString());
        int language = languageSpinner.getSelectedItemPosition();
        boolean colormode = colormodeSwitch.isChecked();

        prefsEditor.putString("username", name);
        prefsEditor.putInt("userage", age);
        prefsEditor.putInt("language", language);
        prefsEditor.putBoolean("colormode", colormode);

        prefsEditor.apply();

        finish();

    }

    public void btnClick (View v){

        Intent intent = null;

        if (v.getId() == R.id.btn_about)
            intent = new Intent(this,AboutActivity.class);


        if(intent != null)
            startActivity(intent);


    }
}
