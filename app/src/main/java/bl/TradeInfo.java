package bl;

import java.util.Date;

import data.EorA;
import data.upORdown;

/**
 * Created by FD on 2015/10/21.
 */
public class TradeInfo {
    private upORdown up_down;
    private EorA eorA;
    private double executePrice;
    private Date dll;
    private boolean in;
    private int amount;
    private String optionName;
    private double rate;

    //查询使用
    //非障碍期权
    public TradeInfo (String optionName,EorA eorA, upORdown up_down, double executePrice, Date dll)
    {
        this.optionName = optionName;
        this.eorA = eorA;
        this.up_down = up_down;
        this.executePrice = executePrice;
        this.dll = dll;
    }
    //障碍期权
    public TradeInfo(String optionName,EorA eorA, upORdown up_down, double executePrice,double rate,Date dll)
    {
        this.optionName = optionName;
        this.eorA = eorA;
        this.up_down = up_down;
        this.executePrice = executePrice;
        this.dll = dll;
        this.rate  = rate;
    }


    //交易使用
    //非障碍期权
    public TradeInfo(String optionName,EorA eorA, upORdown up_down, double executePrice, boolean in, Date dll, int amount) {
        this.optionName = optionName;
        this.eorA = eorA;
        this.up_down = up_down;
        this.executePrice = executePrice;
        this.in = in;
        this.dll = dll;
        this.amount = amount;
    }

    //障碍期权
    public TradeInfo(String optionName,EorA eorA, upORdown up_down, double executePrice, boolean in, double rate,Date dll, int amount) {
        this.optionName = optionName;
        this.eorA = eorA;
        this.up_down = up_down;
        this.executePrice = executePrice;
        this.in = in;
        this.dll = dll;
        this.amount = amount;
        this.rate  = rate;
    }

    public String getOptionName()
    {
        return  optionName;
    }
    public upORdown getUp_down() {
        return up_down;
    }

    public EorA getEorA() {
        return eorA;
    }

    public double getExecutePrice() {
        return executePrice;
    }

    public Date getDll() {
        return dll;
    }

    public boolean isIn() {
        return in;
    }

    public int getAmount() {
        return amount;
    }
    public double getRate(){return rate;}
}
