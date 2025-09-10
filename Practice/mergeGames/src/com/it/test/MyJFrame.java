package com.it.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MyJFrame extends JFrame implements ActionListener{
    //创建一个按钮对象
    JButton jtb = new JButton("点我啊");
    JButton jtb2 = new JButton("别点我");

    public MyJFrame(){
        JFrame jFrame = new JFrame("测试");

        jFrame.setSize(603,608);

        //操作：置顶，居中，关闭模式，取消默认
        jFrame.setAlwaysOnTop(true);

        jFrame.setLocationRelativeTo(null);

        jFrame.setDefaultCloseOperation(3);

        jFrame.setLayout(null);



        //设置位置和宽高
        jtb.setBounds(0,0,100,50);
        jtb2.setBounds(100,0,100,50);

    /*jtb.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("爽一下");
        }
    });*/

        //第二种
        jtb.addActionListener(this);
        jtb2.addActionListener(this);


        //把按钮添加到界面当中
        jFrame.getContentPane().add(jtb);
        jFrame.getContentPane().add(jtb2);


        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取当前被操作的内个对象
        Object source = e.getSource();

        if (source == jtb){
            jtb.setSize(200,200);
        }else if (source == jtb2){
            Random r = new Random();
            jtb2.setLocation(r.nextInt(500),r.nextInt(500));
        }

        System.out.println("这也行");
    }
}
