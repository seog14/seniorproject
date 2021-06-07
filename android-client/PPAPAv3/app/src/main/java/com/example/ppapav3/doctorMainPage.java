package com.example.ppapav3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ppapav3.dto.AppResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class doctorMainPage extends AppCompatActivity {

    private Button Ready;
    private Button LogOut;
    private Button EditPatient;
    private Button EditPharm;
    private Button AddPresc;
    private static String url = "https://dxge68yxm6.execute-api.us-east-1.amazonaws.com/";
    private static String url1 = "https://ofgge26xui.execute-api.us-east-1.amazonaws.com/";


    public void pollCallBack() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        AppResponse response1 = AppResponse.newInstance(AppResponse.class, response);
                        if (response1.getStatus() == 1) {
                            Ready.setEnabled(true);
                            Intent intent = new Intent(doctorMainPage.this, doctorMainPage.class);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }


        });

        RQueueSingleton.getInstance(getApplicationContext()).getRequestQueue().add(stringRequest);

        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        AppResponse response1 = AppResponse.newInstance(AppResponse.class,  response);
                        if(response1.getStatus() == 1) {
                            Intent intent = new Intent(doctorMainPage.this, breakageDetected.class);
                            startActivity(intent);;
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }


        });

        RQueueSingleton.getInstance(getApplicationContext()).getRequestQueue().add(stringRequest1);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main_page);

        Ready = (Button)findViewById(R.id.btReady);
        LogOut = (Button)findViewById(R.id.btLO);
        EditPatient = (Button)findViewById(R.id.btEditPatient);
        EditPharm = (Button)findViewById(R.id.btEditPharm);
        AddPresc = (Button)findViewById(R.id.btAddPresc);
        RQueueSingleton.getInstance(getApplicationContext()).setDoctorMainPage(this);
        RQueueSingleton.getInstance(getApplicationContext()).startPollingThread();
        RQueueSingleton.getInstance(getApplicationContext()).setPage(0);
        Intent intent3 = getIntent();
        String Signal = intent3.getStringExtra("patientReady");
        Ready.setEnabled(false);

        
        Ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(doctorMainPage.this, doctorReady.class);
                startActivity(intent);
            }
        });

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(doctorMainPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
        EditPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(doctorMainPage.this, doctorEdit.class);
                startActivity(intent);
            }
        });
        EditPharm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(doctorMainPage.this, editPharm.class);
                startActivity(intent);
            }
        });
        AddPresc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(doctorMainPage.this, addPrescr.class);
                startActivity(intent);
            }
        });

        
        Intent intent = getIntent();
        String str = intent.getStringExtra("Username");
    }

}