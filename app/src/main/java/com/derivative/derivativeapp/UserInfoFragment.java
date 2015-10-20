package com.derivative.derivativeapp;


import android.app.ActionBar;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.*;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by FD on 2015/10/18.
 */
public class UserInfoFragment extends Fragment {
    private String[] userInfoItems;
    private String[] userInfos;
    private TableLayout tableLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userInfoItems = getResources().getStringArray(R.array.user_info_array);
        loadContent();
    }

    //异步加载数据
    private void loadContent() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_info,container,false);
        tableLayout = (TableLayout)rootView.findViewById(R.id.orderTable);
        if (userInfoItems != null)
        {

            for (int i = 0; i < userInfoItems.length; ++i)
            {
                TableRow tableRow = (TableRow)inflater.inflate(R.layout.tablerow,null);
                TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams();
                layoutParams.width  = TableRow.LayoutParams.MATCH_PARENT;
                tableRow.setLayoutParams(layoutParams);
                TextView itemText = (TextView)tableRow.findViewById(R.id.column1);
                TextView dataText = (TextView)tableRow.findViewById(R.id.column2);
                itemText.setText(userInfoItems[i]);
                dataText.setText(userInfoItems[i]);
                tableLayout.addView(tableRow);
            }
        }
        return rootView;
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }


}
