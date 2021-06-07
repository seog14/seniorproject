package com.example.ppapav3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.ppapav3.dto.AppResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class doctorReady extends AppCompatActivity {

    private WebView myWebView;
    private Button Back;
    private Button Reposition;
    private Button MoveCloser;
    private Button MoveFace;
    private Button MoveRight;
    private Button MoveLeft;
    private Button Dispense;
    private static String url = "https://ao9txgzw6f.execute-api.us-east-1.amazonaws.com/";
    private static String url1 = "https://c18oa141xa.execute-api.us-east-1.amazonaws.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_ready);

        Back = (Button)findViewById(R.id.btBack200);
        Reposition = (Button)findViewById(R.id.btReposition);
        MoveCloser = (Button)findViewById(R.id.btMoveCloser);
        MoveFace = (Button)findViewById(R.id.btMoveFace);
        MoveRight = (Button)findViewById(R.id.btMoveRight);
        MoveLeft = (Button)findViewById(R.id.btMoveLeft);
        Dispense = (Button)findViewById(R.id.btDispense);
        myWebView = (WebView)findViewById(R.id.ankitugly);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("http://192.168.1.14:8000");

        Dispense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
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
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(doctorReady.this, doctorMainPage.class);
                startActivity(intent);
            }
        });
        Reposition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObj;
                try {
                    jsonObj = new JSONObject();
                    jsonObj.put("key", "1");
                } catch (JSONException e){
                    throw new RuntimeException(e);
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.PUT, url1, jsonObj, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
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
        MoveCloser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObj;
                try {
                    jsonObj = new JSONObject();
                    jsonObj.put("key", "2");
                } catch (JSONException e){
                    throw new RuntimeException(e);
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.PUT, url1, jsonObj, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
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
        MoveFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObj;
                try {
                    jsonObj = new JSONObject();
                    jsonObj.put("key", "3");
                } catch (JSONException e){
                    throw new RuntimeException(e);
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.PUT, url1, jsonObj, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
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
        MoveRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObj;
                try {
                    jsonObj = new JSONObject();
                    jsonObj.put("key", "4");
                } catch (JSONException e){
                    throw new RuntimeException(e);
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.PUT, url1, jsonObj, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
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
        MoveLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObj;
                try {
                    jsonObj = new JSONObject();
                    jsonObj.put("key", "5");
                } catch (JSONException e){
                    throw new RuntimeException(e);
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.PUT, url1, jsonObj, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
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

    }
}