package com.example.studentdatabase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.collect.Range;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;


public class UpdateStudent extends AppCompatActivity {

    FirebaseFirestore dataBase;
    EditText editTextUpdateGrNo,editTextFirstName, editTextGrNo,editTextYear, editTextRollNo, editTextDOB, editTextLastName, editTextEmail, editTextMobile,editTextMiddleName,editTextCaste,editTextSkills,
            editTextmarks10,editTextmarks12,editTextFeCgpa,editTextSeCgpa,editTextTeCgpa,editTextBeCgpa,
            editTextFatherName,editTextFatherDesignation,editTextFatherProfession,editTextFatherEmail,editTextFatherMobile,
            editTextMotherName,editTextMotherDesignation,editTextMotherProfession,editTextMotherEmail,editTextMotherMobile,
            editTextPermanentAddress,editTextPermanentState,editTextPermanentCity,editTextPermanentPinCode,editTextPermanentArea,
            editTextCurentAddress,editTextCurrentState,editTextCurrentCity,editTextCurrentPinCode,editTextCurrentArea;
    TextView textViewFirstName,textViewMiddleName,textViewLastName,textViewDOB,textViewYear,textViewGrNo,textViewRollNo,textViewEmail,textViewMobileNumber,
            textViewCaste,textViewSkills,textViewBranch,textViewShift,textViewMarks10,textViewMarks12,textViewFeCgpa,textViewSeCgpa,textViewTeCgpa,textViewBeCgpa,
            textViewFatherName,textViewFatherDesignation,textViewFatherProfession,textViewFatherEmail,textViewFatherMobileNumber,textViewMotherName,textViewMotherDesignation,textViewMotherProfession,textViewMotherEmail,textViewMotherMobileNumber,
            textViewPerAddress,textViewPerArea,textViewPerCity,textViewPerState,textViewPerPinCode,textViewCurAdddress,textViewCurArea,textViewCurCity,textViewCurState,textViewCurPincode,
            tetxViewPermanent,textViewPersonaldetails,textViewAcademicDetails,textViewFather,textViewMother,textViewCurrent,textViewFamilyDetails;

