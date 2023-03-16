package com.example.c196_application_latest.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.c196_application_latest.R;

public class AddTerms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_terms);
    }

    public void saveAddTerm(View view) {
        Intent intent = new Intent(AddTerms.this, AllTerms.class);
        startActivity(intent);
    }
}