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
import com.example.ppapav3.dto.AppResponse;

import org.json.JSONException;
import org.json.JSONObject;


public class addPrescr extends AppCompatActivity {

    private Button Back;
    private EditText NewPharm;
    private EditText Prescription;
    private Button CreatePresc;
    private static String url = "https://q6c92m1zv6.execute-api.us-east-1.amazonaws.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prescr2);

        Back = (Button)findViewById(R.id.btBack);
        CreatePresc = (Button)findViewById(R.id.btCreatePres);
        NewPharm = (EditText)findViewById(R.id.etPharmNewUser);
        Prescription = (EditText)findViewById(R.id.etPrescInfo);

        CreatePresc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObj;
                try {
                    jsonObj = new JSONObject();
                    jsonObj.put("key", NewPharm.getText().toString());
                    jsonObj.put("information", Prescription.getText().toString());
                } catch (JSONException e){
                    throw new RuntimeException(e);
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                AppResponse response1 = AppResponse.newInstance(AppResponse.class,  response.toString());
                                if(response1.getStatus() == 1){
                                    Intent intent = new Intent(addPrescr.this, createGoodPresc.class);
                                    startActivity(intent);
                                }
                                else{
                                    Intent intent = new Intent(addPrescr.this, createBadPresc.class);
                                    startActivity(intent);
                                }
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
                Intent intent = new Intent(addPrescr.this, doctorMainPage.class);
                startActivity(intent);
            }
        });
    }

}