package com.example.c196_application_latest.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.c196_application_latest.Database.Repository;
import com.example.c196_application_latest.Entity.Assessment;
import com.example.c196_application_latest.Entity.Course;
import com.example.c196_application_latest.Entity.Term;
import com.example.c196_application_latest.R;

public class MainActivity extends AppCompatActivity {
    //TODO 4/2 Created for the "AddEditAssessments" class notificationManager for incrementing messages
    public static int numAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enterHere(View view) {
        Intent intent=new Intent(MainActivity.this, AllTerms.class);
        startActivity(intent);
        Repository repo = new Repository(getApplication());

        Term term = new Term(1, "Sample Data - Term 1", "01/01/23", "06/30/23");
        repo.insert(term);

        Course course = new Course(1, 1, "C196", "01/01/23", "06/30/23", "In-Progress", "Bill Jing", "123-456-7890", "bill.jing@wgu.edu", "Mobile Application Course");
        repo.insert(course);

        Assessment assessment = new Assessment(1, 1, "Basic Quiz", "04/25/23", "04/25/23", "C196 Quiz", "Normal quiz for C196");
        repo.insert(assessment);



    }
}