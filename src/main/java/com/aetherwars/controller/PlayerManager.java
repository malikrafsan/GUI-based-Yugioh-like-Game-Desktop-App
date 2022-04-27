package com.aetherwars.controller;

import com.aetherwars.model.*;

public class PlayerManager {
    private GameManager gm;
    private Player p;

    public PlayerManager(Player p) {
        this.p = p;
        this.gm = gm.getInstance();
    }

    // bila idx_board kosong && mana cukup, melakukan summon
    public void summon(int idx_hand, int idx_board) {
        Card c = p.getHand().takeCard(idx_hand);
        p.getActiveChars().addChar((CharacterCard) c , idx_board);
        p.useMana(c.getMana());
    }

    public void handToBoard(int idx_hand, int idx_board) {
        Card c = p.getHand().getCard(idx_hand);
        ActiveChar ac = p.getActiveChars().getActChar(idx_board);
        if(c.getMana()<= p.getMana()) {
            if(c instanceof SpellCard) {
                if(ac != null) {
                    System.out.println("apply spell c to ac");
                    // TODO:pake activeChar manager
                } else {
                    System.out.println("Selected board is empty");
                }
            } else if(c instanceof CharacterCard) {
                if(ac == null) {
                    summon(idx_hand, idx_board);
                } else {
                    System.out.println("Selected board is already occupied");
                }
            } else {
                System.out.println("not recognized card");
            }
        } else {
            System.out.println("Not enough mana");
        }
    }

    public boolean canReceiveSpellAt(int idx_board) {
        return p.getActiveChars().getActChar(idx_board) != null;
    }

    public boolean canGiveSpellAt(int idx_hand) {
        Card c = p.getHand().getCard(idx_hand);
        return c instanceof SpellCard && p.getMana()>=c.getMana();
    }

    public SpellCard takeSpellAt(int idx_hand) {
        return (SpellCard) p.getHand().takeCard(idx_hand);
    }

    public void receiveSpell(SpellCard sc, int idx_board) {
//        p.getActiveChars().getActChar(idx_board).addSpell(new ActiveSpell(sc));
        ;
    }

    public void useMana(int x) {
        p.useMana(x);
    }

    public ActiveCharObserver getActiveChars() {
        return p.getActiveChars();
    }
}
