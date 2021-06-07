package com.example.ppapav3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class editPharm extends AppCompatActivity {

    private Button Back;
    private Button CreateP;
    private EditText PharmUser;
    private EditText PharmPass;
    private static String url = "https://v1rcjxkgvc.execute-api.us-east-1.amazonaws.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pharm);

        Back = (Button)findViewById(R.id.btBack);
        CreateP = (Button) findViewById(R.id.btCreateP);
        PharmUser = (EditText)findViewById(R.id.etPharmNewUser);
        PharmPass = (EditText)findViewById(R.id.etPatientPassEdit);

        CreateP.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                JSONObject jsonObj;
                try {
                    jsonObj = new JSONObject();
                    jsonObj.put("userId", PharmUser.getText().toString());
                    jsonObj.put("password", PharmPass.getText().toString());
                    jsonObj.put("role", "PHARMACIST");
                } catch (JSONException e){
                    throw new RuntimeException(e);
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.PUT, url, jsonObj, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                Intent intent = new Intent(editPharm.this, createGood.class);
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
                Intent intent = new Intent(editPharm.this, doctorMainPage.class);
                startActivity(intent);
            }
        });


    }
}