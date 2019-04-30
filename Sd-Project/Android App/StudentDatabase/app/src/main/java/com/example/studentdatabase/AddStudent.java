package com.example.studentdatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.common.collect.Range;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddStudent extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText editTextFirstName, editTextGrNo, editTextYear,editTextRollNo, editTextDOB, editSearchValue, editTextLastName, editTextEmail, editTextMobile,editTextMiddleName,editTextCaste,editTextSkills,
            editTextmarks10,editTextmarks12,editTextFeCgpa,editTextSeCgpa,editTextTeCgpa,editTextBeCgpa,
            editTextFatherName,editTextFatherDesignation,editTextFatherProfession,editTextFatherEmail,editTextFatherMobile,
            editTextMotherName,editTextMotherDesignation,editTextMotherProfession,editTextMotherEmail,editTextMotherMobile,
            editTextPermanentAddress,editTextPermanentState,editTextPermanentCity,editTextPermanentPinCode,editTextPermanentArea,
            editTextCurentAddress,editTextCurrentState,editTextCurrentCity,editTextCurrentPinCode,editTextCurrentArea;
    Button addButton, searchButton;
    Spinner spinnerBranch,spinnerShift;
    ListView listViewStudents;
    List<Student> studentList;
    HashMap <String,String> personalDetails,academicDetails,currentAddress,father,mother,permanentAddress;

    FirebaseFirestore dataBase;
    AwesomeValidation awesomeValidation;

    String firstName, middleName , lastName, dob, grNo,year, rollNo, email, caste , skills , mobileNumber , marks10 , marks12 , feCgpa , seCgpa , teCgpa ,beCgpa,interships,branch,shift,
            fatherName,motherName,fatherDesignation,motherDesignation,fatherProfession,motherProfession,fatherEmail,motherEmail,fatherMobile,motherMobile,
            perAddress,curAddress,perState,curState,perArea,curArea,perCity,curCity,perPinCode,curPinCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);

        FirebaseApp.initializeApp(this);

        dataBase = FirebaseFirestore.getInstance();

        initialize();
        checkValidation();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();
            }
        });


    }

    private void initialize()
    {
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        personalDetails = new HashMap<>();
        academicDetails = new HashMap<>();
        currentAddress = new HashMap<>();
        father = new HashMap<>();
        mother = new HashMap<>();
        permanentAddress = new HashMap<>();

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextMiddleName = findViewById(R.id.editTextMiddleName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextDOB = findViewById(R.id.editTextDOB);
        editTextGrNo = findViewById(R.id.editTextGrNo);
        editTextYear = findViewById(R.id.editTextYear);
        editTextRollNo = findViewById(R.id.editTextRollNo);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextMobile = findViewById(R.id.editTextMobileNumber);
        editTextCaste = findViewById(R.id.editTextCaste);
        editTextSkills = findViewById(R.id.editTextSkills);

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
        editTextmarks10 = findViewById(R.id.editTextmarks10);
        editTextmarks12 = findViewById(R.id.editTextmarks12);
        editTextFeCgpa = findViewById(R.id.editTextFeCgpa);
        editTextSeCgpa = findViewById(R.id.editTextmarksSeCgpa);
        editTextTeCgpa = findViewById(R.id.editTextmarksTeCgpa);
        editTextBeCgpa = findViewById(R.id.editTextmarksBeCgpa);

        spinnerBranch = findViewById(R.id.spinnerBranch);
        spinnerShift = findViewById(R.id.spinnerShift);

        editTextFatherName = findViewById(R.id.editTextFatherName);
        editTextFatherProfession = findViewById(R.id.editTextProfession);
        editTextFatherDesignation = findViewById(R.id.editTextDesignation);
        editTextFatherEmail = findViewById(R.id.editTextFatherEmail);
        editTextFatherMobile = findViewById(R.id.editTextFatherMobileNumber);

        editTextMotherName = findViewById(R.id.editTextMotherName);
        editTextMotherDesignation = findViewById(R.id.editTextMotherDesignation);
        editTextMotherProfession = findViewById(R.id.editTextMotherProfession);
        editTextMotherEmail = findViewById(R.id.editTextMotherEmail);
        editTextMotherMobile = findViewById(R.id.editTextMotherMobileNumber);

        editTextPermanentAddress = findViewById(R.id.editTextPermanentAddress);
        editTextPermanentArea = findViewById(R.id.editTextPermanentAddressArea);
        editTextPermanentCity = findViewById(R.id.editTextPermanentAddressCity);
        editTextPermanentPinCode = findViewById(R.id.editTextPermanentAddressPincode);
        editTextPermanentState = findViewById(R.id.editTextpermanentAddressState);

        editTextCurentAddress = findViewById(R.id.editTextCurrentAddress);
        editTextCurrentArea = findViewById(R.id.editTextCurrentAddressArea);
        editTextCurrentCity = findViewById(R.id.editTextCurrentAddressCity);
        editTextCurrentPinCode = findViewById(R.id.editTextCurrentAddressPincode);
        editTextCurrentState = findViewById(R.id.editTextCurrentAddressState);

        editSearchValue = findViewById(R.id.searchText);
        addButton = findViewById(R.id.buttonAddStudent);
        searchButton = findViewById(R.id.SearchButton);
        listViewStudents = findViewById(R.id.listViewStudents);
        studentList = new ArrayList<>();
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

        awesomeValidation.addValidation(this,R.id.editTextmarks10 , Range.closed(35.00, 100.00),R.string.marks );
        awesomeValidation.addValidation(this,R.id.editTextmarks12 , Range.closed(35.00, 100.00),R.string.marks );
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


    private void addStudent() {
        firstName = editTextFirstName.getText().toString().trim().toUpperCase();
        middleName = editTextMiddleName.getText().toString().trim().toUpperCase();
        lastName = editTextLastName.getText().toString().trim().toUpperCase();
        dob = editTextDOB.getText().toString().trim().toUpperCase();
        grNo = editTextGrNo.getText().toString().trim().toUpperCase();
        year = editTextYear.getText().toString().trim().toUpperCase();
        rollNo = editTextRollNo.getText().toString().trim().toUpperCase();
        email = editTextEmail.getText().toString().trim();
        mobileNumber = editTextMobile.getText().toString().trim().toUpperCase();
        caste = editTextCaste.getText().toString().trim().toUpperCase();
        skills = editTextSkills.getText().toString().trim().toUpperCase();

        personalDetails.put("firstName",firstName );
        personalDetails.put("middleName",middleName );
        personalDetails.put("lastName",lastName );
        personalDetails.put("birthDate",dob );
        personalDetails.put("grNo",grNo );
        personalDetails.put("academicYear", year);
        personalDetails.put("rollNo",rollNo );
        personalDetails.put("email",email );
        personalDetails.put("mobile",mobileNumber );
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

        mother.put("name", motherName);
        mother.put("email",motherEmail );
        mother.put("mobileNo", motherMobile);
        mother.put("profession",motherProfession );
        mother.put("designation",motherDesignation );


        if (awesomeValidation.validate() && !branch.isEmpty() && !shift.isEmpty()) {

            Student student = new Student(personalDetails,academicDetails,currentAddress,father,mother,permanentAddress,interships,skills);

            dataBase.collection("students")
                    .document(grNo)
                    .set(student)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(AddStudent.this, "student added", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddStudent.this, "student not added", Toast.LENGTH_SHORT).show();
                        }
                    });

        } else {
            Toast.makeText(AddStudent.this, "You must enter all fields ", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

