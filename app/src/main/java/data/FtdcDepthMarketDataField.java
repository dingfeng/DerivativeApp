package data;

import java.io.Serializable;

/**
 * Created by Nifury on 9/2/2015.
 */

///深度行情
public class FtdcDepthMarketDataField implements Serializable {
    ///交易日
    private String tradingDay;
    ///合约代码
    private String instrumentID;
    ///交易所代码
    private String exchangeID;
    ///合约在交易所的代码
    private String exchangeInstID;
    ///最新价
    private double lastPrice;
    ///上次结算价
    private double preSettlementPrice;
    ///昨收盘
    private double preClosePrice;
    ///昨持仓量
    private double preOpenInterest;
    ///今开盘
    private double openPrice;
    ///最高价
    private double highestPrice;
    ///最低价
    private double lowestPrice;
    ///数量
    private int volume;
    ///成交金额
    private double turnover;
    ///持仓量
    private double openInterest;
    ///今收盘
    private double closePrice;
    ///本次结算价
    private double settlementPrice;
    ///涨停板价
    private double upperLimitPrice;
    ///跌停板价
    private double lowerLimitPrice;
    ///昨虚实度
    private double preDelta;
    ///今虚实度
    private double currDelta;
    ///最后修改时间
    private String updateTime;
    ///最后修改毫秒
    private int updateMillisec;
    ///申买价一
    private double bidPrice1;
    ///申买量一
    private int bidVolume1;
    ///申卖价一
    private double askPrice1;
    ///申卖量一
    private int askVolume1;
    ///申买价二
    private double bidPrice2;
    ///申买量二
    private int bidVolume2;
    ///申卖价二
    private double askPrice2;
    ///申卖量二
    private int askVolume2;
    ///申买价三
    private double bidPrice3;
    ///申买量三
    private int bidVolume3;
    ///申卖价三
    private double askPrice3;
    ///申卖量三
    private int askVolume3;
    ///申买价四
    private double bidPrice4;
    ///申买量四
    private int bidVolume4;
    ///申卖价四
    private double askPrice4;
    ///申卖量四
    private int askVolume4;
    ///申买价五
    private double bidPrice5;
    ///申买量五
    private int bidVolume5;
    ///申卖价五
    private double askPrice5;
    ///申卖量五
    private int askVolume5;
    ///当日均价
    private double averagePrice;
    ///业务日期
    private String actionDay;

    public FtdcDepthMarketDataField(String tradingDay, String instrumentID, String exchangeID, String exchangeInstID,
                                    double lastPrice, double preSettlementPrice, double preClosePrice, double
                                            preOpenInterest, double openPrice, double highestPrice, double
                                            lowestPrice, int volume, double turnover, double openInterest, double
                                            closePrice, double settlementPrice, double upperLimitPrice, double
                                            lowerLimitPrice, double preDelta, double currDelta, String updateTime,
                                    int updateMillisec, double bidPrice1, int bidVolume1, double askPrice1, int
                                            askVolume1, double bidPrice2, int bidVolume2, double askPrice2, int
                                            askVolume2, double bidPrice3, int bidVolume3, double askPrice3, int
                                            askVolume3, double bidPrice4, int bidVolume4, double askPrice4, int
                                            askVolume4, double bidPrice5, int bidVolume5, double askPrice5, int
                                            askVolume5, double averagePrice, String actionDay) {
        this.tradingDay = tradingDay;
        this.instrumentID = instrumentID;
        this.exchangeID = exchangeID;
        this.exchangeInstID = exchangeInstID;
        this.lastPrice = lastPrice;
        this.preSettlementPrice = preSettlementPrice;
        this.preClosePrice = preClosePrice;
        this.preOpenInterest = preOpenInterest;
        this.openPrice = openPrice;
        this.highestPrice = highestPrice;
        this.lowestPrice = lowestPrice;
        this.volume = volume;
        this.turnover = turnover;
        this.openInterest = openInterest;
        this.closePrice = closePrice;
        this.settlementPrice = settlementPrice;
        this.upperLimitPrice = upperLimitPrice;
        this.lowerLimitPrice = lowerLimitPrice;
        this.preDelta = preDelta;
        this.currDelta = currDelta;
        this.updateTime = updateTime;
        this.updateMillisec = updateMillisec;
        this.bidPrice1 = bidPrice1;
        this.bidVolume1 = bidVolume1;
        this.askPrice1 = askPrice1;
        this.askVolume1 = askVolume1;
        this.bidPrice2 = bidPrice2;
        this.bidVolume2 = bidVolume2;
        this.askPrice2 = askPrice2;
        this.askVolume2 = askVolume2;
        this.bidPrice3 = bidPrice3;
        this.bidVolume3 = bidVolume3;
        this.askPrice3 = askPrice3;
        this.askVolume3 = askVolume3;
        this.bidPrice4 = bidPrice4;
        this.bidVolume4 = bidVolume4;
        this.askPrice4 = askPrice4;
        this.askVolume4 = askVolume4;
        this.bidPrice5 = bidPrice5;
        this.bidVolume5 = bidVolume5;
        this.askPrice5 = askPrice5;
        this.askVolume5 = askVolume5;
        this.averagePrice = averagePrice;
        this.actionDay = actionDay;
    }

