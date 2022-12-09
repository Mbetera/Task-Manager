package com.example.kennethndungu.lab2class;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button btnLoginLogin, btnCreateAccLogin;
    EditText txtUsernameLogin, txtPasswordLogin;
    String txtUsernameLoginHolder,txtPasswordLoginHolder;
    String finalResult;
    String HttpURL = "https://sancarepreparatoryschool.co.ke/AndroidTaskMng/UserLogin.php";
    Boolean CheckEditText ;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    public static final String UserName = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLoginLogin= (Button) findViewById(R.id.BtnLogin);
        btnCreateAccLogin =(Button) findViewById(R.id.BtnCreateAccLogin);
        txtUsernameLogin= (EditText) findViewById(R.id.txtUsernameLogin);
        txtPasswordLogin=(EditText) findViewById(R.id.txtPasswordLogin);


        btnLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckEditTextIsEmptyOrNot();
                if (CheckEditText){
                    UserLoginFunction(txtUsernameLoginHolder,txtPasswordLoginHolder );
                }else {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void CheckEditTextIsEmptyOrNot(){
        txtUsernameLoginHolder=txtUsernameLogin.getText().toString();
        txtPasswordLoginHolder=txtPasswordLogin.getText().toString();

        if (TextUtils.isEmpty(txtUsernameLoginHolder) || TextUtils.isEmpty(txtPasswordLoginHolder)){
            CheckEditText=false;

        }else {
            CheckEditText=true;

        }
    }

    public void UserLoginFunction(final String txtUsernameLogin, final String txtPasswordLogin){
        class UserLoginClass extends AsyncTask<String,Void,String>{

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog=progressDialog.show(MainActivity.this,"Just a few seconds..",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {
                super.onPostExecute(httpResponseMsg);
                progressDialog.dismiss();
                if (httpResponseMsg.equalsIgnoreCase("Login Successful")){
                    finish();
                    Intent intent=new Intent(MainActivity.this,DashboardActivity.class);
                    intent.putExtra(UserName,txtUsernameLogin);
                    startActivity(intent);

                }else {
                    Toast.makeText(MainActivity.this,httpResponseMsg,Toast.LENGTH_LONG).show();

                }
            }

            @Override
            protected String doInBackground(String... params) {
                hashMap.put("username",params[0]);
                hashMap.put("password",params[1]);
                finalResult = httpParse.postRequest(hashMap, HttpURL);
                return finalResult;
            }
        }
        UserLoginClass userLoginClass = new UserLoginClass();
        userLoginClass.execute(txtUsernameLogin,txtPasswordLogin);

    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }


    /*public void toastMsg(String msg) {

        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();

    }

    public void displayToastMsg(View v) {

        toastMsg("This might not work!!");

    }*/


}
