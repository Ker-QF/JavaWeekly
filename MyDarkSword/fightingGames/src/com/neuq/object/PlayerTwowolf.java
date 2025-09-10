package com.neuq.object;

import com.neuq.ui.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayerTwowolf extends Pokemon{
    public PlayerTwowolf(String img,int speed, int x, int y, GamePanel gamePanel, String upImg, String leftImg, String rightImg, String downImg) {
        super(img,speed, x, y, gamePanel, upImg, leftImg, rightImg, downImg);
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_K:
                attack();
            default:
                break;
        }
    }

    // 新增：处理按键释放，停止移动
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_UP:
                up = false;
                break;
            default:
                break;
        }
    }

    public void move() {
        // 保存原始位置用于边界检测
        int originalX = x;
        int originalY = y;

        // 根据方向更新位置
        if (up) {
            upward(); // 更新为向上移动的图像
        }
        if (down) {
            downward(); // 更新为向下移动的图像
        }
        if (left) {
            leftward(); // 更新为向左移动的图像
        }
        if (right) {
            rightward(); // 更新为向右移动的图像
        }

        // 边界检测，确保角色不会移出游戏面板
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        if (x > gamePanel.width - width) {
            x = gamePanel.width - width;
        }
        if (y > gamePanel.height - height) {
            y = gamePanel.height - height;
        }
    }

    @Override
    public void attack(){
        if (attackCoolDown){
            Point p = this.getHeadPoint();
            switch (direction) {
                case LEFT:
                    this.gamePanel.biteList.add(new Bite("fightingGames\\image2\\wolf\\biteL.png", p.x, p.y, this.gamePanel, direction));
                    break;
                case RIGHT:
                    this.gamePanel.biteList.add(new Bite("fightingGames\\image2\\wolf\\biteR.png", p.x, p.y, this.gamePanel, direction));
                    break;
                case UP:
                    this.gamePanel.biteList.add(new Bite("fightingGames\\image2\\wolf\\biteU.png", p.x, p.y, this.gamePanel, direction));
                    break;
                case DOWN:
                    this.gamePanel.biteList.add(new Bite("fightingGames\\image2\\wolf\\biteD.png", p.x, p.y, this.gamePanel, direction));
                    break;
            }
            //线程开始
            new AttackCD().start();
        }

    }

    @Override
    public Point getHeadPoint(){
        switch(direction){
            case LEFT:
                return new Point(x-width,y - height-35);
            case RIGHT:
                return new Point(x + width,y - height-35);
            case UP:
                return new Point(x - width-35,y-height);
            case DOWN:
                return new Point(x - width-35,y + height);
            default:
                return null;
        }
    }

    @Override
    public void paintSelft(Graphics g) {
        g.drawImage(img,x,y,null);
        move();
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,width,height);
    }
}
