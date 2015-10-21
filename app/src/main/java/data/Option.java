package data;

import java.io.Serializable;

public class Option implements Serializable { // 期权
	private static final long serialVersionUID = 1L;
	private String firstClassName; // 第一大分类;
	private String secondClassName; // 第二大分类;
	private EorA eora; // 欧式或美式
	private upORdown upordown; // 看涨或看跌
	private double payOff = 1; // 支付金，只有二元期权会有，其他的情况下值都为-1（注：现在又取消了支付金这个东西，我也是醉了）
	private double ObstacleRate = -1; // 障碍水平，只有障碍期权会有，其他的情况下都为-1

	public Option(String firstClassName, String secondClassName, EorA eora,
			upORdown upordown) {
		this.firstClassName = firstClassName;
		this.secondClassName = secondClassName;
		this.eora = eora;
		this.upordown = upordown;
	}

	public String getFirstClassName() {
		return firstClassName;
	}

	public String getSecondClassName() {
		return secondClassName;
	}

	public EorA getEora() {
		return eora;
	}

	public upORdown getUpordown() {
		return upordown;
	}

	public double getPayOff() {
		return payOff;
	}

	public void setPayOff(double payOff) {
		this.payOff = payOff;
	}

	public double getObstacleRate() {
		return ObstacleRate;
	}

	public void setObstacleRate(double obstacleRate) {
		ObstacleRate = obstacleRate;
	}

	public String toString() {
		return "firstClassName:\t" + firstClassName + "\tsecondClassName:\t"
				+ secondClassName + "\teora:\t" + eora + "\tupordown:\t"
				+ upordown + "\t";
	}

	public boolean isequal(Option option) { // 判断两个期权是否相等
		// TODO Auto-generated method stub
		if (this.eora == option.getEora()
				&& this.upordown == option.getUpordown()
				&& this.firstClassName != null
				&& this.firstClassName.equals(option.getFirstClassName())
				&& (this.secondClassName != null && this.secondClassName
						.equals(option.getSecondClassName()))
				|| (this.secondClassName == null && option.getSecondClassName() == null)
				&& option.getObstacleRate() == this.getObstacleRate()) {
			return true;
		}
		return false;
	}
}
