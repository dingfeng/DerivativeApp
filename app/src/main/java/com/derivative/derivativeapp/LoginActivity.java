package com.derivative.derivativeapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import bl.BlController;
import bl.BlService;
import data.User;


public class LoginActivity extends Activity  {
    private EditText accountView;
    private EditText passwordView;
    private String fileName = "userAccount.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accountView = (EditText)findViewById(R.id.textview_account);
        accountView.setText(getRecordedAccount());
        passwordView = (EditText) findViewById(R.id.textview_password);
        passwordView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //获得焦点时 密码框清空
                    passwordView.setText("");
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void button_click(View view)
    {
        BlService blController = BlController.instance();
       int viewId = view.getId();
        if (viewId == R.id.signin_button) {
            //登陆按钮受到触发
            String account = accountView.getText().toString();
            String password = passwordView.getText().toString();
            AsyncTask<String,Integer,User> task = new LoginTask(this);
            task.execute(account,password);
        }
        else if (viewId == R.id.register_button)
        {
           //注册按钮受到触发
           //暂时实现注册功能
        }
    }

    private void saveAccount(String account)
    {
       try
       {
           FileOutputStream fos = openFileOutput(fileName,MODE_PRIVATE);
           PrintStream ps = new PrintStream(fos);
           ps.println(account);
           ps.close();
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
    }

    private String getRecordedAccount()
    {
        String account = "";
        try
        {
            FileInputStream fis = openFileInput(fileName);
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String temp = null;
            while ((temp = br.readLine()) != null)
            {
                sb.append(temp);
            }
            fis.close();
            account = sb.toString();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return account;
    }
}
