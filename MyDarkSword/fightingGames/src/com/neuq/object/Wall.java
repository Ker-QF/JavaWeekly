package com.neuq.object;

import com.neuq.ui.GamePanel;

import java.awt.*;

public class Wall extends GameObject{

    //尺寸
    int length = 70;

    public Wall(String img, int x, int y, GamePanel gamePanel) {
        super(img, x, y, gamePanel);
    }

    @Override
    public void paintSelft(Graphics g) {
        g.drawImage(img,x,y,gamePanel);
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,length,length);
    }
}
