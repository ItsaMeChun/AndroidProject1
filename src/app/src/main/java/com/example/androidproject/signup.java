package com.example.androidproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import Helper.helper;

import MD5.hashmd5;

public class signup extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private Button btn_DK;
    EditText Name, emailLogin, hashPassword, password_Re;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());
        mapping();
        handleButtonAll();
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

    public void mapping(){
        btn_DK = findViewById(R.id.btn_Reg);
        Name = findViewById(R.id.txtName);
        emailLogin = findViewById(R.id.txtGmail);
        hashPassword = findViewById(R.id.txtPassword);
        password_Re = findViewById(R.id.txtPasswordRe);
    }

    public void handleButtonAll(){

        btn_DK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateDK()) return;
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, helper.registerURL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String res = response.toString();

                        if(res.trim().toString().equals("already")){
                            Toast.makeText(getApplicationContext(),"Account is" + response,Toast.LENGTH_LONG).show();
                        }else{
                            openLoginActivity();
                            Toast.makeText(getApplicationContext(),"Create Account Success" + response,Toast.LENGTH_LONG).show();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap = new HashMap<String,String>();
                        //Name.getText().toString();
                        //hashmd5.md5hashing(emailLogin.getText().toString());
                        //hashmd5.md5hashing(hashPassword.getText().toString());
                        return hashMap;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }

    private boolean validateDK() {
        if (Name.getText().length() == 0 || hashPassword.getText().length() == 0 ||
                password_Re.getText().length() == 0
        ) {
            Toast.makeText(signup.this,
                    "Pass or Username is empty", Toast.LENGTH_LONG).show();
            return false;
        } else if (!hashPassword.getText().toString().equals(password_Re.getText().toString())) {
            Toast.makeText(signup.this,
                    "Pass not math", Toast.LENGTH_LONG).show();
            return false;

        } else if (Name.getText().length() < 6) {
            Toast.makeText(signup.this,
                    "Username > 6", Toast.LENGTH_LONG).show();
            return false;

        } else {
            return true;
        }
    }

    public  void openLoginActivity(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}