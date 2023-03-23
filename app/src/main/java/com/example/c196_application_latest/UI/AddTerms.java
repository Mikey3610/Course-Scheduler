package com.example.c196_application_latest.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.c196_application_latest.Database.Repository;
import com.example.c196_application_latest.Entity.Term;
import com.example.c196_application_latest.R;

public class AddTerms extends AppCompatActivity {
    EditText editTermName;
    EditText editTermStart;
    EditText editTermEnd;
    int termId;
    String termName;
    String termStart;
    String termEnd;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_terms);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        editTermName = findViewById(R.id.editTermName);
        editTermStart = findViewById(R.id.editTermStart);
        editTermEnd = findViewById(R.id.editTermEnd);

        termId = getIntent().getIntExtra("termId", -1);
        termName = getIntent().getStringExtra("termName");
        termStart = getIntent().getStringExtra("termStart");
        termEnd = getIntent().getStringExtra("termEnd");

        repository = new Repository(getApplication());
    }

    public void saveAddTerm(View view) {
        Term term;
        if (termId == -1){
            int newId = repository.getAllTerms().get(repository.getAllTerms().size() - 1).getTermId() +1;
            term = new Term(newId, editTermName.getText().toString(), editTermStart.getText().toString(), editTermEnd.getText().toString());
            repository.insert(term);
        } else {
            term = new Term(termId, editTermName.getText().toString(), editTermStart.getText().toString(), editTermEnd.getText().toString());
            repository.update(term);
        }

        //Intent intent = new Intent(AddTerms.this, AllTerms.class);
        //startActivity(intent);

        finish();
    }
    //TODO Need to create onResume method
}