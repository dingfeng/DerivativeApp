package com.derivative.derivativeapp;


import android.os.Bundle;
import android.support.v4.app.*;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by FD on 2015/10/18.
 */
public class HuShen300Fragment extends Fragment {
    private TextView hushen300Text;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //异步加载数据
    private void loadContent() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragemnt_hushen300_layout, container, false);
        hushen300Text = (TextView) rootView.findViewById(R.id.hushen300);
        return rootView;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        loadContent();
    }



}
