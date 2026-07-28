/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpoly.java2.models;

/**
 *
 * @author trongnghia
 */
public class Statistical {
    private int totalStudent;
    private int totalPass;
    private int totalFail;
    private float averageClass;
    private Student studentMaxScore;
    private Student studentMinScore;

    public Statistical() {
    }

    public Statistical(int totalStudent, int totalPass, int totalFail, float averageClass, Student studentMaxScore, Student studentMinScore) {
        this.totalStudent = totalStudent;
        this.totalPass = totalPass;
        this.totalFail = totalFail;
        this.averageClass = averageClass;
        this.studentMaxScore = studentMaxScore;
        this.studentMinScore = studentMinScore;
    }

    public int getTotalStudent() {
        return totalStudent;
    }

    public void setTotalStudent(int totalStudent) {
        this.totalStudent = totalStudent;
    }

    public int getTotalPass() {
        return totalPass;
    }

    public void setTotalPass(int totalPass) {
        this.totalPass = totalPass;
    }

    public int getTotalFail() {
        return totalFail;
    }

    public void setTotalFail(int totalFail) {
        this.totalFail = totalFail;
    }

    public float getAverageClass() {
        return averageClass;
    }

    public void setAverageClass(float averageClass) {
        this.averageClass = averageClass;
    }

    public Student getStudentMaxScore() {
        return studentMaxScore;
    }

    public void setStudentMaxScore(Student studentMaxScore) {
        this.studentMaxScore = studentMaxScore;
    }

    public Student getStudentMinScore() {
        return studentMinScore;
    }

    public void setStudentMinScore(Student studentMinScore) {
        this.studentMinScore = studentMinScore;
    }
    
    
}
