package com.derivative.derivativeapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private ExpandableListView expandableList;
    private String[] groups = {"全部", "普通期权", "二元期权", "回望期权", "亚式期权", "障碍期权"};
    private String[][] child = {{"全部"}, {"普通期权"}, {"资产或无价值期权", "现金或无价值期权"}, {"浮动执行价格期权", "固定执行价格期权"}, {"平均价格期权",
            "平均执行价格期权"}, {"向上敲入期权", "向上敲出期权", "向下敲入期权", "向下敲出期权"}};
    private String[] leftMenuItems = {"注销登录", "用户交易", "交易记录"};
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_main);
        rightLayout = findViewById(R.id.rightLayout);
        leftLayout = findViewById(R.id.leftLayout);
        drawerLayout = (DrawerLayout) findViewById(R.id.my_drawer_layout);
        drawerLayout.setStatusBarBackgroundColor(Color.parseColor("#FF0000"));
        toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        toolbar.setTitle("期权衍生品组合商品交易");
        setSupportActionBar(toolbar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawlayout, R.string.close_drawlayout);
        actionBarDrawerToggle.syncState();
        expandableList = (ExpandableListView) findViewById(R.id.rightMenu);

        notAllAdapter = new TreeViewAdapter(this, TreeViewAdapter.PaddingLeft >> 1);
        List<TreeViewAdapter.TreeNode> treeNode = notAllAdapter.GetTreeNode();
        for (int i = 1; i < groups.length; i++) {
            TreeViewAdapter.TreeNode node = new TreeViewAdapter.TreeNode();
            node.parent = groups[i];
            for (int ii = 1; ii < child[i].length; ii++) {
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
            for (int ii = 0; ii < child[i].length; ii++) {
                node.childs.add(child[i][ii]);
            }
            treeNode.add(node);
        }

        allAdapter.UpdateTreeNode(treeNode);
        expandableList.setAdapter(notAllAdapter);
        expandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView arg0, View arg1,
                                        int parent, int children, long arg4) {
                String str = "parent id:" + String.valueOf(parent) + ",children id:" + String.valueOf(children);
                Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
                return false;
            }
        });

        listView = (ListView) findViewById(R.id.leftListView);

        ArrayAdapter<String> leftMenuItems = new ArrayAdapter<String>(this, R.layout.left_array_item, this.leftMenuItems);
        listView.setAdapter(leftMenuItems);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int choiceNum,
                                    long arg3) {

                if (choiceNum == 0) {

                    //注销登陆
                } else if (choiceNum == 1) {
                    //用户交易
                    toolbar.setSubtitle("用户交易");
                    drawerLayout.closeDrawer(leftLayout);
                    drawerLayout.openDrawer(rightLayout);
                    expandableList.setAdapter(allAdapter);
                } else if (choiceNum == 2) {
                    //交易记录
                    toolbar.setSubtitle("交易记录");
                    drawerLayout.closeDrawer(leftLayout);
                    drawerLayout.openDrawer(rightLayout);
                    expandableList.setAdapter(notAllAdapter);

                }

            }
        });


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
}
