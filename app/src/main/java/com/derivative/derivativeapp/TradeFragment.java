package com.derivative.derivativeapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by FD on 2015/10/18.
 */
public class TradeFragment extends Fragment {
    private RadioGroup radioGroup_rgud;
    private RadioButton radioButton_up;
    private RadioButton radioButton_down;
    private RadioGroup radioGroup_rgea;
    private RadioButton radioButton_e;
    private RadioButton radioButton_a;
    private  EditText editText_executePriceText;
    private  DatePicker datePicker;
    private  TextView textView_buyPrice;
    private  TextView textView_sellPrice;
    private Activity activity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.activity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trade_layout, container, false);
        radioGroup_rgud = (RadioGroup) rootView.findViewById(R.id.rgud);
        radioButton_up = (RadioButton) rootView.findViewById(R.id.up);
        radioButton_down = (RadioButton)rootView.findViewById(R.id.down);
        radioGroup_rgea = (RadioGroup)rootView.findViewById(R.id.rgea);
        radioButton_a = (RadioButton)rootView.findViewById(R.id.a);
        radioButton_e= (RadioButton)rootView.findViewById(R.id.e);
        editText_executePriceText = (EditText) rootView.findViewById(R.id.executePriceText);
        datePicker = (DatePicker) rootView.findViewById(R.id.datePicker);
        textView_buyPrice = (TextView) rootView.findViewById(R.id.buyPrice);
        textView_sellPrice= (TextView) rootView.findViewById(R.id.sellPrice);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }




}
