package com.it.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //所有跟游戏相关的代码都写在这



    //用来管理数据
    int[][] data = new int[4][4];

    //记录空白方块在二维数组中的位置
    int x = 0;
    int y = 0;

    //定义一个变量，记录当前展示图片的路径
    String path = "mergeGames\\image1\\";

    //定义二维数组，存储正确的数据
    int[][] win = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };

    //统计步数
    int step = 0;

    //创建选项下面的条目对象
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem accountItem = new JMenuItem("公众号");

    public GameJFrame(){
        //初始化界面
        initjFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据
        initData();

        //初始化图片
        initImage();


        //让界面显示出来，建议写在最后
        this.setVisible(true);
    }

    private void initData() {
        int[] tempArr = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }

        //第一种添加二维数组
        for (int i = 0; i < tempArr.length; i++) {
            if(tempArr[i] == 0){
                x = i/4;
                y = i%4;
            }
            data[i / 4][i % 4] = tempArr[i];
        }

    }

    //初始化图片
    //通过二维数组中管理的数据添加图片
    private void initImage() {

        //清空原本已经出现的所有图片
        this.getContentPane().removeAll();

        if (victory()){
            JLabel winJLable = new JLabel(new ImageIcon("mergeGames\\image1\\win.png"));
            winJLable.setBounds(203,283,197,73);
            this.getContentPane().add(winJLable);
        }

        JLabel stepCount = new JLabel("步数：" + step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        //路径：
        //绝对路径：一定从盘符开始
        //相对路径：不是从盘符开始，相对于当前项目而言

        //细节：先加载的图片在上方，后加载的在下方

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int num = data[i][j];
                //创建一个JLabel的对象（管理容器）
                JLabel jLabel1 = new JLabel(new ImageIcon(path + num +".png"));
                //指定图片位置
                jLabel1.setBounds(105*j + 83,105*i + 134,105,105);
                //给图片添加边框
                //1：让图片凹下去，0：让图片凸起来
                jLabel1.setBorder(new BevelBorder(0));
                //把管理容器添加到界面中
                this.getContentPane().add(jLabel1);
            }
        }

        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("mergeGames\\image1\\-1.png"));
        background.setBounds(40,40,508,560);
        //把背景图片添加到界面中
        this.getContentPane().add(background);

        //刷新一下界面
        this.getContentPane().repaint();

        /*//创建一个图片ImageIcon的对象
        ImageIcon icon1 = new ImageIcon("C:\\Users\\ker\\Desktop\\Javatest\\Practice\\mergeGames\\图片\\2BCDBB094886F0690A2E1EA5D4830EAB.png");
        //创建一个JLabel的对象（管理容器）
        JLabel jLabel1 = new JLabel(icon1);
        //指定图片位置
        jLabel1.setBounds(0,0,105,105);

        //把管理容器添加到界面中
        //this.add(jLabel1);
        this.getContentPane().add(jLabel1);*/
    }


    private void initjFrame() {
        //设置界面的宽高
        this.setSize(603,680);
        //设置界面的标题
        this.setTitle("拼图游戏单机版 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(3);

        //取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);
        //给整个界面添加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //按下不松时会调用这个方法
    @Override
    public void keyPressed(KeyEvent e) {
        if (victory()){
            return;
        }

        int code = e.getKeyCode();
        if(code == 65){
            //把界面中所有图片清除
            this.getContentPane().removeAll();
            //加载一张完整的图片
            JLabel all = new JLabel(new ImageIcon("C:\\Users\\ker\\Desktop\\Javatest\\Practice\\mergeGames\\image1\\-1.png"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);
            //加载背景图片
            JLabel background = new JLabel(new ImageIcon("mergeGames\\image1\\-1.png"));
            background.setBounds(40,40,508,560);
            //把背景图片添加到界面中
            this.getContentPane().add(background);
            //刷新界面
            this.getContentPane().repaint();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //判断游戏是否胜利
        if (victory()){
            return;
        }

        //对上下左右进行判断
        //左：37，上：38，右：39，下：40
        int code = e.getKeyCode();
        if(code == 37){//向左
            if(y == 3){
                return;
            }

            data[x][y] = data[x][y+1];
            data[x][y+1] = 0;
            y++;
            //每移动一次，步数就自增一次
            step++;
            //调用方法按照最新的方法加载图片
            initImage();
        }else if(code == 38){//向上
            if(x == 3){
                return;
            }

            data[x][y] = data[x+1][y];
            data[x+1][y] = 0;
            x++;
            step++;
            //调用方法按照最新的方法加载图片
            initImage();
        }else if(code == 39){//向右
            if(y == 0){
                return;
            }

            data[x][y] = data[x][y-1];
            data[x][y-1] = 0;
            y--;
            step++;
            //调用方法按照最新的方法加载图片
            initImage();
        }else if(code == 40){//向下
            if(x == 0){
                return;
            }

            data[x][y] = data[x-1][y];
            data[x-1][y] = 0;
            x--;
            step++;
            //调用方法按照最新的方法加载图片
            initImage();
        }else if(code == 65){
            initImage();
        }else if(code == 87){
            data = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            initImage();
        }
    }

    private void initJMenuBar() {
        //创建整个的菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单上面的两个选项的对象（功能 关于我们）
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");


        //将每一个选项下面的条目添加到选项当中
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        //给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);

        accountItem.addActionListener(this);

        //将菜单里面的两个选项添加到菜单当中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    public boolean victory(){
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]){
                    return false;
                }
            }
        }

        return true;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //获取当前被点击的条目对象
        Object obj = e.getSource();
        //判断
        if (obj == replayItem){
            System.out.println("重新游戏");


            //再次打乱二维数组中的数据
            initData();
            //计步器清零
            step = 0;
            //重新加载图片
            initImage();

        }else if(obj == reLoginItem){
            System.out.println("重新登录");

            //关闭当前有戏界面
            this.setVisible(false);
            //打开登录界面
            new LoginJFrame();
        }else if (obj == closeItem){
            System.out.println("关闭游戏");

            //直接关闭虚拟机即可
            System.exit(0);
        }else if (obj == accountItem){
            System.out.println("公众号");

            //创建一个弹框对象
            JDialog jDialog = new JDialog();
            //创建一个管理图片的容器对象
            JLabel jLabel = new JLabel(new ImageIcon("mergeGames\\image1\\aboutus.png"));
            jLabel.setBounds(0,0,258,258);
            //把图片添加到弹框
            jDialog.getContentPane().add(jLabel);
            //给弹框设置大小
            jDialog.setSize(344,344);
            //给弹框置顶
            jDialog.setAlwaysOnTop(true);
            //让弹框居中
            jDialog.setLocationRelativeTo(null);
            //弹框不关闭则无法操作下面的界面
            jDialog.setModal(true);
            //让弹框显示出来
            jDialog.setVisible(true);
        }
    }
}
