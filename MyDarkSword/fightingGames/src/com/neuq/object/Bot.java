package com.neuq.object;

import com.neuq.ui.GamePanel;

import java.awt.*;
import java.util.Random;

public class Bot extends Pokemon {

    int moveTime = 0;

    public Bot(String img, int speed, int x, int y, GamePanel gamePanel, String upImg, String leftImg, String rightImg, String downImg) {
        super(img, speed, x, y, gamePanel, upImg, leftImg, rightImg, downImg);
    }

    public Direction getRandomDirection(){
        Random random = new Random();
        int rnum = random.nextInt(4);
        switch (rnum){
            case 0:
                return Direction.LEFT;
            case 1:
                return Direction.RIGHT;
            case 2:
                return Direction.UP;
            case 3:
                return Direction.DOWN;
            default:
                return null;
        }
    }
    public void go(){
        attack();
        if (moveTime >= 20){
            direction = getRandomDirection();
            moveTime = 0;
        }else{
            moveTime++;
        }
        switch (direction){
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
    }

    @Override
    public void attack(){
        Point p = getHeadPoint();
        Random random = new Random();
        int rnum = random.nextInt(100);
        if (rnum < 1){
            switch (direction){
                case LEFT:
                    this.gamePanel.waveList.add(new OU_waterAttack("fightingGames\\image2\\waveL.png",p.x,p.y,this.gamePanel,direction));
                    break;
                case RIGHT:
                    this.gamePanel.waveList.add(new OU_waterAttack("fightingGames\\image2\\waveR.png",p.x,p.y,this.gamePanel,direction));
                    break;
                case UP:
                    this.gamePanel.waveList.add(new OU_waterAttack("fightingGames\\image2\\waveU.png",p.x,p.y,this.gamePanel,direction));
                    break;
                case DOWN:
                    this.gamePanel.waveList.add(new OU_waterAttack("fightingGames\\image2\\wave.png",p.x,p.y,this.gamePanel,direction));
                    break;
            }
        }
    }

    @Override
    public Point getHeadPoint(){
        switch(direction){
            case LEFT:
                return new Point(x-width,y);
            case RIGHT:
                return new Point(x + width,y);
            case UP:
                return new Point(x ,y);
            case DOWN:
                return new Point(x ,y + height);
            default:
                return null;
        }
    }

    @Override
    public void paintSelft(Graphics g) {
        g.drawImage(img,x,y,null);
        go();
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,width,height);
    }
}
