package com.derivative.derivativeapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import bl.BlController;
import bl.BlService;
import data.User;

/**
 * Created by FD on 2015/10/21.
 */
class LoginTask extends AsyncTask<String,Integer, User>
{

    private Context context;
    private ProgressDialog pdialog;
    public LoginTask(Context context)
    {
        this.context = context;
    }

    //执行中
    @Override
    protected User doInBackground(String... params) {
        String account = params[0];
        String password = params[1];
        BlService blController = BlController.instance();
        User user = blController.login(account,password);
        return user;
    }

    //执行之后
    @Override
    protected  void onPostExecute(User user)
    {
        if (user != null)
        {
            //登录成功
            Toast.makeText(this.context,"登录成功!",Toast.LENGTH_SHORT);
            Intent intent = new Intent(this.context, MainActivity.class);
            this.context.startActivity(intent);
            ((Activity)this.context).finish();
        }
        else
        {
            //登录失败
            Toast.makeText(this.context,"登录失败！",Toast.LENGTH_SHORT);
        }
        pdialog.dismiss();
    }

    //执行之前
    @Override
    protected  void onPreExecute()
    {
        pdialog = new ProgressDialog(context);
        //进度条为环状进度条
        pdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //进度条不显示进度
        pdialog.setIndeterminate(false);
        pdialog.show();
    }

    //执行中更新
    @Override
    protected  void onProgressUpdate(Integer... value)
    {
        //do nothing
    }

}
