package data;

import java.io.Serializable;

public class User_fund implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String UserId;
	long time;           //时间
	private long Customer_equity;			//客户权益
	private long property;			//可用资金  需要除以100
	private long bail;					//保证金
	private long royalty;					//权利金
	private long market_value;		//期权市值
	private long Cost_customer_equity;					//带成本用户权益，只有作为市商的系统有
	
	
	public User_fund(String userId,long time, long customer_equity, long property,
			long bail, long royalty, long market_value,long Cost_customer_equity) {
		super();
		this.UserId = userId;
		this.time = time;
		this.Customer_equity = customer_equity;
		this.property = property;
		this.bail = bail;
		this.royalty = royalty;
		this.market_value = market_value;
		this.Cost_customer_equity = Cost_customer_equity;
	}
	
	public String getUserId() {
		return UserId;
	}

	public long getProperty() {
		return property;
	}

	public long getCustomer_equity() {
		Customer_equity = property + bail - royalty + market_value;
		return Customer_equity;
	}

	public long getBail() {
		return bail;
	}

	public long getRoyalty() {
		return royalty;
	}

	public long getMarket_value() {
		return market_value;
	}

	public long getTime() {
		return time;
	}

	public long getCost_customer_equity() {
		return Cost_customer_equity;
	}
	
}
