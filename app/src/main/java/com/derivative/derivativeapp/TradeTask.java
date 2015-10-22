package com.derivative.derivativeapp;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;
import bl.BlController;
import bl.BlService;
import bl.TradeInfo;
import data.EorA;
import data.upORdown;

/**
 * Created by FD on 2015/10/21.
 */
class TradeTask extends AsyncTask<TradeInfo, Integer, Double[]> {

    private Context context;
    private ProgressDialog pdialog;
    private TextView buyTextView;
    private TextView sellTextiew;

    public TradeTask(Context context, TextView buyTextView, TextView sellTextView) {
        this.context = context;
        this.buyTextView = buyTextView;
        this.sellTextiew = sellTextView;
    }

    //执行中
    @Override
    protected Double[] doInBackground(TradeInfo... params) {
        BlService blController = BlController.instance();
        TradeInfo tradeInfo = params[0];
        upORdown up_down = tradeInfo.getUp_down();
        EorA eorA = tradeInfo.getEorA();
        double executePrice = tradeInfo.getExecutePrice();
        Date dll = tradeInfo.getDll();
        String optionName = tradeInfo.getOptionName();
        double rate = tradeInfo.getRate();
        double[] datas = null;
        if (optionName.startsWith("向")) {
            //障碍期权
            datas = blController.getPriceOfOption_noObstacle(optionName, eorA, up_down, executePrice, dll);
        } else {
            //非障碍期权
            datas = blController.getPriceOfOption_obstacle(optionName, eorA, up_down, executePrice, rate, dll);
        }
        return new Double[]{datas[0], datas[1]};
    }

    //执行之后
    @Override
    protected void onPostExecute(Double[] prices) {
        //消除进度条
        pdialog.dismiss();
        if (prices != null) {
            //查询成功
            //保留4位小数
            buyTextView.setText(String.format("%.4f", prices[0]));
            sellTextiew.setText(String.format("%.4f", prices[1]));
            MainActivity mainActivity = (MainActivity)this.context;
            TradeFragment tradeFragment = mainActivity.getTradeFragment();
            tradeFragment.tryBeginTrade();
        } else {
            //查询失败 提示查询失败
            Toast.makeText(this.context,"查询失败，请检查网络",Toast.LENGTH_SHORT);
        }
    }

    //执行之前
    @Override
    protected void onPreExecute() {
        pdialog = new ProgressDialog(context);
        //进度条为环状进度条
        pdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //进度条不显示进度
        pdialog.setIndeterminate(false);
        pdialog.show();
    }

    //执行中更新
    @Override
    protected void onProgressUpdate(Integer... value) {
        //do nothing
    }

}
