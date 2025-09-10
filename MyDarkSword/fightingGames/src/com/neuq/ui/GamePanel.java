package com.neuq.ui;

import com.neuq.network.GameClient;
import com.neuq.network.GameServer;
import com.neuq.network.GameState;
import com.neuq.network.PlayerAction;
import com.neuq.object.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JFrame {

    public int width = 1200;
    public int height = 700;

    //定义双缓存图片
    Image offSreemImage = null;

    //指针图片
    Image select = Toolkit.getDefaultToolkit().getImage("fightingGames\\image2\\pokeball.png");
    //指针纵坐标
    int y = 165;

    // 背景图片
    Image backgroundImage = Toolkit.getDefaultToolkit().getImage("fightingGames\\image2\\pikaBackground.png");
    Image backgroundOneImage = Toolkit.getDefaultToolkit().getImage("fightingGames\\image2\\backgroundOne.png");
    Image backgroundTwoImage = Toolkit.getDefaultToolkit().getImage("fightingGames\\image2\\backgroundTwo.png");

    // 网络相关变量
    private GameServer gameServer; // 服务器实例
    private GameClient gameClient; // 客户端实例
    private int serverPort = 8888; // 服务器端口
    private String serverIp = "localhost"; // 默认服务器IP（本地）

    //游戏模式 0 游戏未开始， 1 单人模式， 2 双人模式， 5 游戏胜利
    int state = 0;
    int a = 1;
    //重绘次数
    int count = 0;
    //已生成敌人数量
    int enemyCount = 0;

    //游戏元素列表
    public ArrayList<Lighting> lightingsList = new ArrayList<>();
    public ArrayList<Bot> botList = new ArrayList<>();
    public ArrayList<OU_waterAttack> waveList = new ArrayList<>();
    public ArrayList<GameObject> removeList = new ArrayList<>();
    public ArrayList<Pokemon> playerList = new ArrayList<>();
    public ArrayList<Wall> wallList = new ArrayList<>();
    public ArrayList<Base> baseList = new ArrayList<>();
    public ArrayList<PikaDeath> pikaDeathList = new ArrayList<>();
    public ArrayList<OuDeath> ouDeathList = new ArrayList<>();
    public ArrayList<Bite> biteList = new ArrayList<>();


    //PlayerOnepika
    PlayerOnepika playerOnepika = new PlayerOnepika("fightingGames\\image2\\pikaBack.png",4,125,510,this,
            "fightingGames\\image2\\pikaBack.png","fightingGames\\image2\\pikaLeft.png",
            "fightingGames\\image2\\pikaRight.png","fightingGames\\image2\\pikaFront.png");
    //PlayerTwowolf
    PlayerTwowolf playerTwowolf = new PlayerTwowolf("fightingGames\\image2\\wolfBack.png",6,625,510,this,
            "fightingGames\\image2\\wolfBack.png","fightingGames\\image2\\wolfLeft.png",
            "fightingGames\\image2\\wolfRight.png","fightingGames\\image2\\wolfFront.png");

    //Bot
    Bot bot = new Bot("fightingGames\\image2\\ouD.png",5,500,110,this,
            "fightingGames\\image2\\ouU.png","fightingGames\\image2\\ouL.png",
            "fightingGames\\image2\\ouR.png","fightingGames\\image2\\ouD.png");

    //Base
    Base base = new Base("fightingGames\\image2\\base.png",385,585,this);

    public void launch(){
        initjFrame();
    }

    private void initjFrame() {
        //设置窗口标题
        setTitle("宝可梦大作战");
        //设置窗口大小
        setSize(1200,700);
        //设置窗口居中
        setLocationRelativeTo(null);
        //设置窗口置顶
        setAlwaysOnTop(true);
        //设置关闭模式
        setDefaultCloseOperation(3);
        //用户不能调整窗口大小
        setResizable(false);
        //取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        setLayout(null);
        //设置键盘监听器
        addKeyListener(new GamePanel.KeyMonitor());
        //使窗口可见
        setVisible(true);

        //添加围墙
        addWall();
        //预加载
        ouDeathList.add(new OuDeath("",bot.x-34,bot.y-14,this));

        //添加基地
        baseList.add(base);

        //重绘
        while(true){
            //游戏胜利判定
            if (botList.size() == 0 && enemyCount == 10){
                state = 5;
            }
            //游戏失败判定
            if ((playerList.size() == 0 && (state == 1 || state == 2)) || baseList.size() == 0){
                state = 4;
                a = 4;
            }
            //添加人机
            if (count % 100 == 1 && enemyCount < 10 && (state == 1 || state == 2) && baseList.size() < 10){
                Random random = new Random();
                int rnum = random.nextInt(800);

                botList.add(new Bot("fightingGames\\image2\\ouD.png",5,rnum,110,this,
                        "fightingGames\\image2\\ouU.png","fightingGames\\image2\\ouL.png",
                        "fightingGames\\image2\\ouR.png","fightingGames\\image2\\ouD.png"));
                enemyCount++;
            }
            repaint();
            try{
                Thread.sleep(25);
            }catch(Exception e){
                e.printStackTrace();
            }
        }


        /*JLabel background = new JLabel(new ImageIcon("fightingGames\\image2\\Dark.png"));
        background.setBounds(0,0,1200,700);
        background.setOpaque(false);
        getContentPane().add(background);*/




    }

    @Override
    public void paint(Graphics g){
        //创建和容器一样大小的Image图片
        if (offSreemImage == null){
            offSreemImage = this.createImage(width,height);
        }
        //获得该图片的画笔
        Graphics gImage = offSreemImage.getGraphics();
        // 绘制背景图片（确保在其他元素之前绘制）
        if (backgroundImage != null) {
            if (state == 0 || state == 3){
                gImage.drawImage(backgroundImage, 0, 0, width, height, this);
            }
        } else {
            // 如果背景图片加载失败，使用白色背景
            gImage.setColor(Color.white);
            gImage.fillRect(0, 0, width, height);
        }

        //改变画笔颜色
        gImage.setColor(Color.BLACK);
        //改变文字大小和样式
        gImage.setFont(new Font("仿宋",Font.BOLD,40));
        //state = 0,游戏未开始
        if (state == 0){
            //添加文字
            gImage.drawString("选择游戏模式",220,150);
            gImage.drawString("单人模式",220,200);
            gImage.drawString("双人模式",220,250);
            gImage.setFont(new Font("仿宋",Font.BOLD,20));
            gImage.drawString("按键·1·选择单人模式，按键·2·选择双人模式，确认请按回车",220,300);
            gImage.drawString("按键·6·创建房间，按键·7·加入房间，确认请按回车", 220, 430);
            //绘制指针
            gImage.drawImage(select,160,y,null);

        }else if (state == 1 || state == 2){
            gImage.setFont(new Font("仿宋",Font.BOLD,30));
            gImage.setColor(Color.red);
            gImage.drawString("剩余敌人：" + botList.size(),50,100);
            if (state == 1){
                gImage.drawImage(backgroundOneImage, 0, 0, width, height, this);
            }else{
                gImage.drawImage(backgroundTwoImage, 0, 0, width, height, this);
            }

            //绘制游戏元素
            for (Pokemon player: playerList){
                player.paintSelft(gImage);
            }
            for(Lighting lighting: lightingsList){
                lighting.paintSelft(gImage);
            }
            lightingsList.removeAll(removeList);
            for (Bot bot: botList){
                bot.paintSelft(gImage);
            }
            for (OU_waterAttack ou_waterAttack: waveList){
                ou_waterAttack.paintSelft(gImage);
            }
            waveList.removeAll(removeList);
            for (Bite bites: biteList){
                bites.paintSelft(gImage);
            }
            biteList.removeAll(removeList);
            for (Wall wall: wallList){
                wall.paintSelft(gImage);
            }
            for (Base base: baseList){
                base.paintSelft(gImage);
            }
            for (PikaDeath pikaDeaths: pikaDeathList){
                pikaDeaths.paintSelft(gImage);
            }
            for (OuDeath ouDeaths: ouDeathList){
                ouDeaths.paintSelft(gImage);
            }
            //重绘一次
            count++;

        }else if (state == 3){
            gImage.drawString("游戏暂停",500,350);
        }else if (state == 4){
            gImage.drawString("游戏失败",500,350);
            if (baseList.size() == 0){
                gImage.drawString("基地已被摧毁，无法复活",390,400);
                gImage.drawString("扣9获得重生",470,450);
            }else{
                gImage.drawString("复活请按O",490,400);
            }
        }else if (state == 5){
            gImage.drawString("游戏胜利",500,350);
            gImage.drawString("扣9继续战",470,450);
        }
        //将缓存区绘制好的图形一次性绘制到整个容器的画布中
        g.drawImage(offSreemImage,0,0,null);

    }

    void reStart(){
        /*this.removeList.add(playerOnepika);
        this.removeList.add(playerTwowolf);
        playerList.removeAll(removeList);*/
        playerList.clear();
        state = 0;
        a = 1;
        enemyCount = 0;
        botList.clear();
        baseList.add(base);
        if (wallList.size() == 0){
            addWall();
        }
    }

    void addWall(){
        for (int i = 0;i < 14;i++){
            wallList.add(new Wall("fightingGames\\image2\\wall.png",50 + i*70,170,this));
        }
        wallList.add(new Wall("fightingGames\\image2\\wall.png",305,570,this));
        wallList.add(new Wall("fightingGames\\image2\\wall.png",305,500,this));
        wallList.add(new Wall("fightingGames\\image2\\wall.png",375,500,this));
        wallList.add(new Wall("fightingGames\\image2\\wall.png",445,500,this));
        wallList.add(new Wall("fightingGames\\image2\\wall.png",445,570,this));
    }

    // 服务器调用此方法，将本地游戏状态广播给所有客户端
    public GameState getGameState() {
        // 收集需要同步的游戏数据
        GameState state = new GameState();
        state.playerList = new ArrayList<>(playerList); // 玩家列表
        state.botList = new ArrayList<>(botList);       // 敌人列表
        state.lightingsList = new ArrayList<>(lightingsList); // 子弹列表
        state.waveList = new ArrayList<>(waveList);
        state.biteList = new ArrayList<>(biteList);
        state.wallList = new ArrayList<>(wallList);     // 围墙列表
        state.baseList = new ArrayList<>(baseList);     // 基地列表
        return state;
    }

    // 客户端调用此方法，更新本地游戏状态
    public void updateGameState(GameState state) {
        // 注意：实际应用中需要考虑线程安全问题
        // 这里简化处理，直接替换列表

        // 清空本地列表
        playerList.clear();
        botList.clear();
        lightingsList.clear();
        wallList.clear();
        baseList.clear();

        // 添加服务器同步的数据
        playerList.addAll(state.playerList);
        botList.addAll(state.botList);
        lightingsList.addAll(state.lightingsList);
        wallList.addAll(state.wallList);
        baseList.addAll(state.baseList);

        // 触发重绘
        repaint();
    }

    // 处理玩家操作
    public void applyAction(PlayerAction action) {
        // 根据action.playerId确定操作的玩家
        // 假设playerId=1是PlayerOnepika，playerId=2是PlayerTwowolf
        if (action.playerId == 1 && playerOnepika.alive) {
            // 模拟按键事件并触发玩家操作
            KeyEvent keyEvent = action.isPressed ?
                    new KeyEvent(this, 0, System.currentTimeMillis(), 0, action.keyCode, (char)action.keyCode) :
                    new KeyEvent(this, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, action.keyCode, (char)action.keyCode);

            playerOnepika.keyPressed(keyEvent);
        } else if (action.playerId == 2 && playerTwowolf.alive) {
            KeyEvent keyEvent = action.isPressed ?
                    new KeyEvent(this, 0, System.currentTimeMillis(), 0, action.keyCode, (char)action.keyCode) :
                    new KeyEvent(this, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, action.keyCode, (char)action.keyCode);

            playerTwowolf.keyPressed(keyEvent);
        }
    }

    // 玩家操作发生时，客户端调用此方法发送操作到服务器
    public void sendPlayerAction(PlayerAction action) {
        if (gameClient != null) {
            gameClient.sendAction(action);
        }
    }

    class KeyMonitor extends KeyAdapter{
        //按下键盘
        @Override
        public void keyPressed(KeyEvent e){
            //返回键值
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_1:
                    a = 1;
                    y = 165;
                    break;
                case KeyEvent.VK_2:
                    a = 2;
                    y = 215;
                    break;
                case KeyEvent.VK_ENTER:
                    if (state == 0 && a == 1){
                        playerList.add(playerOnepika);
                        playerOnepika.alive = true;
                    }else if (state == 0 && a == 2){
                        playerList.add(playerOnepika);
                        playerList.add(playerTwowolf);
                        playerOnepika.alive = true;
                        playerTwowolf.alive = true;
                    }
                    state = a;
                    break;
                case KeyEvent.VK_P:
                    if (state != 3){
                        a = state;
                        state = 3;
                    }else{
                        state = a;
                        if (a == 0){
                            a = 1;
                        }
                    }
                    break;
                case KeyEvent.VK_O:
                    state = 0;
                    a = 1;
                    break;
                case KeyEvent.VK_9:
                    if (state == 4 || state == 5){
                        reStart();
                    }
                    break;
                default:
                    playerOnepika.keyPressed(e);
                    playerTwowolf.keyPressed(e);
            }
            System.out.println(e.getKeyChar());
        }

        //松开键盘
        @Override
        public void keyReleased(KeyEvent e){
            playerOnepika.keyReleased(e);
            playerTwowolf.keyReleased(e);
        }
    }
}
