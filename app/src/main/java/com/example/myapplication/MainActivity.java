package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.example.myapplication.DBUtil.Database;
import com.example.myapplication.Entity.Users;
import com.example.myapplication.adapters.UserAdapters;
import com.example.myapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    ArrayList<Users> dataArrayList = new ArrayList<>();
    UserAdapters listAdapter;
    Users listData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
//        binding.

//        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Database db = new Database(getApplicationContext());
        dataArrayList = (ArrayList<Users>) db.getAllUsers();

        listAdapter = new UserAdapters(MainActivity.this, dataArrayList);
        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);
//        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                Toast.makeText(MainActivity.this, dataArrayList.get(i).toString(), Toast.LENGTH_LONG).show();
//
////                Intent intent = new Intent(MainActivity.this, Detailed.class);
////                intent.putExtra("name", nameList[i]);
////                intent.putExtra("time", timeList[i]);
////                intent.putExtra("ingredients", ingredientList[i]);
////                intent.putExtra("desc", descList[i]);
////                intent.putExtra("image", imageList[i]);
////                startActivity(intent);
//            }
//        });
    }
}