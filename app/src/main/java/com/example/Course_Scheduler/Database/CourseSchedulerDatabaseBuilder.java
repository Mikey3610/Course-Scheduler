package com.example.Course_Scheduler.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.Course_Scheduler.DAO.AssessmentDAO;
import com.example.Course_Scheduler.DAO.CourseDAO;
import com.example.Course_Scheduler.DAO.TermDAO;
import com.example.Course_Scheduler.Entity.Assessment;
import com.example.Course_Scheduler.Entity.Course;
import com.example.Course_Scheduler.Entity.Term;


@Database(entities={Assessment.class, Course.class, Term.class}, version=1, exportSchema = false)
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
