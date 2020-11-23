package com.example.finalProject;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidlabs.R;
import com.example.finalProject.Audio.AudioActivity;

public class Navigation extends AppCompatActivity {
    private final Intent goHome = new Intent(Navigation.this, MainActivity.class);
    private final Intent goToCovid = new Intent(Navigation.this, Covid.class);
    private final Intent goToAudio = new Intent(this, AudioActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Spinner navSpinner = findViewById(R.id.navSpinner);
        navSpinner.setOnItemClickListener((parent, view, position, id) -> {
            if (position == 0) startActivity(goHome);
            else if (position == 1) startActivity(goToCovid);
        });
    }


}
