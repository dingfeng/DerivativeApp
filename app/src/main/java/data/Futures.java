package data;

import java.io.Serializable;

public class Futures implements Serializable{				
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//名称，最新价，涨跌，买量，卖量，成交量，开盘价，昨收价，最高价，最低价，持仓量，增仓；名称，最新价，涨跌，买量，卖量，成交量，开盘价，昨收价，最高价，最低价，持仓量，增仓；
	private String name;
	private double lastPrice;
	private double updownRate;
	private double purchaseNumber;
	private double sellNumber;
	private double dealNumber;
	private double openPrice;
	private double yesterdayPrice;
	private double maxPrice;
	private double minprice;
	private double number;
	private double addNumber;
	
	public Futures(String name, double lastPrice, double updownRate,
			double purchaseNumber, double sellNumber, double dealNumber,
			double openPrice, double yesterdayPrice, double maxPrice,
			double minprice, double number, double addNumber) {
		super();
		this.name = name;
		this.lastPrice = lastPrice;
		this.updownRate = updownRate;
		this.purchaseNumber = purchaseNumber;
		this.sellNumber = sellNumber;
		this.dealNumber = dealNumber;
		this.openPrice = openPrice;
		this.yesterdayPrice = yesterdayPrice;
		this.maxPrice = maxPrice;
		this.minprice = minprice;
		this.number = number;
		this.addNumber = addNumber;
	}

	public String getName() {
		return name;
	}

	public double getLastPrice() {
		return lastPrice;
	}

	public double getUpdownRate() {
		return updownRate;
	}

	public double getPurchaseNumber() {
		return purchaseNumber;
	}

	public double getSellNumber() {
		return sellNumber;
	}

	public double getDealNumber() {
		return dealNumber;
	}

	public double getOpenPrice() {
		return openPrice;
	}

	public double getYesterdayPrice() {
		return yesterdayPrice;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public double getMinprice() {
		return minprice;
	}

	public double getNumber() {
		return number;
	}

	public double getAddNumber() {
		return addNumber;
	}
	
}
