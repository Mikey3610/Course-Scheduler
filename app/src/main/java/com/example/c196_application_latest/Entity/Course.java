package com.example.c196_application_latest.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "courses")
public class Course {
    @PrimaryKey(autoGenerate = true)

    private int courseId;
    private int termId;
    private String courseTitle;
    private String courseStart;
    private String courseEnd;
    private String courseStatus;
    private int instructorId;
    private String instructorName;
    private String instructorPhone;
    private String instructorEmail;
    private String courseNotes;

    public Course(int courseId, int termId, String courseTitle, String courseStart, String courseEnd, String courseStatus, int instructorId, String instructorName, String instructorPhone, String instructorEmail, String courseNotes) {
        this.courseId = courseId;
        this.termId = termId;
        this.courseTitle = courseTitle;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.courseStatus = courseStatus;
        this.instructorId = instructorId;
        this.instructorName = instructorName;
        this.instructorPhone = instructorPhone;
        this.instructorEmail = instructorEmail;
        this.courseNotes = courseNotes;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseStart() {
        return courseStart;
    }

    public void setCourseStart(String courseStart) {
        this.courseStart = courseStart;
    }

    public String getCourseEnd() {
        return courseEnd;
    }

    public void setCourseEnd(String courseEnd) {
        this.courseEnd = courseEnd;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorPhone() {
        return instructorPhone;
    }

    public void setInstructorPhone(String instructorPhone) {
        this.instructorPhone = instructorPhone;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public String getCourseNotes() {
        return courseNotes;
    }

    public void setCourseNotes(String courseNotes) {
        this.courseNotes = courseNotes;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", termId=" + termId +
                ", courseTitle='" + courseTitle + '\'' +
                ", courseStart='" + courseStart + '\'' +
                ", courseEnd='" + courseEnd + '\'' +
                ", courseStatus='" + courseStatus + '\'' +
                ", instructorId=" + instructorId +
                ", courseInstructorName='" + instructorName + '\'' +
                ", courseInstructorPhone='" + instructorPhone + '\'' +
                ", courseInstructorEmail='" + instructorEmail + '\'' +
                ", courseNotes='" + courseNotes + '\'' +
                '}';
    }
}
