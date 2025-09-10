package com.it.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test3 {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("测试");

        jFrame.setSize(603,608);

        //操作：置顶，居中，关闭模式，取消默认
        jFrame.setAlwaysOnTop(true);

        jFrame.setLocationRelativeTo(null);

        jFrame.setDefaultCloseOperation(3);

        jFrame.setLayout(null);


        //创建一个按钮对象
        JButton jtb = new JButton("点我啊");
        //设置位置和宽高
        jtb.setBounds(0,0,100,50);
        //给按钮添加动作监听
        //添加事件监听
        //addActionListener:动作监听(鼠标左键点击，空格)
        //括号里的参数：表示事件被触发后要执行的代码
        //jtb.addActionListener(new MyActionListener());，只使用了一次，可用匿名内部类
        jtb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("爽一下");
            }
        });


        //把按钮添加到界面当中
        jFrame.getContentPane().add(jtb);


        jFrame.setVisible(true);
    }
}
