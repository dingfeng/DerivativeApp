package com.derivative.derivativeapp;


import android.os.Bundle;
import android.support.v4.app.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by FD on 2015/10/18.
 */
public class TableFragment extends Fragment {
    public static final String RECORD_TYPE = "record";
    public static final String ITEM_ID = "option";
    private String record;
    private String option;
    private LayoutInflater inflater;
    private String[][] content;
    private String[] header;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ITEM_ID) && getArguments().containsKey(RECORD_TYPE)) {
            option = getArguments().getString(ITEM_ID);
            record = getArguments().getString(RECORD_TYPE);
        }
    }

    //异步加载数据
    private void loadContent() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trade_record, container, false);
        TableLayout table = (TableLayout) rootView.findViewById(R.id.orderTable);
        if(record.equals("交易记录"))
        {
          //交易记录的表头
          header = getResources().getStringArray(R.array.trade_table_header);
          View headerView = getTableHeader(header);
          table.addView(headerView);
        }
        else if (record.equals("持仓记录"))
        {
         //持仓记录的表头
            header = getResources().getStringArray(R.array.hold_table_header);
            View headerView = getTableHeader(header);
            table.addView(headerView);
        }
        return rootView;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        loadContent();
    }

    public View getTableHeader(String[] items) {

        TableRow row = (TableRow) inflater.inflate(R.layout.record_tablerow, null);
        for (String item : items) {
            TextView textView = (TextView) inflater.inflate(R.layout.header_textview, null);
            textView.setText(item);
            row.addView(textView);
        }
        return row;
    }

    public View getOddRow(String[] items) {
        TableRow row = (TableRow) inflater.inflate(R.layout.record_tablerow, null);
        for (String item : items) {
            TextView textView = (TextView) inflater.inflate(R.layout.odd_row_textview, null);
            textView.setText(item);
            row.addView(textView);
        }
        return row;
    }

    public View getEvenRow(String[] items) {
        TableRow row = (TableRow) inflater.inflate(R.layout.record_tablerow, null);
        for (String item : items) {
            TextView textView = (TextView) inflater.inflate(R.layout.even_row_textview, null);
            textView.setText(item);
            row.addView(textView);
        }
        return row;
    }


}
