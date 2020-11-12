package com.example.finalProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.androidlabs.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button covid = (Button) findViewById(R.id.covid);
        covid.setOnClickListener( (click) ->
        { Intent covidActivity = new Intent(MainActivity.this, Covid.class);
          startActivity(covidActivity); });

    }
}