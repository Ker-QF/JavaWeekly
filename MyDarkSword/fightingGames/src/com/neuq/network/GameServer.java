package com.neuq.network;

import com.neuq.ui.GamePanel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameServer {
    private ServerSocket serverSocket;
    private List<ClientHandler> clients = new ArrayList<>();
    private GamePanel gamePanel; // 可注入游戏逻辑，或单独维护游戏状态

    public GameServer(int port, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("服务器启动，端口：" + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(clientSocket, this);
                clients.add(handler);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 广播游戏状态给所有客户端
    public void broadcastState(GameState state) {
        for (ClientHandler client : clients) {
            client.sendState(state);
        }
    }

    // 客户端处理器
    private class ClientHandler implements Runnable {
        private Socket socket;
        private ObjectInputStream in;
        private ObjectOutputStream out;
        private GameServer server; // 服务器实例

        public ClientHandler(Socket socket, GameServer server) {
            this.socket = socket;
            try {
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                while (true) {
                    // 接收客户端操作（如移动指令）
                    PlayerAction action = (PlayerAction) in.readObject();
                    // 应用操作到游戏逻辑
                    gamePanel.applyAction(action);
                    // 广播更新后的状态
                    server.broadcastState(gamePanel.getGameState());
                }
            } catch (Exception e) {
                clients.remove(this);
                close();
            }
        }

        public void sendState(GameState state) {
            try {
                out.writeObject(state);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void close() {
            // 关闭流和socket
        }
    }
}
