package com.it.test;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyJFrame2 extends JFrame implements MouseListener {

    JButton jtb1 = new JButton("点这");

    public MyJFrame2(){
        JFrame jFrame = new JFrame("测试2");
        //jFrame.setTitle("");

        jFrame.setSize(603,680);

        jFrame.setAlwaysOnTop(true);

        jFrame.setLocationRelativeTo(null);

        jFrame.setLayout(null);

        jFrame.setDefaultCloseOperation(3);

        jtb1.setBounds(0,0,100,50);
        jtb1.addMouseListener(this);

        jFrame.getContentPane().add(jtb1);


        jFrame.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("单击");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("按下不松");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("松开");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("划入");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("划出");
    }
}