    String value;
    Button searchButton,updateButton;
    Spinner spinnerBranch,spinnerShift;
    AwesomeValidation awesomeValidation;
    HashMap <String,String> personalDetails,academicDetails,currentAddress,father,mother,permanentAddress;
    String firstName, middleName , lastName, dob, grNo, rollNo, email, caste , skills , mobileNumber , marks10 , marks12 , feCgpa , seCgpa , teCgpa ,beCgpa,interships,branch,shift,
            fatherName,motherName,fatherDesignation,motherDesignation,fatherProfession,motherProfession,fatherEmail,motherEmail,fatherMobile,motherMobile,
            perAddress,curAddress,perState,curState,perArea,curArea,perCity,curCity,perPinCode,curPinCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_student);

        FirebaseApp.initializeApp(this);

        dataBase = FirebaseFirestore.getInstance();
        initialize();

        searchButton = findViewById(R.id.searchButtonUpdate);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editTextUpdateGrNo.getText().toString().equals(""))
                {
                    value = editTextUpdateGrNo.getText().toString().trim().toUpperCase();
                    search(value);
                }
                else
                {
                    Toast.makeText(UpdateStudent.this, "Enter a valid Gr Number", Toast.LENGTH_LONG).show();
                }
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();
            }
        });

        checkValidation();


    }

    public void search(String value)
    {
        if(!value.equals(""))
        {
            final DocumentReference documentReference = dataBase.collection("students").document(value);
            Log.d("grNumber", "searchStudent: " + value + " " + documentReference.get());
            documentReference.get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()){
                                DocumentSnapshot document = task.getResult();
                                Student student =  document.toObject(Student.class);
                                setData(student);
                                Log.d("student", "onComplete: " + student.getPersonalInfo().get("grNo") + student.getPersonalInfo().get("firstName"));
                            }
                            else
                            {
                                Toast.makeText(UpdateStudent.this, "INVALID GR NUMBER", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

            editTextFirstName.setVisibility(View.VISIBLE);
            textViewFirstName.setVisibility(View.VISIBLE);
            editTextMiddleName.setVisibility(View.VISIBLE);
            textViewMiddleName.setVisibility(View.VISIBLE);
            editTextLastName.setVisibility(View.VISIBLE);
            textViewLastName.setVisibility(View.VISIBLE);
            editTextGrNo.setVisibility(View.VISIBLE);
            textViewGrNo.setVisibility(View.VISIBLE);
            editTextDOB.setVisibility(View.VISIBLE);
            textViewDOB.setVisibility(View.VISIBLE);
            editTextEmail.setVisibility(View.VISIBLE);
            textViewEmail.setVisibility(View.VISIBLE);
            editTextRollNo.setVisibility(View.VISIBLE);
            textViewRollNo.setVisibility(View.VISIBLE);
            editTextYear.setVisibility(View.VISIBLE);
            textViewYear.setVisibility(View.VISIBLE);
            editTextCaste.setVisibility(View.VISIBLE);
            textViewCaste.setVisibility(View.VISIBLE);
            spinnerBranch.setVisibility(View.VISIBLE);
            textViewBranch.setVisibility(View.VISIBLE);
            spinnerShift.setVisibility(View.VISIBLE);
            textViewShift.setVisibility(View.VISIBLE);
            editTextMobile.setVisibility(View.VISIBLE);
            textViewMobileNumber.setVisibility(View.VISIBLE);
            editTextmarks10.setVisibility(View.VISIBLE);
            textViewMarks10.setVisibility(View.VISIBLE);
            editTextmarks12.setVisibility(View.VISIBLE);
            textViewMarks12.setVisibility(View.VISIBLE);
            editTextFeCgpa.setVisibility(View.VISIBLE);
            textViewFeCgpa.setVisibility(View.VISIBLE);
            editTextSeCgpa.setVisibility(View.VISIBLE);
            textViewSeCgpa.setVisibility(View.VISIBLE);
            editTextTeCgpa.setVisibility(View.VISIBLE);
            textViewTeCgpa.setVisibility(View.VISIBLE);
            editTextBeCgpa.setVisibility(View.VISIBLE);
            textViewBeCgpa.setVisibility(View.VISIBLE);
            editTextFatherName.setVisibility(View.VISIBLE);
            textViewFatherName.setVisibility(View.VISIBLE);
            editTextFatherProfession.setVisibility(View.VISIBLE);
            textViewFatherProfession.setVisibility(View.VISIBLE);
            editTextFatherDesignation.setVisibility(View.VISIBLE);
            textViewFatherDesignation.setVisibility(View.VISIBLE);
            editTextFatherEmail.setVisibility(View.VISIBLE);
            textViewFatherEmail.setVisibility(View.VISIBLE);
            editTextFatherMobile.setVisibility(View.VISIBLE);
            textViewFatherMobileNumber.setVisibility(View.VISIBLE);
            editTextMotherName.setVisibility(View.VISIBLE);
            textViewMotherName.setVisibility(View.VISIBLE);
            editTextMotherDesignation.setVisibility(View.VISIBLE);
            textViewMotherDesignation.setVisibility(View.VISIBLE);
            editTextMotherEmail.setVisibility(View.VISIBLE);
            textViewMotherEmail.setVisibility(View.VISIBLE);
            editTextMotherProfession.setVisibility(View.VISIBLE);
            textViewMotherProfession.setVisibility(View.VISIBLE);
            editTextMotherMobile.setVisibility(View.VISIBLE);
            textViewMotherMobileNumber.setVisibility(View.VISIBLE);
            editTextPermanentAddress.setVisibility(View.VISIBLE);
            textViewPerAddress.setVisibility(View.VISIBLE);
            editTextPermanentArea.setVisibility(View.VISIBLE);
            textViewPerArea.setVisibility(View.VISIBLE);
            editTextPermanentCity.setVisibility(View.VISIBLE);
            textViewPerCity.setVisibility(View.VISIBLE);
            editTextPermanentPinCode.setVisibility(View.VISIBLE);
            textViewPerPinCode.setVisibility(View.VISIBLE);
            editTextPermanentState.setVisibility(View.VISIBLE);
            textViewPerState.setVisibility(View.VISIBLE);
            editTextCurentAddress.setVisibility(View.VISIBLE);
            textViewCurAdddress.setVisibility(View.VISIBLE);
            editTextCurrentArea.setVisibility(View.VISIBLE);
            textViewCurArea.setVisibility(View.VISIBLE);
            editTextCurrentCity.setVisibility(View.VISIBLE);
            textViewCurCity.setVisibility(View.VISIBLE);
            editTextCurrentState.setVisibility(View.VISIBLE);
            textViewCurState.setVisibility(View.VISIBLE);
            editTextCurrentPinCode.setVisibility(View.VISIBLE);
            textViewCurPincode.setVisibility(View.VISIBLE);
            updateButton.setVisibility(View.VISIBLE);

            textViewMother.setVisibility(View.VISIBLE);
            tetxViewPermanent.setVisibility(View.VISIBLE);
            textViewFather.setVisibility(View.VISIBLE);
            textViewPersonaldetails.setVisibility(View.VISIBLE);
            textViewAcademicDetails.setVisibility(View.VISIBLE);
            textViewPersonaldetails.setVisibility(View.VISIBLE);
            textViewFamilyDetails.setVisibility(View.VISIBLE);


        }

    }

    private void setData(Student student)
    {
        editTextFirstName.setText(student.getPersonalInfo().get("firstName"));
        editTextMiddleName.setText(student.getPersonalInfo().get("middleName"));
        editTextLastName.setText(student.getPersonalInfo().get("lastName"));
        editTextGrNo.setText(student.getPersonalInfo().get("grNo"));
        editTextDOB.setText(student.getPersonalInfo().get("birthDate"));
        editTextYear.setText(student.getPersonalInfo().get("academicYear"));
        editTextEmail.setText(student.getPersonalInfo().get("email"));
        editTextRollNo.setText(student.getPersonalInfo().get("rollNo"));
        editTextCaste.setText(student.getPersonalInfo().get("caste"));
        branch = (student.getPersonalInfo().get("branch"));
        shift = (student.getPersonalInfo().get("shift"));
        editTextMobile.setText(student.getPersonalInfo().get("mobile"));
        editTextmarks10.setText(student.getAcademicDetails().get("10th Marks"));
        editTextmarks12.setText(student.getAcademicDetails().get("12th Marks"));
        editTextFeCgpa.setText(student.getAcademicDetails().get("fycgpa"));
        editTextSeCgpa.setText(student.getAcademicDetails().get("sycgpa"));
        editTextBeCgpa.setText(student.getAcademicDetails().get("becgpa"));
        editTextTeCgpa.setText(student.getAcademicDetails().get("tycgpa"));
        editTextFatherName.setText(student.getFather().get("name"));
        editTextFatherDesignation.setText(student.getFather().get("designation"));
        editTextFatherProfession.setText(student.getFather().get("profession"));
        editTextFatherMobile.setText(student.getFather().get("mobileNo"));
        editTextFatherEmail.setText(student.getFather().get("email"));
        editTextMotherName.setText(student.getFather().get("name"));
        editTextMotherDesignation.setText(student.getMother().get("designation"));
        editTextMotherProfession.setText(student.getMother().get("profession"));
        editTextMotherMobile.setText(student.getMother().get("mobileNo"));
        editTextMotherEmail.setText(student.getMother().get("email"));
        editTextPermanentAddress.setText(student.getPermanentAddress().get("address"));
        editTextPermanentArea.setText(student.getPermanentAddress().get("area"));
        editTextPermanentCity.setText(student.getPermanentAddress().get("city"));
        editTextPermanentPinCode.setText(student.getPermanentAddress().get("pinCode"));
        editTextPermanentState.setText(student.getPermanentAddress().get("state"));
        editTextCurentAddress.setText(student.getCurrentAddress().get("address"));
        editTextCurrentArea.setText(student.getCurrentAddress().get("area"));
        editTextCurrentCity.setText(student.getCurrentAddress().get("city"));
        editTextCurrentPinCode.setText(student.getCurrentAddress().get("pinCode"));
        editTextCurrentState.setText(student.getCurrentAddress().get("state"));
    }

    private void addStudent() {
        firstName = editTextFirstName.getText().toString().trim().toUpperCase();
        middleName = editTextMiddleName.getText().toString().trim().toUpperCase();
        lastName = editTextLastName.getText().toString().trim().toUpperCase();
        dob = editTextDOB.getText().toString().trim().toUpperCase();
        grNo = editTextGrNo.getText().toString().trim().toUpperCase();
        rollNo = editTextRollNo.getText().toString().trim().toUpperCase();
        email = editTextEmail.getText().toString().trim();
        mobileNumber = editTextMobile.getText().toString().trim().toUpperCase();
        caste = editTextCaste.getText().toString().trim().toUpperCase();
        skills = editTextSkills.getText().toString().trim().toUpperCase();

        personalDetails.put("firstName",firstName );
        personalDetails.put("middleName",middleName );
        personalDetails.put("lastName",lastName );
        personalDetails.put("dob",dob );
        personalDetails.put("grNo",grNo );
        personalDetails.put("rollNo",rollNo );
        personalDetails.put("email",email );
        personalDetails.put("mobileNumber",mobileNumber );
        personalDetails.put("caste",caste );
        personalDetails.put("branch", branch);
        personalDetails.put("shift", shift);

        marks10 = editTextmarks10.getText().toString().trim();
        marks12 = editTextmarks12.getText().toString().trim();
        feCgpa = editTextFeCgpa.getText().toString().trim();
        seCgpa = editTextSeCgpa.getText().toString().trim();
        teCgpa = editTextTeCgpa.getText().toString().trim();
        beCgpa = editTextBeCgpa.getText().toString().trim();

        academicDetails.put("10th Marks",marks10 );
        academicDetails.put("12th Marks", marks12);
        academicDetails.put("fycgpa", feCgpa);
        academicDetails.put("sycgpa", seCgpa );
        academicDetails.put("tycgpa",teCgpa);
        academicDetails.put("becgpa",beCgpa );

        curAddress = editTextCurentAddress.getText().toString().trim();
        curArea = editTextCurrentArea.getText().toString().trim();
        curCity = editTextCurrentCity.getText().toString().trim();
        curPinCode = editTextCurrentPinCode.getText().toString().trim();
        curState = editTextCurrentState.getText().toString().trim();

        currentAddress.put("address", curAddress);
        currentAddress.put("area", curArea);
        currentAddress.put("city",curCity );
        currentAddress.put("state",curState);
        currentAddress.put("pinCode", curPinCode);

        perAddress = editTextPermanentAddress.getText().toString().trim();
        perArea = editTextPermanentArea.getText().toString().trim();
        perCity = editTextPermanentCity.getText().toString().trim();
        perPinCode = editTextPermanentPinCode.getText().toString().trim();
        perState = editTextPermanentState.getText().toString().trim();

        permanentAddress.put("address", perAddress);
        permanentAddress.put("area", perArea);
        permanentAddress.put("city",perCity );
        permanentAddress.put("state",perState);
        permanentAddress.put("pinCode", perPinCode);

        fatherName = editTextFatherName.getText().toString().trim();
        fatherEmail = editTextFatherEmail.getText().toString().trim();
        fatherDesignation = editTextFatherDesignation.getText().toString().trim();
        fatherProfession = editTextFatherProfession.getText().toString().trim();
        fatherMobile = editTextFatherMobile.getText().toString().trim();

        father.put("name", fatherName);
        father.put("email",fatherEmail );
        father.put("mobileNo", fatherMobile);
        father.put("profession",fatherProfession );
        father.put("designation",fatherDesignation );

        motherName = editTextMotherName.getText().toString().trim();
        motherEmail = editTextMotherEmail.getText().toString().trim();
        motherDesignation = editTextMotherDesignation.getText().toString().trim();
        motherProfession = editTextMotherProfession.getText().toString().trim();
        motherMobile = editTextMotherMobile.getText().toString().trim();

        mother.put("name", fatherName);
        mother.put("email",fatherEmail );
        mother.put("mobileNo", fatherMobile);
        mother.put("profession",fatherProfession );
        mother.put("designation",fatherDesignation );


        if (awesomeValidation.validate() && !branch.isEmpty() && !shift.isEmpty()) {

            Student student = new Student(personalDetails,academicDetails,currentAddress,father,mother,permanentAddress,interships,skills);

            dataBase.collection("students")
                    .document(grNo)
                    .set(student)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(UpdateStudent.this, "student added", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UpdateStudent.this, "student not added", Toast.LENGTH_SHORT).show();
                        }
                    });

        } else {
            Toast.makeText(UpdateStudent.this, "You must enter all fields ", Toast.LENGTH_LONG).show();
        }
    }

    private void initialize()
    {
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        spinnerBranch = findViewById(R.id.spinnerBranch);
        ArrayAdapter<CharSequence> adapterBranch = ArrayAdapter.createFromResource(this,R.array.Branch, R.layout.spinner_item);
        adapterBranch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBranch.setAdapter(adapterBranch);
        spinnerBranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                branch = spinnerBranch.getItemAtPosition(position).toString().trim().toUpperCase();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerShift = findViewById(R.id.spinnerShift);
        ArrayAdapter<CharSequence> adapterShift = ArrayAdapter.createFromResource(this,R.array.Shift, R.layout.spinner_item);
        adapterShift.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerShift.setAdapter(adapterShift);
        spinnerShift.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                shift = spinnerShift.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        personalDetails = new HashMap<>();
        academicDetails = new HashMap<>();
        currentAddress = new HashMap<>();
        father = new HashMap<>();
        mother = new HashMap<>();
        permanentAddress = new HashMap<>();

        textViewPersonaldetails = findViewById(R.id.textViewPersonalDetails);
        editTextFirstName = findViewById(R.id.editTextFirstName);
        textViewFirstName = findViewById(R.id.firstName);
        editTextMiddleName = findViewById(R.id.editTextMiddleName);
        textViewMiddleName = findViewById(R.id.middleName);
        editTextLastName = findViewById(R.id.editTextLastName);
        textViewLastName = findViewById(R.id.lastName);
        editTextDOB = findViewById(R.id.editTextDOB);
        textViewDOB = findViewById(R.id.dob);
        editTextYear = findViewById(R.id.editTextYear);
        textViewYear = findViewById(R.id.year);
        editTextGrNo = findViewById(R.id.editTextGrNo);
        textViewGrNo = findViewById(R.id.grNo);
        editTextRollNo = findViewById(R.id.editTextRollNo);
        textViewRollNo = findViewById(R.id.rollNo);
        editTextEmail = findViewById(R.id.editTextEmail);
        textViewEmail = findViewById(R.id.emailPrimary);
        editTextMobile = findViewById(R.id.editTextMobileNumber);
        textViewMobileNumber = findViewById(R.id.mobileNumber);
        editTextCaste = findViewById(R.id.editTextCaste);
        textViewCaste = findViewById(R.id.caste);
        editTextSkills = findViewById(R.id.editTextSkills);
        textViewSkills = findViewById(R.id.skills);
        textViewBranch = findViewById(R.id.branch);
        textViewShift = findViewById(R.id.shift);

        textViewAcademicDetails = findViewById(R.id.textViewAcademicDetails);
        editTextmarks10 = findViewById(R.id.editTextmarks10);
        textViewMarks10 = findViewById(R.id.marks10);
        editTextmarks12 = findViewById(R.id.editTextmarks12);
        textViewMarks12 = findViewById(R.id.marks12);
        editTextFeCgpa = findViewById(R.id.editTextFeCgpa);
        textViewFeCgpa = findViewById(R.id.feCgpa);
        editTextSeCgpa = findViewById(R.id.editTextmarksSeCgpa);
        textViewSeCgpa = findViewById(R.id.seCgpa);
        editTextTeCgpa = findViewById(R.id.editTextmarksTeCgpa);
        textViewTeCgpa = findViewById(R.id.teCgpa);
        editTextBeCgpa = findViewById(R.id.editTextmarksBeCgpa);
        textViewBeCgpa = findViewById(R.id.beCgpa);

        textViewFamilyDetails = findViewById(R.id.textViewFamilyDetails);
        textViewFather = findViewById(R.id.textViewFather);
        editTextFatherName = findViewById(R.id.editTextFatherName);
        textViewFatherName = findViewById(R.id.fatherName);
        editTextFatherProfession = findViewById(R.id.editTextProfession);
        textViewFatherDesignation = findViewById(R.id.designation);
        editTextFatherDesignation = findViewById(R.id.editTextDesignation);
        textViewFatherProfession = findViewById(R.id.profession);
        editTextFatherEmail = findViewById(R.id.editTextFatherEmail);
        textViewFatherEmail = findViewById(R.id.email);
        editTextFatherMobile = findViewById(R.id.editTextFatherMobileNumber);
        textViewFatherMobileNumber = findViewById(R.id.fatherMobileNumber);

        textViewMother = findViewById(R.id.textViewMother);
        editTextMotherName = findViewById(R.id.editTextMotherName);
        textViewMotherName = findViewById(R.id.motherName);
        editTextMotherDesignation = findViewById(R.id.editTextMotherDesignation);
        textViewMotherDesignation = findViewById(R.id.motherDesignation);
        editTextMotherProfession = findViewById(R.id.editTextMotherProfession);
        textViewMotherProfession = findViewById(R.id.motherProfession);
        editTextMotherEmail = findViewById(R.id.editTextMotherEmail);
        textViewMotherEmail = findViewById(R.id.motherEmail);
        editTextMotherMobile = findViewById(R.id.editTextMotherMobileNumber);
        textViewMotherMobileNumber = findViewById(R.id.motherMobileNumber);

        tetxViewPermanent = findViewById(R.id.textViewPermanentAddress);
        editTextPermanentAddress = findViewById(R.id.editTextPermanentAddress);
        textViewPerAddress = findViewById(R.id.permanentAddress);
        editTextPermanentArea = findViewById(R.id.editTextPermanentAddressArea);
        textViewPerArea = findViewById(R.id.permanentArea);
        editTextPermanentCity = findViewById(R.id.editTextPermanentAddressCity);
        textViewPerCity = findViewById(R.id.permanentCity);
        editTextPermanentPinCode = findViewById(R.id.editTextPermanentAddressPincode);
        textViewPerPinCode = findViewById(R.id.permanentPincode);
        editTextPermanentState = findViewById(R.id.editTextpermanentAddressState);
        textViewPerState = findViewById(R.id.permanentState);

        textViewCurrent = findViewById(R.id.textViewCurrent);
        editTextCurentAddress = findViewById(R.id.editTextCurrentAddress);
        textViewCurAdddress =findViewById(R.id.currentAddress);
        editTextCurrentArea = findViewById(R.id.editTextCurrentAddressArea);
        textViewCurArea = findViewById(R.id.currentArea);
        editTextCurrentCity = findViewById(R.id.editTextCurrentAddressCity);
        textViewCurCity =findViewById(R.id.currentCity);
        editTextCurrentPinCode = findViewById(R.id.editTextCurrentAddressPincode);
        textViewCurPincode = findViewById(R.id.currentPinCode);
        editTextCurrentState = findViewById(R.id.editTextCurrentAddressState);
        textViewCurState = findViewById(R.id.currentState);

        editTextUpdateGrNo = findViewById(R.id.editTextUpdateGrNo);
        updateButton = findViewById(R.id.updateButton);
        searchButton = findViewById(R.id.SearchButton);

    }

    private void checkValidation()
    {
        awesomeValidation.addValidation(this, R.id.editTextFirstName, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.firstNameError);
        awesomeValidation.addValidation(this, R.id.editTextMiddleName, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.middleNameError);
        awesomeValidation.addValidation(this, R.id.editTextLastName, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.lastNameError);
        awesomeValidation.addValidation(this, R.id.editTextDOB, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.dateError);
        awesomeValidation.addValidation(this, R.id.editTextEmail, Patterns.EMAIL_ADDRESS, R.string.emailError);
        awesomeValidation.addValidation(this, R.id.editTextMobileNumber, "^(\\+?\\d{1,4}[\\s-])?(?!0+\\s+,?$)\\d{10}\\s*,?$", R.string.mobileError);
//        awesomeValidation.addValidation(this, R.id.editTextRollNo, Range.closed(200000, 300000), R.string.rollError);
        awesomeValidation.addValidation(this, R.id.editTextCaste, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.casteError);

        awesomeValidation.addValidation(this,R.id.editTextmarks10 , Range.closed(35, 100),R.string.marks );
        awesomeValidation.addValidation(this,R.id.editTextmarks12 , Range.closed(35, 100),R.string.marks );
        awesomeValidation.addValidation(this,R.id.editTextFeCgpa , Range.closed(5, 10),R.string.cgpa );
        awesomeValidation.addValidation(this,R.id.editTextmarksSeCgpa , Range.closed(5, 10),R.string.cgpa );
        awesomeValidation.addValidation(this,R.id.editTextmarksTeCgpa , Range.closed(5, 10),R.string.cgpa );
        awesomeValidation.addValidation(this,R.id.editTextmarksBeCgpa , Range.closed(5, 10),R.string.cgpa );
        awesomeValidation.addValidation(this,R.id.editTextFatherName , "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$",R.string.nameError);
        awesomeValidation.addValidation(this,R.id.editTextMotherName , "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$",R.string.nameError);
        awesomeValidation.addValidation(this, R.id.editTextFatherEmail, Patterns.EMAIL_ADDRESS, R.string.emailError);
        awesomeValidation.addValidation(this, R.id.editTextMotherEmail, Patterns.EMAIL_ADDRESS, R.string.emailError);
        awesomeValidation.addValidation(this, R.id.editTextFatherMobileNumber, "^(\\+?\\d{1,4}[\\s-])?(?!0+\\s+,?$)\\d{10}\\s*,?$", R.string.mobileError);
        awesomeValidation.addValidation(this, R.id.editTextMotherMobileNumber, "^(\\+?\\d{1,4}[\\s-])?(?!0+\\s+,?$)\\d{10}\\s*,?$", R.string.mobileError);
    }
}


