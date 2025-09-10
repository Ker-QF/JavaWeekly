package com.neuq.network;

import com.neuq.ui.GamePanel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class GameClient {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private GamePanel gamePanel;

    public GameClient(String serverIp, int port, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        try {
            socket = new Socket(serverIp, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            // 启动接收线程
            new Thread(this::receiveState).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 发送玩家操作到服务器
    public void sendAction(PlayerAction action) {
        try {
            out.writeObject(action);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 接收服务器广播的游戏状态
    private void receiveState() {
        try {
            while (true) {
                GameState state = (GameState) in.readObject();
                // 更新本地游戏状态
                gamePanel.updateGameState(state);
            }
        } catch (Exception e) {
            close();
        }
    }

    private void close() {
        // 关闭流和socket
    }
}
