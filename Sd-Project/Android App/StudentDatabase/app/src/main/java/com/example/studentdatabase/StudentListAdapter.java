package com.example.studentdatabase;

import android.app.Activity;
import android.content.Context;
import android.media.tv.TvView;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class StudentListAdapter extends ArrayAdapter {

    private Activity context;
    private List<Student> studentList;

    public StudentListAdapter(Activity context, List<Student> studentList)
    {
        super(context,R.layout.list_layout,studentList);
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        TextView firstName = listViewItem.findViewById(R.id.firstName);
        TextView middleName = listViewItem.findViewById(R.id.middleName);
        TextView lastName = listViewItem.findViewById(R.id.lastName);
        TextView dob = listViewItem.findViewById(R.id.dob);
        TextView grNo = listViewItem.findViewById(R.id.grNo);
        TextView year = listViewItem.findViewById(R.id.academicYear);
        TextView rollNo = listViewItem.findViewById(R.id.rollNo);
        TextView email = listViewItem.findViewById(R.id.email);
        TextView mobileNumber = listViewItem.findViewById(R.id.mobileNumber);
        TextView caste = listViewItem.findViewById(R.id.caste);
        TextView skills = listViewItem.findViewById(R.id.skills);
        TextView branch = listViewItem.findViewById(R.id.branch);
        TextView shift = listViewItem.findViewById(R.id.shift);
        TextView marks10 = listViewItem.findViewById(R.id.marks10);
        TextView marks12 = listViewItem.findViewById(R.id.marks12);
        TextView feCgpa = listViewItem.findViewById(R.id.feCgpa);
        TextView seCgpa = listViewItem.findViewById(R.id.seCgpa);
        TextView teCgpa= listViewItem.findViewById(R.id.teCgpa);
        TextView beCgpa = listViewItem.findViewById(R.id.beCgpa);
        TextView fatherName = listViewItem.findViewById(R.id.fatherName);
        TextView fatherDesignation = listViewItem.findViewById(R.id.fatherDesignation);
        TextView fatherProfession = listViewItem.findViewById(R.id.fatherProfession);
        TextView fatherEmail = listViewItem.findViewById(R.id.fatherEmail);
        TextView fatherMobileNumber = listViewItem.findViewById(R.id.fatherMobileNumber);
        TextView motherName = listViewItem.findViewById(R.id.motherName);
        TextView motherDesignation = listViewItem.findViewById(R.id.motherDesignation);
        TextView motherProfession = listViewItem.findViewById(R.id.motherProfession);
        TextView motherEmail = listViewItem.findViewById(R.id.motherEmail);
        TextView motherMobileNumber = listViewItem.findViewById(R.id.motherMobileNumber);
        TextView perAddress = listViewItem.findViewById(R.id.perAddress);
        TextView perArea = listViewItem.findViewById(R.id.perArea);
        TextView perCity = listViewItem.findViewById(R.id.perCity);
        TextView perPinCode = listViewItem.findViewById(R.id.perPinCode);
        TextView perState = listViewItem.findViewById(R.id.perState);
        TextView curAddress = listViewItem.findViewById(R.id.curAddress);
        TextView curArea = listViewItem.findViewById(R.id.curArea);
        TextView curCity = listViewItem.findViewById(R.id.curCity);
        TextView curPinCode = listViewItem.findViewById(R.id.curPinCode);
        TextView curState = listViewItem.findViewById(R.id.curState);


        Student student = studentList.get(position);

        firstName.setText(student.getPersonalInfo().get("firstName"));
        middleName.setText(student.getPersonalInfo().get("middleName"));
        lastName.setText(student.getPersonalInfo().get("lastName"));
        dob.setText(student.getPersonalInfo().get("birthDate"));
        grNo.setText(student.getPersonalInfo().get("grNo"));
        year.setText(student.getPersonalInfo().get("academicYear"));
        rollNo.setText(student.getPersonalInfo().get("rollNo"));
        email.setText(student.getPersonalInfo().get("email"));
        mobileNumber.setText(student.getPersonalInfo().get("mobile"));
        caste.setText(student.getPersonalInfo().get("caste"));
        skills.setText(student.getPersonalInfo().get("skills"));
        branch.setText(student.getPersonalInfo().get("branch"));
        shift.setText(student.getSkills());

        marks10.setText(student.getAcademicDetails().get("10th Marks"));
        marks12.setText(student.getAcademicDetails().get("12th Marks"));
        feCgpa.setText(student.getAcademicDetails().get("fycgpa"));
        seCgpa.setText(student.getAcademicDetails().get("sycgpa"));
        teCgpa.setText(student.getAcademicDetails().get("tycgpa"));
        beCgpa.setText(student.getAcademicDetails().get("becgpa"));

        fatherName.setText(student.getFather().get("name"));
        fatherDesignation.setText(student.getFather().get("designation"));
        fatherProfession.setText(student.getFather().get("profession"));
        fatherEmail.setText(student.getFather().get("email"));
        fatherMobileNumber.setText(student.getFather().get("mobileNo"));

        motherName.setText(student.getMother().get("name"));
        motherDesignation.setText(student.getMother().get("designation"));
        motherProfession.setText(student.getMother().get("profession"));
        motherEmail.setText(student.getMother().get("email"));
        motherMobileNumber.setText(student.getMother().get("mobileNo"));

        perAddress.setText(student.getPermanentAddress().get("address"));
        perCity.setText(student.getPermanentAddress().get("city"));
        perState.setText(student.getPermanentAddress().get("state"));
        perPinCode.setText(student.getPermanentAddress().get("pinCode"));
        perArea.setText(student.getPermanentAddress().get("area"));

        curAddress.setText(student.getCurrentAddress().get("address"));
        curCity.setText(student.getCurrentAddress().get("city"));
        curState.setText(student.getCurrentAddress().get("state"));
        curPinCode.setText(student.getCurrentAddress().get("pinCode"));
        curArea.setText(student.getCurrentAddress().get("area"));




        return listViewItem;
    }
}