package com.example.myapplication.restApi;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Entity.Student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APIRequestDao {
    String baseUrl = "http://192.168.20.135:3000/student";

    public interface ApiCallback {
        void onSuccess(List<Student> students);
        void onError(String errorMessage);
    }

    // Method to fetch the list of students asynchronously
    public void getList(Context con, final ApiCallback callback) {
        List<Student> studentList = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(con);

        // StringRequest to make the API request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // Parse the JSON response and populate the student list
                            JSONArray ja = new JSONArray(response);
                            for (int i = 0; i < ja.length(); i++) {
                                JSONObject jb = ja.getJSONObject(i);
                                Student student = new Student(
                                        jb.getString("id"),
                                        jb.getString("name"),
                                        jb.getString("username"),
                                        jb.getString("password"));
                                studentList.add(student);
                            }

                            // Notify the callback with the list of students
                            callback.onSuccess(studentList);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            callback.onError("Failed to parse JSON");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Notify the callback with the error message
                callback.onError("Error: " + error.getLocalizedMessage());
            }
        });

        // Add the request to the Volley queue
        queue.add(stringRequest);
    }

    public void addStudent(Context con, Student std) {

        RequestQueue queue = Volley.newRequestQueue(con);

        JSONObject params = new JSONObject();
        try {
            params.put("name", std.getName());
            params.put("username", std.getUsername());
            params.put("password", std.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, baseUrl, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle success
                        Log.d("Response", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Log.e("Error", error.toString());
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");  // Ensure the correct content type
                return headers;
            }
        };

        queue.add(jsonObjectRequest);

    }


    public void updateStudent(Context con, Student std) {
        RequestQueue queue = Volley.newRequestQueue(con);

        JSONObject params = new JSONObject();
        try {
            params.put("id", std.getId());
            params.put("name", std.getName());
            params.put("username", std.getUsername());
            params.put("password", std.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, baseUrl + "/" + std.getId(), params,
                response -> {
                    Log.d("Response", response.toString());
                    Toast.makeText(con, "Student Updated", Toast.LENGTH_SHORT).show();
                },
                error -> {
                    Log.e("Error", error.toString());
                    Toast.makeText(con, "Error: " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        queue.add(jsonObjectRequest);
    }

    public void deleteStudent(Context con, String studentId) {
        RequestQueue queue = Volley.newRequestQueue(con);

        String url = baseUrl + "/" + studentId;

        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
                response -> {
                    Log.d("Response", response.toString());
                    Toast.makeText(con, "Student Deleted", Toast.LENGTH_SHORT).show();
                },
                error -> {
                    Log.e("Error", error.toString());
                    Toast.makeText(con, "Error: " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        queue.add(stringRequest);
    }



}