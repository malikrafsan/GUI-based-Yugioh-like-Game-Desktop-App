package com.aetherwars.controller;

import com.aetherwars.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class GameManager {
    private static GameManager instance;
    private GameState gs;
    private List<Object> lastClicked;
    private PlayerManager[] pm;

    private GameManager() {
        this.gs = new GameState();
        pm = new PlayerManager[2];
        pm[0] = new PlayerManager(gs.getPlayer1());
        pm[1] = new PlayerManager(gs.getPlayer2());
        lastClicked = new ArrayList<Object>(3);
    }

    public static GameManager getInstance() {
        if(instance==null) {
            instance = new GameManager();
        }
        return instance;
    }

//    public void addObserver(String str, Observer obs) {
//        switch (str) {
//            case "COUNT":
//                this.model.getCount().addObserver(obs);
//        }
//    }

    public void clickActChar(int idx, int id) {
        if(((String)lastClicked.get(0)).equals("HANDCARD")) {
            int idxHand = (Integer)lastClicked.get(1);
            int idxSelf = gs.getTurn().ordinal();
            int idxEnemy = 1-idxSelf;
            if(id-1==idxSelf) {
                pm[idxSelf].handToBoard(idxHand, id);
            } else {
                // kasih spell ke lawan
                if(pm[idxEnemy].canReceiveSpellAt(idx) && pm[idxSelf].canGiveSpellAt(idxHand)) {
                    SpellCard spell = pm[idxSelf].takeSpellAt(idxHand); // or take spell
                    pm[idxEnemy].receiveSpell(spell, idx);
                    pm[idxSelf].useMana(spell.getMana());
                }
            }
        }

        String what = "ACTIVECHAR";
        lastClicked.set(0, what);
        lastClicked.set(1, idx);
        lastClicked.set(2, id);
    }

    public void clickHand(int idx) {
        String what = "HANDCARD";
        lastClicked.set(0, what);
        lastClicked.set(1,idx);
        lastClicked.set(2,-1);
    }

    public void clickPlayer(int id) {
        // && id==3-curPlayer, belum cek board nya ada attacker ga.
        int idxSelf = gs.getTurn().ordinal();
        int idxEnemy = 1- idxSelf;
        if(lastClicked.get(0).equals("ACTIVECHAR") && (Integer)lastClicked.get(2)-1==idxSelf && id-1==idxEnemy) {
            // bila board lawan kosong, direct attack
            int idx_board = (Integer)lastClicked.get(1);
//            if(pm[idxEnemy].canBeDirectAttacked() && pm[idxSelf].canAttackWith(idx_board)) {
//                int atk = pm[idxSelf].getAtack(idx_board);
//                pm[idxEnemy].minusHealth(atk);
//                // if lawan mati, win
//            }
        }
        String what = "PLAYER";
        lastClicked.set(0, what);
        lastClicked.set(1, id);
        lastClicked.set(2,-1);
    }

//    public void hover(Hoverable h) {
//        // set hovered.
//    }
//
//    public void unhover(Hoverable h) {
//        // set hovered.
//    }

    public void nextPhase() {
        ;
    }


}
