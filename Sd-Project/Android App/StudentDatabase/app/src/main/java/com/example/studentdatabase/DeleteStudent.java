package com.example.studentdatabase;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class DeleteStudent extends AppCompatActivity {
    EditText editSearchValue;
    Button searchButton,deleteButton;
    ListView listViewStudents;
    List<Student> studentList;

    FirebaseFirestore dataBase;

    String value;

//    Boolean deleteStudent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_student);

        FirebaseApp.initializeApp(this);

        dataBase = FirebaseFirestore.getInstance();

        editSearchValue = findViewById(R.id.searchText);
        searchButton = findViewById(R.id.SearchButton);
        deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setVisibility(View.GONE);
        listViewStudents = findViewById(R.id.listViewStudents);
        studentList = new ArrayList<>();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editSearchValue.getText().toString().trim().equals("")){
                    value = editSearchValue.getText().toString().trim().toUpperCase();
                    searchStudent(value);
                    deleteButton.setVisibility(View.VISIBLE);

                }
                else {
                    Toast.makeText(DeleteStudent.this, "Enter a value to be searched", Toast.LENGTH_SHORT).show();
                }

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DeleteStudent.this);

                builder.setMessage("Delete Student Data from DataBase ?")
                        .setTitle("Delete Student Permanently");


                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteStudent(value);
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DeleteStudent.this, "Nahi karat delete", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });


    }

    public void searchStudent(String value)
    {
        if(value!=null)
        {
            final DocumentReference documentReference = dataBase.collection("students").document(value);
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

                                StudentListAdapter adapter = new StudentListAdapter(DeleteStudent.this,studentList );
                                listViewStudents.setAdapter(adapter);
                            }

                            if(studentList.isEmpty())
                                Toast.makeText(DeleteStudent.this, "No results found", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    public void deleteStudent(String value)
    {
            dataBase.collection("students").document(value)
                    .delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(DeleteStudent.this, "Student deleted", Toast.LENGTH_LONG).show();
                        }
                    });
    }
}
