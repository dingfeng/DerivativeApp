package com.derivative.derivativeapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


public class OrderActivity extends ActionBarActivity {
    TableLayout tableLayout;
    String[] orderItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        tableLayout = (TableLayout) findViewById(R.id.orderTable);
        for (int i = 0; i < 10; ++i)
        {
          View view = getLayoutInflater().inflate(R.layout.tablerow,null);
            TextView orderItem = (TextView)view.findViewById(R.id.column1);
            TextView orderData = (TextView)view.findViewById(R.id.column2);
            orderItem.setText("日期：");
            orderData.setText("2015年9月1号");
            tableLayout.addView(view);
        }
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
