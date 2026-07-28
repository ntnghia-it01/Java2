/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpoly.java2.models;

/**
 *
 * @author trongnghia
 */
public class Student {
    private int id;
    private String studentCode;
    private String fullName;
    private String address;
    private String phone;
    private float labScore;
    private float quizScore;
    private float assignmentScore;
    private float finalExamScore;
    private float averageScore;
    private String status;

    public Student() {
    }

    public Student(int id, String studentCode, String fullName, String address, String phone, float labScore, float quizScore, float assignmentScore, float finalExamScore, float averageScore, String status) {
        this.id = id;
        this.studentCode = studentCode;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.labScore = labScore;
        this.quizScore = quizScore;
        this.assignmentScore = assignmentScore;
        this.finalExamScore = finalExamScore;
        this.averageScore = averageScore;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getLabScore() {
        return labScore;
    }

    public void setLabScore(float labScore) {
        this.labScore = labScore;
    }

    public float getQuizScore() {
        return quizScore;
    }

    public void setQuizScore(float quizScore) {
        this.quizScore = quizScore;
    }

    public float getAssignmentScore() {
        return assignmentScore;
    }

    public void setAssignmentScore(float assignmentScore) {
        this.assignmentScore = assignmentScore;
    }

    public float getFinalExamScore() {
        return finalExamScore;
    }

    public void setFinalExamScore(float finalExamScore) {
        this.finalExamScore = finalExamScore;
    }

    public float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
