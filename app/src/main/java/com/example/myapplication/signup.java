package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.DBUtil.Database;
import com.example.myapplication.Entity.Users;

import java.util.Calendar;

public class signup extends AppCompatActivity {

    private EditText username, email, password, dob;
    private Button signup, login;
//    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.sUsername);
        email = findViewById(R.id.SEmail);
        password = findViewById(R.id.SPassword);
        dob = findViewById(R.id.SDob);

        signup = findViewById(R.id.SSignup);
        login = findViewById(R.id.SLogin);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("USER_ID")) {
            username.setText(intent.getStringExtra("USER_NAME"));
            email.setText(intent.getStringExtra("USER_EMAIL"));
            dob.setText(intent.getStringExtra("USER_DOB"));
        }

//      radioGroup.clearCheck();

        dob.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            // Open the DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(signup.this,
                    (view, selectedYear, selectedMonth, selectedDayOfMonth) -> {
                        // Set the selected date in the EditText
                        String selectedDate = selectedDayOfMonth + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        dob.setText(selectedDate);
                    }, year, month, dayOfMonth);
            datePickerDialog.show();
        });

//        radioGroup.setOnCheckedChangeListener(
//                new RadioGroup
//                        .OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(RadioGroup group,
//                                                 int checkedId) {
//
//                        // Get the selected Radio Button
//                        RadioButton
//                                radioButton
//                                = (RadioButton) group
//                                .findViewById(checkedId);
//                        Toast.makeText(getApplicationContext(),
//                                        radioButton.getText(),
//                                        Toast.LENGTH_SHORT)
//                                .show();
//                    }
//                });

        signup.setOnClickListener(v -> {
            String user = username.getText().toString();
            String mail = email.getText().toString();
            String pass = password.getText().toString();
            String dateOfbirth = dob.getText().toString();

//            String sub = "";
//
//            int selectedId = radioGroup.getCheckedRadioButtonId();
//            if (selectedId == -1) {
//                Toast.makeText(getApplicationContext(),
//                                "No answer has been selected",
//                                Toast.LENGTH_SHORT)
//                        .show();
//            } else {
//                RadioButton radioButton
//                        = (RadioButton) radioGroup
//                        .findViewById(selectedId);
//                sub = radioButton.getText().toString();
//            }

            if (user.isEmpty() || mail.isEmpty() || pass.isEmpty() || dateOfbirth.isEmpty()) {
                Toast.makeText(signup.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                Database db = new Database(getApplicationContext());
                Users user1 = new Users(user, mail, pass, dateOfbirth);
                if (intent.hasExtra("USER_ID")) {
                    int userId = intent.getIntExtra("USER_ID", -1);
                    user1.id = userId;
                    db.updateUser(user1);  // Assuming you have an update method in the Database class
                } else {
                    db.insertUser(user1);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


            Toast.makeText(signup.this, "Signup Successfull!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(signup.this, Login.class));
        });
        login.setOnClickListener(v -> {
            startActivity(new Intent(signup.this, Login.class));
        });
    }
}