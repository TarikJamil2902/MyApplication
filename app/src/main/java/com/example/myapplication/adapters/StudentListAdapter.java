package com.example.myapplication.adapters;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.DBUtil.Database;
import com.example.myapplication.Entity.Student;
import com.example.myapplication.R;


import com.example.myapplication.Entity.Users;
import com.example.myapplication.SignUp;
import com.example.myapplication.restApi.APIRequestDao;
import com.example.myapplication.restApi.addStudentActivity;

import java.util.ArrayList;
import java.util.List;

public class StudentListAdapter extends ArrayAdapter<Student>{
    public StudentListAdapter(@NonNull Context context, List<Student> dataArrayList) {
        super(context, R.layout.list_student, dataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        Student listData = getItem(position);
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_student, parent, false);
        }
        TextView listName = view.findViewById(R.id.listName);
        TextView listUsername = view.findViewById(R.id.listUsername);
        TextView listsId = view.findViewById(R.id.sId);

        TextView delete = view.findViewById(R.id.listDelete);
        TextView update = view.findViewById(R.id.listUpdate);

        listName.setText(listData != null ? listData.getName() : " ");
        listUsername.setText(listData != null ? listData.getUsername() : "");
        listsId.setText(listData != null ? listData.getId() : "");


//        Database db = new Database(view.getContext());
        View finalView = view;


        update.setOnClickListener(v -> {

            Toast.makeText(v.getContext(), "Update!!!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(v.getContext(), addStudentActivity.class);
            intent.putExtra("student", listData);
            v.getContext().startActivity(intent);
        });

        delete.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Delete!!!", Toast.LENGTH_SHORT).show();
            // Handle delete logic, e.g., calling your API to delete this student
            APIRequestDao apiRequest = new APIRequestDao();
            apiRequest.deleteStudent(v.getContext(), listData.getId());
            remove(listData);
            notifyDataSetChanged();
        });
        return view;
    }
}
