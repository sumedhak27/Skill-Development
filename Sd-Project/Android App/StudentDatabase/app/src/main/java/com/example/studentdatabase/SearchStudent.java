package com.example.studentdatabase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SearchStudent extends AppCompatActivity {

    EditText editSearchValue;
    Button searchButton;
    ListView listViewStudents;
    List<Student> studentList;

    FirebaseFirestore dataBase;

    String field,value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_student);

        FirebaseApp.initializeApp(this);

        dataBase = FirebaseFirestore.getInstance();

        editSearchValue = findViewById(R.id.searchText);
        searchButton = findViewById(R.id.SearchButton);
        listViewStudents = findViewById(R.id.listViewStudents);
        studentList = new ArrayList<>();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editSearchValue.getText().toString().trim().equals("")){
                    value = editSearchValue.getText().toString().trim().toUpperCase();
                    searchStudent(value);
                }
                else {
                    Toast.makeText(SearchStudent.this, "Enter a value to be searched", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public void searchStudent(String value)
    {
        if(value!=null)
        {
            final DocumentReference documentReference = dataBase.collection("students").document(value);
            Log.d("gr", "searchStudent: " + value + " " + documentReference.get());
                    documentReference.get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                studentList.clear();
                                DocumentSnapshot document = task.getResult();
                                Student student =  document.toObject(Student.class);
//                                Log.d("student", "onComplete: " + student.getPersonalInfo().get("grNo") + student.getPersonalInfo().get("firstName"));
                                studentList.add(student);
                                if(studentList.isEmpty())
                                {
                                    Toast.makeText(SearchStudent.this, "No results found", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    StudentListAdapter adapter = new StudentListAdapter(SearchStudent.this,studentList );
                                    listViewStudents.setAdapter(adapter);
                                }
                            }
                        }
                    });
        }
    }
}
