package com.aetherwars.model;

import java.util.Observable;

public class ClickObject extends Observable {
    private int player;
    private String name;
    private int index;

    public ClickObject() {
        this.player = -1;
        this.name = "";
        this.index = -1;
    }

    public int getPlayer() {
        return this.player;
    }

    public String getName() {
        return this.name;
    }

    public int getIndex() {
        return this.index;
    }

    public void  setClickObject(int player, String name, int index) {
        this.player = player;
        this.name = name;
        this.index = index;
        this.setChanged();
        this.notifyObservers();
    }

    public void sync() {
        this.setChanged();
        this.notifyObservers();
    }
}
