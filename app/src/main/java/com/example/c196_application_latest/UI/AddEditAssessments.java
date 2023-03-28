package com.example.c196_application_latest.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.c196_application_latest.Database.Repository;
import com.example.c196_application_latest.Entity.Assessment;
import com.example.c196_application_latest.Entity.Course;
import com.example.c196_application_latest.R;

public class AddEditAssessments extends AppCompatActivity {
    EditText editAssessmentTitle;
    EditText editAssessmentType;
    EditText editAssessmentStart;
    EditText editAssessmentEnd;
    EditText editAssessmentDescription;

    int assessmentId;
    int courseId;

    String assessmentType;
    String assessmentStart;
    String assessmentEnd;
    String assessmentTitle;
    String assessmentDescription;

    Repository repository;

    String courseTitle;
    String courseStart;
    String courseEnd;
    String courseStatus;
    String instructorName;
    String instructorPhone;
    String instructorEmail;
    String courseNotes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_assessments);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        editAssessmentTitle = findViewById(R.id.editAssessmentTitle);
        editAssessmentType = findViewById(R.id.editAssessmentType);
        editAssessmentStart = findViewById(R.id.editAssessmentStart);
        editAssessmentEnd = findViewById(R.id.editAssessmentEnd);
        editAssessmentDescription = findViewById(R.id.editAssessmentDescription);

        assessmentId = getIntent().getIntExtra("assessmentId", -1);
        courseId = getIntent().getIntExtra("courseId", courseId);
        assessmentType = getIntent().getStringExtra("assessmentType");
        assessmentStart = getIntent().getStringExtra("assessmentStart");
        assessmentEnd = getIntent().getStringExtra("assessmentEnd");
        assessmentTitle = getIntent().getStringExtra("assessmentTitle");
        assessmentDescription = getIntent().getStringExtra("assessmentDescription");

        repository = new Repository(getApplication());

        editAssessmentTitle.setText(assessmentTitle);
        editAssessmentType.setText(assessmentType);
        editAssessmentStart.setText(assessmentStart);
        editAssessmentEnd.setText(assessmentEnd);
        editAssessmentDescription.setText(assessmentDescription);
    }

    public void saveEditAddAssessment(View view) {
        Assessment assessment;
        if (assessmentId == -1){
            int newId = repository.getAllAssessments().get(repository.getAllAssessments().size() - 1).getAssessmentId() +1;
            assessment = new Assessment(newId, courseId, editAssessmentType.getText().toString(), editAssessmentStart.getText().toString(),
                    editAssessmentEnd.getText().toString(), editAssessmentTitle.getText().toString(), editAssessmentDescription.getText().toString());
            repository.insert(assessment);
        } else {
            assessment = new Assessment(assessmentId, courseId, editAssessmentType.getText().toString(), editAssessmentStart.getText().toString(),
                    editAssessmentEnd.getText().toString(), editAssessmentTitle.getText().toString(), editAssessmentDescription.getText().toString());
            repository.update(assessment);
        }

        Intent intent = new Intent(AddEditAssessments.this, EditCourses_AssessmentsList.class);
        intent.putExtra("courseTitle", courseTitle);
        intent.putExtra("courseStart", courseStart);
        intent.putExtra("courseEnd", courseEnd);
        intent.putExtra("courseStatus", courseStatus);
        intent.putExtra("instructorName", instructorName);
        intent.putExtra("instructorPhone", instructorPhone);
        intent.putExtra("instructorEmail", instructorEmail);
        intent.putExtra("courseNotes", courseNotes);
        //startActivity(intent);
        finish();
    }
}