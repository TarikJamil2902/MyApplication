package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TestActivity extends AppCompatActivity {

    Button buttonAdd, buttonclear;
    TextView text;
    ImageView imgview;

    Integer val = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test);

        buttonAdd = findViewById(R.id.btnAdd);
        buttonclear = findViewById(R.id.btnclear);

        text = findViewById(R.id.textView);

        imgview = findViewById(R.id.imageView);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val++;
                text.setText(""+val);
            }
        });

        buttonclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val = 0;
                text.setText(""+val);
            }
        });

        imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestActivity.this, "Please fill all field", Toast.LENGTH_LONG).show();
            }
        });
    }
}