package com.derivative.derivativeapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;


public class OrderActivity extends ActionBarActivity {
    private  TableLayout tableLayout;
    private  String[] orderItems;
    private  ProgressDialog pdialog;
    private  Handler handler;
    private AlertDialog.Builder builder;
    private boolean success = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        tableLayout = (TableLayout) findViewById(R.id.orderTable);
        setOrderItems();
        String[] orderDataExample = getResources().getStringArray(R.array.testcase_example);
        updateOrder(orderDataExample);

    }

    private void setOrderItems()
    {
        orderItems = getResources().getStringArray(R.array.order_item_array);
        for (String item : orderItems)
        {
            View view = getLayoutInflater().inflate(R.layout.tablerow,null);
            TextView orderItem = (TextView)view.findViewById(R.id.column1);
            TextView orderData = (TextView)view.findViewById(R.id.column2);
            orderItem.setText(item);
            tableLayout.addView(view);
        }
    }

    private void updateOrder(String[] orderDatas)
    {
        int childCount = tableLayout.getChildCount();
        for ( int i = 0; i < childCount; ++i)
        {
            View childView = tableLayout.getChildAt(i);
            TextView orderDataText = (TextView)childView.findViewById(R.id.column2);
            orderDataText.setText(orderDatas[i]);
        }
    }

    public void button_click(View view)
    {
        int buttonId = view.getId();
        if (buttonId == R.id.confirmButton)
        {
            //确认
            showProgressBar();
            handler = new Handler(){
              @Override
            public void handleMessage(Message mess)
              {
                  removeProgressBar();
                  builder = new AlertDialog.Builder(OrderActivity.this);
                  builder.setTitle("提交结果").setIcon(R.drawable.hi_icon);
                  if (mess.what == MessageNum.TRADE_COMMIT_SUCCESS)
                  {
                      //提交成功
                      builder.setMessage("提交成功！");
                      success = true;
                  }
                  else if (mess.what == MessageNum.TRADE_COMMIT_FAILURE)
                  {
                      //提交失败
                     builder.setMessage("提交失败！");
                      success = false;
                  }
                  builder = setPositiveButton(builder);
                  builder.create().show();

              }
            };
        }
        else if (buttonId == R.id.cancelButton)
        {
            //取消
            toMainActivity();
        }
    }

    //从订单确认界面 跳转回主界面
    private void toMainActivity()
    {
        Intent intent = getIntent();
        intent.putExtra("result",success);
        this.setResult(0,intent);
        this.finish();
    }

    //设置确定按钮
    private AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder)
    {
        return builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //跳转回主界面
                //to MainActivity
                toMainActivity();
            }
        });
    }
    //显示进度条
    private  void showProgressBar()
    {
        pdialog = new ProgressDialog(this);
        //进度条为环状进度条
        pdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //进度条不显示进度
        pdialog.setIndeterminate(false);
        pdialog.show();
    }

    //去除进度条
    private void removeProgressBar()
    {
      pdialog.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_order, menu);
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


}
