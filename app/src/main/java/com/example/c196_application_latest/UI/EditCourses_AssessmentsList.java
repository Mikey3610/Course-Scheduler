package com.example.c196_application_latest.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.c196_application_latest.Adapter.AssessmentAdapter;
import com.example.c196_application_latest.Adapter.CourseAdapter;
import com.example.c196_application_latest.Database.Repository;
import com.example.c196_application_latest.Entity.Assessment;
import com.example.c196_application_latest.Entity.Course;
import com.example.c196_application_latest.Entity.Term;
import com.example.c196_application_latest.R;

import java.util.ArrayList;
import java.util.List;

public class EditCourses_AssessmentsList extends AppCompatActivity {

    EditText editCourseNameText;
    EditText editCourseStartText;
    EditText editCourseEndText;
    EditText editCourseStatusText;
    EditText editInstructorNameText;
    EditText editInstructorPhoneText;
    EditText editInstructorEmailText;
    EditText editCourseNotesText;

    int courseId;
    int termId;

    String courseTitle;
    String courseStart;
    String courseEnd;
    String courseStatus;
    String instructorName;
    String instructorPhone;
    String instructorEmail;
    String courseNotes;

    Repository repository;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_courses_assessments_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.AssessmentsForCourseRecyclerView);
        repository = new Repository(getApplication());

        final AssessmentAdapter adapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        editCourseNameText = findViewById(R.id.editCourseNameText);
        editCourseStartText = findViewById(R.id.editCourseStartText);
        editCourseEndText = findViewById(R.id.editCourseEndText);
        editCourseStatusText = findViewById(R.id.editCourseStatusText);
        editInstructorNameText = findViewById(R.id.editInstructorNameText);
        editInstructorPhoneText = findViewById(R.id.editInstructorPhoneText);
        editInstructorEmailText = findViewById(R.id.editInstructorEmailText);
        editCourseNotesText = findViewById(R.id.editCourseNotesText);

        courseId = getIntent().getIntExtra("courseId", courseId);
        termId = getIntent().getIntExtra("termId", termId);
        courseTitle = getIntent().getStringExtra("courseTitle");
        courseStart = getIntent().getStringExtra("courseStart");
        courseEnd = getIntent().getStringExtra("courseEnd");
        courseStatus = getIntent().getStringExtra("courseStatus");
        instructorName = getIntent().getStringExtra("instructorName");
        instructorPhone = getIntent().getStringExtra("instructorPhone");
        instructorEmail = getIntent().getStringExtra("instructorEmail");
        courseNotes = getIntent().getStringExtra("courseNotes");


        editCourseNameText.setText(courseTitle);
        editCourseStartText.setText(courseStart);
        editCourseEndText.setText(courseEnd);
        editCourseStatusText.setText(courseStatus);
        editInstructorNameText.setText(instructorName);
        editInstructorPhoneText.setText(instructorPhone);
        editInstructorEmailText.setText(instructorEmail);
        editCourseNotesText.setText(courseNotes);



        List<Assessment> assessments = new ArrayList<>();
        for(Assessment a: repository.getAllAssessments()){
            if (a.getCourseId() == courseId){
                assessments.add(a);
            }
        }
        adapter.setAssessments(assessments);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_courses, menu);
        return true;
    }



    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, editCourseNotesText.getText().toString());
                sendIntent.putExtra(Intent.EXTRA_TITLE,editCourseNameText.getText().toString());
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;
            case R.id.notifyCourseStart:
                return true;
            case R.id.notifyCourseEnd:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void addAssessment(View view) {
        Intent intent = new Intent(EditCourses_AssessmentsList.this, AddEditAssessments.class);
        intent.putExtra("termId", termId);
        intent.putExtra("courseId", courseId);
        startActivity(intent);
    }

    public void saveEditCourse(View view) {
        Course course;
        if (courseId == -1) {
            int newId = repository.getAllCourses().get(repository.getAllCourses().size() - 1).getCourseId() + 1;
            course = new Course(newId, termId, editCourseNameText.getText().toString(), editCourseStartText.getText().toString(), editCourseEndText.getText().toString(),
                    editCourseStatusText.getText().toString(), editInstructorNameText.getText().toString(), editInstructorPhoneText.getText().toString(), editInstructorEmailText.getText().toString(),
                    editCourseNotesText.getText().toString());
            repository.insert(course);
        } else {
            course = new Course(courseId, termId, editCourseNameText.getText().toString(), editCourseStartText.getText().toString(), editCourseEndText.getText().toString(),
                    editCourseStatusText.getText().toString(), editInstructorNameText.getText().toString(), editInstructorPhoneText.getText().toString(), editInstructorEmailText.getText().toString(),
                    editCourseNotesText.getText().toString());
            repository.update(course);
        }

        Intent intent = new Intent(EditCourses_AssessmentsList.this, EditTerms_CoursesList.class);
        //startActivity(intent);
        finish();
    }

    public void deleteCourseButton(View view) {

    }

    @Override
    protected void onResume() {
        super.onResume();

        repository = new Repository(getApplication());

        final AssessmentAdapter adapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        editCourseNameText.setText(courseTitle);
        editCourseStartText.setText(courseStart);
        editCourseEndText.setText(courseEnd);
        editCourseStatusText.setText(courseStatus);
        editInstructorNameText.setText(instructorName);
        editInstructorPhoneText.setText(instructorPhone);
        editInstructorEmailText.setText(instructorEmail);
        editCourseNotesText.setText(courseNotes);

        List<Assessment> assessments = new ArrayList<>();
        for (Assessment a : repository.getAllAssessments()) {
            if (a.getCourseId() == courseId) {
                assessments.add(a);
            }
        }
        adapter.setAssessments(assessments);
    }


}