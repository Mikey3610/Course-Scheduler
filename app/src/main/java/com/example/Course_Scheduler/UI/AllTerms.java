package com.example.Course_Scheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.Course_Scheduler.Adapter.TermAdapter;
import com.example.Course_Scheduler.Database.Repository;
import com.example.Course_Scheduler.Entity.Term;
import com.example.Course_Scheduler.R;

import java.util.List;

public class AllTerms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_terms);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.allTermsRecyclerView);
        Repository repo = new Repository(getApplication());

        List<Term> terms = repo.getAllTerms();
        final TermAdapter adapter = new TermAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setTerms(terms);
    }

    public void addTerm(View view) {
        Intent intent = new Intent(AllTerms.this, AddTerms.class);
        startActivity(intent);
    }
}