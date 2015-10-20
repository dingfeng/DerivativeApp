package com.derivative.derivativeapp;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by FD on 2015/10/18.
 */
public class TradeFragment extends Fragment implements View.OnClickListener{
    public final static String OPTION_NAME = "option_name";
    private String optionName;
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
    private Button searchButton;
    private Button tradeButton;
    private TableLayout tableLayout;
    private EditText obstacleLevel;
    private boolean totalValid;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.activity = getActivity();
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(OPTION_NAME))
        {
          optionName = bundle.getString(OPTION_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trade_layout, container, false);
        tableLayout = (TableLayout) rootView.findViewById(R.id.trade_table);
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
        searchButton = (Button) rootView.findViewById(R.id.searchButton);
        tradeButton = (Button) rootView.findViewById(R.id.tradeButton);
        searchButton.setOnClickListener(this);
        tradeButton.setOnClickListener(this);


        //输入规范化检查
        editText_executePriceText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                     {
                         //获得焦点
                         editText_executePriceText.setText("");
                         editText_executePriceText.setTextColor(Color.BLACK);
                     }
                else
                     {
                         //失去焦点
                         String text = ((EditText)v).getText().toString();
                         boolean valid = true;
                         try
                         {
                             Double.parseDouble(text);
                         }catch (Exception e)
                         {
                             valid = false;
                         }
                         totalValid |= valid;
                         if (!valid)
                         {
                             //输入不符合规范
                             editText_executePriceText.setText("请正确输入");
                             editText_executePriceText.setTextColor(Color.RED);
                         }
                     }
            }
        });
        if ( optionName != null&&optionName.startsWith("向"))
        {
            //障碍期权需要增加障碍水平填项
            TableRow tableRow = (TableRow) inflater.inflate(R.layout.obstacle_tablerow,null);
            obstacleLevel = (EditText) tableRow.findViewById(R.id.column2);
            tableLayout.addView(tableRow,3);
            obstacleLevel.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        //获得焦点
                        obstacleLevel.setText("");
                        obstacleLevel.setTextColor(Color.BLACK);
                    }
                    else
                    {
                        //失去焦点
                        boolean valid = true;
                        String text = ((TextView) v).getText().toString();
                        try {
                            Double.parseDouble(text);
                        } catch (Exception e) {
                            valid = false;
                        }
                        //整体是否符合规范 判断是否能够查询
                        totalValid |= valid;
                        if (!valid) {
                            //数字不符合规范;
                            obstacleLevel.setText("请正确输入");
                            obstacleLevel.setTextColor(Color.RED);
                        }
                    }
                }

            });
        }
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onClick(View v) {
       if (v.getId() == R.id.search_button)
       {
           //查询按钮触发事件
       }
       else if (v.getId() == R.id.tradeButton)
       {
           //交易按钮被触发事件
           Intent intent = new Intent(activity,OrderActivity.class);
           activity.startActivity(intent);
       }
    }


}
