package com.aetherwars.model;
import com.aetherwars.interfaces.IActiveCharObserverGetter;
import java.util.*;

public class ActiveCharObserver extends Observable implements IActiveCharObserverGetter{
    private ActiveChar[] chars;

    /**
     * Constructor
     */
    public ActiveCharObserver() {
        this.chars = new ActiveChar[5];
        for (int i = 0; i < 5; i++) {
            this.chars[i] = null;
        }
        // randomizeHandCard();
    }

    /**
     * DEBUG ONLY
     */
    public void randomizeHandCard() {
        Random rand = new Random();
        CharType[] types = new CharType[] {CharType.END, CharType.OVERWORLD, CharType.NETHER};
        String[] imgPaths = new String[]{"card/image/spell/morph/Sugondese.png", "card/image/spell/potion/Sadikin Elixir.png", "card/image/character/Shulker.png", "card/image/character/Zombie.png", "card/data/image/character/Shulker.png"};

        for (int i = 0; i < this.chars.length; i++) {
            if (rand.nextDouble() > 0.5) {
                this.chars[i] = new ActiveChar(new CharacterCard(rand.nextInt(10), "INI NAMA" + rand.nextInt(10), types[rand.nextInt(types.length)], "...", imgPaths[rand.nextInt(imgPaths.length)], rand.nextInt(10), rand.nextInt(10), rand.nextInt(10), rand.nextInt(10), rand.nextInt(10)));
            }
        }
    }


    /**
     * Method when using a CharacterCard
     * @param card
     * @param index
     */
    public void addChar(CharacterCard card, int index) {
        // System.out.println("\n\nadd char to board\n\n");
        this.chars[index] = new ActiveChar(card);
        this.sync();
    }

    /**
     * Method when a card dies
     * @param index
     */
    public void delChar(int index) {
        this.chars[index] = null;
        this.sync();
    }


    /**
     * getter
     * @param idx_board
     * @return AtiveChar
     */
    public ActiveChar getActChar(int idx_board) {
        return this.chars[idx_board];
    }

    /**
     * Method to set clicked
     */
    public void onClick(int index) {
        this.chars[index].onClick();
        this.sync();
    }

    /**
     * Method to set clicked
     */
    public void offClick(int index) {
        this.chars[index].offClick();
        this.sync();
    }

    /**
     * Method to set hovered
     */
    public void onHover(int index) {
        this.chars[index].onHover();
        this.sync();
    }

    /**
     * Method to set hovered
     */
    public void offHover(int index) {
        this.chars[index].offHover();
        this.sync();
    }


    /**
     * Method to add exp
     * @param index
     * @param exp
     */
    public void addExp(int index, int exp) {
        this.chars[index].addExp(exp);
        this.sync();
    }

    /**
     * Method to check if there is a character <= 0
     */
    public void update() {
        for(int i=0; i<5; i++) {
            if(this.chars[i] != null && this.chars[i].getHealth() <= 0) {
                delChar(i);
            }
        }
        this.sync();
    }

    /**
     * getter
     * @return ActiveChar[]
     */
    public ActiveChar[] getChars() {
        return (this.chars);
    }

    public void newRound() {
        for (int i=0; i<5; i++) {
            if (this.chars[i] != null) {
                this.chars[i].newRound();
            }
        }
        this.sync();
    }

    /**
     * Method to add spells
     * @param index
     * @param card
     */
    public void addSpell(int index, SpellCard card) {
        switch (card.getType()) {
            case POTION:
                this.chars[index].addSpellPotion((SpellPotionCard) card);
                break;
            case LEVEL:
                this.chars[index].levelUpSpell((SpellLevelCard) card);
                break;
            case SWAP:
                this.chars[index].swapSpell((SpellSwapCard) card);
                break;
        }
        this.sync();
    }

    /**
     * Method to heal fully
     * @param index
     */
    public void healAll(int index) {
        this.chars[index].healAll();
        this.sync();
    }

    /**
     * Method when recieving damage
     * @param index
     * @param damage
     */
    public void receiveDamage(int index, double damage) {
        this.chars[index].receiveDamage(damage);
        this.sync();
    }

    /**
     * Method to set to cannot attack
     * @param index
     */
    public void cannotAttack(int index) {
        this.chars[index].cannotAttack();
        this.sync();
    }

    /**
     * getter
     * @param index
     * @return boolean
     */
    public boolean canAttack(int index) { return (this.chars[index].canAttack()); }

    /**
     * Method to sync
     */
    public void sync() {
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Method to check if empty
     * @return
     */
    public boolean empty() {
        for(int i=0; i<5; i++) {
            if(this.chars[i] != null) {
                return false;
            }
        }
        return true;
    }
}
