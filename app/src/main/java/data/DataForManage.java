package data;

import java.io.Serializable;

public class DataForManage implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double risk_free_Rate = 0.1; // 无风险率  注：服务器可修改该值
	private double fluctuation_Rate = 0.5; // 波动率  注：服务器可修改该值
	private double float_Rate = 0.02; // 价格浮动  注：服务器可修改该值
	private double delta_threshold = 10; // delta阈值  注：服务器可修改该值
	private double gamma_threshold = 0.5; // gamma阈值  注：服务器可修改该值
	private double vega_threshold = 150; // vega阈值值  注：服务器可修改该值
	private Futures futres; // 自动交易期货·  注：服务器可修改该值
	private Option option1; // 自动交易期权A  注：服务器可修改该值
	private Option option2; // 自动交易期权B  注：服务器可修改该值
	private long serverProperty = new Long("999999999999999");//客户原可用资金数值
	private double clientKvalue = 2.0;//客户资金计算K值     注：服务器可修改该值
	
	
	public DataForManage(double risk_free_Rate, double fluctuation_Rate,
			double float_Rate, double delta_threshold, double gamma_threshold,
			double vega_threshold, Futures futres, Option option1,
			Option option2, long serverProperty, double clientKvalue) {
		super();
		this.risk_free_Rate = risk_free_Rate;
		this.fluctuation_Rate = fluctuation_Rate;
		this.float_Rate = float_Rate;
		this.delta_threshold = delta_threshold;
		this.gamma_threshold = gamma_threshold;
		this.vega_threshold = vega_threshold;
		this.futres = futres;
		this.option1 = option1;
		this.option2 = option2;
		this.serverProperty = serverProperty;
		this.clientKvalue = clientKvalue;
	}
	public long getServerProperty() {
		return serverProperty;
	}
	public void setServerProperty(long serverProperty) {
		this.serverProperty = serverProperty;
	}
	public double getClientKvalue() {
		return clientKvalue;
	}
	public void setClientKvalue(double clientKvalue) {
		this.clientKvalue = clientKvalue;
	}
	public double getRisk_free_Rate() {
		return risk_free_Rate;
	}
	public void setRisk_free_Rate(double risk_free_Rate) {
		this.risk_free_Rate = risk_free_Rate;
	}
	public double getFluctuation_Rate() {
		return fluctuation_Rate;
	}
	public void setFluctuation_Rate(double fluctuation_Rate) {
		this.fluctuation_Rate = fluctuation_Rate;
	}
	public double getFloat_Rate() {
		return float_Rate;
	}
	public void setFloat_Rate(double float_Rate) {
		this.float_Rate = float_Rate;
	}
	public double getDelta_threshold() {
		return delta_threshold;
	}
	public void setDelta_threshold(double delta_threshold) {
		this.delta_threshold = delta_threshold;
	}
	public double getGamma_threshold() {
		return gamma_threshold;
	}
	public void setGamma_threshold(double gamma_threshold) {
		this.gamma_threshold = gamma_threshold;
	}
	public double getVega_threshold() {
		return vega_threshold;
	}
	public void setVega_threshold(double vega_threshold) {
		this.vega_threshold = vega_threshold;
	}
	public Futures getFutres() {
		return futres;
	}
	public void setFutres(Futures futres) {
		this.futres = futres;
	}
	public Option getOption1() {
		return option1;
	}
	public void setOption1(Option option1) {
		this.option1 = option1;
	}
	public Option getOption2() {
		return option2;
	}
	public void setOption2(Option option2) {
		this.option2 = option2;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
