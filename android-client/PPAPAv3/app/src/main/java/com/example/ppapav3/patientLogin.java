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

public class patientLogin extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private static String url ="https://9ss939r4xa.execute-api.us-east-1.amazonaws.com/";
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharm_login);

        Name = (EditText)findViewById(R.id.etPatient);
        Password = (EditText)findViewById(R.id.etPatientPass);
        Info = (TextView)findViewById(R.id.tvIncorrect);
        Login = (Button)findViewById(R.id.btnLogin);

        Info.setText("# of Attempts Remaining: 5");


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonObj;
                try {
                    jsonObj = new JSONObject();
                    jsonObj.put("user", Name.getText().toString());
                    jsonObj.put("pass", Password.getText().toString());
                    jsonObj.put("role", "PATIENT");
                } catch (JSONException e){
                    throw new RuntimeException(e);
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                String pUser = Name.getText().toString();
                                AppResponse response1 = AppResponse.newInstance(AppResponse.class,  response.toString());
                                if(response1.getStatus() == 1) {
                                    Intent intent = new Intent(patientLogin.this, patientMainPage.class);
                                    intent.putExtra("Username", pUser);
                                    startActivity(intent);
                                }
                                else {
                                    counter--;
                                    Info.setText("# of Attempts Remaining: " + String.valueOf(counter));

                                    if (counter == 0)
                                    {
                                        Login.setEnabled(false);
                                    }
                                }
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error

                            }
                        });
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
////                                JSONParser parser = new JSONParser();
////                                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
////                                JSONObject responseJson = new JSONObject();
//                                AppResponse response1 = AppResponse.newInstance(AppResponse.class,  response);
//                                System.out.println("response = " + response);
//
//
//                               // Intent intent = new Intent(pharmLogin.this, pharmMainPage.class);
//                             //   startActivity(intent);
//
//                                // Display the first 500 characters of the response string.
//                                //textView.setText("Response is: "+ response.substring(0,500));
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        System.out.println("ffdfdfdfdf error" );
//                        error.printStackTrace();
//                    }
//                });
                RQueueSingleton.getInstance(getApplicationContext()).getRequestQueue().add(jsonObjectRequest);

            }


        });
    }

//    private void validate()
//    {
//        if (status)
//        {
//            Intent intent = new Intent(pharmLogin.this, pharmMainPage.class);
//            startActivity(intent);
//        }
//        else
//        {
//            counter--;
//            Info.setText("# of Attempts Remaining: " + String.valueOf(counter));
//
//            if (counter == 0)
//            {
//                Login.setEnabled(false);
//            }
//        }
//    }


}