    public String getTradingDay() {
        return tradingDay;
    }

    public String getInstrumentID() {
        return instrumentID;
    }

    public String getExchangeID() {
        return exchangeID;
    }

    public String getExchangeInstID() {
        return exchangeInstID;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public double getPreSettlementPrice() {
        return preSettlementPrice;
    }

    public double getPreClosePrice() {
        return preClosePrice;
    }

    public double getPreOpenInterest() {
        return preOpenInterest;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public double getHighestPrice() {
        return highestPrice;
    }

    public double getLowestPrice() {
        return lowestPrice;
    }

    public int getVolume() {
        return volume;
    }

    public double getTurnover() {
        return turnover;
    }

    public double getOpenInterest() {
        return openInterest;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public double getSettlementPrice() {
        return settlementPrice;
    }

    public double getUpperLimitPrice() {
        return upperLimitPrice;
    }

    public double getLowerLimitPrice() {
        return lowerLimitPrice;
    }

    public double getPreDelta() {
        return preDelta;
    }

    public double getCurrDelta() {
        return currDelta;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public int getUpdateMillisec() {
        return updateMillisec;
    }

    public double getBidPrice1() {
    	if(bidPrice1>100000){
    		return getLastPrice()*(1+0.02);
    	}
        return bidPrice1;
    }

    public int getBidVolume1() {
        return bidVolume1;
    }

    public double getAskPrice1() {
    	if(askPrice1>100000){
    		return getLastPrice()*(1- 0.02);
    	}
        return askPrice1;
    }

    public int getAskVolume1() {
        return askVolume1;
    }

    public double getBidPrice2() {
        return bidPrice2;
    }

    public int getBidVolume2() {
        return bidVolume2;
    }

    public double getAskPrice2() {
        return askPrice2;
    }

    public int getAskVolume2() {
        return askVolume2;
    }

    public double getBidPrice3() {
        return bidPrice3;
    }

    public int getBidVolume3() {
        return bidVolume3;
    }

    public double getAskPrice3() {
        return askPrice3;
    }

    public int getAskVolume3() {
        return askVolume3;
    }

    public double getBidPrice4() {
        return bidPrice4;
    }

    public int getBidVolume4() {
        return bidVolume4;
    }

    public double getAskPrice4() {
        return askPrice4;
    }

    public int getAskVolume4() {
        return askVolume4;
    }

    public double getBidPrice5() {
        return bidPrice5;
    }

    public int getBidVolume5() {
        return bidVolume5;
    }

    public double getAskPrice5() {
        return askPrice5;
    }

    public int getAskVolume5() {
        return askVolume5;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public String getActionDay() {
        return actionDay;
    }
}
