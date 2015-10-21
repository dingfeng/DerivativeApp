package data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Price implements Comparable<Price>, Serializable{
	private static final long serialVersionUID = 1L;
    long time;           //时间
    double price;        //现价
    double lastPrice;    //昨日收盘
    double maxPrice;     //最低
    double minPrice;    //最高
    long   amount; //成交量
    long   turnover;    //成交额
    public Price (long time, double price,double lastPrice,double maxPrice, double minPrice,long amount, long turnover){
    	this.time = time;
    	this.price = price;
    	this.lastPrice = lastPrice;
    	this.maxPrice = maxPrice;
    	this.minPrice = minPrice;
    	this.amount = amount;
    	this.turnover = turnover;
    }
    
    public Price ( Date date,double price,double lastPrice,double maxPrice, double minPrice,long amount, long turnover)
    {
    	this.time = date.getTime();
    	this.price = price;
    	this.lastPrice = lastPrice;
    	this.maxPrice = maxPrice;
    	this.minPrice = minPrice;
    	this.amount = amount;
    	this.turnover = turnover;
    }
    
    public String toString()
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String dateStr = format.format(new Date(time));
		return "date : \t"+dateStr+"\tprice:\t"+price+"\tlastPrice:\t"+lastPrice+"\tmaxPrice:\t"+maxPrice+"\tminPrice:\t"+minPrice+"\tamount:\t"+amount+"\tturnover:\t"+turnover+"\tamplitude:\t"+this.getAmplitude()+"\tmargin:\t"+this.getMargin()+"\tmarginRate:\t"+this.getMarginRate()+"\n";
	}
    
    
    public double getAmplitude ()
    {
    	return Math.max(Math.abs(maxPrice - price),Math.abs(minPrice - price)) / lastPrice; 
    }
    
    public double getMargin()
    {
    	return price - lastPrice;
    }
    
    public double getMarginRate()
    {
     return (price - lastPrice) / lastPrice;
    }
    
    public double getLastPrice() {
		return lastPrice;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public long getAmount() {
		return amount;
	}

	public long getTurnover() {
		return turnover;
	}

	public long getTime ()
    {
    	return this.time;
    }
    
    public double getPrice ()
    {
    	return this.price;
    }
    
	public int compareTo(Price o) {
		if (this.time > o.getTime())
		{
			return 1;
		}
		else if (this.time < o.getTime())
		{
			return -1;
		}
		
		return 0;
	}
	
	

}
