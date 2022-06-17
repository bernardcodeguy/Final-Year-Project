package com.app.dermcop.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.dermcop.R;

public class UVModel extends AppCompatActivity {
    Button btnSend;
    TextView displayResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uvindex);


        btnSend = findViewById(R.id.btnGetUV);
        displayResult = findViewById(R.id.uvDisplay);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayResult.setText("2.50");
            }
        });



    }
}