package com.example.prueba1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Iniciamos Layout
        linearLayoutManager = new LinearLayoutManager(this);
    }
}