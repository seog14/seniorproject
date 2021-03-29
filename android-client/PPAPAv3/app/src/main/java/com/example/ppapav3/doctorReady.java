package com.example.ppapav3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class doctorReady extends AppCompatActivity {

    private Button Back;
    private Button Reposition;
    private Button MoveCloser;
    private Button MoveFace;
    private Button MoveRight;
    private Button MoveLeft;

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

            }
        });
        MoveCloser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        MoveFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        MoveRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        MoveLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}