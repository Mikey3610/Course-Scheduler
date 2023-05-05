package com.example.c196_application_latest.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.c196_application_latest.Database.Repository;
import com.example.c196_application_latest.Entity.Assessment;
import com.example.c196_application_latest.R;
import com.example.c196_application_latest.Receiver.MyReceiver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddEditAssessments extends AppCompatActivity {
    EditText editAssessmentTitle;
    EditText editAssessmentType;
    EditText editAssessmentStart;
    EditText editAssessmentEnd;
    EditText editAssessmentDescription;

    DatePickerDialog.OnDateSetListener startDate;
    final Calendar myCalendarStart = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener endDate;
    final Calendar myCalendarEnd = Calendar.getInstance();

    String myFormat;
    SimpleDateFormat sdf;

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

    Assessment currentAssessment;
    int numAssessments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_assessments);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        editAssessmentTitle = findViewById(R.id.editAssessmentTitle);
        editAssessmentType = findViewById(R.id.editAssessmentType);

        editAssessmentStart = findViewById(R.id.editAssessmentStart);

        myFormat = "MM/dd/yy";
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        editAssessmentStart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Date date;
                String info = editAssessmentStart.getText().toString();
                if (info.equals(""))info="04/25/23";
                try {
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(AddEditAssessments.this, startDate, myCalendarStart.get(Calendar.YEAR),
                        myCalendarStart.get(Calendar.MONTH),
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

        editAssessmentEnd = findViewById(R.id.editAssessmentEnd);

        myFormat = "MM/dd/yy";
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        editAssessmentEnd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Date date;
                String info = editAssessmentEnd.getText().toString();
                if (info.equals(""))info="04/10/23";
                try {
                    myCalendarEnd.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(AddEditAssessments.this, endDate, myCalendarEnd.get(Calendar.YEAR), myCalendarEnd.get(Calendar.MONTH),
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

    //Updates the datePicker if you choose a new date
    private void updateLabelStart(){
        editAssessmentStart.setText(sdf.format(myCalendarStart.getTime()));
    }
    private void updateLabelEnd(){
        editAssessmentEnd.setText(sdf.format(myCalendarEnd.getTime()));
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
        finish();
    }

    public void deleteAssessment(View view) {
        Assessment assessmentToDelete = new Assessment(assessmentId, courseId, assessmentType, assessmentStart, assessmentEnd, assessmentTitle, assessmentDescription);
        repository.delete(assessmentToDelete);

        Intent intent = new Intent(AddEditAssessments.this, EditCourses_AssessmentsList.class);
        intent.putExtra("courseTitle", courseTitle);
        intent.putExtra("courseStart", courseStart);
        intent.putExtra("courseEnd", courseEnd);
        intent.putExtra("courseStatus", courseStatus);
        intent.putExtra("instructorName", instructorName);
        intent.putExtra("instructorPhone", instructorPhone);
        intent.putExtra("instructorEmail", instructorEmail);
        intent.putExtra("courseNotes", courseNotes);
        finish();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_assessments, menu);
        return true;
    }



    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.shareAssessmentNotes:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, editAssessmentDescription.getText().toString());
                sendIntent.putExtra(Intent.EXTRA_TITLE, editAssessmentTitle.getText().toString());
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;
            case R.id.notifyAssessmentStart:
                String assessStart = editAssessmentStart.getText().toString();
                Date myStartDate = null;

                try {
                    myStartDate = sdf.parse(assessStart);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Long trigger = myStartDate.getTime();
                Intent intent = new Intent(AddEditAssessments.this, MyReceiver.class);
                intent.putExtra("key", editAssessmentTitle.getText().toString() + " starts today.");

                PendingIntent sender = PendingIntent.getBroadcast(AddEditAssessments.this,MainActivity.numAlert++,intent,0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;

            case R.id.notifyAssessmentEnd:
                String assessEnd = editAssessmentEnd.getText().toString();
                Date myEndDate = null;
                try {
                    myEndDate = sdf.parse(assessEnd);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Long endTrigger = myEndDate.getTime();
                Intent endIntent = new Intent(AddEditAssessments.this, MyReceiver.class);
                endIntent.putExtra("key", editAssessmentTitle.getText().toString() + " ends today.");

                PendingIntent endSender = PendingIntent.getBroadcast(AddEditAssessments.this,MainActivity.numAlert++,endIntent,0);
                AlarmManager endAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                endAlarmManager.set(AlarmManager.RTC_WAKEUP, endTrigger, endSender);
                return true;
            case R.id.deleteAssessment:
                for (Assessment a : repository.getAllAssessments()) {
                    if (a.getAssessmentId() == assessmentId) currentAssessment = a;
                }
                repository.delete(currentAssessment);
                Toast.makeText(AddEditAssessments.this, currentAssessment.getAssessmentTitle() + " was deleted", Toast.LENGTH_LONG).show();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }
}