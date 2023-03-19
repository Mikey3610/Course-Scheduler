package com.example.c196_application_latest.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.c196_application_latest.Database.Repository;
import com.example.c196_application_latest.Entity.Course;
import com.example.c196_application_latest.Entity.Term;
import com.example.c196_application_latest.R;

public class AddCourses extends AppCompatActivity {
    EditText editCourseTitle;
    EditText editCourseStart;
    EditText editCourseEnd;
    EditText editCourseStatus;
    EditText editInstructorName;
    EditText editInstructorPhone;
    EditText editInstructorEmail;
    EditText editCourseNotes;

    int instructorId;
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
        setContentView(R.layout.activity_add_courses);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        editCourseTitle = findViewById(R.id.editCourseTitle);
        editCourseStart = findViewById(R.id.editCourseStart);
        editCourseEnd = findViewById(R.id.editCourseEnd);
        editCourseStatus = findViewById(R.id.editCourseStatus);
        editInstructorName = findViewById(R.id.editInstructorName);
        editInstructorPhone = findViewById(R.id.editInstructorPhone);
        editInstructorEmail = findViewById(R.id.editInstructorEmail);
        editCourseNotes = findViewById(R.id.editCourseNotes);

        courseId = getIntent().getIntExtra("courseId", -1);
        termId = getIntent().getIntExtra("termId", -1);
        courseTitle = getIntent().getStringExtra("courseTitle");
        courseStart = getIntent().getStringExtra("courseStart");
        courseEnd = getIntent().getStringExtra("courseEnd");
        instructorId = getIntent().getIntExtra("instructorId", -1);
        instructorName = getIntent().getStringExtra("instructorName");
        instructorPhone = getIntent().getStringExtra("instructorPhone");
        instructorEmail = getIntent().getStringExtra("instructorEmail");
        courseNotes = getIntent().getStringExtra("courseNotes");

        repository = new Repository(getApplication());
    }

    public void saveAddCourse(View view) {
        Course course;
        if (courseId == -1){
            int newId = repository.getAllCourses().get(repository.getAllCourses().size() - 1).getCourseId() +1;

            //TODO Need to figure out if termId and InstructorId for the below object are correctly written
            course = new Course(newId, termId, editCourseTitle.getText().toString(), editCourseStart.getText().toString(),
                    editCourseEnd.getText().toString(), editCourseStatus.getText().toString(), instructorId, editInstructorName.getText().toString(), editInstructorPhone.getText().toString(), editInstructorEmail.getText().toString(), editCourseNotes.getText().toString());
            repository.insert(course);
        } else {
            course = new Course(courseId, termId, editCourseTitle.getText().toString(), editCourseStart.getText().toString(), editCourseEnd.getText().toString(),
                    editCourseStatus.getText().toString(), instructorId, editInstructorName.getText().toString(), editInstructorPhone.getText().toString(), editInstructorEmail.getText().toString(), editCourseNotes.getText().toString());
            repository.update(course);
        }

        Intent intent = new Intent(AddCourses.this, EditTerms_CoursesList.class);
        startActivity(intent);
    }
}