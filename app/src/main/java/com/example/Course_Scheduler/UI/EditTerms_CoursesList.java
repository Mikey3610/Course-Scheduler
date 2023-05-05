package com.example.Course_Scheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Course_Scheduler.Adapter.CourseAdapter;
import com.example.Course_Scheduler.Database.Repository;
import com.example.Course_Scheduler.Entity.Course;
import com.example.Course_Scheduler.Entity.Term;
import com.example.Course_Scheduler.R;

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

    Term currentTerm;
    int numCourses;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_terms_courses_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.CoursesForTermRecyclerView);
        repository = new Repository(getApplication());

        final CourseAdapter adapter = new CourseAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //EditText fields 1st
        editTermNameText = findViewById(R.id.editTermNameText);
        editTermStartText = findViewById(R.id.editTermStartText);
        editTermEndText = findViewById(R.id.editTermEndText);

        //Info from adapters 2nd
        termId = getIntent().getIntExtra("termId", termId);
        termName = getIntent().getStringExtra("termName");
        termStart = getIntent().getStringExtra("termStart");
        termEnd = getIntent().getStringExtra("termEnd");

        //Set the text of the EditText fields 3rd
        editTermNameText.setText(termName);
        editTermStartText.setText(termStart);
        editTermEndText.setText(termEnd);

        List<Course> courses = new ArrayList<>();
        for (Course c : repository.getAllCourses()) {
            if (c.getTermId() == termId) {
                courses.add(c);
            }
        }
        adapter.setCourses(courses);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_allterms, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.deleteTerm:
                for (Term t : repository.getAllTerms()) {
                    if (t.getTermId() == termId) currentTerm = t;
                }

                numCourses = 0;
                for (Course course : repository.getAllCourses()) {
                    if (course.getTermId() == termId) ++numCourses;
                }

                if (numCourses == 0) {
                    repository.delete(currentTerm);
                    Toast.makeText(EditTerms_CoursesList.this, currentTerm.getTermName() + " was deleted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(EditTerms_CoursesList.this, "Can't delete a term with assigned courses", Toast.LENGTH_LONG).show();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveAddTerm(View view) {
        Term term;
        if (termId == -1) {
            int newId = repository.getAllTerms().get(repository.getAllTerms().size() - 1).getTermId() + 1;
            term = new Term(newId, editTermNameText.getText().toString(), editTermStartText.getText().toString(), editTermEndText.getText().toString());
            repository.insert(term);
        } else {
            term = new Term(termId, editTermNameText.getText().toString(), editTermStartText.getText().toString(), editTermEndText.getText().toString());
            repository.update(term);
        }

        Intent intent = new Intent(EditTerms_CoursesList.this, AllTerms.class);
        startActivity(intent);
    }

    public void deleteTerm(View view) {

    }

    public void addCourse(View view) {
        Intent intent = new Intent(EditTerms_CoursesList.this, AddCourses.class);
        intent.putExtra("termId", termId);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        repository = new Repository(getApplication());

        final CourseAdapter adapter = new CourseAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        editTermNameText.setText(termName);
        editTermStartText.setText(termStart);
        editTermEndText.setText(termEnd);

        List<Course> courses = new ArrayList<>();
        for (Course c : repository.getAllCourses()) {
            if (c.getTermId() == termId) {
                courses.add(c);
            }
        }
        adapter.setCourses(courses);
    }
}