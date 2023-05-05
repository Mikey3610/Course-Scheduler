package com.example.Course_Scheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.Course_Scheduler.Database.Repository;
import com.example.Course_Scheduler.Entity.Course;
import com.example.Course_Scheduler.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddCourses extends AppCompatActivity {
    EditText editCourseTitle;
    EditText editCourseStart;
    EditText editCourseEnd;
    EditText editCourseStatus;
    EditText editInstructorName;
    EditText editInstructorPhone;
    EditText editInstructorEmail;
    EditText editCourseNotes;

    DatePickerDialog.OnDateSetListener startDate;
    final Calendar myCalendarStart = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener endDate;
    final Calendar myCalendarEnd = Calendar.getInstance();

    String myFormat;
    SimpleDateFormat sdf;

    int courseId;
    int termId;

    String termName;
    String termStart;
    String termEnd;

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

        //NEW
        myFormat = "MM/dd/yy";
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        editCourseStart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Date date;
                String info = editCourseStart.getText().toString();
                if (info.equals(""))info="04/10/23";
                try {
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(AddCourses.this, startDate, myCalendarStart.get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
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

        myFormat = "MM/dd/yy";
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        editCourseEnd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Date date;
                String info = editCourseEnd.getText().toString();
                if (info.equals(""))info="04/10/23";
                try {
                    myCalendarEnd.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(AddCourses.this, endDate, myCalendarEnd.get(Calendar.YEAR), myCalendarEnd.get(Calendar.MONTH),
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

        editCourseStatus = findViewById(R.id.editCourseStatus);
        editInstructorName = findViewById(R.id.editInstructorName);
        editInstructorPhone = findViewById(R.id.editInstructorPhone);
        editInstructorEmail = findViewById(R.id.editInstructorEmail);
        editCourseNotes = findViewById(R.id.editCourseNotes);

        courseId = getIntent().getIntExtra("courseId", -1);
        termId = getIntent().getIntExtra("termId", termId);
        courseTitle = getIntent().getStringExtra("courseTitle");
        courseStart = getIntent().getStringExtra("courseStart");
        courseEnd = getIntent().getStringExtra("courseEnd");
        courseStatus = getIntent().getStringExtra("courseStatus");
        instructorName = getIntent().getStringExtra("instructorName");
        instructorPhone = getIntent().getStringExtra("instructorPhone");
        instructorEmail = getIntent().getStringExtra("instructorEmail");
        courseNotes = getIntent().getStringExtra("courseNotes");

        repository = new Repository(getApplication());
    }

    //Updates the datePicker if you choose a new date
    private void updateLabelStart(){
        editCourseStart.setText(sdf.format(myCalendarStart.getTime()));
    }
    private void updateLabelEnd(){
        editCourseEnd.setText(sdf.format(myCalendarEnd.getTime()));
    }

    public void saveAddCourse(View view) {
        Course course;
        if (courseId == -1){
            int newId = repository.getAllCourses().get(repository.getAllCourses().size() - 1).getCourseId() +1;

            course = new Course(newId, termId, editCourseTitle.getText().toString(), editCourseStart.getText().toString(),
                    editCourseEnd.getText().toString(), editCourseStatus.getText().toString(), editInstructorName.getText().toString(),
                    editInstructorPhone.getText().toString(), editInstructorEmail.getText().toString(), editCourseNotes.getText().toString());
            repository.insert(course);
        } else {
            course = new Course(courseId, termId, editCourseTitle.getText().toString(), editCourseStart.getText().toString(), editCourseEnd.getText().toString(),
                    editCourseStatus.getText().toString(), editInstructorName.getText().toString(), editInstructorPhone.getText().toString(),
                    editInstructorEmail.getText().toString(), editCourseNotes.getText().toString());
            repository.update(course);
        }

        Intent intent = new Intent(AddCourses.this, EditTerms_CoursesList.class);
        intent.putExtra("termId", termId);
        intent.putExtra("termName", termName);
        intent.putExtra("termStart", termStart);
        intent.putExtra("termEnd", termEnd);
        finish();
    }

}