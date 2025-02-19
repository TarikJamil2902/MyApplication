package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DBUtil.Database;

public class Login extends AppCompatActivity {

    private EditText username, password;
    private Button login;
    private TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.LUsername);
        password = findViewById(R.id.LPassword);

        login = findViewById(R.id.Login);
        signup= findViewById(R.id.Lsignup);

        login.setOnClickListener(v ->{
            String user = username.getText().toString();
            String pass = password.getText().toString();


            if(user.isEmpty() || pass.isEmpty()){
                Toast.makeText(Login.this,"Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                Database db = new Database(getApplicationContext());

                int val = db.loginUser(user, pass);

                if( val < 1){
                    Toast.makeText(Login.this,"Invalid Username or Password", Toast.LENGTH_LONG).show();
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            Toast.makeText(Login.this,"Login Successfull!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(Login.this, MainActivity.class));
        });

        signup.setOnClickListener(v -> {
            startActivity(new Intent(Login.this, SignUp.class));
        });
    }
}