package data;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderOFholdings implements Serializable{
	private static final long serialVersionUID = 1L;
	private String account;	//用户账号
	private Option option; // 期权
	private Date deadline; // 到期时间
	private long number; // 数量,购买记录相加就行
	private double cost;//成本，是买卖价格的加权平均
	/**
	 * 如果一开始买了A100个，买价为50，那么成本就是50，如果后来有卖了A120个，卖价为60，那么成本就是（50*100-60*120）/（100-120）
	 * */
	private int deadTime; // 距离到期时间（给界面用的，数据库那边不用管）
	private double executeprice; // 执行价格
	
	public OrderOFholdings(String account,Option option, Date deadline, long number, double cost, double executeprice) {
		super();
		this.account =account;
		this.option = option;
		this.deadline = deadline;
		this.number = number;
		this.cost = cost;
		this.executeprice = executeprice;
	}
	
   	
	
	public Option getOption() {
		return option;
	}
	public Date getDeadline() {
		return deadline;
	}
	public long getNumber() {
		return number;
	}
	public double getCost() {
		return cost;
	}
	
	public int getDeadTime() {
		Date now = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		Date date=new Date();
		String str=sdf.format(date);  
		try {
			now = sdf.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		deadTime = (int)((deadline.getTime() - now.getTime())
				/ (24 * 60 * 60 * 1000));
		return this.deadTime;
	}
	
	public String toString()
	{
		return "account:\t "+account+ "\toption : \t"+option +" \tdeadTime:\t"+getDeadTime()+"\tnum:\t"+getNumber()+"\tcost:\t"+getCost()+"\texecutePrice:\t"+executeprice+"\n";
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	public double getExecuteprice() {
		return executeprice;
	}

	public void setExecuteprice(double executeprice) {
		this.executeprice = executeprice;
	}
	
}
