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

    /**
     * setup deck, hand p
     * @param p
     * @param cm
     */
    public PlayerManager(Player p, CardManager cm) {
        this.p = p;
        this.cm = cm;
        cm.setupDeck(p.getDeck());
        p.getDeck().draw();
        List<Card> threeCards = p.getDeck().getThreeCard();
        for(Card c : threeCards) {
            p.getHand().addCard(c);
        }
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

    /**
     * choose card to draw
     * @param idx
     */
    public void chooseCard(int idx) {
        Card c = p.getDeck().select(idx);
        p.getHand().addCard(c);
    }

    public void drawCard() {
        p.getDeck().draw();
        p.getDeck().pickCard();
    }

    /**
     * summon from hand to board
     * @param idx_hand
     * @param idx_board
     */
    public void summon(int idx_hand, int idx_board) {
        Card c = p.getHand().takeCard(idx_hand);
        p.getActiveChars().addChar((CharacterCard) c , idx_board);
        p.useMana(c.getMana());
    }

    /**
     * apply spell or summon
     * @param idx_hand
     * @param idx_board
     */
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
            if (ac == null && c.getMana() <= p.getMana()) {
                summon(idx_hand, idx_board);
            } else {
                System.out.println("Selected board is already occupied or mana is not enough");
            }
        } else {
            System.out.println("not recognized card");
        }
    }

    /**
     * validasi receive spell
     * @param idx_board
     * @return
     */
    public boolean canReceiveSpellAt(int idx_board) {
        return p.getActiveChars().getActChar(idx_board) != null;
    }

    /**
     * validasi give spell
     * @param idx_hand
     * @param levelActChar
     * @return
     */
    public boolean canGiveSpellAt(int idx_hand, int levelActChar) {
        Card c = p.getHand().getCard(idx_hand);
        return c instanceof SpellCard && canGiveSpell((SpellCard) c, levelActChar);
    }

    /**
     * validasi give spell
     * @param sc
     * @param levelActChar
     * @return
     */
    public boolean canGiveSpell(SpellCard sc, int levelActChar) {
        if(sc instanceof SpellLevelCard) {
            return p.getMana()>= (levelActChar+1)/2;
        } else {
            return p.getMana()>=sc.getMana();
        }
    }

    /**
     * wrapper get mana spell level dan lainnya
     * @param sc
     * @param levelActChar
     * @return
     */
    public int getManaFromSpell(SpellCard sc, int levelActChar) {
        if(sc instanceof SpellLevelCard) {
            return (levelActChar+1)/2;
        } else {
            return sc.getMana();
        }
    }

    /**
     * ambil spell
     * @param idx_hand
     * @return
     */
    public SpellCard takeSpellAt(int idx_hand) {
        return (SpellCard) p.getHand().takeCard(idx_hand);
    }

    /**
     * give non morph spell
     * @param sc
     * @param ac
     */
    public void giveSpell(SpellCard sc, ActiveChar ac) {
        p.useMana(getManaFromSpell(sc, ac.getLevel()));
        ac.addSpell(sc);
    }

    /**
     * give morph spell
     * @param spellMorphCard
     * @param idx_board
     */
    public void morph(SpellMorphCard spellMorphCard, int idx_board) {
        CharacterCard cc = this.cm.getCharacterCard(spellMorphCard.getTargetId());
        p.getActiveChars().addChar(cc, idx_board);
        p.useMana(spellMorphCard.getMana());
    }

    public void useMana(int x) {
        p.useMana(x);
    }

    public int getMana() {
        return p.getMana();
    }

    public ActiveCharObserver getActiveChars() {
        return p.getActiveChars();
    }

    public boolean canBeDirectAttacked() {
        return p.getActiveChars().empty();
    }

    /**
     * set hover obj
     * @param idx_hand
     */
    public void hoverHand(int idx_hand) {
        Card c = p.getHand().getCard(idx_hand);
        GameManager.getInstance().hover(p.getHand().getCard(idx_hand));
    }

    /**
     * set hover obj
     * @param idx_board
     */
    public void hoverBoard(int idx_board) {
        ActiveChar ac = p.getActiveChars().getActChar(idx_board);
        ac.onHover();
        GameManager.getInstance().hover(p.getActiveChars().getActChar(idx_board));
    }

    /**
     * minus health
     * @param x
     */
    public void minusHealth(Double x) {
        p.minusHealth(x);
    }
}
