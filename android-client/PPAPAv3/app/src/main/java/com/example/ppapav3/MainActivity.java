package com.example.ppapav3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button LoginD;
    private Button LoginP;
    private Button LoginPh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginD = (Button)findViewById(R.id.btDoctor);
        LoginP = (Button)findViewById(R.id.btPatient);
        LoginPh = (Button)findViewById(R.id.btPharm);

        LoginD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDoctor();
            }
        });
        LoginP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPatient();
            }
        });
        LoginPh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPharmacist();
            }
        });
        RQueueSingleton.getInstance(getApplicationContext()).getRequestQueue().start();
//        RQueueSingleton.getInstance(getApplicationContext()).setMainActivity(this);
//        RQueueSingleton.getInstance(getApplicationContext()).startPollingThread();
    }

    private void toDoctor()
    {
        Intent intent = new Intent(MainActivity.this, doctorLogin.class);
        startActivity(intent);
    }
    private void toPatient()
    {
        Intent intent = new Intent(MainActivity.this, patientLogin.class);
        startActivity(intent);
    }
    private void toPharmacist()
    {
        Intent intent = new Intent(MainActivity.this, pharmLogin.class);
        startActivity(intent);
    }

    public void pollCallBack() {
        System.out.println("Main Activity callback called");
        Intent intent = new Intent(MainActivity.this, doctorLogin.class);
        startActivity(intent);
//                Intent intent = new Intent(createGood.this, doctorMainPage.class);
//                startActivity(intent);

    }
}