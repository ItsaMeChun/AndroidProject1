package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn_Signup);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                openSignup();
            }
        });
        }
        public void openSignup(){
        Intent intent = new Intent(this, signup.class);
        startActivity(intent);
    }

    private void mappingView(){
        username = findViewById(R.id.txtGmail);
        password = findViewById(R.id.txtPassword);
    }

}