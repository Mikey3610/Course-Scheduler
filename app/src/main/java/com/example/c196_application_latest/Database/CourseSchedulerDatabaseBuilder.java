package com.example.c196_application_latest.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.c196_application_latest.DAO.AssessmentDAO;
import com.example.c196_application_latest.DAO.CourseDAO;
import com.example.c196_application_latest.DAO.TermDAO;
import com.example.c196_application_latest.Entity.Assessment;
import com.example.c196_application_latest.Entity.Course;
import com.example.c196_application_latest.Entity.Term;


@Database(entities={Assessment.class, Course.class, Term.class}, version=2, exportSchema = false)
public abstract class CourseSchedulerDatabaseBuilder extends RoomDatabase {
    public abstract TermDAO termDAO();
    public abstract CourseDAO courseDAO();
    public abstract AssessmentDAO assessmentDAO();

    private static volatile CourseSchedulerDatabaseBuilder INSTANCE;

    static CourseSchedulerDatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CourseSchedulerDatabaseBuilder.class) {
                if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CourseSchedulerDatabaseBuilder.class,
                        "CourseSchedulerDatabase").fallbackToDestructiveMigration().build();
            }
        }
    }
        return INSTANCE;
    }
}
