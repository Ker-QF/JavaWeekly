package com.neuq.network;

import com.neuq.object.*;

import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;

    public ArrayList<Pokemon> playerList;
    public ArrayList<Bot> botList;
    public ArrayList<Lighting> lightingsList;
    public ArrayList<OU_waterAttack> waveList;
    public ArrayList<Bite> biteList;
    public ArrayList<Wall> wallList;
    public ArrayList<Base> baseList;
}
