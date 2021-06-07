package com.example.ppapav3;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.ppapav3.dto.AppResponse;
import com.example.ppapav3.dto.PharmacistInfo;


import org.json.JSONException;
import org.json.JSONObject;

public class pharmMainPage extends AppCompatActivity {

    private Button LogOut;
    private Button Refresh;
    private TextView Pharmer;
    private String info;
    private Button Unlock;
    private Button Lock;
    private static String url = "https://j6r40qwzv8.execute-api.us-east-1.amazonaws.com/";
    private static String url1 = "https://ncqpwo2usa.execute-api.us-east-1.amazonaws.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharm_main_page);

        Refresh = (Button)findViewById(R.id.btRefresh1);
        Pharmer = (TextView)findViewById(R.id.tvPharm);
        LogOut = (Button)findViewById(R.id.btLO3);
        Unlock = (Button)findViewById(R.id.btUnlock);

        Intent intent = getIntent();
        String str = intent.getStringExtra("Username");

        JSONObject jsonObj;
        try {
            jsonObj = new JSONObject();
            jsonObj.put("key", str);
        } catch (JSONException e){
            throw new RuntimeException(e);
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        PharmacistInfo pharmacistInfo = PharmacistInfo.newInstance(PharmacistInfo.class,  response.toString());
                        info = pharmacistInfo.getInformation();
                        Pharmer.setText(info);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                    }
                });

        RQueueSingleton.getInstance(getApplicationContext()).getRequestQueue().add(jsonObjectRequest);



        Unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url1,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }


                });

                RQueueSingleton.getInstance(getApplicationContext()).getRequestQueue().add(stringRequest);

            }
        });

        Refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pharmMainPage.this, pharmMainPage.class);
                intent.putExtra("Username", str);
                startActivity(intent);

            }
        });
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pharmMainPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}