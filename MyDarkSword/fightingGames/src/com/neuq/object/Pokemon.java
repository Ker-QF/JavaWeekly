package com.neuq.object;

import com.neuq.ui.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Pokemon extends GameObject implements Serializable {
    private static final long serialVersionUID = 1L;

    int lightingLength = 0;

    //尺寸
    public int width = 40;
    public int height = 40;
    //速度
    public int speed = 3;
    //方向
    public Direction direction = Direction.UP;
    //生命
    public boolean alive = false;
    //四个方向
    public boolean up = false;
    public boolean down = false;
    public boolean left = false;
    public boolean right = false;
    //四个方向图片
    public String upImg;
    public String leftImg;
    public String rightImg;
    public String downImg;

    //攻击冷却状态
    public boolean attackCoolDown = true;
    //攻击冷却时间毫秒间隔1000ms
    public int attackCoolDownTime = 1000;

    public Pokemon(String img, int speed,int x, int y, GamePanel gamePanel,String upImg,String leftImg,String rightImg,String downImg) {
        super(img, x, y, gamePanel);
        this.speed = speed;
        this.upImg = upImg;
        this.leftImg = leftImg;
        this.rightImg = rightImg;
        this.downImg = downImg;
    }

    public void rightward(){
        direction = Direction.RIGHT;
        setImg(rightImg);
        if (!hitWall(x+speed,y) && !moveToBorder(x+speed,y)){
            this.x += speed;
        }

    }
    public void upward(){
        direction = Direction.UP;
        setImg(upImg);
        if (!hitWall(x,y-speed) && !moveToBorder(x,y-speed)){
            this.y -= speed;
        }

    }
    public void downward(){
        direction = Direction.DOWN;
        setImg(downImg);
        if (!hitWall(x,y + speed) && !moveToBorder(x,y+speed)){
            this.y += speed;
        }

    }
    public void leftward(){
        direction = Direction.LEFT;
        setImg(leftImg);
        if (!hitWall(x-speed,y) && !moveToBorder(x-speed,y)){
            this.x -= speed;
        }
    }

    private void setImg(String img) {
        this.img = Toolkit.getDefaultToolkit().getImage(img);
    }

    public void attack(){
        if (attackCoolDown && alive){
            Point p = this.getHeadPoint();
            Lighting lighting = new Lighting("fightingGames\\image2\\lighting.png",p.x,p.y,this.gamePanel,direction);
            this.gamePanel.lightingsList.add(lighting);

            //线程开始
            new AttackCD().start();
        }

    }

    //新线程
    class AttackCD extends Thread{
        @Override
        public void run() {
            attackCoolDown = false;
            //休眠一秒
            try{
                Thread.sleep(attackCoolDownTime);
            }catch(Exception e){
                e.printStackTrace();
            }
            //将攻击解除冷却状态
            attackCoolDown = true;
            //线程终止
            this.stop();
        }
    }

    public Point getHeadPoint(){
        switch(direction){
            case LEFT:
                return new Point(x,y + height/2);
            case RIGHT:
                return new Point(x + width,y + height/2);
            case UP:
                return new Point(x + width/2,y);
            case DOWN:
                return new Point(x + width/2,y + height);
            default:
                return null;
        }
    }


    //与围墙碰撞检测
    public boolean hitWall(int x,int y){
        //围墙列表
        ArrayList<Wall> walls = this.gamePanel.wallList;
        //下一步矩形
        Rectangle next = new Rectangle(x,y,width,height);
        //遍历列表
        for (Wall wall: walls){
            //与每一个围墙进行碰撞检测
            if (next.intersects(wall.getRec())){
                //发生碰撞，返回true
                return true;
            }

        }

        return false;
    }

    public boolean moveToBorder(int x,int y){
        if (x < 0){
            return true;
        }else if (x + width > this.gamePanel.getWidth()){
            return true;
        }else if (y < 0){
            return true;
        }else if (y + height > this.gamePanel.getHeight()){
            return true;
        }
        return false;
    }

    @Override
    public abstract void paintSelft(Graphics g);
    @Override
    public abstract Rectangle getRec();
}
