package com.it.ui;

import javax.swing.*;
import java.util.Scanner;

public class LoginJFrame extends JFrame {
    //登录界面

    public LoginJFrame(){
        //在创建登录界面的时候，同时给这个界面设置一些信息
        //比如直接展示出来

        //初始化界面
        initJFrame();



        //初始化菜单
        //initJMenuBar();


        //让界面显示出来，建议写在最后
        this.setVisible(true);
    }





    private void initJFrame() {
        this.setSize(488,430);
        //设置界面的标题
        this.setTitle("拼图 登录");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
