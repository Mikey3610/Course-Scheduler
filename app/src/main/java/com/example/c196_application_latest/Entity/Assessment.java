package com.example.c196_application_latest.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessments")
public class Assessment {
    @PrimaryKey(autoGenerate = true)

    private int assessmentId;
    private int assessmentCourseId;
    private String assessmentType;
    private String assessmentStart;
    private String assessmentEnd;
    private String assessmentTitle;
    private String assessmentDescription;

    public Assessment(int assessmentId, int assessmentCourseId, String assessmentType, String assessmentStart, String assessmentEnd, String assessmentTitle, String assessmentDescription) {
        this.assessmentId = assessmentId;
        this.assessmentCourseId = assessmentCourseId;
        this.assessmentType = assessmentType;
        this.assessmentStart = assessmentStart;
        this.assessmentEnd = assessmentEnd;
        this.assessmentTitle = assessmentTitle;
    }

    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public int getAssessmentCourseId() {
        return assessmentCourseId;
    }

    public void setAssessmentCourseId(int assessmentCourseId) {
        this.assessmentCourseId = assessmentCourseId;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public String getAssessmentStart() {
        return assessmentStart;
    }

    public void setAssessmentStart(String assessmentStart) {
        this.assessmentStart = assessmentStart;
    }

    public String getAssessmentEnd() {
        return assessmentEnd;
    }

    public void setAssessmentEnd(String assessmentEnd) {
        this.assessmentEnd = assessmentEnd;
    }

    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        this.assessmentTitle = assessmentTitle;
    }

    public String getAssessmentDescription() {
        return assessmentDescription;
    }

    public void setAssessmentDescription(String assessmentDescription) {
        this.assessmentDescription = assessmentDescription;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "assessmentId=" + assessmentId +
                ", assessmentCourseId=" + assessmentCourseId +
                ", assessmentType='" + assessmentType + '\'' +
                ", assessmentStart='" + assessmentStart + '\'' +
                ", assessmentEnd='" + assessmentEnd + '\'' +
                ", assessmentTitle='" + assessmentTitle + '\'' +
                ", assessmentDescription='" + assessmentDescription + '\'' +
                '}';
    }
}
