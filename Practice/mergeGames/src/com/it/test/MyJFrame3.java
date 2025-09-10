package com.it.test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyJFrame3 extends JFrame implements KeyListener {

    public MyJFrame3(){
        this.setSize(603,680);

        this.setTitle("测试3");

        this.setAlwaysOnTop(true);

        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(3);

        this.setLayout(null);

        //给整个窗体添加键盘监听
        //调用者this:本类对象，当前的界面对象，表示我要给某个界面添加监听
        //addKeyListener:表示要给本界面添加键盘监听
        //参数this:当事件被触发之后，会执行本类中的对应代码
        this.addKeyListener(this);


        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("按下不松");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("松开");
        //获取键盘上每一个按键的编号
        int code = e.getKeyCode();
        if (code == 65){
            System.out.println("你现在按的是a");
        }
    }
}
