package com.ndb345.midtest12;

public class Course {

    //variable declare
    private String coursename;
    private double coursefees;
    private int coursehours;

    //Generate Constructor
    public Course(String coursename, double coursefees, int coursehours) {
        this.coursename = coursename;
        this.coursefees = coursefees;
        this.coursehours = coursehours;
    }

    //Generate Getter and Setter
    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public double getCoursefees() {
        return coursefees;
    }

    public void setCoursefees(double coursefees) {
        this.coursefees = coursefees;
    }

    public int getCoursehours() {
        return coursehours;
    }

    public void setCoursehours(int coursehours) {
        this.coursehours = coursehours;
    }

    //Generate String

    @Override
    public String toString() {
        return "Course{" +
                "coursename='" + coursename + '\'' +
                ", coursefees=" + coursefees +
                ", coursehours=" + coursehours +
                '}';
    }
}
