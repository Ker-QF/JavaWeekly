package com.neuq.network;

import java.io.Serializable;

public class PlayerAction implements Serializable {
    private static final long serialVersionUID = 1L;

    public int playerId;        // 玩家ID
    public int keyCode;         // 按键码
    public boolean isPressed;   // 是否按下
    public long timestamp;      // 操作时间戳

    public PlayerAction(int playerId, int keyCode, boolean isPressed) {
        this.playerId = playerId;
        this.keyCode = keyCode;
        this.isPressed = isPressed;
        this.timestamp = System.currentTimeMillis();
    }
}
