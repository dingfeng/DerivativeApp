package com.derivative.derivativeapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.*;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private ExpandableListView expandableList;
    private String[] groups = {"全部", "普通期权", "二元期权", "回望期权", "亚式期权", "障碍期权"};
    private String[][] child = {{"全部"}, {"普通期权"}, {"资产或无价值期权", "现金或无价值期权"}, {"浮动执行价格期权", "固定执行价格期权"}, {"平均价格期权",
            "平均执行价格期权"}, {"向上敲入期权", "向上敲出期权", "向下敲入期权", "向下敲出期权"}};
    private String[] leftMenuItems = {"注销登录", "用户信息", "用户交易", "交易记录", "持仓记录"};
    private View layout;
    private ArrayList<String> list;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ListView listView;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private View rightLayout;
    private View leftLayout;
    private TreeViewAdapter notAllAdapter;
    private TreeViewAdapter allAdapter;
    private FragmentManager fragmentManager;
    private String leftMenuOption;
    private String rightMenuOption;
    private TableFragment tableFragment;
    private UserInfoFragment userInfoFragment;
    private TradeFragment tradeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rightLayout = findViewById(R.id.rightLayout);
        leftLayout = findViewById(R.id.leftLayout);
        drawerLayout = (DrawerLayout) findViewById(R.id.my_drawer_layout);
        drawerLayout.setStatusBarBackgroundColor(Color.parseColor("#FF0000"));
        toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        toolbar.setTitle("高级衍生品做市与组合管理系统");
        setSupportActionBar(toolbar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawlayout, R.string.close_drawlayout);
        actionBarDrawerToggle.syncState();
        expandableList = (ExpandableListView) findViewById(R.id.rightMenu);

        notAllAdapter = new TreeViewAdapter(this, TreeViewAdapter.PaddingLeft >> 1);
        List<TreeViewAdapter.TreeNode> treeNode = notAllAdapter.GetTreeNode();
        for (int i = 1; i < groups.length; i++) {
            TreeViewAdapter.TreeNode node = new TreeViewAdapter.TreeNode();
            node.parent = groups[i];

            for (int ii = 0; ii < child[i].length; ii++) {
                node.childs.add(child[i][ii]);
            }
            treeNode.add(node);
        }

        notAllAdapter.UpdateTreeNode(treeNode);

        allAdapter = new TreeViewAdapter(this, TreeViewAdapter.PaddingLeft >> 1);
        treeNode = allAdapter.GetTreeNode();
        for (int i = 0; i < groups.length; i++) {
            TreeViewAdapter.TreeNode node = new TreeViewAdapter.TreeNode();
            node.parent = groups[i];
            int ii = 0;
            if (i == 1) {
                ++ii;
            }
            for (; ii < child[i].length; ii++) {
                node.childs.add(child[i][ii]);
            }
            treeNode.add(node);
        }

        allAdapter.UpdateTreeNode(treeNode);
        expandableList.setAdapter(notAllAdapter);
        expandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView arg0, View view,
                                        int parent, int children, long arg4) {
                String choice = ((TextView) view).getText().toString();
                if (!choice.equals(rightMenuOption)) {
                    rightMenuOption = choice;
                    if (leftMenuOption.equals("用户交易")) {
                       toTradeFragment();
                    }
                    drawerLayout.closeDrawer(rightLayout);
                    updateTable();
                }
                return false;
            }
        });

        expandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View view, int groupPosition, long id) {
                String choice = ((TextView) view).getText().toString();
                if (choice.equals("全部") || choice.equals("普通期权")) {
                    if (!choice.equals(rightMenuOption)) {
                        rightMenuOption = choice;
                        if (leftMenuOption.equals("用户交易")) {
                            toTradeFragment();
                        }
                        drawerLayout.closeDrawer(rightLayout);
                        updateTable();
                    }
                }

                return false;
            }
        });


        listView = (ListView) findViewById(R.id.leftListView);
        final ArrayAdapter<String> leftMenuItems = new ArrayAdapter<String>(this, R.layout.left_array_item, this.leftMenuItems);
        listView.setAdapter(leftMenuItems);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int choiceNum,
                                    long arg3) {

                drawerLayout.closeDrawer(leftLayout);
                if (choiceNum == 0) {
                    //注销登陆
                    toolbar.setSubtitle("注销登录");
                    leftMenuOption = "注销登录";
                    drawerLayout.removeView(rightLayout);
                } else if (choiceNum == 1) {
                    //用户信息
                    toolbar.setSubtitle("用户信息");
                    leftMenuOption = "用户信息";
                    drawerLayout.removeView(rightLayout);
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.main_container, userInfoFragment);
                    fragmentTransaction.commit();
                } else if (choiceNum == 2) {
                    //用户交易
                    //交易记录
                    if (drawerLayout.findViewById(R.id.rightLayout) == null) {
                        drawerLayout.addView(rightLayout);
                    }
                    drawerLayout.openDrawer(rightLayout);
                    expandableList.setAdapter(notAllAdapter);
                    toolbar.setSubtitle("用户交易");
                    leftMenuOption = "用户交易";
                    expandableList.setAdapter(notAllAdapter);
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.main_container, tradeFragment);
                    fragmentTransaction.commit();

                } else if (choiceNum == 3) {
                    //交易记录
                    if (drawerLayout.findViewById(R.id.rightLayout) == null) {
                        drawerLayout.addView(rightLayout);
                    }
                    toolbar.setSubtitle("交易记录");
                    drawerLayout.openDrawer(rightLayout);
                    expandableList.setAdapter(notAllAdapter);
                    leftMenuOption = "交易记录";
                    tableFragment = new TableFragment();
                    Bundle arguments = new Bundle();
                    arguments.putString(TableFragment.RECORD_TYPE, leftMenuOption);
                    arguments.putString(TableFragment.ITEM_ID, rightMenuOption);
                    tableFragment.setArguments(arguments);
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.main_container, tableFragment);
                    fragmentTransaction.commit();
                } else if (choiceNum == 4) {
                    //持仓记录
                    if (drawerLayout.findViewById(R.id.rightLayout) == null) {
                        drawerLayout.addView(rightLayout);
                    }
                    toolbar.setSubtitle("持仓记录");
                    drawerLayout.openDrawer(rightLayout);
                    expandableList.setAdapter(notAllAdapter);
                    leftMenuOption = "持仓记录";
                    tableFragment = new TableFragment();
                    Bundle arguments = new Bundle();
                    arguments.putString(TableFragment.RECORD_TYPE, leftMenuOption);
                    arguments.putString(TableFragment.ITEM_ID, rightMenuOption);
                    tableFragment.setArguments(arguments);
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.main_container, tableFragment);
                    fragmentTransaction.commit();
                }


            }
        });
        userInfoFragment = new UserInfoFragment();
        tableFragment = new TableFragment();
        tradeFragment = new TradeFragment();
        fragmentManager = getSupportFragmentManager();

        //初始化中心主界面 用户交易-普通期权
        leftMenuOption = "用户交易";
        rightMenuOption = "普通期权";
        toolbar.setSubtitle(leftMenuOption + "-" + rightMenuOption);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, tradeFragment);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String getRightMenuOption()
    {
        return rightMenuOption;
    }

    private void updateTable() {

    }


    private void toTradeFragment()
    {
        toolbar.setSubtitle("用户交易-" + rightMenuOption);
        tradeFragment = new TradeFragment();
        Bundle arguments = new Bundle();
        arguments.putString(TradeFragment.OPTION_NAME,rightMenuOption);
        tradeFragment.setArguments(arguments);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, tradeFragment);
        fragmentTransaction.commit();
    }

}
