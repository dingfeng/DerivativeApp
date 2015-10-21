package data;

import java.io.Serializable;

public class RiskRateRecord implements Serializable{					//系统风险值的记录
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	long time;           //时间
	  double delta;
	  double gamma;
	  double vega;
	  double theta;
	public RiskRateRecord(long time, double delta, double gamma, double vega,
			double theta) {
		super();
		this.time = time;
		this.delta = delta;
		this.gamma = gamma;
		this.vega = vega;
		this.theta = theta;
	}
	public long getTime() {
		return time;
	}
	public double getDelta() {
		return delta;
	}
	public double getGamma() {
		return gamma;
	}
	public double getVega() {
		return vega;
	}
	public double getTheta() {
		return theta;
	}
	  
}
