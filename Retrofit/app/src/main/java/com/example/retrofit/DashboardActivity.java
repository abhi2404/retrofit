package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        textView=findViewById(R.id.username);
        Intent intent = getIntent();

        if(intent.getExtras() != null){
            String usr =intent.getStringExtra("data");
            textView.setText("welcome"+usr+"!");
        }
    }
}