package bl;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Future;

import data.EorA;
import data.Futures;
import data.Option;
import data.Order;
import data.OrderOFholdings;
import data.User;
import data.User_fund;
import data.upORdown;

/**
 * Created by FD on 2015/10/21.
 */
public class BlController implements  BlService {

    private String account = "dingfeng";
    private static BlService blController = new BlController();
    private BlController()
    {

    }

    public String getUserAccount()
    {
        return account;
    }

    public static BlService instance()
    {
        return blController;
    }
    @Override
    public User login(String account, String password) {
        return new User("丁峰","dingfeng", "777373", "88536432",
                "南京大学", 1, 1, 1);
    }

    public User getUserInfo(String account)
    {
        return new User("丁峰","dingfeng", "777373", "88536432",
                "南京大学", 1, 1, 1);
    }

    @Override
    public void signOut(String clientID) {
        Log.e("用户注销", clientID);
    }


    @Override
    public boolean askForMoreAuthority(String userId) {
        Log.e("申请更高权限",userId);
        return true;
    }

    @Override
    public Futures[] getAllFutures() {
        return new Futures[0];
    }


    @Override
    public Futures[] getAllOptions() {
        Futures futures1 = new Futures("helloWorld", 1, 1,
        1, 1, 1, 1,1, 1, 1,1, 1);
        return new Futures[]{futures1};
    }



    @Override
    public double[] getPriceOfOption_noObstacle(String optionName, EorA eora, upORdown upORdown, double executePrice, Date deadline) {

        return new double[]{1,2};
    }

    @Override
    public double[] getPriceOfOption_obstacle(String optionName, EorA eora, upORdown upORdown, double executePrice, double rate, Date deadline) {
        return new double[]{1,2};
    }

    @Override
    public Order[] getOrderByAccount(String account) {
        ArrayList<Option> options = getAllOptionList();
        ArrayList<Order> orders = new ArrayList<>();
        for (int i = 0; i < options.size(); ++i)
        {
            Option option = options.get(i);
            orders.add(new Order("hh123","dingfeng", option, new Date(),
            1, 1, 12,true));
            orders.add(new Order("kk123","helloworld", option, new Date(),
                    21,21, 212,false));
        }
        Order[] ordersArray = new Order[orders.size()];
        orders.toArray(ordersArray);
        return ordersArray;
    }

    @Override
    public OrderOFholdings[] getOrderOFholdingsByAccount(String account) {
        ArrayList<Option> optionList = getAllOptionList();
        ArrayList<OrderOFholdings> orderOFholdingsList = new ArrayList<OrderOFholdings>();
        for (int i = 0; i < optionList.size(); ++i)
        {
            Option option = optionList.get(i);
            orderOFholdingsList.add(new OrderOFholdings("dingfeng1"+i, option,
                    new Date(), 10, 10, 11));
            orderOFholdingsList.add(new OrderOFholdings("dingfeng2"+i, option,
                    new Date(), 6, 6, 20));
            orderOFholdingsList.add(new OrderOFholdings("dingfeng3"+i, option,
                    new Date(), 36, 24, 60));
        }
        OrderOFholdings[] orderOFholdingses = new OrderOFholdings[orderOFholdingsList.size()];
        orderOFholdingsList.toArray(orderOFholdingses);
        return orderOFholdingses;
    }

    @Override
    public User_fund getFundByAccount(String account) {
        return new User_fund("dingfeng",new Date().getTime(), 1,1,
        2, 3, 4,5);
    }

    @Override
    public Order requestOrder(Option option, int number, String ClientID, Date deadline, double executeprice, double dealprice) {
        ArrayList<Option> options = getAllOptionList();
        return new Order("hh123","dingfeng", options.get(0), new Date(),
                1, 1, 12,true);
    }

    @Override
    public boolean UpdateUser(User user) {

        return false;
    }

    @Override
    public boolean insureSell(Order order) {
        return false;
    }

    @Override
    public double getPresentValue() {
        return 0;
    }

    private ArrayList<Option> getAllOptionList()
    {
        String[] groups = {"全部", "普通期权", "二元期权", "回望期权", "亚式期权", "障碍期权"};
        String[][] child = {{"全部"}, {"普通期权"}, {"资产或无价值期权", "现金或无价值期权"}, {"浮动执行价格期权", "固定执行价格期权"}, {"平均价格期权",
                "平均执行价格期权"}, {"向上敲入期权", "向上敲出期权", "向下敲入期权", "向下敲出期权"}};
        ArrayList<Option> optionList = new ArrayList<Option>();
        for (int i = 1; i < groups.length; ++i)
        {
            for (int j = 0; j < child[i].length; ++j)
            {
                String firstClassName = groups[i];
                String secondClassName = child[i][j];
                optionList.add(new Option(firstClassName,secondClassName,EorA.A,upORdown.down));
                optionList.add(new Option(firstClassName,secondClassName,EorA.E,upORdown.up));
                optionList.add(new Option(firstClassName,secondClassName,EorA.A,upORdown.up));
                optionList.add(new Option(firstClassName,secondClassName,EorA.E,upORdown.down));
            }
        }
        return  optionList;
    }
}
