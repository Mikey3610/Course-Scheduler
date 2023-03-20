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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enterHere(View view) {
        Intent intent=new Intent(MainActivity.this, AllTerms.class);
        startActivity(intent);
        Repository repo = new Repository(getApplication());

        Term term = new Term(1, "Term 1", "01/01/2023", "06/30/2023");
        repo.insert(term);

        Course course = new Course(1, 1, "C196", "01/01/2023", "06/30/2023", "In-Progress", "Bill Jing", "123-456-7890", "bill.jing@wgu.edu", "Mobile Application Course");
        repo.insert(course);

        Assessment assessment = new Assessment(1, 1, "Basic Quiz", "03/25/2023", "03/25/2023", "C196 Quiz", "Normal quiz for C196");
        repo.insert(assessment);



    }
}