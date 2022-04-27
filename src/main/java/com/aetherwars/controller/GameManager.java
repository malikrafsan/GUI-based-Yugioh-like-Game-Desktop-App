package com.aetherwars.controller;

import com.aetherwars.interfaces.Hoverable;
import com.aetherwars.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class GameManager {
    private static GameManager instance;
    private GameState gs;
    private PlayerManager[] pm;
    private BattleManager bm;

    private GameManager() {
        this.gs = new GameState();
        pm = new PlayerManager[2];
        pm[0] = new PlayerManager(gs.getPlayer1());
        pm[1] = new PlayerManager(gs.getPlayer2());
        bm = new BattleManager();
    }

    public static GameManager getInstance() {
        if(instance==null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void addObserver(String str, Observer obs) {
        switch (str) {
            case "GAMESTATE":
                this.gs.addObserver(obs);
                break;
            case "PLAYER1":
                this.gs.getPlayer1().addObserver(obs);
                break;
            case "PLAYER2":
                this.gs.getPlayer2().addObserver(obs);
                break;
            case "CLICKOBJECT":
                this.gs.getClickObject().addObserver(obs);
                break;
            case "DECK1":
                this.gs.getPlayer1().getDeck().addObserver(obs);
                break;
            case "DECK2":
                this.gs.getPlayer2().getDeck().addObserver(obs);
                break;
            case "HANDCARD1":
                this.gs.getPlayer1().getHand().addObserver(obs);
                break;
            case "HANDCARD2":
                this.gs.getPlayer2().getHand().addObserver(obs);
                break;
            case "ACTIVECHARS1":
                this.gs.getPlayer1().getActiveChars().addObserver(obs);
                break;
            case "ACTIVECHARS2":
                this.gs.getPlayer2().getActiveChars().addObserver(obs);
                break;
        }
    }

    public void click(int player, String name, int index) {
        ClickObject prevClicked = gs.getClickObject();
        gs.setClickObject(player,name,index);

        if(gs.getClickObject().getName().equals("ACTIVECHAR")) {
            clickedActChar(prevClicked);
        } else if(gs.getClickObject().getName().equals("PLAYER")) {
            clickedPlayer(prevClicked);
        }
    }

    public void clickedActChar(ClickObject prevClicked) {
        ClickObject curActCharClicked = gs.getClickObject();

        int idxSelf = gs.getTurn().ordinal();
        int idxEnemy = 1-idxSelf;
        if((prevClicked.getName()).equals("HANDCARD")) {
            int idxHand = prevClicked.getIndex();
            if(curActCharClicked.getPlayer()-1==idxSelf) {
                pm[idxSelf].handToBoard(idxHand, curActCharClicked.getIndex());
            } else {
                // kasih spell ke lawan
                if(pm[idxEnemy].canReceiveSpellAt(curActCharClicked.getIndex()) && pm[idxSelf].canGiveSpellAt(idxHand)) {
                    SpellCard spell = pm[idxSelf].takeSpellAt(idxHand); // or take spell
                    pm[idxEnemy].receiveSpell(spell, curActCharClicked.getIndex());
                    pm[idxSelf].useMana(spell.getMana());
                }
            }
        } else if(prevClicked.getName().equals("ACTIVECHAR") && prevClicked.getPlayer()-1==idxSelf && curActCharClicked.getPlayer()-1==idxEnemy) {
            ActiveCharObserver acsSelf = pm[idxSelf].getActiveChars();
            ActiveCharObserver acsEnemy = pm[idxEnemy].getActiveChars();
            ActiveChar acSelf = acsSelf.getActChar(prevClicked.getIndex());
            ActiveChar acEnemy = acsEnemy.getActChar(curActCharClicked.getIndex());
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
    }

    public void clickedPlayer(ClickObject prevClicked) {
        ClickObject curPlayerClicked = gs.getClickObject();
        int id = curPlayerClicked.getPlayer()-1;
        // && id==3-curPlayer, belum cek board nya ada attacker ga.
        int idxSelf = gs.getTurn().ordinal();
        int idxEnemy = 1- idxSelf;
//        if(lastClicked.get(0).equals("ACTIVECHAR") && (Integer)lastClicked.get(2)-1==idxSelf && id-1==idxEnemy) {
//            // bila board lawan kosong, direct attack
//            int idx_board = (Integer)lastClicked.get(1);
//            ActiveChar acSelf = pm[idxSelf].getActiveChars().getActChar(idx_board);
//            if(gs.getPhase().equals(Phase.ATTACK) && acSelf!=null && acSelf.canAttack() && pm[idxEnemy].canBeDirectAttacked()) {
//                int atk = acSelf.getAttack();
//                pm[idxEnemy].minusHealth(atk);
//                // if lawan mati, win
//            }
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
