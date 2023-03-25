package com.example.c196_application_latest.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.c196_application_latest.Adapter.CourseAdapter;
import com.example.c196_application_latest.Adapter.TermAdapter;
import com.example.c196_application_latest.Database.Repository;
import com.example.c196_application_latest.Entity.Course;
import com.example.c196_application_latest.Entity.Term;
import com.example.c196_application_latest.R;

import java.util.ArrayList;
import java.util.List;

public class EditTerms_CoursesList extends AppCompatActivity {
    EditText editTermNameText;
    EditText editTermStartText;
    EditText editTermEndText;
    int termId;
    String termName;
    String termStart;
    String termEnd;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_terms_courses_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        repository = new Repository(getApplication());
        RecyclerView recyclerView = findViewById(R.id.CoursesForTermRecyclerView);
        Repository repo = new Repository(getApplication());

        //TODO Does this code need to be changed so the recyclerview doesnt show in all the terms?
        List<Course> courses = repo.getAllCourses();

        final CourseAdapter adapter = new CourseAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        for(Course c: repository.getAllCourses()){
            if (c.getTermId() == termId){
                courses.add(c);
            }
        }
        adapter.setCourses(courses);

        editTermNameText = findViewById(R.id.editTermNameText);
        editTermStartText = findViewById(R.id.editTermStartText);
        editTermEndText = findViewById(R.id.editTermEndText);

        termId = getIntent().getIntExtra("termId", -1);
        termName = getIntent().getStringExtra("termName");
        termStart = getIntent().getStringExtra("termStart");
        termEnd = getIntent().getStringExtra("termEnd");

        editTermNameText.setText(termName);
        editTermStartText.setText(termStart);
        editTermEndText.setText(termEnd);

    }


    public void saveAddTerm(View view) {
        Term term;
        if (termId == -1){
            int newId = repository.getAllTerms().get(repository.getAllTerms().size() - 1).getTermId() +1;
            term = new Term(newId, editTermNameText.getText().toString(), editTermStartText.getText().toString(), editTermEndText.getText().toString());
            repository.insert(term);
        } else {
            term = new Term(termId, editTermNameText.getText().toString(), editTermStartText.getText().toString(), editTermEndText.getText().toString());
            repository.update(term);
        }

        //TODO Check if this is correct
        Intent intent = new Intent(EditTerms_CoursesList.this, AllTerms.class);
        startActivity(intent);
        //finish();
    }

    public void deleteTerm(View view) {

    }

    public void addCourse(View view) {
        Intent intent = new Intent(EditTerms_CoursesList.this, AddCourses.class);
        intent.putExtra("termId", termId);
        startActivity(intent);
    }
}