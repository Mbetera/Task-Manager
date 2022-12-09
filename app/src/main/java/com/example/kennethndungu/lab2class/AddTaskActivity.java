package com.example.kennethndungu.lab2class;


import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Calendar;
import java.util.HashMap;

import static com.example.kennethndungu.lab2class.R.*;

public class AddTaskActivity extends AppCompatActivity {
    EditText EtTitleAddtask, EtDescriptionAddtask,EtSubmissionAddtask;
    String EtTitleAddtaskHolder, EtDescriptionAddtaskHolder, TvStartdateAddtaskHolder,TvDuedateAddtaskHolder,EtSubmissionAddtaskHolder;
    Button BtnAddTask, selectdate, BtnDuedateAddtask;
    TextView TvStartdateAddtask, TvDuedateAddtask;
    String finalResult;
    String HttpURL = "https://sancarepreparatoryschool.co.ke/AndroidTaskMng/AddTask.php";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_add_task);

        //assignment of IDs to variables.
        EtTitleAddtask=(EditText)findViewById(id.EtTitleAddtask);
        EtDescriptionAddtask=(EditText)findViewById(id.EtDescriptionAddtask);
        EtSubmissionAddtask=(EditText)findViewById(id.EtSubmissionAddtask);
        BtnAddTask=(Button)findViewById(id.BtnAddTask);
        selectdate=(Button)findViewById(id.selectdate);
        TvStartdateAddtask=(TextView)findViewById(id.TvStartdateAddtask);
        BtnDuedateAddtask=(Button)findViewById(id.BtnDuedateAddtask);
        TvDuedateAddtask=(TextView)findViewById(id.TvDuedateAddtask);

        //Adding Click Listener on addtask button.
        BtnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();
                if (CheckEditText){
                    // If EditText is not empty and CheckEditText = True then this block will execute.
                    AddTaskFunction(EtTitleAddtaskHolder, EtDescriptionAddtaskHolder, TvStartdateAddtaskHolder,
                            TvDuedateAddtaskHolder, EtSubmissionAddtaskHolder);
                }else {
                    // If EditText is empty then this block will execute .
                    Toast.makeText(AddTaskActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Date picker on click listener for Start date.
        selectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
               DatePickerDialog datePickerDialog = new DatePickerDialog(AddTaskActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                TvStartdateAddtask.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        //Date picker on click listener for Due date.
        BtnDuedateAddtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddTaskActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                TvDuedateAddtask.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();

            }
        });
    }


    public void CheckEditTextIsEmptyOrNot(){
        EtTitleAddtaskHolder = EtTitleAddtask.getText().toString();
        EtDescriptionAddtaskHolder = EtDescriptionAddtask.getText().toString();
        TvStartdateAddtaskHolder = TvStartdateAddtask.getText().toString();
        TvDuedateAddtaskHolder=TvDuedateAddtask.getText().toString();
        EtSubmissionAddtaskHolder = EtSubmissionAddtask.getText().toString();

        if(TextUtils.isEmpty(EtTitleAddtaskHolder) || TextUtils.isEmpty(EtDescriptionAddtaskHolder) || TextUtils.isEmpty(TvStartdateAddtaskHolder)
                || TextUtils.isEmpty(TvDuedateAddtaskHolder) ||TextUtils.isEmpty(EtSubmissionAddtaskHolder))
        {
            CheckEditText = false;
        }
        else {
            CheckEditText = true ;
        }
    }

    public void AddTaskFunction(final String EtTitleAddtask, final String EtDescriptionAddtask, final String TvStartdateAddtask,
                                     final String TvDuedateAddtask, final String EtSubmissionAddtask) {

        class AddTaskFunctionClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(AddTaskActivity.this, "Just a few seconds..", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {
                super.onPostExecute(httpResponseMsg);
                progressDialog.dismiss();
                Toast.makeText(AddTaskActivity.this, httpResponseMsg.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                //names should be the same as POST method declared in AddTask.php
                hashMap.put("Title", params[0]);
                hashMap.put("Description", params[1]);
                hashMap.put("StartDate", params[2]);
                hashMap.put("DueDate", params[3]);
                hashMap.put("Submethod", params[4]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);
                return finalResult;
            }
        }

        AddTaskFunctionClass AddTaskFunctionClass = new AddTaskFunctionClass();
        AddTaskFunctionClass.execute(EtTitleAddtask,EtDescriptionAddtask,TvStartdateAddtask,TvDuedateAddtask,EtSubmissionAddtask);
    }
}
