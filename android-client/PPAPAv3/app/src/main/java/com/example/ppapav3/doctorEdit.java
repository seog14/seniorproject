package com.example.ppapav3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class doctorEdit extends AppCompatActivity {

    private EditText PUser;
    private EditText PPass;
    private EditText Date1;
    private EditText Date2;
    private EditText Date3;
    private Button Back;
    private Button CreatePA;
    private Button CreateTime;
    private static String url = "\thttps://vsa8yau8le.execute-api.us-east-1.amazonaws.com/APICreateUser/APICreateUser";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_edit);

        CreatePA = (Button)findViewById(R.id.btCreatePA);
        Back = (Button)findViewById(R.id.btBack);
        PUser = (EditText)findViewById(R.id.etPharmNewUser);
        PPass = (EditText)findViewById(R.id.etPatientPassEdit);
        Date1 = (EditText)findViewById(R.id.etMonth);
        Date2 = (EditText)findViewById(R.id.etHour);
        Date3 = (EditText)findViewById(R.id.etMinute);
        CreateTime = (Button)findViewById(R.id.btCreateTime);

        CreateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(doctorEdit.this, createGoodTime.class);
                startActivity(intent);
            }
        });
        CreatePA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObj;
                try {
                    jsonObj = new JSONObject();
                    jsonObj.put("userId", PUser.getText().toString());
                    jsonObj.put("password", PPass.getText().toString());
                    jsonObj.put("role", "PATIENT");
                } catch (JSONException e){
                    throw new RuntimeException(e);
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.PUT, url, jsonObj, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                Intent intent = new Intent(doctorEdit.this, createGood2.class);
                                startActivity(intent);
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error

                            }
                        });
                RQueueSingleton.getInstance(getApplicationContext()).getRequestQueue().add(jsonObjectRequest);
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(doctorEdit.this, doctorMainPage.class);
                startActivity(intent);
            }
        });
    }
}