package com.aetherwars.controller;

import com.aetherwars.event.PickCardEvent;
import com.aetherwars.interfaces.IEvent;
import com.aetherwars.interfaces.ISubscriber;
import com.aetherwars.model.*;

import java.util.List;

public class PlayerManager {
    private CardManager cm;
    private Player p;
    private List<Card> threeCards;

    public PlayerManager(Player p, CardManager cm) {
        this.p = p;
        this.cm = cm;
        cm.setupDeck(p.getDeck());
    }

    public void syncAll() {
        p.sync();
        p.getDeck().sync();
        p.getHand().sync();
        p.getActiveChars().sync();
    }


    public void removeCard(int idx_hand) {
        p.getHand().takeCard(idx_hand);
    }

    public void removeChar(int idx_board) {
        p.getActiveChars().delChar(idx_board);
    }

    // idx_board kosong && mana cukup, melakukan summon
    public void summon(int idx_hand, int idx_board) {
        Card c = p.getHand().takeCard(idx_hand);
        p.getActiveChars().addChar((CharacterCard) c , idx_board);
        p.useMana(c.getMana());
    }

    public void handToBoard(int idx_hand, int idx_board) {
        Card c = p.getHand().getCard(idx_hand);
        ActiveChar ac = p.getActiveChars().getActChar(idx_board);
        if(c instanceof SpellCard) {
            if(ac != null) {
                System.out.println("apply spell c to ac");
                SpellCard sc = (SpellCard) p.getHand().takeCard(idx_hand);
                if(canGiveSpell(sc, ac.getLevel())) {
                    if(sc instanceof SpellMorphCard) {
                        morph((SpellMorphCard) sc, idx_board);
                    } else {
                        giveSpell(sc, ac);
                        p.getActiveChars().update();
                    }
                }
            } else {
                System.out.println("Selected board is empty");
            }
        } else if(c instanceof CharacterCard) {
            if(ac == null && c.getMana() <= p.getMana()) {
                summon(idx_hand, idx_board);
            } else {
                System.out.println("Selected board is already occupied or mana is not enough");
            }
        } else {
            System.out.println("not recognized card");
        }
    }

    public boolean canReceiveSpellAt(int idx_board) {
        return p.getActiveChars().getActChar(idx_board) != null;
    }

    public boolean canGiveSpellAt(int idx_hand, int levelActChar) {
        Card c = p.getHand().getCard(idx_hand);
        return c instanceof SpellCard && canGiveSpell((SpellCard) c, levelActChar);
    }

    public boolean canGiveSpell(SpellCard sc, int levelActChar) {
        if(sc instanceof SpellLevelCard) {
            return p.getMana()>= (levelActChar+1)/2;
        } else {
            return p.getMana()>=sc.getMana();
        }
    }

    public int getManaFromSpell(SpellCard sc, int levelActChar) {
        if(sc instanceof SpellLevelCard) {
            return (levelActChar+1)/2;
        } else {
            return sc.getMana();
        }
    }

    public SpellCard takeSpellAt(int idx_hand) {
        return (SpellCard) p.getHand().takeCard(idx_hand);
    }

    public void receiveSpell(SpellCard sc, int idx_board) {
//        p.getActiveChars().getActChar(idx_board).addSpell(new ActiveSpell(sc));
        ;
    }

    public void giveSpell(SpellCard sc, ActiveChar ac) {
//        ac.addSpell(new ActiveSpell(sc));
        p.useMana(getManaFromSpell(sc, ac.getLevel()));
        ac.addSpell(sc);
    }

    public void morph(SpellMorphCard spellMorphCard, int idx_board) {
        CharacterCard cc = this.cm.getCharacterCard(spellMorphCard.getTargetId());
        p.getActiveChars().addChar(cc, idx_board);
        p.useMana(spellMorphCard.getMana());
    }

    public void useMana(int x) {
        p.useMana(x);
    }

    public ActiveCharObserver getActiveChars() {
        return p.getActiveChars();
    }

    public boolean canBeDirectAttacked() {
        return p.getActiveChars().empty();
    }

    public void hoverHand(int idx_hand) {
        Card c = p.getHand().getCard(idx_hand);
        // panggil hover kali
        GameManager.getInstance().hover(p.getHand().getCard(idx_hand));
    }

    public void hoverBoard(int idx_board) {
        ActiveChar ac = p.getActiveChars().getActChar(idx_board);
        ac.onHover();
        GameManager.getInstance().hover(p.getActiveChars().getActChar(idx_board));
    }

//    public void minusHealth(Double x) {
//        p.minusHealth(x);
//    }
}
