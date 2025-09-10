package com.neuq.object;

import com.neuq.ui.GamePanel;

import java.awt.*;

public class OuDeath extends GameObject{
    //爆炸图集
    static Image[] imgs = new Image[10];
    int explodeCount = 0;

    static{
        for (int i = 0; i < 9; i++) {
            imgs[i] = Toolkit.getDefaultToolkit().getImage("fightingGames\\image2\\ouDeath\\deathNew" + (i+1) + ".png");
        }
    }


    public OuDeath(String img, int x, int y, GamePanel gamePanel) {
        super(img, x, y, gamePanel);
    }

    @Override
    public void paintSelft(Graphics g) {
        if (explodeCount < 10){
            g.drawImage(imgs[explodeCount],x,y,null);
            explodeCount++;
        }
    }

    @Override
    public Rectangle getRec() {
        return null;
    }
}
