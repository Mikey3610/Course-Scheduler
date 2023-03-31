package com.example.c196_application_latest.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.c196_application_latest.Adapter.AssessmentAdapter;
import com.example.c196_application_latest.Database.Repository;
import com.example.c196_application_latest.Entity.Assessment;
import com.example.c196_application_latest.Entity.Course;
import com.example.c196_application_latest.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EditCourses_AssessmentsList extends AppCompatActivity {

    EditText editCourseNameText;
    EditText editCourseStartText;
    EditText editCourseEndText;
    EditText editCourseStatusText;
    EditText editInstructorNameText;
    EditText editInstructorPhoneText;
    EditText editInstructorEmailText;
    EditText editCourseNotesText;

    //NEW
    DatePickerDialog.OnDateSetListener startDate;
    final Calendar myCalendarStart = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener endDate;
    final Calendar myCalendarEnd = Calendar.getInstance();

    String myFormat;
    SimpleDateFormat sdf;

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

        //NEW
        myFormat = "MM/dd/yy";
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        editCourseStartText.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Date date;
                String info = editCourseStartText.getText().toString();
                if (info.equals(""))info="04/10/23";
                try {
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(EditCourses_AssessmentsList.this, startDate, myCalendarStart.get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        startDate = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendarStart.set(Calendar.YEAR,year);
                myCalendarStart.set(Calendar.MONTH,monthOfYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabelStart();
            }
        };

        editCourseEndText = findViewById(R.id.editCourseEndText);

        myFormat = "MM/dd/yy";
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        editCourseEndText.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Date date;
                String info = editCourseEndText.getText().toString();
                if (info.equals(""))info="04/10/23";
                try {
                    myCalendarEnd.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(EditCourses_AssessmentsList.this, endDate, myCalendarEnd.get(Calendar.YEAR), myCalendarEnd.get(Calendar.MONTH),
                        myCalendarEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        endDate = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendarEnd.set(Calendar.YEAR,year);
                myCalendarEnd.set(Calendar.MONTH,monthOfYear);
                myCalendarEnd.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabelEnd();
            }
        };

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

    //Updates the datePicker if you choose a new date
    private void updateLabelStart(){
        editCourseStartText.setText(sdf.format(myCalendarStart.getTime()));
    }
    private void updateLabelEnd(){
        editCourseEndText.setText(sdf.format(myCalendarEnd.getTime()));
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
            case R.id.shareCourseNotes:
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