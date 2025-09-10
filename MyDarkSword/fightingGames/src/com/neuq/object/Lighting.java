package com.neuq.object;

import com.neuq.ui.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public class Lighting extends GameObject {

    //尺寸
    int width = 10;
    int height = 11;
    //速度
    int speed = 45;
    //方向
    Direction direction;
    // 新增：记录初始位置和最大射程
    private int maxRange = 1200;
    private int distanceTraveled = 0;
    private boolean active = true; // 技能是否有效


    public Lighting(String img, int x, int y, GamePanel gamePanel, Direction direction) {
        super(img, x, y, gamePanel);
        this.direction = direction;
    }

    public void leftward(){
        x -= speed;
        distanceTraveled += speed;
    }
    public void rightward(){
        x += speed;
        distanceTraveled += speed;
    }
    public void upward(){
        y -= speed;
        distanceTraveled += speed;
    }
    public void downward(){
        y += speed;
        distanceTraveled += speed;
    }

    public void go(){
        if (!active){
            return;
        }

        switch(direction){
            case LEFT:
                leftward();
                break;
            case RIGHT:
                rightward();
                break;
            case UP:
                upward();
                break;
            case DOWN:
                downward();
                break;
        }

        this.hitWall();
        this.moveToBorder();
        this.hitBase();

        // 检查是否超出最大射程
        if (distanceTraveled >= maxRange) {
            active = false;
        }
    }

    public void hitBot(){
        ArrayList<Bot> bots = this.gamePanel.botList;
        for (Bot bot: bots){
            if (this.getRec().intersects(bot.getRec())){
                this.gamePanel.ouDeathList.add(new OuDeath("",bot.x-34,bot.y-14,this.gamePanel));
                this.gamePanel.botList.remove(bot);
                this.gamePanel.removeList.add(this);
                break;
            }
        }
    }

    public void hitPlayer(){
        ArrayList<Pokemon> players = this.gamePanel.playerList;
        for (Pokemon player: players){
            if (this.getRec().intersects(player.getRec())){
                this.gamePanel.playerList.remove(player);
                this.gamePanel.removeList.add(this);
                player.alive = false;
                break;
            }
        }
    }

    public void hitBase(){
        ArrayList<Base> basesList = this.gamePanel.baseList;
        for (Base base: basesList){
            if (this.getRec().intersects(base.getRec())){
                this.gamePanel.baseList.remove(base);
                this.gamePanel.removeList.add(this);
                break;
            }
        }
    }

    //子弹与围墙碰撞检测
    public void hitWall(){
        //围墙列表
        ArrayList<Wall> walls = this.gamePanel.wallList;
        //遍历列表
        for (Wall wall: walls){
            if (this.getRec().intersects(wall.getRec())){
                this.gamePanel.wallList.remove(wall);
                this.gamePanel.removeList.add(this);
                break;
            }
        }

    }

    public void moveToBorder(){
        if (x < 0 || x + width > this.gamePanel.getWidth()){
            this.gamePanel.removeList.add(this);
        }
        if (y < 0 || y + height > this.gamePanel.getWidth()){
            this.gamePanel.removeList.add(this);
        }
    }

    @Override
    public void paintSelft(Graphics g) {
        if (active) {
            g.drawImage(img,x,y,null);
            this.go();
            this.hitBot();
            this.hitPlayer();
        }
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,width,height);
    }
}
