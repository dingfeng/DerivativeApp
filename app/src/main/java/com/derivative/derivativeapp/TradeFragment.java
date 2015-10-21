package com.derivative.derivativeapp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import bl.TradeInfo;
import data.EorA;
import data.upORdown;

/**
 * Created by FD on 2015/10/18.
 */
public class TradeFragment extends Fragment implements View.OnClickListener{
    public final static String OPTION_NAME = "option_name";
    public final static  int TRADE_TIME_LIMIT = 30;
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
    private ScrollView scrollView;
    private boolean totalValid;
    private Handler count_down_handler;
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
        scrollView = (ScrollView)rootView.findViewById(R.id.trade_scroll_view);
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
        if ( optionName != null&&optionName.startsWith("向"))
        {
            //障碍期权需要增加障碍水平填项
            TableRow tableRow = (TableRow) inflater.inflate(R.layout.obstacle_tablerow,null);
            obstacleLevel = (EditText) tableRow.findViewById(R.id.column2);
            tableLayout.addView(tableRow,3);
        }
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onClick(View v) {
       if (v.getId() == R.id.searchButton)
       {
           //查询按钮触发事件
           testChoose();
           if (totalValid)
           {
             //测试通过开始查询
             // 滚动条滑倒最下面
               MainActivity activity = (MainActivity)getActivity();
               scrollView.fullScroll(View.FOCUS_DOWN);

               String optionName = activity.getRightMenuOption();
               upORdown upordown = upORdown.up;
               if (radioButton_up.isChecked())
               {
                   //选择看涨
                   upordown = upORdown.up;
               }
               else
               {
                   //选择看跌
                   upordown = upORdown.down;
               }

               EorA eora = EorA.E;
               if(radioButton_e.isChecked())
               {
                   //选择欧式
                   eora =  EorA.E;
               }
               else
               {
                   //选择美式
                   eora = EorA.A;
               }
               double executePrice = Double.parseDouble(editText_executePriceText.getText().toString());
               //原始为 年份和月份从零开始
               int year = datePicker.getYear() + 1;
               int month = datePicker.getMonth() + 1;
               int day = datePicker.getDayOfMonth();
               String yearStr = year < 10 ? "0" + String.valueOf(year) : String.valueOf(year);
               String monthStr = month < 10 ? "0" + String.valueOf(month) : String.valueOf(month);
               String dayStr = String.valueOf(day);
               String dateStr = yearStr+monthStr+dayStr;
               String formatStr = "yyyyMMdd";
               SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
               Date date = null;
               try {
                   date = dateFormat.parse(formatStr);
               } catch (ParseException e) {
                   e.printStackTrace();
               }
               TradeTask task = new TradeTask(activity,textView_buyPrice,textView_sellPrice);
               TradeInfo tradeInfo = null;
               if (optionName.startsWith("向"))
               {
                   //障碍期权
                   String obstacleStr = obstacleLevel.getText().toString();
                   double rate = Double.parseDouble(obstacleStr);
                   tradeInfo = new TradeInfo(optionName,eora,upordown,executePrice,rate,date);
               }
               else
               {
                   //非障碍期权
                   tradeInfo = new TradeInfo(optionName,eora, upordown, executePrice, date);
               }
               task.execute(tradeInfo);
           }

       }
       else if (v.getId() == R.id.tradeButton)
       {
           //交易按钮被触发事件
           Intent intent = new Intent(activity,OrderActivity.class);
           activity.startActivity(intent);
       }
    }

    private void testChoose()
    {
        testExecutePriceText();
        testObstacleLevel();
        if (obstacleLevel != null)
        {
            testObstacleLevel();
        }


    }
    private void testExecutePriceText()
    {
        String text = editText_executePriceText.getText().toString();
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
        }

    }

    private void testObstacleLevel()
    {
        //失去焦点
        boolean valid = true;
        String text = obstacleLevel.getText().toString();
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
        }
    }

    private void testDate()
    {
        boolean valid = true;
        int year = datePicker.getYear() + 1;
        int month = datePicker.getMonth() + 1;
        int day = datePicker.getDayOfMonth();
        Calendar date = Calendar.getInstance();
        int nowYear =  date.get(Calendar.YEAR);
        int nowMonth = date.get(Calendar.MONTH);
        int nowDay = date.get(Calendar.DAY_OF_MONTH);
        if (nowYear > year || nowMonth > month || nowDay > day ||
                (nowYear == year && nowMonth == month && nowDay == day))
        {
            //截至日期未大于当前
            valid = false;
            Toast.makeText(getActivity(), "日期必须为今天之后", Toast.LENGTH_SHORT);
        }
        totalValid |= valid;
    }


    //开始交易 倒计时
    private void beginTrade()
    {
        tradeButton.setEnabled(true);
      count_down_handler = new Handler() {
          @Override
          public void handleMessage(Message message)
          {
              if (message.what == MessageNum.TRADE_COUNT_DOWN)
              {
                  //收到倒计时的消息
                  int count = message.getData().getInt("count_down");
                  tradeButton.setText("交易("+count+")");
              }
          }
      };

        new Thread(){
            public void run()
            {
                Looper.prepare();
                int count = TRADE_TIME_LIMIT;
                for(; count > 0; --count)
                {
                   sendMessageWithCount(count);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                sendMessageWithCount(count);
                Looper.loop();
            }

            private void sendMessageWithCount(int count)
            {
                Message mess = new Message();
                mess.what = MessageNum.TRADE_COUNT_DOWN;
                Bundle bundle = new Bundle();
                bundle.putInt("count_down",count);
                mess.setData(bundle);
                count_down_handler.sendMessage(mess);
            }
        }.start();

    }



}
