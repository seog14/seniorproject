package com.example.ppapav3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.ppapav3.dto.AppResponse;
import org.json.JSONException;

import org.json.JSONObject;

public class patientMainPage extends AppCompatActivity {

    private Button Ready;
    private Button LogOut;
    private static String url = "https://dd4n7veiy2.execute-api.us-east-1.amazonaws.com/";
    private static String url1 = "https://7gynejatnj.execute-api.us-east-1.amazonaws.com/";
    public void pollCallBack() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        AppResponse response1 = AppResponse.newInstance(AppResponse.class,  response);
                        if(response1.getStatus() == 1) {
                            Ready.setEnabled(true);
                            Intent intent = new Intent(patientMainPage.this, patientMainPage.class);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }


        });
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
//                (Request.Method.GET, url, new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        AppResponse response1 = AppResponse.newInstance(AppResponse.class,  response.toString());
//                        if(response1.getStatus() == 1) {
//                            Ready.setEnabled(true);
//                            Intent intent = new Intent(patientMainPage.this, patientMainPage.class);
//                        }
//                    }
//                }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // TODO: Handle error
//
//                    }
//                });
        RQueueSingleton.getInstance(getApplicationContext()).getRequestQueue().add(stringRequest);

//                Intent intent = new Intent(createGood.this, doctorMainPage.class);
//                startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_main_page);

        Ready = (Button)findViewById(R.id.btReady2);
        LogOut = (Button)findViewById(R.id.btLO2);
        Intent intent = getIntent();
        String str = intent.getStringExtra("Username");
        RQueueSingleton.getInstance(getApplicationContext()).setPatientMainPage(this);
        RQueueSingleton.getInstance(getApplicationContext()).startPollingThread();
        RQueueSingleton.getInstance(getApplicationContext()).setPage(1);
        Ready.setEnabled(false);
        /*if ( this is where you would do ure whole thing )
        {
            Ready.setEnabled(true);
        }*/

        Ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url1,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                AppResponse response1 = AppResponse.newInstance(AppResponse.class,  response);
                                System.out.println(response1.getStatus());
                                if(response1.getStatus() == 1) {

                                    Intent intent = new Intent(patientMainPage.this, patientReady.class);
                                    startActivity(intent);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
                RQueueSingleton.getInstance(getApplicationContext()).getRequestQueue().add(stringRequest1);


//                Intent intent3 = new Intent(patientMainPage.this, doctorMainPage.class);
//                intent3.putExtra("patientReady", signal);

//                startActivity(intent3);
            }
        });
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(patientMainPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}