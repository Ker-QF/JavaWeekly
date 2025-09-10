package com.neuq.object;

import com.neuq.ui.GamePanel;

import java.awt.*;

public class Base extends GameObject{

    int length = 50;

    public Base(String img, int x, int y, GamePanel gamePanel) {
        super(img, x, y, gamePanel);
    }

    @Override
    public void paintSelft(Graphics g) {
        g.drawImage(img,x,y,null);
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,length,length);
    }
}
