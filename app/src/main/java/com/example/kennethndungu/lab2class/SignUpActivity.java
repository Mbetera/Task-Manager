package com.example.kennethndungu.lab2class;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    Button BtnCreateAccSignup, BtnLoginSignup;
    EditText EtFNameSignUp, EtLNameSignUp, EtEmailSignUp, EtUsernameSignUp, EtPasswordSignUp, EtPhonenumberSignUp;
    String EtFNameSignUpHolder, EtLNameSignUpHolder, EtEmailSignUpHolder, EtUsernameSignUpHolder, EtPasswordSignUpHolder, EtPhonenumberSignUpHolder;
    String finalResult;
    String HttpURL = "https://sancarepreparatoryschool.co.ke/AndroidTaskMng/UserRegistration.php";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Assignment of ids to variables
        BtnCreateAccSignup=(Button) findViewById(R.id.BtnCreateAccSignup);
        BtnLoginSignup=(Button)findViewById(R.id.BtnLoginSignup);
        EtFNameSignUp=(EditText)findViewById(R.id.EtFNameSignUp);
        EtLNameSignUp=(EditText)findViewById(R.id.EtLNameSignUp);
        EtEmailSignUp=(EditText)findViewById(R.id.EtEmailSignUp);
        EtUsernameSignUp=(EditText)findViewById(R.id.EtUsernameSignUp);
        EtPasswordSignUp=(EditText)findViewById(R.id.EtPasswordSignUp);
        EtPhonenumberSignUp=(EditText)findViewById(R.id.EtPhonenumberSignup);

        //Adding Click Listener on create account button.
        BtnCreateAccSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();
                if (CheckEditText){
                    // If EditText is not empty and CheckEditText = True then this block will execute.
                    UserRegisterFunction(EtFNameSignUpHolder,EtLNameSignUpHolder,EtEmailSignUpHolder,
                            EtUsernameSignUpHolder, EtPasswordSignUpHolder, EtPhonenumberSignUpHolder);
                }else{
                    // If EditText is empty then this block will execute .
                    Toast.makeText(SignUpActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void CheckEditTextIsEmptyOrNot(){
        EtFNameSignUpHolder = EtFNameSignUp.getText().toString();
        EtLNameSignUpHolder = EtLNameSignUp.getText().toString();
        EtEmailSignUpHolder = EtEmailSignUp.getText().toString();
        EtUsernameSignUpHolder=EtUsernameSignUp.getText().toString();
        EtPasswordSignUpHolder = EtPasswordSignUp.getText().toString();
        EtPhonenumberSignUpHolder=EtPhonenumberSignUp.getText().toString();

        if(TextUtils.isEmpty(EtFNameSignUpHolder) || TextUtils.isEmpty(EtLNameSignUpHolder) || TextUtils.isEmpty(EtEmailSignUpHolder)
                || TextUtils.isEmpty(EtUsernameSignUpHolder) ||TextUtils.isEmpty(EtPasswordSignUpHolder)
                || TextUtils.isEmpty(EtPhonenumberSignUpHolder) )
        {
            CheckEditText = false;
        }
        else {
            CheckEditText = true ;
        }
    }

    public void UserRegisterFunction(final String EtFNameSignUp, final String EtLNameSignUp, final String EtEmailSignUp,
                                     final String EtUsernameSignUp, final String EtPasswordSignUp, final String EtPhonenumberSignUp) {

        class UserRegisterFunctionClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(SignUpActivity.this, "Just a few seconds..", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {
                super.onPostExecute(httpResponseMsg);
                progressDialog.dismiss();
                Toast.makeText(SignUpActivity.this, httpResponseMsg.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                //names should be the same as POST method declared in UserRegistration.php
                hashMap.put("f_name", params[0]);
                hashMap.put("L_name", params[1]);
                hashMap.put("email", params[2]);
                hashMap.put("username", params[3]);
                hashMap.put("password", params[4]);
                hashMap.put("phonenumber", params[5]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);
                return finalResult;
            }
        }

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();
        userRegisterFunctionClass.execute(EtFNameSignUp,EtLNameSignUp,EtEmailSignUp,EtUsernameSignUp,EtPasswordSignUp,EtPhonenumberSignUp);
    }

    public void LoginView(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



}
