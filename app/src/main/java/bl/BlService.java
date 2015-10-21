package bl;

import java.util.Date;
import java.util.concurrent.Future;

import data.*;

/**
 * Created by FD on 2015/10/21.
 */
public interface BlService {
    //用户登录
    public  User login(String account, String password);
    //用户退出
    public void signOut(String clientID);

    //申请更高权限
    public boolean askForMoreAuthority(String userId);

    //获得所有期货
    public Futures[] getAllFutures();

    //获得所有期权
    public Futures[] getAllOptions();

    //得到期权买卖价 非障碍期权
    public double[] getPriceOfOption_noObstacle(String optionName, EorA eora, upORdown upORdown, double executePrice, Date deadline);

    //得到期权买卖价 障碍期权
    public double[] getPriceOfOption_obstacle(String optionName, EorA eora, upORdown upORdown, double executePrice, double rate, Date deadline);

    //通过客户ID（账号）得到该用户所有订单
    public Order[] getOrderByAccount(String account);

    //通过客户ID（账号）得到该用户所有持仓记录
    public OrderOFholdings[] getOrderOFholdingsByAccount(String account);

    //通过客户ID(账号)和该用户可用资金数得到该用户资金情况
    public User_fund getFundByAccount(String account);

    /*参数：option为卖出的期权类型，number为卖出的数量，ClientID用户唯一标识符
     deadline 截止日期，executeprice为执行价格，dealprice为买卖价*/
    public Order requestOrder(Option option, int number, String ClientID,
                              Date deadline, double executeprice, double dealprice);

    //更新用户信息
    public boolean UpdateUser(User user);

    /*确认购卖期权
    返回值为true，表明卖出成功*/
    public boolean insureSell(Order order);
    /*
     得到当前沪深300的股指
	 返回值，沪深300当前价格*/
    public double getPresentValue();

}
