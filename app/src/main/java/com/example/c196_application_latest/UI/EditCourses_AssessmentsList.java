package com.example.c196_application_latest.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.c196_application_latest.Adapter.AssessmentAdapter;
import com.example.c196_application_latest.Adapter.CourseAdapter;
import com.example.c196_application_latest.Database.Repository;
import com.example.c196_application_latest.Entity.Assessment;
import com.example.c196_application_latest.Entity.Course;
import com.example.c196_application_latest.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_courses_assessments_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.AssessmentsForCourseRecyclerView);
        Repository repo = new Repository(getApplication());
        List<Assessment> assessments = repo.getAllAssessments();
        final AssessmentAdapter adapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setAssessments(assessments);

        editCourseNameText = findViewById(R.id.editCourseNameText);
        editCourseStartText = findViewById(R.id.editCourseStartText);
        editCourseEndText = findViewById(R.id.editCourseEndText);
        editCourseStatusText = findViewById(R.id.editCourseStatusText);
        editInstructorNameText = findViewById(R.id.editInstructorNameText);
        editInstructorPhoneText = findViewById(R.id.editInstructorPhoneText);
        editInstructorEmailText = findViewById(R.id.editInstructorEmailText);
        editCourseNotesText = findViewById(R.id.editCourseNotesText);

        courseId = getIntent().getIntExtra("courseId", -1);
        termId = getIntent().getIntExtra("termId", -1);
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

        repository = new Repository(getApplication());

    }

    public void addAssessment(View view) {
        Intent intent = new Intent(EditCourses_AssessmentsList.this, AddEditAssessments.class);
        intent.putExtra("termId", termId);
        intent.putExtra("courseId", courseId);
        startActivity(intent);
    }

    public void saveEditCourse(View view) {
    }

    public void deleteCourseButton(View view) {

    }
}