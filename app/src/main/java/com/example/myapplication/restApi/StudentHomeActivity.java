package com.example.myapplication.restApi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Entity.Student;
import com.example.myapplication.R;
import com.example.myapplication.adapters.StudentListAdapter;

import java.util.List;

public class StudentHomeActivity extends AppCompatActivity {

    Button getBtn, addBtn;
    TextView tv;
    ListView listView;
    StudentListAdapter adapter;
    List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_home);


        getBtn = findViewById(R.id.getAll);
        addBtn = findViewById(R.id.addStudent);

//        tv = findViewById(R.id.textViewGetAll);
//        APIRequestDao apiRequest = new APIRequestDao();


        getBtn = findViewById(R.id.getAll);
        addBtn = findViewById(R.id.addStudent);
        listView = findViewById(R.id.studentListView);

        APIRequestDao apiRequest = new APIRequestDao();

        apiRequest.getList(StudentHomeActivity.this, new APIRequestDao.ApiCallback() {
            @Override
            public void onSuccess(List<Student> students) {
                // Update the student list and notify the adapter
                studentList = students;
                adapter = new StudentListAdapter(StudentHomeActivity.this, studentList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(StudentHomeActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        addBtn.setOnClickListener(v -> {
            // Navigate to Add Student Activity
            startActivity(new Intent(StudentHomeActivity.this, addStudentActivity.class));
        });

        getBtn.setOnClickListener(v -> {
            // Fetch students again (if needed)
            apiRequest.getList(StudentHomeActivity.this, new APIRequestDao.ApiCallback() {
                @Override
                public void onSuccess(List<Student> students) {
                    studentList.clear();
                    studentList.addAll(students);
                    adapter.notifyDataSetChanged(); // Refresh the list
                }

                @Override
                public void onError(String errorMessage) {
                    Toast.makeText(StudentHomeActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        });
    }



//        apiRequest.getList(StudentHomeActivity.this, new APIRequestDao.ApiCallback() {
//            @Override
//            public void onSuccess(List<Student> students) {
//                // Handle the successful response and update the UI
//                StringBuilder studentDetails = new StringBuilder();
//                for (Student student : students) {
//                    studentDetails.append("Name: " + student.getName() + "\n");
//                    studentDetails.append("Username: " + student.getUsername() + "\n");
//                    studentDetails.append("ID: " + student.getId() + "\n\n");
//                }
//
//                // Set the details to the TextView
//                tv.setText(studentDetails.toString());
//
//                // Optionally log the student names for debugging
//                for (Student student : students) {
//                    Log.d("Student Info", student.getName());
//                }
//            }
//
//            @Override
//            public void onError(String errorMessage) {
//                // Handle the error (e.g., show a Toast)
//                Toast.makeText(StudentHomeActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
//            }
//        });



//        addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(StudentHomeActivity.this, StudentAddActivity.class));
//            }
//        });
//
//
//        getBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                apiRequest.getList(StudentHomeActivity.this, new APIRequestDao.ApiCallback() {
//                    @Override
//                    public void onSuccess(List<Student> students) {
//                        // Handle the successful response and update the UI
//                        StringBuilder studentDetails = new StringBuilder();
//                        for (Student student : students) {
//                            studentDetails.append("Name: " + student.getName() + "\n");
//                            studentDetails.append("Username: " + student.getUsername() + "\n");
//                            studentDetails.append("ID: " + student.getId() + "\n\n");
//                        }
//
//                        // Set the details to the TextView
//                        tv.setText(studentDetails.toString());
//
//                        // Optionally log the student names for debugging
//                        for (Student student : students) {
//                            Log.d("Student Info", student.getName());
//                        }
//                    }
//
//                    @Override
//                    public void onError(String errorMessage) {
//                        // Handle the error (e.g., show a Toast)
//                        Toast.makeText(StudentHomeActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//      });

}
