package com.aetherwars.controller;

import com.aetherwars.interfaces.Hoverable;
import com.aetherwars.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class GameManager {
    private static GameManager instance;
    private GameState gs;
    private List<Object> lastClicked;
    private PlayerManager[] pm;
    private BattleManager bm;

    private GameManager() {
        this.gs = new GameState();
        pm = new PlayerManager[2];
        pm[0] = new PlayerManager(gs.getPlayer1());
        pm[1] = new PlayerManager(gs.getPlayer2());
        lastClicked = new ArrayList<Object>(3);
        bm = new BattleManager();
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
        int idxSelf = gs.getTurn().ordinal();
        int idxEnemy = 1-idxSelf;
        pm[id-1].clickActChar(idx);
        if(((String)lastClicked.get(0)).equals("HANDCARD")) {
            int idxHand = (Integer)lastClicked.get(1);
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
        } else if(((String)lastClicked.get(0)).equals("ACTIVECHAR") && (Integer)lastClicked.get(2)-1==idxSelf && id-1==idxEnemy) {
            ActiveCharObserver acsSelf = pm[idxSelf].getActiveChars();
            ActiveCharObserver acsEnemy = pm[idxEnemy].getActiveChars();
            ActiveChar acSelf = acsSelf.getActChar(idx);
            ActiveChar acEnemy = acsEnemy.getActChar(idx);
            if(gs.getPhase().equals(Phase.ATTACK)) {
                bm.setSelf(acSelf);
                bm.setEnemy(acEnemy);
                if(bm.canBattle()) {
                    bm.battle();
                    acsSelf.update();
                    acsEnemy.update();
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
            ActiveChar acSelf = pm[idxSelf].getActiveChars().getActChar(idx_board);
//            if(gs.getPhase().equals(Phase.ATTACK) && acSelf!=null && acSelf.canAttack() && pm[idxEnemy].canBeDirectAttacked()) {
//                int atk = acSelf.getAttack();
//                pm[idxEnemy].minusHealth(atk);
//                // if lawan mati, win
//            }
        }
        String what = "PLAYER";
        lastClicked.set(0, what);
        lastClicked.set(1, id);
        lastClicked.set(2,-1);
    }

    public void hover(Hoverable h) {
        gs.setHoveredObject(h);
    }

    public void unhover() {
        gs.setHoveredObject(null);
    }

    public void hoverHand(int idx_hand) {
        int idxSelf = gs.getTurn().ordinal();
        pm[idxSelf].hoverHand(idx_hand);
    }

    public void hoverBoard(int idx_board, int id) {
        pm[id-1].hoverBoard(idx_board);
    }

    // delete kartu
    // add exp from mana

    public void nextPhase() {
        gs.nextPhase();
    }

}
