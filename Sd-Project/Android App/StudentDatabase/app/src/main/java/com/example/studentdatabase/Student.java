package com.example.studentdatabase;

import java.util.HashMap;

public class Student {
    String internships,skills;
    HashMap<String ,String> academicDetails,currentAddress,father,mother,permanentAddress,personalInfo ;
    public Student(){}

    public String getInternships() {
        return internships;
    }

    public void setInternships(String internships) {
        this.internships = internships;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public HashMap<String, String> getAcademicDetails() {
        return academicDetails;
    }

    public void setAcademicDetails(HashMap<String, String> academicDetails) {
        this.academicDetails = academicDetails;
    }

    public HashMap<String, String> getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(HashMap<String, String> currentAddress) {
        this.currentAddress = currentAddress;
    }

    public HashMap<String, String> getFather() {
        return father;
    }

    public void setFather(HashMap<String, String> father) {
        this.father = father;
    }

    public HashMap<String, String> getMother() {
        return mother;
    }

    public void setMother(HashMap<String, String> mother) {
        this.mother = mother;
    }

    public HashMap<String, String> getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(HashMap<String, String> permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public HashMap<String, String> getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(HashMap<String, String> personalInfo) {
        this.personalInfo = personalInfo;
    }

    public Student(HashMap personalInfo,HashMap academicDetails, HashMap currentAddress, HashMap father, HashMap mother, HashMap permanentAddress, String internships, String skills) {
        this.academicDetails = academicDetails;
        this.currentAddress = currentAddress;
        this.father = father;
        this.mother = mother;
        this.permanentAddress = permanentAddress;
        this.personalInfo = personalInfo;
        this.internships = internships;
        this.skills = skills;
    }


}
